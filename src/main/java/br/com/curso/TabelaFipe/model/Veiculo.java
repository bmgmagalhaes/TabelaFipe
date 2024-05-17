package br.com.curso.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Veiculo {

    private double valor;
    private String marca;
    private String modelo;
    private int ano;

    public Veiculo(VeiculoApi veiculoApi) {

        var valor = veiculoApi.valor().replace("R$ ", "");
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        this.valor = Double.parseDouble(valor);

        this.marca = veiculoApi.marca();
        this.modelo = veiculoApi.modelo();
        this.ano = veiculoApi.ano();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "("+getAno()+") = R$ "+ getValor();
    }
}
