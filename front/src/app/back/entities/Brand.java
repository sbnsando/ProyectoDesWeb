package app.back.entities;

public class Brand {
    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }

    private int id;

    private  String name;

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
}
