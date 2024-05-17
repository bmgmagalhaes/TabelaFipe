package br.com.curso.TabelaFipe.service;

import java.util.List;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterListaDeDados(String json, Class<T> classe);
}
