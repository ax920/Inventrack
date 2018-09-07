import java.math.BigDecimal;

public class item {
    private String name;
    private int id;
    private BigDecimal listPrice;
    private BigDecimal sellPrice;
    private int quantity;
    private String typeOfItem;
    private String dateAdded;

    public item(String name, int id, BigDecimal listPrice, BigDecimal sellPrice, int quantity, String typeOfItem, String dateAdded) {
        super();
        this.name = name;
        this.id = id;
        this.listPrice = listPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.typeOfItem = typeOfItem;
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTypeOfItem() {
        return typeOfItem;
    }

    public String getDateAdded() {
        return dateAdded;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setListPrice(BigDecimal listPrice){
        this.listPrice = listPrice;
    }
    public void setSellPrice(BigDecimal sellPrice){
        this.sellPrice = sellPrice;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setTypeOfItem(String typeOfItem){
        this.typeOfItem = typeOfItem;
    }
    public void setDateAdded(String dateAdded){
        this.dateAdded = dateAdded;
    }

}

