package br.newtonpaiva.dominio;

import java.util.ArrayList;
import java.util.Objects;

public class Pedido {
    private Integer idPedido;
    private Boolean hasBorda;
    private Cliente cliente;
    private Integer qntPizzas;
    private Cardapio pedidoPizza;
    private Double precoTotal;
    public ArrayList<Ingredient> adicionais;

    public Double calcTotal(){
        precoTotal = 0.0;

        for (Ingredient tempIng : adicionais){
            precoTotal += tempIng.getPreco();
        }
        precoTotal += pedidoPizza.getPreco();

        if (hasBorda){
            precoTotal+=5.0;
        }

        return precoTotal;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
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

    public Cardapio getPedidoPizza() {
        return pedidoPizza;
    }

    public void setPedidoPizza(Cardapio pedidoPizza) {
        this.pedidoPizza = pedidoPizza;
    }

    public Integer getQntPizzas() {
        return qntPizzas;
    }

    public void setQntPizzas(Integer qntPizzas) {
        this.qntPizzas = qntPizzas;
    }

    public void addAdicionais(Ingredient i){
        adicionais.add(i);
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
        this.adicionais = new ArrayList<Ingredient>();
    }

    public Pedido(Integer idPedido, Cliente cliente, ArrayList<Ingredient> adicionais) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        if (adicionais!=null){
            this.adicionais = adicionais;
        }else {
            this.adicionais = new ArrayList<Ingredient>();
        }
    }
}
