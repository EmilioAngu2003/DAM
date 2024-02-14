package com.example.ejercicio07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                CalculadoraSeguro()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraSeguro() {
    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var edad by remember { mutableStateOf(TextFieldValue()) }
    var valorAuto by remember { mutableStateOf(TextFieldValue()) }
    var modelo by remember { mutableStateOf("") }
    var accidentes by remember { mutableStateOf(false) }
    var cantidadAccidentes by remember { mutableStateOf(0) }
    var costoTotal by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Propietario") }
        )
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") }
        )
        OutlinedTextField(
            value = valorAuto,
            onValueChange = { valorAuto = it },
            label = { Text("Valor del Automovil") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Modelo del auto")
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            RadioButton(
                selected = modelo == "A",
                onClick = { modelo = "A" },
                modifier = Modifier.weight(1f)
            )
            Text(
                "A",
                style = TextStyle(fontSize = 22.sp),
                modifier = Modifier.weight(1f).padding(vertical = 10.dp)
            )
            RadioButton(
                selected = modelo == "B",
                onClick = { modelo = "B" },
                modifier = Modifier.weight(1f)
            )
            Text(
                "B",
                style = TextStyle(fontSize = 22.sp),
                modifier = Modifier.weight(1f).padding(vertical = 10.dp)
            )
            RadioButton(
                selected = modelo == "C",
                onClick = { modelo = "C" },
                modifier = Modifier.weight(1f)
            )
            Text(
                "C",
                style = TextStyle(fontSize = 22.sp),
                modifier = Modifier.weight(1f).padding(vertical = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = accidentes,
                onCheckedChange = { accidentes = it }
            )
            Text("Accidentes previos")
        }
        if (accidentes) {
            OutlinedTextField(
                value = cantidadAccidentes.toString(),
                onValueChange = { cantidadAccidentes = it.toIntOrNull() ?: 0 },
                label = { Text("Cantidad de Accidentes") }
            )
        }else {
            cantidadAccidentes = 0
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if(edad.text.toInt() >= 18){
                    costoTotal = costoTotal(
                        valorAuto.text.toFloat(),
                        modelo,
                        edad.text.toInt(),
                        cantidadAccidentes
                    ).toString()
                    showError = false
                } else {
                    showError = true
                }
            }
        ) {
            Text("Calcular costo total")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Costo total: $costoTotal")
        if (showError) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { showError = false }) {
                        Text("Cerrar")
                    }
                }
            ) {
                Text("La edad del propietario debe ser mayor o igual a 18 años")
            }
        }
    }
}

fun costoTotal(
    valorAuto: Float,
    modelo: String,
    edad: Int,
    cantidadAccidentes: Int
): Float {

    val cargoPorValor = valorAuto * 0.035f

    val cargoPorModelo = when (modelo) {
        "A" -> valorAuto * 0.011f
        "B" -> valorAuto * 0.012f
        "C" -> valorAuto * 0.015f
        else -> 0f
    }

    val cargoPorEdad = when (edad) {
        in 18..23 -> 360f
        in 24..52 -> 240f
        else -> 430f
    }

    val cargoPorAccidentes = when {
        cantidadAccidentes <= 3 -> cantidadAccidentes * 17f
        else -> 51f + (cantidadAccidentes - 3) * 21f
    }

    return cargoPorValor + cargoPorModelo + cargoPorEdad + cargoPorAccidentes
}
