package model;

public class Pet implements Treatable {
    private String name;
    private String type;
    private int age;

    public Pet(String name, String type, int age) {
        setName(name);
        setType(type);
        setAge(age);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid pet name");
        }
        this.name = name;
    }

    public void setType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Invalid pet type");
        }
        this.type = type;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid pet age");
        }
        this.age = age;
    }

    public String getInfo() {
        return name + " " + type + " " + age;
    }

    @Override
    public void treat() {
        System.out.println(name + " treated");
    }
}
