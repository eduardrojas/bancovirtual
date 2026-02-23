package com.ronny.bancovirtual.view

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ronny.bancovirtual.databinding.ActivityTransferenciaBinding
import com.ronny.bancovirtual.model.AdminSQLite
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransferenciaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferenciaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Inflar la vista usando View Binding
        binding = ActivityTransferenciaBinding.inflate(layoutInflater)
        // 2. Establecer el contenido de la vista a la raíz del binding
        setContentView(binding.root)

        // 3. Acceder a las vistas a través del objeto binding (¡sin findViewById!)
        binding.btnConfirmar.setOnClickListener {
            val destino = binding.etDestino.text.toString()
            val montoStr = binding.etMonto.text.toString()

            if (destino.isEmpty() || montoStr.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val monto = try {
                montoStr.toDouble()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar en BD
            val admin = AdminSQLite(this)
            val db = admin.writableDatabase
            val registro = ContentValues()

            // Fecha actual automática
            val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            registro.put("descripcion", "Transferencia a $destino")
            registro.put("fecha", fechaActual)
            registro.put("monto", monto)
            registro.put("tipo", "EGRESO") // Importante: Es una salida de dinero

            db.insert("movimientos", null, registro)
            db.close()

            Toast.makeText(this, "¡Transferencia Exitosa!", Toast.LENGTH_LONG).show()
            finish() // Cierra esta pantalla y vuelve al Main
        }
    }
}
