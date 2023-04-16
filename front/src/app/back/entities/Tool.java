package app.back.entities;

public class Tool {
    public Tool() {
    }

    public Tool(String brand, String name, String description, int price, String img) {
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    private int id;

    private String brand;

    private  String name;

    private String description;

    private int price;

    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setIdBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
