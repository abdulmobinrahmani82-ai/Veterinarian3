package menu;

import database.OwnerDAO;
import model.OwnerEntity;
import model.PetEntity;
import exception.InvalidInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final OwnerDAO ownerDAO = new OwnerDAO();
    private final Scanner scanner = new Scanner(System.in);

    // Temporary in-memory list for pets
    private final List<PetEntity> petList = new ArrayList<>();

    @Override
    public void displayMenu() {
        System.out.println("\n===== VETERINARIAN SYSTEM =====");
        System.out.println("1. Add Owner");
        System.out.println("2. View All Owners");
        System.out.println("3. Add Pet");
        System.out.println("4. View Pets");
        System.out.println("5. Update Owner");
        System.out.println("6. Delete Owner");
        System.out.println("7. Search by Name");
        System.out.println("8. Search by Min Age");
        System.out.println("9. Exit");
        System.out.print("Choose option: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: addOwner(); break;
                    case 2: viewOwners(); break;
                    case 3: addPet(); break;
                    case 4: viewPets(); break;
                    case 5: updateOwner(); break;
                    case 6: deleteOwner(); break;
                    case 7: searchOwnerByName(); break;
                    case 8: searchByMinAge(); break;
                    case 9:
                        running = false;
                        System.out.println("👋 Exiting program...");
                        break;
                    default:
                        throw new InvalidInputException("Invalid menu choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }

    // --- Owner Methods ---
    private void addOwner() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Address: ");
            String address = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            OwnerEntity owner = new OwnerEntity(id, name, phone, address, age);
            boolean success = ownerDAO.insertOwner(owner);

            if (success) {
                System.out.println("✅ Owner added successfully!");
            } else {
                System.out.println("❌ Failed to add owner. Maybe the ID already exists.");
            }

        } catch (NumberFormatException e) {
            System.out.println(" Please enter a valid number for ID and Age.");
        } catch (Exception e) {
            System.out.println(" Error while adding owner: " + e.getMessage());
        }
    }

    private void viewOwners() {
        ownerDAO.viewAllOwners();
    }

    private void updateOwner() {
        try {
            System.out.print("Enter Owner ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            OwnerEntity existing = ownerDAO.getOwnerById(id);
            if (existing == null) {
                System.out.println(" Owner not found.");
                return;
            }

            System.out.println("Current Info: " + existing.getName() + ", Age: " + existing.getAge());

            System.out.print("New Name [" + existing.getName() + "]: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) name = existing.getName();

            System.out.print("New Phone [" + existing.getPhone() + "]: ");
            String phone = scanner.nextLine();
            if (phone.trim().isEmpty()) phone = existing.getPhone();

            System.out.print("New Address [" + existing.getAddress() + "]: ");
            String address = scanner.nextLine();
            if (address.trim().isEmpty()) address = existing.getAddress();

            System.out.print("New Age [" + existing.getAge() + "]: ");
            String ageInput = scanner.nextLine();
            int age = existing.getAge();
            if (!ageInput.trim().isEmpty()) age = Integer.parseInt(ageInput);

            OwnerEntity updated = new OwnerEntity(id, name, phone, address, age);
            ownerDAO.updateOwner(updated);

        } catch (NumberFormatException e) {
            System.out.println(" Invalid input format.");
        } catch (Exception e) {
            System.out.println(" Error while updating owner: " + e.getMessage());
        }
    }

    private void deleteOwner() {
        try {
            System.out.print("Enter Owner ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            OwnerEntity owner = ownerDAO.getOwnerById(id);
            if (owner == null) {
                System.out.println(" Owner not found.");
                return;
            }

            System.out.println("Owner to be deleted: " + owner.getName());
            System.out.print("Are you sure? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                ownerDAO.deleteOwner(id);
                System.out.println("✅ Owner deleted successfully!");
            } else {
                System.out.println(" Deletion cancelled.");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid ID format.");
        }
    }

    private void searchOwnerByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        ownerDAO.searchByName(name);
    }

    private void searchByMinAge() {
        try {
            System.out.print("Enter minimum age: ");
            int minAge = Integer.parseInt(scanner.nextLine());
            ownerDAO.searchByMinAge(minAge);
        } catch (NumberFormatException e) {
            System.out.println(" Please enter a valid number.");
        }
    }

    // --- Pet Methods ---
    private void addPet() {
        try {
            System.out.print("Pet ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Pet Name: ");
            String name = scanner.nextLine();

            System.out.print("Pet Type (Dog/Cat/etc.): ");
            String type = scanner.nextLine();

            System.out.print("Pet Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            PetEntity pet = new PetEntity(id, name, type, age);
            petList.add(pet);

            System.out.println("✅ Pet added successfully!");
        } catch (NumberFormatException e) {
            System.out.println(" Please enter valid numbers for ID and Age.");
        } catch (Exception e) {
            System.out.println(" Error while adding pet: " + e.getMessage());
        }
    }

    private void viewPets() {
        if (petList.isEmpty()) {
            System.out.println("❌ No pets added yet.");
            return;
        }

        System.out.println("\n--- PET LIST ---");
        for (PetEntity pet : petList) {
            System.out.println(pet);
        }
    }
}
