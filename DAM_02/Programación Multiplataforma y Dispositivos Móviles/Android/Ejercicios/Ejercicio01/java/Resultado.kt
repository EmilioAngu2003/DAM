package com.example.ejercicio01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class Resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        val intent = intent

        if (intent.hasExtra("resultado")) {
            val resultado = intent.getDoubleExtra("resultado", 0.0)
            val resultadoFormateado = if (resultado % 1.0 == 0.0) resultado.toInt().toString() else resultado.toString()
            val textResultado:TextView = findViewById(R.id.txtResultado)
            textResultado.text = resultadoFormateado
        } else {
            Toast.makeText(this, "No se proporcionó ningún resultado", Toast.LENGTH_SHORT).show()
        }
    }
}
