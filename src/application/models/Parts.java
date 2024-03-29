package application.models;

public class Parts {

    private String id;
    private String name;
    private int quntitie;
    private String description;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuntitie() {
        return quntitie;
    }

    public void setQuntitie(int quntitie) {
        this.quntitie = quntitie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Parts(String id, String name, int quntitie, String description, int price) {
        this.id = id;
        this.name = name;
        this.quntitie = quntitie;
        this.description = description;
        this.price = price;
    }

    public Parts() {

    }

}
