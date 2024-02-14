package com.example.ejercicio02

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val errorMessage = intent.getStringExtra("error_message")
        if (errorMessage != null) {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.apply {
                setTitle("Error")
                setMessage(errorMessage)
                setPositiveButton("Aceptar", null)
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

        setContent {
            MyApp(onAddClicked = { cantidades, costos -> starResultadoActivity(cantidades, costos, this)})
        }
    }

    private fun starResultadoActivity(cantidades: List<String>, costos: List<String>, context: Context) {
        var total = BigDecimal.ZERO
        val cantidadesIncorrectas = mutableListOf<Int>()
        for (i in cantidades.indices) {
            try {
                val cantidad = cantidades[i].toIntOrNull()
                val costo = costos[i].toDoubleOrNull()
                if (cantidad != null && costo != null) {
                    val costoRedondeado = BigDecimal.valueOf(costo).setScale(2, RoundingMode.HALF_EVEN)
                    val producto = BigDecimal.valueOf(cantidad.toLong()).multiply(costoRedondeado)
                    total = total.add(producto)
                } else {
                    throw NumberFormatException()
                }
            } catch (e: NumberFormatException) {
                cantidadesIncorrectas.add(i)
            }
        }

        if (cantidadesIncorrectas.isNotEmpty()) {
            val mensajeError = StringBuilder("Los siguientes elementos tienen valores incorrectos:\n")
            for (indice in cantidadesIncorrectas) {
                mensajeError.append("Producto ${indice + 1}\n")
            }
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.apply {
                setTitle("Error")
                setMessage(mensajeError.toString())
                setPositiveButton("Aceptar", null)
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }else{
            val intent = Intent(context, ResultadoActivity::class.java).apply {
                putExtra("resultado", total.toString())
            }
            startActivity(intent)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyApp(
        onAddClicked: (List<String>, List<String>) -> Unit
    ) {

        var cantidades by remember { mutableStateOf(listOf("")) }
        var costos by remember { mutableStateOf(listOf("")) }

        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(cantidades.size) { index ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        "Producto ${index + 1}",
                        modifier = Modifier.weight(8f).padding(vertical = 20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextField(
                        value = cantidades[index],
                        onValueChange = { newValue ->
                            cantidades = cantidades.toMutableList().apply { set(index, newValue) }
                        },
                        singleLine = true,
                        label = { Text("Cantidad ${index + 1}") },
                        modifier = Modifier.weight(15f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextField(
                        value = costos[index],
                        onValueChange = { newValue ->
                            costos = costos.toMutableList().apply { set(index, newValue) }
                        },
                        singleLine = true,
                        label = { Text("Costo ${index + 1}") },
                        modifier = Modifier.weight(15f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            cantidades += ""
                            costos += ""
                        },
                        modifier = Modifier.weight(5f)
                    ) {
                        Text("+")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { onAddClicked(cantidades.toMutableList(), costos.toMutableList()) },
                        modifier = Modifier.weight(30f)
                    ) {
                        Text("Calcular")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
