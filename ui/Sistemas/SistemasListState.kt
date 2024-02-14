package com.ucne.sistemas.ui.Sistemas

import com.ucne.sistemas.data.remote.dto.SistemasDto

data class SistemasListState(
    val isLoading: Boolean = false,
    val sistemas: List<SistemasDto>? = emptyList(),
    val error: String? = null,
    val sistema: SistemasDto? = SistemasDto(
        0,
        "",

    ),
    val selectedSistemas: SistemasDto? = null
)