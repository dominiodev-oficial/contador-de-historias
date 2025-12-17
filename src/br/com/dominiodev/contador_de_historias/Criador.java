package br.com.dominiodev.contador_de_historias;

import java.util.ArrayList;
import java.util.Scanner;

public class Criador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o título ativador da primeira interação: ");
        String tituloAtivador = sc.nextLine();

        System.out.println("Informe o texto narrativo da primeira interação: ");
        String textoNarrativo = sc.nextLine();

        Interacao interacaoInicial = Interacao.builder()
                .withTituloAtivador(tituloAtivador)
                .withTextoNarrativo(textoNarrativo)
                .withOpcoesParaEscolha(new ArrayList<>());


    }
}
