package model;

public abstract class Person {
    protected String name;
    protected String phone;

    public Person(String name, String phone) {
        setName(name);
        setPhone(phone);
    }

    public abstract String getRole();

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Invalid phone");
        }
        this.phone = phone;
    }
}
