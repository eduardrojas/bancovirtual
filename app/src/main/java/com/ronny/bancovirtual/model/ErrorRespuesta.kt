package com.ronny.bancovirtual.model

data class ErrorRespuesta(
    val Codigo  : Int?,
    val Titulo  : String?,
    val Estado  : Int?,
    val Detalle : String?
)