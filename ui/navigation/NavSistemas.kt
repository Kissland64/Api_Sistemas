package com.ucne.sistemas.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.sistemas.ui.Consulta.C_Sistemas
import com.ucne.sistemas.ui.Home.Home
import com.ucne.sistemas.ui.Sistemas.SistemasScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavSistemas(){
    val navcontroller = rememberNavController()

    NavHost(navController = navcontroller, startDestination = "r_sistemas"){
        composable("home"){
            Home()
        }

        composable("r_sistemas"){
            SistemasScreen()
        }

        composable("c_sistemas"){
            C_Sistemas()
        }
    }
}