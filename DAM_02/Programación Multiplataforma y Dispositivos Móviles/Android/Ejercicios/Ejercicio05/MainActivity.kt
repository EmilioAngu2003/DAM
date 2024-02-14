package com.example.ejercicio05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversor()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Conversor() {

    val cambioActial = 1.21
    var euros by remember { mutableStateOf(TextFieldValue()) }
    var dolares by remember { mutableStateOf(0.00) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = euros,
            onValueChange = { euros = it },
            label = { Text("Euros") }
        )
        Button(
            onClick = {
                val eurosDouble = euros.text.toDoubleOrNull()
                if (eurosDouble != null) dolares = eurosDouble * cambioActial
            }
        ) {
            Text("Calculate")
        }
        Text("${formatDecimal(dolares)} dolares")
    }
}

fun formatDecimal(value: Double): String {
    val df = DecimalFormat("#.##")
    df.maximumFractionDigits = 2
    return df.format(value)
}
