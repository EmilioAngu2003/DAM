package ut01

fun main() {

    print("Ingrese el numero de DNI: ")

    val numeroDNI = readlnOrNull()?.toIntOrNull()

    if (numeroDNI != null && numeroDNI in 0..99999999) {

        val resto = numeroDNI % 23

        val letras = arrayOf('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E')

        val letraDNI = letras[resto]

        println("La letra del DNI es: $letraDNI")

    } else {
        println("Ingrese un numero de DNI valido.")
    }
}