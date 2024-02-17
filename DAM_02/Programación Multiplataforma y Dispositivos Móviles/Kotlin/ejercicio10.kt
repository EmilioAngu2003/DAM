package ut01

fun main() {

    print("Ingrese el primer numero: ")
    val numero1 = readLine()?.toIntOrNull()

    print("Ingrese el segundo numero: ")
    val numero2 = readLine()?.toIntOrNull()

    if (numero1 != null && numero2 != null) {
        println("La multiplicacion de $numero1 y $numero2 es: ${numero1 * numero2}")
        if (numero2 != 0) println("La division de $numero1 entre $numero2 es: ${numero1 / numero2}")
    } else {
        println("Ingrese numeros validos.")
    }
}


