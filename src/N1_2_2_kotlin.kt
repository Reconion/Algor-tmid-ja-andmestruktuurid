object N1_2_2_kotlin {
    @JvmStatic
    fun main(args: Array<String>) {
        y2_a()
    }

    fun y2_a() {
        for (i in 0..200) {
            val start: Long = System.currentTimeMillis()
            println(bitt_gen(i, ""))
            val stop: Long = System.currentTimeMillis()
            val aeg = stop - start
            println("Aega kulus $aeg millisekundit")

            if (aeg > 1000) {
                println("$i aega kulus $aeg millisekundit")
                break
            }
        }
    }

    fun bitt_gen(n: Int, vektor: String): Int {
        if (vektor.length == n) {
            return 1
        }
        return bitt_gen(n, vektor + "0") + bitt_gen(n, vektor + "1")
    }
}