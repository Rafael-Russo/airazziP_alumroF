package br.newtonpaiva.dominio;

import javax.swing.*;
import java.util.Objects;

public class Pizza {
    private Integer idPizza;
    private String nomePizza;
    private JList<Ingredient> Ingredientes;
    private String imagem;

    public Pizza() {
        this.idPizza = null;
        this.nomePizza = null;
        Ingredientes = new JList<Ingredient>();
        this.imagem = null;
    }

    public Pizza(Integer idPizza, String nomePizza, String imagem) {
        this.idPizza = idPizza;
        this.nomePizza = nomePizza;
        Ingredientes = new JList<Ingredient>();
        this.imagem = imagem;
    }

    public Integer getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(Integer idPizza) {
        this.idPizza = idPizza;
    }

    public String getNomePizza() {
        return nomePizza;
    }

    public void setNomePizza(String nomePizza) {
        this.nomePizza = nomePizza;
    }

    public JList<Ingredient> getIngredientes() {
        return Ingredientes;
    }

    public void addIngredientes(JList<Ingredient> ingrediente) {
        Ingredientes.add(ingrediente);
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(idPizza, pizza.idPizza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPizza);
    }
}
