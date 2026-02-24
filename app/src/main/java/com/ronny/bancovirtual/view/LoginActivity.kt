package com.ronny.bancovirtual.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.ronny.bancovirtual.R
import com.ronny.bancovirtual.api.RetrofitCliente
import com.ronny.bancovirtual.model.ErrorRespuesta
import com.ronny.bancovirtual.model.InicioSesionRespuesta
import com.ronny.bancovirtual.model.InicioSesionSolicitud
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val progressBar  = findViewById<ProgressBar>(R.id.progressBar) // opcional: agregar al XML

        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val password = etPassword.text.toString()

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressBar?.visibility = View.VISIBLE
            btnLogin.isEnabled = false

            val request = InicioSesionSolicitud(
                NombreUsuario     = usuario,
                Clave             = password,
                UsuarioTipoLogin  = "NombreUsuario"
            )

            RetrofitCliente.apiServicio.login(request).enqueue(object : Callback<InicioSesionRespuesta> {
                override fun onResponse(call: Call<InicioSesionRespuesta>, respuesta: Response<InicioSesionRespuesta>) {
                    progressBar.visibility = View.GONE
                    btnLogin.isEnabled = true

                    if (respuesta.isSuccessful) {
                        val body = respuesta.body()
                        if (body?.InicioSesionResultadoTipo == "Exitoso") {

                            val usuario = body.Usuario

                            val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                                putExtra("USUARIO_ID",     usuario?.Id)
                                putExtra("USUARIO_NOMBRE", usuario?.NombreMostrar)
                                putExtra("USUARIO_CODIGO", usuario?.Codigo)
                            }
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        try {
                            val errorBody = respuesta.errorBody()?.string()
                            val error = Gson().fromJson(errorBody, ErrorRespuesta::class.java)
                            val mensaje = error?.Detalle ?: "Error ${respuesta.code()}"
                            Toast.makeText(this@LoginActivity, mensaje, Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(this@LoginActivity, "Error ${respuesta.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<InicioSesionRespuesta>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    btnLogin.isEnabled = true
                    Toast.makeText(this@LoginActivity, "Error de conexión: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}