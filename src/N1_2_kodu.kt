/*****************************************************************************
 * Algoritmid ja andmestruktuurid (MTAT.03.133)
 * 2019/2020 sügissemester
 *
 * Kodutöö. Ülesanne nr 1
 * Teema: Ajalise keerukuse empiiriline hindamine
 *
 * Autor: Risto Luik

 *****************************************************************************/
import java.io.File
import java.util.*

object N1_2_kodu {

    @JvmStatic
    fun main(args: Array<String>) {
        //map mis hoiab massiivi pikkust ja sorteerimiseks kulunud aega
        val bubbleSortMap = mutableMapOf<Int, Long>()
        val mergeSortMap = mutableMapOf<Int, Long>()
        val kotlingSortMap = mutableMapOf<Int, Long>()

        //ei viitsinud ise kirja panna
        val pikkused = looPikkused()

        //katsetan erinevate pikkustega tööaega
        for (i in pikkused) {
            val massiiv = looMassiiv(i)
            //sorteerin massiivi, et anda enda meetoditele täie rauaga tööd
            Arrays.sort(massiiv)

            //Bubblesort
            val bubbleSortMassiiv = massiiv.copyOf()
            var start = System.currentTimeMillis()
            bubbleSort(bubbleSortMassiiv)
            var lõpp = System.currentTimeMillis()
            var aeg = lõpp - start
            bubbleSortMap[i] = aeg

            //Mergesort
            val mergeSortMassiiv = massiiv.copyOf()
            start = System.currentTimeMillis()
            mergeSort(mergeSortMassiiv, mergeSortMassiiv.size)
            lõpp = System.currentTimeMillis()
            aeg = lõpp - start
            mergeSortMap[i] = aeg

            //Kotlini oma sorteerimisalgorütm
            val kotlingSortMassiiv = massiiv.copyOf()
            start = System.currentTimeMillis()
            kotlingSortMassiiv.sortDescending()
            lõpp = System.currentTimeMillis()
            aeg = lõpp - start
            kotlingSortMap[i] = aeg

            //Prindime aeg-ajalt kui kaugel programm omadega on
            if (i % 1000 == 0) {
                println(i)
            }
        }
        //Tekkitan csv faili, et excelis graafikut teha
        salvestaTulemused(bubbleSortMap, mergeSortMap, kotlingSortMap)
    }

    /**
     * Kirjutab CSV faili, et excelis graafikut teha
     * @param bubbleSortMap legend massivi pikksuest ja ajast, mis bubblesortil kulus, et seda sorteerida
     * @param mergeSortMap legend massiivi pikkusest ja ajast, mis mergesortil kulus, et seda sorteerida
     */
    private fun salvestaTulemused(
            bubbleSortMap: Map<Int, Long>,
            mergeSortMap: Map<Int, Long>,
            kotlingSortMap: MutableMap<Int, Long>
    ) {
        //loome csv texti
        var csvString = ""
        for (sisestus in bubbleSortMap) {
            val suurus = sisestus.key
            csvString += "$suurus, " +
                    "${bubbleSortMap[suurus]}, " +
                    "${mergeSortMap[suurus]}, " +
                    "${kotlingSortMap[suurus]}\n"
        }
        //teeme faili
        val fail = File("Tulemused.csv")
        fail.bufferedWriter().use { out ->
            out.write(csvString)
        }
    }

    /**
     * Tekkitab pikkused, millega sorteerimismeetodeid testida
     */
    private fun looPikkused(): List<Int> {
        val list = mutableListOf<Int>()
        for (i in 1..1000) {
            list.add(i * 100)
        }
        return list
    }

    /**
     * Võetud ja muudetud: https://www.geeksforgeeks.org/bubble-sort/
     * Sorteerib massiivi kasutades bubblesort meetodit
     * @param arr massiiv, mida sorteerida
     */
    fun bubbleSort(arr: IntArray) {
        val n = arr.size
        for (i in 0 until n - 1)
            for (j in 0 until n - i - 1)
                if (arr[j] < arr[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
    }

    /**
     * Loob suvalistest elementidest koosneva massiivi(0-100)
     * @param pikkus massiivi pikkus
     * @return massiivi pikksuega pikkus
     */
    private fun looMassiiv(pikkus: Int): IntArray {
        val list = arrayListOf<Int>()

        //Kotlinis on vahemikus a..b b kaasaarvatud
        for (i in 0..pikkus) {
            list.add(juhuArv(0, 100))
        }
        //tühjadest elementidest massiiv, et saaks pärast listi väärtused anda
        return list.toIntArray()
    }

    /**
     * @param miinimum vähim juhuarvu väärtus
     * @param maximum suurim juhuarvu väärtus
     * @return Juhuarv vahemikus miinimum..maximum
     */
    private fun juhuArv(miinimum: Int, maximum: Int): Int {
        return (Math.random() * maximum + miinimum).toInt()
    }

    /**
     * Sorteerib massiivi kasutades mergesort meetodit
     * Võetud ja muudetud: https://www.baeldung.com/java-merge-sort
     * @param a massiiv
     * @param n massiivi pikkus
     */
    private fun mergeSort(a: IntArray, n: Int) {
        if (n < 2) {
            return
        }
        val mid = n / 2
        val l = IntArray(mid)
        val r = IntArray(n - mid)

        for (i in 0 until mid) {
            l[i] = a[i]
        }
        for (i in mid until n) {
            r[i - mid] = a[i]
        }
        mergeSort(l, mid)
        mergeSort(r, n - mid)

        merge(a, l, r, mid, n - mid)
    }

    /**
     * Abimeetod mergesortile
     */
    private fun merge(
            a: IntArray, l: IntArray, r: IntArray, left: Int, right: Int) {

        var i = 0
        var j = 0
        var k = 0
        while (i < left && j < right) {
            if (l[i] >= r[j]) {
                a[k++] = l[i++]
            } else {
                a[k++] = r[j++]
            }
        }
        while (i < left) {
            a[k++] = l[i++]
        }
        while (j < right) {
            a[k++] = r[j++]
        }
    }
}