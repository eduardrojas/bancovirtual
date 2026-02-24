package com.ronny.bancovirtual.model

data class UsuarioDto(
    val Id                : Int?,
    val Codigo            : String?,
    val CodigoConUsuario  : String?,
    val NombreUsuario     : String?,
    val NombreMostrar     : String?,
    val CorreoElectronico : String?,
    val RolesNombres      : String?,
    val Roles             : List<RolDto>?
)