package com.ronny.bancovirtual.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ronny.bancovirtual.R
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMovimientos = findViewById<Button>(R.id.btnVerMovimientos)
        val cardTransferir = findViewById<CardView>(R.id.cardTransferir)
        val cardPagar = findViewById<CardView>(R.id.cardPagar)
        val cardCambio = findViewById<CardView>(R.id.cardCambio)

        // 1. Lógica del botón TRANSFERIR (Funcional)
        cardTransferir.setOnClickListener {
            val intent = Intent(this, TransferenciaActivity::class.java)
            startActivity(intent)
        }

        // 2. Lógica de los otros botones (Solo visuales)
        cardPagar.setOnClickListener {
            Toast.makeText(this, "Pago de servicios: Próximamente", Toast.LENGTH_SHORT).show()
        }

        cardCambio.setOnClickListener {
            Toast.makeText(this, "Cambio de divisas: Próximamente", Toast.LENGTH_SHORT).show()
        }

        // Botón antiguo de historial
        btnMovimientos.setOnClickListener {
            val intent = Intent(this, MovimientoActivity::class.java)
            startActivity(intent)
        }
    }
}
