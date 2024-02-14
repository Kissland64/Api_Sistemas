package com.ucne.sistemas.data.remote.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.ucne.sistemas.data.remote.SistemasApi
import com.ucne.sistemas.data.remote.dto.SistemasDto
import com.ucne.sistemas.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SistemasRepository @Inject constructor(
    private val sistemasApi: SistemasApi,
){
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getSistemas(): Flow<Resource<List<SistemasDto>>> = flow {
        try {
            emit(Resource.Loading())

            val sistemas = sistemasApi.getSistemas()

            emit(Resource.Success(sistemas))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }



    fun getSistemasById(sistemasId: Int): Flow<Resource<SistemasDto>> = flow {
        try {
            emit(Resource.Loading())

            val sistemas = sistemasApi.getSistemasById(sistemasId)

            emit(Resource.Success(sistemas))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    fun postSistemas(sistemasDto: SistemasDto): Flow<Resource<SistemasDto>> = flow{
        try {
            emit(Resource.Loading())

            val response = sistemasApi.addSistemas(sistemasDto)

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(Resource.Success(responseBody))
                    emit(Resource.Error("Se guardo correctamente"))
                } else {
                    emit(Resource.Error("Respuesta vacía del servidor"))
                }
            } else {
                emit(Resource.Error("Error en la solicitud: ${response.code()} ${response.message()}"))
            }
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}