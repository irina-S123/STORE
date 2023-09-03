package Services;

import Toys.Toy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyStore {
    private PriorityQueue<Toy> toyQueue;
    private int[] toyIds;
    private double[] toyWeights;

    public ToyStore() {
        toyQueue = new PriorityQueue<>((t1, t2) -> {
            if (t1.getWeight() < t2.getWeight()) {
                return -1;
            } else if (t1.getWeight() > t2.getWeight()) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    public void put(String input) {
        String[] parts = input.split(" ");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double weight = Double.parseDouble(parts[2]);

        Toy toy = new Toy(id, name, weight);
        toyQueue.add(toy);

        int queueSize = toyQueue.size();
        toyIds = new int[queueSize];
        toyWeights = new double[queueSize];

        int i = 0;
        for (Toy t : toyQueue) {
            toyIds[i] = t.getId();
            toyWeights[i] = t.getWeight();
            i++;
        }
    }

    public int getToyId() {
        Random random = new Random();
        double randomNumber = random.nextDouble();

        if (randomNumber <= 0.2) {
            return 1;
        } else if (randomNumber <= 0.4) {
            return 2;
        } else {
            return 3;
        }
    }

    public Toy getToyById(int id) {
        for (Toy toy : toyQueue) {
            if (toy.getId() == id) {
                return toy;
            }
        }
        return null;
    }

    public void addToy(Toy toy) {
        toyQueue.add(toy);
    }

    public void saveResultToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < toyQueue.size(); i++) {
                writer.write(toyQueue.peek().getName() + " " + toyIds[i] + " " + toyWeights[i]);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
