package ut01

fun main() {
    val numeros = mutableListOf<Int>()

    while (numeros.size < 3) {
        print("Ingrese el numero: ")
        val num = readlnOrNull()?.toIntOrNull()
        if (num != null) {
            numeros.add(num)
        } else {
            println("Ingrese un numero valido.")
        }
    }

    if (numeros.all { it < 10 }) {
        println("Todos los numeros son menores a diez.")
    } else {
        println("Al menos uno de los numeros es 10 o mayor.")
    }
}
