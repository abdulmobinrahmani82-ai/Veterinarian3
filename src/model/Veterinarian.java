package model;

public class Veterinarian extends Person {
    private String specialization;

    public Veterinarian(String name, String phone, String specialization) {
        super(name, phone);
        setSpecialization(specialization);
    }

    @Override
    public String getRole() {
        return "Veterinarian";
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Invalid specialization");
        }
        this.specialization = specialization;
    }
}
