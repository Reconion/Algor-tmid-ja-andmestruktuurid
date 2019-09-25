import java.util.ArrayList;

public class N3_praktikum {
    public static void main(String[] args) {
        int pikkus = 30;
        int k = 2;
        //ul1(pikkus, k);
        ul2(5);
    }

    private static void ul2(int arv) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ul2_abi(arv, list);
    }

    private static void ul2_abi(int arv, ArrayList<Integer> tulem) {
        if (arv == 0) {
            System.out.println(tulem.toString());
        }
        if (arv - 1 >= 0) {
            @SuppressWarnings("unchecked")
            ArrayList<Integer> list1 = (ArrayList<Integer>) tulem.clone();
            list1.add(1);
            ul2_abi(arv - 1, list1);
        }
        if (arv - 2 >= 0) {
            @SuppressWarnings("unchecked")
            ArrayList<Integer> list2 = (ArrayList<Integer>) tulem.clone();
            list2.add(2);
            ul2_abi(arv - 2, list2);
        }
    }

    private static void ul1(int pikkus, int k) {
        ul1_abi(pikkus, k, "", 0);
    }

    private static void ul1_abi(int pikkus, int k, String vektor, int ühtesi) {
        if (pikkus == 0 && ühtesi > k) {
            System.out.println(vektor);
        } else if (pikkus != 0) {
            ul1_abi(pikkus - 1, k, vektor + "0", ühtesi);
            ul1_abi(pikkus - 1, k, vektor + "1", ühtesi + 1);
        }
    }
}
