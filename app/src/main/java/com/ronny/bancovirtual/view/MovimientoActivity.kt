package com.ronny.bancovirtual.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ronny.bancovirtual.R
import com.ronny.bancovirtual.adapter.MovimientoAdapter
import com.ronny.bancovirtual.model.AdminSQLite

class MovimientoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movimientos)

        // 1. Configurar RecyclerView
        val rvMovimientos = findViewById<RecyclerView>(R.id.rvMovimientos)
        rvMovimientos.layoutManager = LinearLayoutManager(this)

        // 2. Instanciar la Base de Datos y obtener datos
        val dbHelper = AdminSQLite(this)
        val listaMovimientos = dbHelper.obtenerMovimientos()

        // 3. Asignar el adaptador
        val adapter = MovimientoAdapter(listaMovimientos)
        rvMovimientos.adapter = adapter
    }
}
