package br.newtonpaiva.dominio;

import java.util.ArrayList;
import java.util.Objects;

public class Cardapio {
    private Integer idPizza;
    private String nomePizza;
    private Integer[] quantidades;
    private ArrayList<Ingredient> ingredientes;
    private Double preco;

    public void addIngredienteNQnt(Integer ingredientID, Integer quantidade){
        quantidades[ingredientID] = quantidade;
    }

    public Integer getIdPizza() {
        return idPizza;
    }

    public Integer[] getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(Integer[] quantidades) {
        this.quantidades = quantidades;
    }

    public void addIngrediente(Ingredient ingredient){
        ingredientes.add(ingredient);
    }

    public void setIdPizza(Integer idPizza) {
        this.idPizza = idPizza;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
        for (Ingredient temp : ingredientes){
            this.preco += temp.getPreco();
        }
    }

    public String getNomePizza() {
        return nomePizza;
    }

    public void setNomePizza(String nomePizza) {
        this.nomePizza = nomePizza;
    }

    public Cardapio() {
        this.ingredientes = new ArrayList<Ingredient>();
        this.idPizza = null;
        this.nomePizza = null;
        this.quantidades = new Integer[Ingredient.getCountIngredients()];

    }

    public Cardapio(Integer idPizza, String nomePizza, ArrayList<Ingredient> ingredientes, String imagem) {
        this.idPizza = idPizza;
        this.nomePizza = nomePizza;
        this.quantidades = new Integer[Ingredient.getCountIngredients()];
        if(ingredientes!=null){
            this.ingredientes = ingredientes;
        }else {
            this.ingredientes = new ArrayList<Ingredient>();
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cardapio cardapio = (Cardapio) o;
        return Objects.equals(idPizza, cardapio.idPizza) && Objects.equals(nomePizza, cardapio.nomePizza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPizza, nomePizza);
    }
}
