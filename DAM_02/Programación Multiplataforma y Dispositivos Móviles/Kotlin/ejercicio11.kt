package ut01

fun main() {

    val notas = mutableListOf<Double>()

    var i = 1
    while (i <= 3) {

        print("Ingrese la nota $i: ")
        val nota = readlnOrNull()?.toDoubleOrNull()

        if (nota != null) {
            notas.add(nota)
            i++
        } else {
            println("Ingrese una nota valida.")
        }
    }

    val media = notas.average()
    println("La nota media es: $media")

    if (media >= 5) {
        println("Aprobado")
    } else {
        println("Suspendido")
    }
}
