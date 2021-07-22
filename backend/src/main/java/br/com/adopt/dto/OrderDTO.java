package br.com.adopt.dto;

import br.com.adopt.entity.Order;

public class OrderDTO {

    private Long id;
    private String status;
    private String sku;
    private String description;
    private Double price;
    private String payerID;
    private String paymentId;
    private UserDTO user;

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.sku = order.getSku();
        this.description = order.getDescription();
        this.price = order.getPrice();
        this.payerID = order.getPayerID();
        this.paymentId = order.getPaymentId();
        this.user = new  UserDTO(order.getPerson().getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
