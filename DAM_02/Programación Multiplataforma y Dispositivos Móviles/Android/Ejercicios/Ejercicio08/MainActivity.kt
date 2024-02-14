package com.example.ejercicio08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun MyApp() {
    val turnos: List<Turno> = Turno.values().toList()
    var turno by remember { mutableStateOf(Turno.MANYANA) }
    var cursos by remember { mutableStateOf(emptySet<Curso>()) }
    var mensaje by remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text("Seleccione el turno:")
                turnos.forEach { option ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (turno == option),
                            onClick = { turno = option }
                        )
                        Text(
                            text = option.descripcion,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column (
                modifier = Modifier.weight(1f)
            ){
                Text("Seleccione los cursos:")
                Curso.values().forEach { curso ->
                    Row {
                        Checkbox(
                            checked = curso in cursos,
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    cursos += curso
                                } else {
                                    cursos -= curso
                                }
                            },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Text(
                            text = curso.nombre,
                            modifier = Modifier.padding(vertical = 18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(
            onClick = {
                val costoTotal = calcularCosto(turno, cursos)
                // Mostrar el costo total en un Snackbar
                // También puedes mostrarlo en un TextView si lo prefieres
                // mostrando el resultado en la parte superior de la Column.
                // La forma de mostrar el resultado dependerá de la preferencia de diseño de tu app.
                // Por simplicidad, aquí se muestra en un Snackbar.
                mensaje = "Costo Total: S/. %.2f".format(costoTotal)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Calcular")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(mensaje)
    }
}

fun calcularCosto(turno: Turno, cursos: Set<Curso>): Double {
    val turnos = when (turno) {
        Turno.MANYANA -> arrayOf(255.00, 330.00, 545.00)
        Turno.TARDE -> arrayOf(260.00, 340.00, 575.00)
        Turno.NOCHE -> arrayOf(275.00, 365.00, 620.00)
    }

    var costoTotal = 0.0
    cursos.forEach { curso ->
        val indiceCurso = when (curso) {
            Curso.PHP -> 0
            Curso.JAVA -> 1
            Curso.ANDROID -> 2
        }
        costoTotal += turnos[indiceCurso]
    }
    return costoTotal
}

enum class Turno(val descripcion: String) {
    MANYANA("Mañana"),
    TARDE("Tarde"),
    NOCHE("Noche")
}

enum class Curso(val nombre: String) {
    PHP("PHP"),
    JAVA("Java"),
    ANDROID("Android")
}
