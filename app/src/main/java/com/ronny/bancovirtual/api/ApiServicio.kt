package com.ronny.bancovirtual.api

import com.ronny.bancovirtual.model.InicioSesionRespuesta
import com.ronny.bancovirtual.model.InicioSesionSolicitud
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServicio {

    @POST("api/v1/identidad/inicio-sesion")
    fun login(@Body request: InicioSesionSolicitud): Call<InicioSesionRespuesta>
}