/*****************************************************************************
 * Algoritmid ja andmestruktuurid (MTAT.03.133)
 * 2019/2020 sügissemester
 *
 * Kodutöö. Ülesanne nr 3
 * Teema: Failipuu
 * Koostada programm, mis magasini kasutades läbib failisüsteemi,
 * alates etteantud kataloogist, ja väljastab ekraanile kõik alamkataloogide
 * ning failide nimed. Tulemus peab rahuldama järgmiseid tingimusi:
 *
 * • Faili- ja katalooginimed väljastada sügavusest sõltuva taandega.
 * • Kataloogi nime ümber panna eristamiseks nurksulud.
 * • Väljastada ainult nende failide nimed, mille suurused ei ületa 500KB
 * (kataloogid võivad olla suvalise suurusega).
 * • Vahetult failinime järel märkida (sulgudes) faili suurus
 *
 * Autor: Risto Luik
 *****************************************************************************/

import java.io.File;

public class kodu3 {
    //Failipuu algus
    private static String asukoht = "C:\\Users\\Risto Luik\\Documents";
    //Suurim lubatud faili suurus mille korral faili välja prindime
    private static int maxFailisuurus = 500000;

    public static void main(String[] args) {
        failipuu(asukoht, 0);
    }

    /**
     * @param kaustVõiFail hetkel vaadeldav fail või kaust
     * @param trepiAste    Määrab mitme taanduse võrra treppida
     * Prindib välja failipuu selliselt nagu faili päises kirjeldatud
     */
    private static void failipuu(String kaustVõiFail, int trepiAste) {
        File file = new File(kaustVõiFail);
        String trepp = "\t".repeat(trepiAste);
        //kontrollime kas tegu on kaustaga või mitte
        if (file.isDirectory()) {
            System.out.println(trepp + "[" + file.getName() + "]");
            String[] failidKaustas = file.list();
            //Kontrollime, et failidKaustas poleks null ja juhul kui on, lõpetame töö
            if (failidKaustas == null) {
                return;
            }
            //Teeme iga failiga mis kaustas on uuesti puu läbi, lisades ühe treppimise iga korra eest juurde
            for (String fail : failidKaustas) {
                failipuu(kaustVõiFail + "\\" + fail, trepiAste + 1);
            }
        } else {
            //Kontrollime failisuurust ja prindime failinime ja suuruse kui on alla lävendi
            if (file.length() < maxFailisuurus) {
                System.out.println(trepp + file.getName() + " (" + (double) file.length() / 1000 + "KB)");
            }
        }
    }
}
