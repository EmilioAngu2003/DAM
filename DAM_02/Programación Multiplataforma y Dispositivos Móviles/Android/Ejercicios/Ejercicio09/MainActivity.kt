package com.example.ejercicio09

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("score", Context.MODE_PRIVATE)
    var numeroSecreto by remember { mutableStateOf(getRandomNumber()) }
    var numeroUsuario by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf(sharedPreferences.getInt("puntos", 0)) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = puntos.toString() , fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = numeroUsuario,
            onValueChange = { numeroUsuario = it },
            label = { Text("Adivina el número") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if(validar(context, numeroUsuario.toInt(), numeroSecreto)){
                        numeroSecreto = getRandomNumber()
                        puntos++
                    }
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if(validar(context, numeroUsuario.toInt(), numeroSecreto)){
                numeroSecreto = getRandomNumber()
                puntos++
            }
        }) {
            Text("Verificar")
        }
    }
}

fun getRandomNumber(): Int {
    return Random().nextInt(50) + 1
}

fun validar(context: Context, numeroUsuario: Int, numeroSecreto: Int): Boolean {
    if (numeroUsuario == numeroSecreto) {
        val sharedPreferences = context.getSharedPreferences("score", Context.MODE_PRIVATE)
        val puntos = sharedPreferences.getInt("puntos", 0)
        sharedPreferences.edit {
            putInt("puntos", puntos + 1)
            apply()
        }
        mensaje(context, "¡Felicidades! Has adivinado el número.")
        return true
    } else {
        if (numeroUsuario < numeroSecreto) {
            mensaje(context, "El número es mayor.")
        } else {
            mensaje(context, "El número es menor.")
        }
        return false
    }
}

fun mensaje(context: Context, message: String) {
    val builder = AlertDialog.Builder(context)
    builder.setMessage(message)
        .setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
    val dialog = builder.create()
    dialog.show()
}
