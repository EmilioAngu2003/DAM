package ut01

fun main() {
    var contador = 1
    val multiplicador = 7

    while (contador <= 10) {
        val resultado = multiplicador * contador
        println("Multiplo $contador de 7: $resultado")
        contador++
    }
}


