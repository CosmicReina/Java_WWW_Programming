package model;

public class ItemCart {

    private Book book;
    private int quantity;

    public ItemCart() {
    }

    public ItemCart(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemCart{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
