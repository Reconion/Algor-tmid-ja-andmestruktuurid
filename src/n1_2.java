public class n1_2 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
// PROGRAMMIOSA, MILLE TÄITMISAEGA MÕÕDAME (algus)
        System.out.println(fibo(20));
// PROGRAMMIOSA, MILLE TÄITMISAEGA MÕÕDAME (lõpp)
        long stop = System.currentTimeMillis();
        System.out.println("Aega kulus " + (stop - start) + " milliskundit");
    }

    static int fibo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibo(n - 1) + fibo(n - 2);
    }

    static int binoB(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int a = 1;
        int b = 0;
        int _temp = 0;

        for (int i = 2; i <= n; i++) {
            _temp = a + b;
            b = a;
            a = _temp;
        }

        return a + b;
    }
}
