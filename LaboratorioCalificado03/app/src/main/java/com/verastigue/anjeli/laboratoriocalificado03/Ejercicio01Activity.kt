package com.verastigue.anjeli.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.verastigue.anjeli.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ejercicio01Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        obtenerTeachers()
    }

    private fun obtenerTeachers() {
        RetrofitClient.instance.getTeachers().enqueue(object : Callback<TeacherResponse> {
            override fun onResponse(
                call: Call<TeacherResponse>,
                response: Response<TeacherResponse>
            ) {
                if (response.isSuccessful) {
                    val lista = response.body()?.teachers ?: emptyList()

                    Log.d("API", "Profesores recibidos: ${lista.size}")
                    Toast.makeText(
                        this@Ejercicio01Activity,
                        "Cargados ${lista.size} profesores",
                        Toast.LENGTH_SHORT
                    ).show()

                    binding.recyclerViewProfesores.apply {
                        layoutManager = LinearLayoutManager(this@Ejercicio01Activity)
                        adapter = TeacherAdapter(
                            lista,
                            onClick = { llamar(it.telefono) },
                            onLongClick = { enviarCorreo(it.email) }
                        )
                    }
                } else {
                    Log.e("API", "Error en la respuesta: código ${response.code()}")
                    Toast.makeText(
                        this@Ejercicio01Activity,
                        "Error: respuesta no exitosa del servidor",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<TeacherResponse>, t: Throwable) {
                Log.e("API", "Fallo de conexión: ${t.message}")
                Toast.makeText(
                    this@Ejercicio01Activity,
                    "Fallo de conexión: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun llamar(numero: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$numero")
        }
        startActivity(intent)
    }

    private fun enviarCorreo(correo: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$correo")
        }
        startActivity(intent)
    }
}
