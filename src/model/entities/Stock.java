package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String category;
    private Double valor;
    private Integer quantity;

    public Stock() {}

    public Stock(Integer id, String name, String category, Double valor, Integer quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.valor = valor;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
