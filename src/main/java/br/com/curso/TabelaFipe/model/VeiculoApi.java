package br.com.curso.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VeiculoApi(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer ano
) {
//    @Override
//    public String toString() {
//        return "valor = '" + valor + '\'' +
//                ", marca = '" + marca + '\'' +
//                ", modelo = '" + modelo + '\'' +
//                ", ano = " + ano;
//    }
}
