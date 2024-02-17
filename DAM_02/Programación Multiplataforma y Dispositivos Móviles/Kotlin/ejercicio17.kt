package ut01

fun main() {

    print("Ingrese el dia: ")
    val dia = readlnOrNull()?.toIntOrNull()

    print("Ingrese el mes: ")
    val mes = readlnOrNull()?.toIntOrNull()

    print("Ingrese el año: ")
    val anio = readlnOrNull()?.toIntOrNull()

    if (dia != null && mes != null && anio != null) {
        if (dia == 25 && mes == 12) {
            println("La fecha ingresada corresponde a Navidad.")
        } else {
            println("La fecha ingresada no corresponde a Navidad.")
        }
    } else {
        println("Ingrese valores validos")
    }
}




