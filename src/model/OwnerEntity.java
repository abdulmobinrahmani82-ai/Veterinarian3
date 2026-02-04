package model;

public class OwnerEntity {

    private int id;        // database auto-generated
    private String name;
    private String phone;
    private String address;
    private int age;

    // Constructor without ID (for insert)
    public OwnerEntity(String name, String phone, String address, int age) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    // Constructor with ID (for update / retrieve)
    public OwnerEntity(int id, String name, String phone, String address, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "OwnerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
