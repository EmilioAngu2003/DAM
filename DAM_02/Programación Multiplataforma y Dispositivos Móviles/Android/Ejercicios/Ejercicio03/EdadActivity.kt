package com.example.ejercicio03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class EdadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nombre = intent.getStringExtra("nombre") ?: ""
        val edad = intent.getIntExtra("edad", 0)

        setContent {
            MyApplicationTheme{
                Surface(color = MaterialTheme.colorScheme.background) {
                    EdadScreen(nombre, edad)
                }
            }
        }
    }
}

@Composable
fun EdadScreen(nombre: String, edad: Int) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val mensaje: String
        val imagen: Int
        if (edad < 18) {
            mensaje = "$nombre, todavía no estás preparado"
            imagen = R.drawable.toy_image
        } else {
            mensaje = "¡$nombre, estás listo para conducir!"
            imagen = R.drawable.car_image
        }

        Image(
            painter = painterResource(id = imagen),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = mensaje,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
