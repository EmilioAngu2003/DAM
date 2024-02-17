package ut01

import java.util.*

fun main() {

    print("Ingrese (casa, mesa, perro, gato): ")
    val palabra = readlnOrNull()?.lowercase(Locale.getDefault())

    val palabraTraducida = when (palabra) {
        "casa" -> "house"
        "mesa" -> "table"
        "perro" -> "dog"
        "gato" -> "cat"
        else -> "Palabra no reconocida"
    }

    println("Palabra traducida: $palabraTraducida")
}




