package BO;

import javax.persistence.*;

/**
 * Created by Teddy on 2016-11-18.
 */
@Entity
@Table(name = "ORDER_TABLE")
public class Order {
    private int id;
    private String address;
    private Customer customer;

    @Id
    @Column(name = "ORDER_ID")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Column(name="SHIPPING_ADDRESS")
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @ManyToOne()
    @JoinColumn(name = "CUSTOMER_ID")
    public Customer getCustoer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    private class Customer{

    }
}
