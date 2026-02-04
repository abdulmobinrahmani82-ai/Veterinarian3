package model;

public class PetEntity {
    private int id;
    private String name;
    private String type;
    private int age;

    public PetEntity(int id, String name, String type, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Type: " + type + " | Age: " + age;
    }
}
