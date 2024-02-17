package ut01

fun main() {

    print("Ingrese un numero entero para calcular su factorial: ")

    val numero = readlnOrNull()?.toIntOrNull()

    if (numero != null && numero >= 0) {
        var factorial = 1

        for (i in 1..numero) {
            factorial *= i
        }

        println("El factorial de $numero es: $factorial")
    } else {
        println("Ingrese un numero entero no negativo valido.")
    }
}