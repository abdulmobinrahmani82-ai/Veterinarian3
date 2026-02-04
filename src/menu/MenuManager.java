package menu;

import database.OwnerDAO;
import database.PetDAO;
import model.Pet;
import model.Owner;
import exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {
    private PetDAO petDAO = new PetDAO();
    private OwnerDAO ownerDAO = new OwnerDAO();
    private ArrayList<Pet> pets = new ArrayList<>();
    private ArrayList<Owner> owners = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("1. Add Pet");
        System.out.println("2. View Pets");
        System.out.println("3. Add Owner");
        System.out.println("4. View Owners");
        System.out.println("0. Exit");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addPet();
                        break;
                    case 2:
                        viewPets();
                        break;
                    case 3:
                        addOwner();
                        break;
                    case 4:
                        viewOwners();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        throw new InvalidInputException("Invalid choice");
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

        // ذخیره در دیتابیس (فعلاً ownerId را null می‌فرستیم)
        petDAO.insertPet(name, type, age, null);

        // اضافه کردن به لیست محلی برای نمایش موقت
        pets.add(new Pet(name, type, age));
    }

    private void viewPets() {
        // خواندن مستقیم از دیتابیس
        petDAO.viewAllPets();
    }

    private void addOwner() {
        try {
            // ۱. دریافت اطلاعات صاحب از کاربر
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Address: ");
            String address = scanner.nextLine();

            // ۲. منطق انتخاب حیوان (اگر لیستی دارید)
            Pet selectedPet = null;
            if (!pets.isEmpty()) {
                viewPets();
                System.out.print("Select Pet Index: ");
                int index = Integer.parseInt(scanner.nextLine());
                if (index >= 0 && index < pets.size()) {
                    selectedPet = pets.get(index);
                }
            } else {
                System.out.println("Notice: No pets available to link at this moment.");
            }

            // ۳. ذخیره در دیتابیس (بخش مربوط به هفته ۷)
            // استفاده از شیء ownerDAO که قبلاً تعریف کردی
            ownerDAO.insertOwner(name, phone, address);

            // ۴. اضافه کردن به لیست محلی (برای نمایش در لحظه در برنامه)
            owners.add(new Owner(name, phone, address, selectedPet));

            System.out.println("✅ Owner successfully registered in Database!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Please enter a valid number for the index.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected Error: " + e.getMessage());
        }
    }

    private void viewOwners() {
        ownerDAO.viewAllOwners();

             }

    }
