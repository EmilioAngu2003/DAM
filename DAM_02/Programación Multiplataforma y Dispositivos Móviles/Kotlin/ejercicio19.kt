package ut01

fun main() {

    print("Ingrese el sueldo: ")
    val sueldo = readlnOrNull()?.toDoubleOrNull()

    print("Ingrese los años de antiguedad: ")
    val antiguedad = readlnOrNull()?.toIntOrNull()

    if (sueldo != null && antiguedad != null) {
        val sueldoFinal = when {
            sueldo < 500 && antiguedad >= 10 -> sueldo * 1.2
            sueldo < 500 && antiguedad < 10 -> sueldo * 1.05
            else -> sueldo
        }

        println("Sueldo a pagar: $sueldoFinal")
    } else {
        println("Ingrese valores validos.")
    }
}




