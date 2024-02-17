package ut01

fun main() {

    val edades = mutableListOf<Int>()
    val nombres = mutableListOf<String>()

    for (i in 1..3) {
        print("Ingrese la edad $i: ")
        val edad = readlnOrNull()?.toIntOrNull()

        if (edad != null) {
            edades.add(edad)

            print("Ingrese el nombre $i: ")
            val nombre = readlnOrNull()

            if (!nombre.isNullOrBlank()) {
                nombres.add(nombre)
            } else {
                println("Nombre invalido. Intentelo de nuevo.")
                return
            }
        } else {
            println("Edad invalida. Intentelo de nuevo.")
            return
        }
    }

    val indiceMayorEdad = edades.indexOf(edades.maxOrNull())

    println("El nombre del mayor es: ${nombres[indiceMayorEdad]}")
}