package ut01

fun main() {
    val numeros = mutableListOf<Int>()

    while (numeros.size < 4) {
        print("Ingrese un numero: ")
        val input = readlnOrNull()?.toIntOrNull()

        if (input != null) {
            numeros.add(input)
        } else {
            println("Ingrese un numero valido.")
        }
    }

    println("La suma de ${numeros[0]} y ${numeros[1]} es: ${numeros[0] + numeros[1]}")
    println("El producto de ${numeros[2]} y ${numeros[3]} es: ${numeros[2] * numeros[3]}")
}
