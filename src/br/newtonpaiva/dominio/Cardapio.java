package br.newtonpaiva.dominio;

import java.util.ArrayList;
import java.util.Objects;

public class Cardapio {
    private Integer idPizza;
    private String nomePizza;
    private Integer[] quantidades;
    private ArrayList<Ingredient> ingredientes;
    private Double preco;
    private String imagem;

    public void addIngredienteNQnt(Ingredient temp, Integer quantidade){
        quantidades = new Integer[ingredientes.size()];
        for (Ingredient ing : ingredientes){
            if (ing.equals(temp.getIdIngrediente())){
                quantidades[ing.getIdIngrediente()] = quantidade;
            }
        }
    }

    public Integer getIdPizza() {
        return idPizza;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNomePizza() {
        return nomePizza;
    }

    public void setNomePizza(String nomePizza) {
        this.nomePizza = nomePizza;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Cardapio() {
        this.ingredientes = new ArrayList<Ingredient>();
        this.idPizza = null;
        this.imagem = null;
    }

    public Cardapio(Integer idPizza, String nomePizza, ArrayList<Ingredient> ingredientes, String imagem) {
        this.idPizza = idPizza;
        this.nomePizza = nomePizza;
        if(ingredientes!=null){
            this.ingredientes = ingredientes;
        }else {
            this.ingredientes = new ArrayList<Ingredient>();
        }
        this.imagem = imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cardapio cardapio = (Cardapio) o;
        return Objects.equals(idPizza, cardapio.idPizza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPizza);
    }
}
