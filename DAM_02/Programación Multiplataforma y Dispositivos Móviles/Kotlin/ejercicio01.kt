package ut01

fun main() {

    val numero1 = 5
    val numero2 = 8

    if (numero1 <= numero2) {
        println("numero1 no es mayor que numero2")
    }

    if (numero2 > 0) {
        println("numero2 es positivo")
    }

    if (numero1 < 0 || numero1 != 0) {
        println("numero1 es negativo o distinto de cero")
    }

    if ((numero1 + 1) <= numero2) {
        println("Incrementar en 1 unidad el valor de numero1 no lo hace mayor o igual que numero2")
    }

}