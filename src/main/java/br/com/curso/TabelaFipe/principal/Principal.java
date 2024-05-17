package br.com.curso.TabelaFipe.principal;

import br.com.curso.TabelaFipe.model.Dados;
import br.com.curso.TabelaFipe.model.Modelo;
import br.com.curso.TabelaFipe.model.Veiculo;
import br.com.curso.TabelaFipe.model.VeiculoApi;
import br.com.curso.TabelaFipe.service.ConsumoApi;
import br.com.curso.TabelaFipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO_API = "https://parallelum.com.br/fipe/api/v1/";

    private Scanner leitor = new Scanner(System.in);

    public void exibeMenu(){

        String endereco = ENDERECO_API;


        // OBTENDO O PARÂMETRO TIPO DE VEÍCULO
        System.out.print("Escolha entre: Carro, Motos ou Caminhoes: ");
        var opcao = leitor.nextLine().toLowerCase();


        if (opcao.contains("car")){
            endereco += "carros/marcas";
        } else if (opcao.contains("mot")){
            endereco += "motos/marcas";
        } else {
            endereco += "caminhoes/marcas";
        }

        var json = consumoApi.obterDados(endereco);

        var dados = conversor.obterListaDeDados(json, Dados.class);
        dados.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(m -> System.out.println("Cod ["+ m.codigo() +"]: "+m.nome()));


        System.out.print("Informe codigo do marca: : ");
        opcao = leitor.nextLine();


        endereco += "/"+opcao+"/modelos";
        json = consumoApi.obterDados(endereco);


        var dadosMarca = conversor.obterDados(json, Modelo.class);
        dadosMarca.modelos().stream()
                .map(m -> new Dados(m.codigo(), m.nome().toUpperCase()) )
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(m -> System.out.println("Cod ["+ m.codigo() +"]: "+m.nome()));

        System.out.println("Informe o nome do modelo");
        var nomeVeiculo = leitor.nextLine().toLowerCase();

        System.out.println("Modelos filtrados");
        dadosMarca.modelos().stream()
            .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo))
            .forEach(System.out::println);


        System.out.print("Informe código do modelo: ");
        opcao = leitor.nextLine();
        endereco += "/"+opcao+"/anos/";


        json = consumoApi.obterDados(endereco);
        var dadosModelo = conversor.obterListaDeDados(json, Dados.class);


        List<Veiculo> listaDeVeiculos = new ArrayList<>();


        for (Dados modelo : dadosModelo) {

            json = consumoApi.obterDados(endereco + modelo.codigo());
            Veiculo veiculo = new Veiculo(conversor.obterDados(json, VeiculoApi.class));
            listaDeVeiculos.add(veiculo);
        }

        if (!listaDeVeiculos.isEmpty()){
            var marca = listaDeVeiculos.stream().findFirst().get().getMarca();
            var modelo = listaDeVeiculos.stream().findFirst().get().getModelo();
            System.out.println();
            System.out.println(marca+" "+modelo);
            listaDeVeiculos.forEach(System.out::println);
        }

    }



}
