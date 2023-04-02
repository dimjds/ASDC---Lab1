import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Market[] m = new Market[50];
        try {
            Scanner fin = new Scanner(new File("\"F:\\FX\\Универ\\Лабы 2  год\\ASDC\\market.txt\""));
            int i = 0;
            while (fin.hasNextLine() && i < m.length) {
                String[] data = fin.nextLine().split(" ");
                m[i] = new Market(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]), data[4], Long.parseLong(data[5]));
                i++;
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error!");
        }

        System.out.println("Линейный поиск:");
        for (int i = 0; i < m.length; i++) {
            if (m[i] != null && m[i].name.equals("tea")) {
                System.out.println(m[i].name + " " + m[i].description + " " + m[i].price + " " + m[i].count + " " + m[i].unit + " " + m[i].id);
            }
        }

        System.out.println("\nБинарный поиск:");
        System.out.print("Введите название продукта: ");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        int first = 0;
        int last = m.length - 1;
        int mid = 0;
        boolean found = false;
        while (first <= last) {
            mid = (first + last) / 2;
            if (m[mid] == null) {
                System.out.println("Product not found.");
                return;
            }
            if (name.compareTo(m[mid].name) > 0) {
                first = mid + 1;
            } else if (name.compareTo(m[mid].name) < 0) {
                last = mid - 1;
            } else {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println(m[mid].name + " " + m[mid].description + " " + m[mid].price + " " + m[mid].count + " " + m[mid].unit + " " + m[mid].id);
        } else {
            System.out.println("Product not found.");
        }
    }
}

class Market {
    public String name;
    public String description;
    public double price;
    public int count;
    public String unit;
    public long id;

    public Market(String name, String description, double price, int count, String unit, long id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
        this.unit = unit;
        this.id = id;
    }
}
