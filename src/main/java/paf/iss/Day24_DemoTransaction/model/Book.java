package paf.iss.Day24_DemoTransaction.model;

public class Book {
    
    private Integer id;

    private String title;

    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Book() {
    }

    public Book(Integer id, String title, Integer quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }
    
}
