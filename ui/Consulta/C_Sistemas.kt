package com.ucne.sistemas.ui.Consulta

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.sistemas.data.remote.dto.SistemasDto
import com.ucne.sistemas.ui.Home.HomeViewModel
import com.ucne.sistemas.ui.theme.SistemasTheme


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun C_Sistemas(viewModel: HomeViewModel = hiltViewModel()){
    val uiState by viewModel.state.collectAsState()
    SistemasTheme{
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(uiState.isLoading)
                CircularProgressIndicator(modifier = Modifier
                    .size(80.dp)
                    .padding(0.dp, 50.dp), strokeWidth = 8.dp)
        }

        LazyColumn (
            Modifier.fillMaxSize()
        ){
            item {
                Spacer(modifier = Modifier.padding(0.dp, 10.dp))
            }
            uiState.sistemas?.forEach { sistemas ->
                item {
                    ListSistemas(sistemas = sistemas)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListSistemas(sistemas: SistemasDto){

    ElevatedCard(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp, 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {

                Text(text = "SistemaId: ${sistemas.idSistema}")
                Text(text = "Nombre: ${sistemas.nombre}")
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}