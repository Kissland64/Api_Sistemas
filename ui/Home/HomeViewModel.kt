package com.ucne.sistemas.ui.Home

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.sistemas.data.remote.repository.SistemasRepository
import com.ucne.sistemas.ui.Sistemas.SistemasListState
import com.ucne.sistemas.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val SistemasRepository: SistemasRepository

) : ViewModel() {

    private val _state = MutableStateFlow(SistemasListState())
    val state = _state.asStateFlow()

    init {
        getSistemas()
    }

    private fun getSistemas() {
        viewModelScope.launch {
            SistemasRepository.getSistemas().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                sistemas = result.data,
                                isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                sistemas = emptyList(),
                                isLoading = false
                            )
                        }
                    }

                }
            }
        }
    }

}