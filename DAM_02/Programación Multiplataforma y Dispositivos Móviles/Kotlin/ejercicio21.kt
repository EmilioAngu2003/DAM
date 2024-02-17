package ut01

fun main() {
    val inicioSerie = 11
    val incremento = 11
    val terminos = 25

    for (i in 1..terminos) {
        val terminoActual = inicioSerie + (i - 1) * incremento
        print("$terminoActual - ")
    }
}

