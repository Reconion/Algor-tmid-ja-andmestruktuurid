public class N3Kodu {
    public static void main(String[] args) {
        double[] tööriistad = suvatööriistad(13, 0, 7);
        int kaal = 13;
        ul6a(tööriistad, kaal);
    }

    private static void ul6a(double[] tööriistad, int kaal) {
        double[] uusmassiiv = new double[tööriistad.length - 1];
        double hetkekaal = 0;
        ul6aAbi(tööriistad, kaal, uusmassiiv, hetkekaal);
    }

    private static void ul6aAbi(double[] tööriistad, int kaal, double[] uusmassiiv, double hetkekaal) {

    }

    private static double massiiviSumma(double[] massiiv) {
        double summa = 0;
        for (double v : massiiv) {
            summa += v;
        }
        return summa;
    }

    private static double[] suvatööriistad(int kogus, int min, int max) {
        double[] tööriistad = new double[kogus];
        for (int i = 0; i < kogus; i++) {
            tööriistad[i] = Math.round((Math.random() * max + min) * 100.00) / 100.00;
        }
        return tööriistad;
    }
}
