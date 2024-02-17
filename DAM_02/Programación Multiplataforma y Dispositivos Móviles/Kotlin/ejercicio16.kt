package ut01

fun main() {

    print("Ingrese la primera clave: ")
    val clave1 = readlnOrNull()

    print("Ingrese la segunda clave: ")
    val clave2 = readlnOrNull()

    if (clave1 == clave2) {
        println("Las claves son iguales.")
    } else {
        println("Las claves no son iguales.")
    }
}



