import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DuplicateFinder {
    private static String Location = "G:\\Porr";
    private static ArrayList<String> extensions = new ArrayList();
    private static TreeMap<String, ArrayList<String>> fileAndLocation = new TreeMap<>();

    public static void main(String[] args) {
        mapDuplicates(Location);
        System.out.println(extensions);
        int nrOfDuplicates = 0;
        for (Map.Entry<String, ArrayList<String>> entry : fileAndLocation.entrySet()) {
            if (entry.getValue().size() > 1 && !entry.getKey().equals("Thumbs.db")) {
                nrOfDuplicates++;
                System.out.println(entry.getKey());
                for (String path : entry.getValue()) {
                    System.out.println("\t" + path);
                }
                String fileLocation = entry.getValue().get(0);
                File file = new File(fileLocation);
                if (file.delete()) {
                    System.out.println("Deleted: \t\t" + fileLocation);
                } else {
                    System.out.println("Not deleted: \t\t" + fileLocation);
                }
            }
        }
        System.out.println("Number of duplicates: " + nrOfDuplicates);
    }

    private static void mapDuplicates(String fileOrLocation) {
        File file = new File(fileOrLocation);
        if (file.isDirectory()) {
            String[] failidKaustas = file.list();
            if (failidKaustas == null) {
                return;
            }
            for (String fail : failidKaustas) {
                mapDuplicates(fileOrLocation + "\\" + fail);
            }
        } else {
            String[] pieces = file.getName().split("\\.");
            String extension = pieces[pieces.length - 1];
            if (!extensions.contains(extension)) {
                extensions.add(extension);
            }

            if (fileAndLocation.containsKey(file.getName())) {
                fileAndLocation.get(file.getName()).add(fileOrLocation);
            } else {
                ArrayList<String> path = new ArrayList<>();
                path.add(fileOrLocation);
                fileAndLocation.put(file.getName(), path);
            }
        }
    }
}
