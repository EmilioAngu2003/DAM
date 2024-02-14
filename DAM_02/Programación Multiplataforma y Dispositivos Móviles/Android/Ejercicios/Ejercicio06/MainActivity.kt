package com.example.ejercicio06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var cantidadPersonas by remember { mutableStateOf("") }
    var costoTotal by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = cantidadPersonas,
            onValueChange = { cantidadPersonas = it },
            label = { Text("Cantidad de personas") }
        )
        Button(
            onClick = {
                costoTotal = calcularCostoTotal(cantidadPersonas.toInt()).toString()
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Presupuesto")
        }
        Text(text = "Costo total: $costoTotal euros")
    }
}

fun calcularCostoTotal(cantidadPersonas: Int): Double {
    return when {
        cantidadPersonas <= 200 -> cantidadPersonas * 30.0
        cantidadPersonas <= 300 -> cantidadPersonas * 25.0
        else -> cantidadPersonas * 10.0
    }
}
