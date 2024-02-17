package ut01

fun main() {

    print("Ingrese el lado del cuadrado: ")
    val lado = readlnOrNull()?.toDoubleOrNull()

    if (lado != null && lado > 0) {
        println("El perimetro del cuadrado con lado $lado es: ${4 * lado}]")
    } else {
        println("Ingrese un valor valido para el lado del cuadrado.")
    }
}



