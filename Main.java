import Services.ToyStore;
import Toys.Toy;

public class Main {
    public static void main(String[] args) {        
        ToyStore toyStore = new ToyStore();

        toyStore.put("1 Мяч 10");
        toyStore.put("2 Кукла 15");
        toyStore.put("3 Машинка 12");

        for (int i = 0; i < 10; i++) {
            int wonToyId = toyStore.getToyId();
            Toy wonToy = toyStore.getToyById(wonToyId);
            if (wonToy != null) {
                System.out.println("Выиграна игрушка: " + wonToy.getName() + " (id: " + wonToy.getId() + ", вес: " + wonToy.getWeight() + ")");
            } else {
                System.out.println("Игрушки закончились");
            }
        }        
        toyStore.saveResultToFile("result.txt");
    }
}