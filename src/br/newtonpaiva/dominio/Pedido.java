package br.newtonpaiva.dominio;

import java.util.ArrayList;
import java.util.Objects;

public class Pedido {
    private Integer idPedido;
    private Boolean hasBorda;
    private Cliente cliente;
    private Integer[] qntAdicionais;
    private Integer qntPizzas;
    private ArrayList<Ingredient> adicionais;

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
