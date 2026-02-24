package com.ronny.bancovirtual.model

data class InicioSesionSolicitud(
    val NombreUsuario    : String,
    val Clave            : String,
    val UsuarioTipoLogin : String = "NombreUsuario"
)