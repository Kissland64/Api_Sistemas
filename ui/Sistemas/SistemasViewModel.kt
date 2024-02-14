package com.ucne.sistemas.ui.Sistemas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.sistemas.data.remote.dto.SistemasDto
import com.ucne.sistemas.data.remote.repository.SistemasRepository
import com.ucne.sistemas.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SistemasViewModel @Inject constructor(
    private val SistemasRepository: SistemasRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SistemasListState())
    val state = _state.asStateFlow()

    fun postSistemas(sistemasDto: SistemasDto) {
        viewModelScope.launch {
            SistemasRepository.postSistemas(sistemasDto).collectLatest  { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(sistema = SistemasDto(
                            0,
                            ""
                        ),
                            isLoading = false,
                            error = result.message
                        )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                sistema =   SistemasDto(
                                    0,
                                    "",
                                ),
                                isLoading = false
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}