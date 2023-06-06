package br.newtonpaiva.dominio;

import java.util.Objects;

public class Ingredient {

    private Integer idIngrediente;
    private String name;
    private Double preco;
    public static int count = 0;

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Ingredient(Integer idIngrediente, String name) {
        this.idIngrediente = idIngrediente;
        this.name = name;
        count++;
    }

    public Ingredient() {
        this.idIngrediente = null;
        this.name = null;
        this.preco = null;
        count++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public boolean comparaID(Integer o) {
        if (this.idIngrediente.equals(o)) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(idIngrediente, that.idIngrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngrediente);
    }

    public static int getCountIngredients() {
        return count;
    }
}
