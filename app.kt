interface IFizzBuzz {
    fun evaluateRange(start: Int, end: Int)
}

class FizzBuzzProcessor : IFizzBuzz {

    override fun evaluateRange(start: Int, end: Int) {
        validateInput(start, end)
        for (i in start..end) {
            printFizzBuzz(i)
            if ((i - start + 1) % 10 == 0) {
                println() // Nueva línea cada 10 elementos
            } else {
                print(" ") // Espacio entre elementos
            }
        }
    }

    private fun printFizzBuzz(number: Int) {
        when {
            number % 15 == 0 -> print("FizzBuzz")
            number % 3 == 0 -> print("Fizz")
            number % 5 == 0 -> print("Buzz")
            else -> print(number)
        }
    }

    private fun validateInput(start: Int, end: Int) {
        if (start > end) {
            throw IllegalArgumentException("El inicio del rango no puede ser mayor que el final.")
        }
        if (start <= 0 || end <= 0) {
            throw IllegalArgumentException("El rango debe contener números positivos.")
        }
    }
}

fun main() {
    try {
        val fizzBuzzProcessor = FizzBuzzProcessor()
        fizzBuzzProcessor.evaluateRange(1, 100)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}
