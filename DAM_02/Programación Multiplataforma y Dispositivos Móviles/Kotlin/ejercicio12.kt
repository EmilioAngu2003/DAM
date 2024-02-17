package ut01

import java.util.*

fun main() {

    print("Ingrese el modelo de coche: ")
    val modelo = readlnOrNull()?.lowercase(Locale.getDefault())

    val descuento = when (modelo) {
        "ford fiesta" -> 5
        "ford focus" -> 10
        else -> 0
    }

    if (descuento > 0) {
        println("Descuento para el $modelo: $descuento%")
    } else {
        println("Modelo no reconocido. No hay descuento.")
    }
}


