package ut01

fun main() {

    print("Ingrese el precio del articulo: ")
    val precio = readlnOrNull()?.toIntOrNull()

    print("Ingrese la cantidad que lleva el cliente: ")
    val cantidad = readlnOrNull()?.toIntOrNull()

    if (precio != null && cantidad != null && precio >= 0 && cantidad >= 0) {
        println("El cliente debe pagar: ${precio * cantidad}")
    } else {
        println("Ingrese valores validos (números enteros no negativos).")
    }
}




