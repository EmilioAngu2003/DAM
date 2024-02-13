package com.example.ejercicio01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText2:EditText = findViewById(R.id.txtNumber2)
        val editText1:EditText = findViewById(R.id.txtNumber1)
        editText2.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sumarNumeros(editText1, editText2)
                return@setOnEditorActionListener true
            }
            false
        }
        val btnSumar:Button = findViewById(R.id.btnSumar)

        btnSumar.setOnClickListener{
            sumarNumeros(editText1, editText2)
        }
    }

    fun sumarNumeros(editText1: EditText, editText2: EditText) {

        val text1 = editText1.text.toString()
        val text2 = editText2.text.toString()

        if (text1.isEmpty() || text2.isEmpty()) {
            Toast.makeText(this@MainActivity, "Por favor, introduzca ambos números", Toast.LENGTH_SHORT).show()
            return
        }

        val n1 = text1.toDoubleOrNull()
        val n2 = text2.toDoubleOrNull()

        if (n1 == null || n2 == null) {
            Toast.makeText(this@MainActivity, "Por favor, introduzca números válidos", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = n1 + n2

        val intent = Intent(this@MainActivity, Resultado::class.java)
        intent.putExtra("resultado", resultado)
        startActivity(intent)
    }
}
