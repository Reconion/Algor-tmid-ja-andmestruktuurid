object n1_2_kotlin {

    @JvmStatic
    fun main(args: Array<String>) {
        val n = 20
        var start = System.currentTimeMillis()
        println(fibo(n))
        var stop = System.currentTimeMillis()
        println("Aega kulus " + (stop - start) + " milliskundit")

        start = System.currentTimeMillis()
        println(fiboB(n))
        stop = System.currentTimeMillis()
        println("Aega klulus " + (stop - start) + " millisekundit")
    }

    private fun fibo(n: Int): Int {
        if (n == 0) return 0
        return if (n == 1) 1 else fibo(n - 1) + fibo(n - 2)
    }

    private fun fiboB(n: Int): Int? {
        if (n == 0) return 0
        if (n == 1) return 1
        var a = 1
        var b = 0
        var temp = 0

        for (i in 2..n) {
            temp = a + b
            b = a
            a = temp
        }

        return temp
    }

}
