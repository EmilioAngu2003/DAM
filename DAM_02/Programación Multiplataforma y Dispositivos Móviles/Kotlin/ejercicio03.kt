package ut01

fun main() {

    print("Ingrese la nota: ")

    val nota = readlnOrNull()?.toDoubleOrNull()

    if (nota != null) {

        val calificacion = when {
            nota >= 0 && nota < 3 -> "Muy deficiente"
            nota >= 3 && nota < 5 -> "Insuficiente"
            nota >= 5 && nota < 6 -> "Suficiente"
            nota >= 6 && nota < 7 -> "Bien"
            nota >= 7 && nota < 9 -> "Notable"
            nota in 9.0..10.0 -> "Sobresaliente"
            else -> "Nota fuera de rango"
        }

        println("La calificacion es: $calificacion")
    } else {
        println("Ingrese una nota valida.")
    }
}