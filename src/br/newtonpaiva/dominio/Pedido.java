package br.newtonpaiva.dominio;

import java.util.ArrayList;
import java.util.Objects;

public class Pedido {
    private Integer idPedido;
    private Boolean hasBorda;
    private Cliente cliente;
    private Integer[] qntAdicionais;
    private Integer[] qntPizzas;
    private Integer[][] qntTotal;
    private ArrayList<Ingredient> adicionais;
    private ArrayList<Cardapio> pizzas;

    public void addIngredienteNQnt(Ingredient temp, Integer qntAdicional){
        qntAdicionais = new Integer[adicionais.size()];
        for (Ingredient ing : adicionais){
            if (ing.equals(temp.getIdIngrediente())){
                qntAdicionais[ing.getIdIngrediente()] = qntAdicional;
            }
        }
    }

    public void addQntsPizzas(Cardapio temp, Integer qntPizza){
        qntPizzas = new Integer[pizzas.size()];
        for (Cardapio pizza : pizzas){
            if (pizza.equals(temp.getIdPizza())){
                qntPizzas[pizza.getIdPizza()] = qntPizza;
            }
        }
    }

    public void addQntTotal(){
        qntTotal = new Integer[qntPizzas.length][qntAdicionais.length];
        for(int x = 0; x<qntPizzas.length; x++){
            for (int y = 0; y<qntAdicionais.length; y++){
                qntTotal[x][y]++;
            }
        }
    }

    public Double calcTotal(){
        Double precoTotal;

        precoTotal = 0.0;

        return precoTotal;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Boolean getHasBorda() {
        return hasBorda;
    }

    public void setHasBorda(Boolean hasBorda) {
        this.hasBorda = hasBorda;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(idPedido, pedido.idPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido);
    }

    public Pedido() {
        this.idPedido = null;
        this.cliente = new Cliente();
        this.pizzas = new ArrayList<Cardapio>();
    }

    public Pedido(Integer idPedido, Cliente cliente, ArrayList<Cardapio> pizzas) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        if (pizzas!=null){
            this.pizzas = pizzas;
        }else {
            this.pizzas = new ArrayList<Cardapio>();
        }
    }
}
