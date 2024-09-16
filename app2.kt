interface INumber {
    val value: Int
    fun isPrime(): Boolean
    fun isEven(): Boolean
    fun isOdd(): Boolean
}

class PrimeNumber(override val value: Int) : INumber {
    override fun isPrime(): Boolean {
        if (value < 2) return false
        for (i in 2..Math.sqrt(value.toDouble()).toInt()) {
            if (value % i == 0) return false
        }
        return true
    }

    override fun isEven() = value % 2 == 0
    override fun isOdd() = value % 2 != 0
}

class EvenNumber(override val value: Int) : INumber {
    override fun isPrime() = PrimeNumber(value).isPrime()
    override fun isEven() = true
    override fun isOdd() = false
}

class OddNumber(override val value: Int) : INumber {
    override fun isPrime() = PrimeNumber(value).isPrime()
    override fun isEven() = false
    override fun isOdd() = true
}

class NumberCounter(private val n: Int) {

    fun countNumbers(): NumberCountResult {
        var primeCount = 0
        var evenCount = 0
        var oddCount = 0

        for (i in 1..n) {
            val number = classifyNumber(i)
            if (number.isPrime()) primeCount++
            if (number.isEven()) evenCount++
            if (number.isOdd()) oddCount++
        }

        return NumberCountResult(primeCount, evenCount, oddCount)
    }

    private fun classifyNumber(num: Int): INumber {
        return when {
            PrimeNumber(num).isPrime() -> PrimeNumber(num)
            num % 2 == 0 -> EvenNumber(num)
            else -> OddNumber(num)
        }
    }
}

data class NumberCountResult(val primeCount: Int, val evenCount: Int, val oddCount: Int)

fun validateInput(n: Int) {
    if (n <= 0) {
        throw IllegalArgumentException("El valor de N debe ser un número entero positivo.")
    }
}

fun main() {
    try {
        print("Ingrese un número entero positivo N: ")
        val n = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Entrada inválida.")
        
        validateInput(n)

        val counter = NumberCounter(n)
        val result = counter.countNumbers()

        println("Conteo de números en el rango de 1 a $n:")
        println("Primos: ${result.primeCount}")
        println("Pares: ${result.evenCount}")
        println("Impares: ${result.oddCount}")
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}
