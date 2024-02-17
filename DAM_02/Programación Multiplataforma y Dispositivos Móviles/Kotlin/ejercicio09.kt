package ut01

fun main() {

    print("Ingrese el primer numero: ")
    val numero1 = readlnOrNull()?.toIntOrNull()

    print("Ingrese el segundo numero: ")
    val numero2 = readlnOrNull()?.toIntOrNull()

    if (numero1 != null && numero2 != null) {
        println("La suma de $numero1 y $numero2 es: ${numero1 + numero2}")
        println("La resta de $numero1 y $numero2 es: ${numero1 - numero2}")
    } else {
        println("Ingrese numeros validos.")
    }
}


