package application;

import model.entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Product> list = new ArrayList<>();

        System.out.print("Enter file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String test = br.readLine();

            while (test != null) {
                String[] vet = test.split(",");
                list.add(new Product(vet[0], Double.parseDouble(vet[1])));
                test = br.readLine();
            }

            double media = list.stream().mapToDouble(Product::getPrice).average().getAsDouble();
            System.out.println("Average price: " + String.format("%.2f", media));

            list.stream()
                    .filter(x -> x.getPrice() < media)
                    .sorted(Comparator.comparing(Product::getName).reversed())
                    .map(Product::getName)
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
