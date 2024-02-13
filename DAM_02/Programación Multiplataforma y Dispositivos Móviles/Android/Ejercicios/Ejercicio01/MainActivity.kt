package com.example.ejercicio01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewMyApp()
        }
    }

    @Preview
    @Composable
    fun PreviewMyApp() {
        MyApp()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyApp() {
        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = text1,
                onValueChange = { text1 = it },
                label = { Text("Primer Numero") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = text2,
                onValueChange = { text2 = it },
                label = { Text("Segundo Numero") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    try {
                        val n1 = BigDecimal(text1)
                        val n2 = BigDecimal(text2)
                        val suma = n1.add(n2)
                        Toast.makeText(context, "$text1 + $text2", Toast.LENGTH_SHORT).show()
                        starResultadoActivity(resultado = suma.toString())
                    } catch (e: NumberFormatException) {
                        Toast.makeText(context, "Introduce numeros validos", Toast.LENGTH_SHORT).show()
                    }
                }
            ){
                Text("Sumar")
            }
        }
    }


    private fun starResultadoActivity(resultado: String) {
        val intent = Intent(this, ResultadoActivity::class.java).apply {
            putExtra("resultado", resultado)
        }
        startActivity(intent)
    }
}

