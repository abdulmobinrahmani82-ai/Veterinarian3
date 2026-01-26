package menu;
import exception.InvalidInputException;
import model.Pet;
import model.Owner;
import java.util.Scanner;
import java.util.ArrayList;

public class MenuManager implements Menu {

        private ArrayList<Pet> pets = new ArrayList<>();
        private ArrayList<Owner> owners = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);

            @Override
            public void displayMenu () {
                System.out.println("1. Add pet");
                System.out.println("2. view pet");
                System.out.println("3. Add Owner");
                System.out.println("4. view Owner");
                System.out.println("0. Exit");
            }
            @Override
    public void run(){
                boolean running = true;
                while (running) {
                displayMenu();
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice){
                        case 1:
                            addPet();
                            break;
                        case 2:
                            viewPets();
                            break;
                        case 3 :
                            addOwner();
                            break;
                        case 4:
                            viewOwners();
                            break;
                        default:
                            throw new  InvalidInputException("invalid choice");

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addPet() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        pets.add(new Pet(name, type, age));
    }

    private void viewPets() {
        for (int i = 0; i < pets.size(); i++) {
            System.out.println(i + " " + pets.get(i).getInfo());
        }
    }

    private void addOwner() {
        if (pets.isEmpty()) {
            throw new IllegalArgumentException("No pets available");
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        viewPets();
        int index = Integer.parseInt(scanner.nextLine());

        owners.add(new Owner(name, phone, address, pets.get(index)));
    }

    private void viewOwners() {
        for (Owner o : owners) {
            o.showInfo();
        }
    }
}
