package cn.com.testjquery.modle;

import java.io.Serializable;

public class Product implements Serializable
{
    
    private static final long serialVersionUID = -7153587980807032085L;
    
    private String proName;
    
    private int price;
    
    private int quantity;
    
    public Product()
    {
        super();
    }
    
    public Product(String proName, int price, int quantity)
    {
        super();
        this.proName = proName;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getProName()
    {
        return proName;
    }
    
    public void setProName(String proName)
    {
        this.proName = proName;
    }
    
    public int getPrice()
    {
        return price;
    }
    
    public void setPrice(int price)
    {
        this.price = price;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    
    public int totalPrice()
    {
        return this.quantity * this.price;
    }
    
}
