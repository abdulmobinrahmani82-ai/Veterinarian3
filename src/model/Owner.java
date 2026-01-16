package model;

public class Owner extends Person {
    private String address;
    private Pet pet;

    public Owner(String name, String phone, String address, Pet pet) {
        super(name, phone);
        setAddress(address);
        this.pet = pet;
    }

    @Override
    public String getRole() {
        return "Owner";
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Invalid address");
        }
        this.address = address;
    }

    public void showInfo() {
        System.out.println(name + " " + address + " " + pet.getInfo());
    }
}
