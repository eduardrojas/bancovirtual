package com.ronny.bancovirtual.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLite(context: Context) : SQLiteOpenHelper(context, "banco_db", null, 1) {

    // Se ejecuta una sola vez cuando la BD no existe
    override fun onCreate(db: SQLiteDatabase?) {
        val crearTabla = """
            CREATE TABLE movimientos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                descripcion TEXT,
                fecha TEXT,
                monto REAL,
                tipo TEXT
            )
        """.trimIndent()

        db?.execSQL(crearTabla)

        // Insertamos datos semilla (Mock Data) para que la lista no esté vacía
        insertarDefault(db, "Sueldo Mensual", "22/01/2026", 2500.00, "INGRESO")
        insertarDefault(db, "Pago Spotify", "20/01/2026", 18.90, "EGRESO")
        insertarDefault(db, "Transferencia Yape", "18/01/2026", 50.00, "EGRESO")
    }

    private fun insertarDefault(db: SQLiteDatabase?, desc: String, fecha: String, monto: Double, tipo: String) {
        val registro = ContentValues().apply {
            put("descripcion", desc)
            put("fecha", fecha)
            put("monto", monto)
            put("tipo", tipo)
        }
        db?.insert("movimientos", null, registro)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // En un caso real aquí migraríamos datos, por ahora solo borramos y creamos
        db?.execSQL("DROP TABLE IF EXISTS movimientos")
        onCreate(db)
    }

    // Método para leer los datos y devolverlos como lista
    fun obtenerMovimientos(): ArrayList<Movimiento> {
        val lista = ArrayList<Movimiento>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM movimientos ORDER BY id DESC", null)

        if (cursor.moveToFirst()) {
            do {
                val mov = Movimiento(
                    id = cursor.getInt(0),
                    descripcion = cursor.getString(1),
                    fecha = cursor.getString(2),
                    monto = cursor.getDouble(3),
                    tipo = cursor.getString(4)
                )
                lista.add(mov)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }
}