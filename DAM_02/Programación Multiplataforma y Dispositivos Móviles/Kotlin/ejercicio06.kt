package ut01

fun main() {

    print("Ingrese una fruta: ")
    val fruta = readlnOrNull()

    if (!fruta.isNullOrBlank()) {
        val mensaje = when (fruta) {
            "platano" -> "hola"
            "manzana" -> "bienvenido"
            else -> "fruta desconocida"
        }

        println(mensaje)
    }else{
        println("Fruta invalida")
    }

}
