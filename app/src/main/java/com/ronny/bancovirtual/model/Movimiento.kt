package com.ronny.bancovirtual.model

data class Movimiento(
    val id: Int,
    val descripcion: String,
    val fecha: String,
    val monto: Double,
    val tipo: String // "INGRESO" o "EGRESO"
)
