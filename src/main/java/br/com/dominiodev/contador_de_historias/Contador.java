package br.com.dominiodev.contador_de_historias;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Contador {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Interacao interacaoInicial = carregarHistoria(sc);
        mostrarMenuInicial(sc, interacaoInicial);

    }

    private static Interacao carregarHistoria(Scanner sc) throws IOException {
        System.out.println("Digite o nome do arquivo da histórico que deseja carregar: ");
        String nomeArquivo = sc.nextLine();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(nomeArquivo + ".json"), Interacao.class);
    }

    private static void mostrarMenuInicial(Scanner sc, Interacao interacaoInicial) {
        System.out.println("Menu inicial");
        System.out.println("[0] Sair");
        System.out.println("[1] " + interacaoInicial.getTituloAtivador());
        System.out.println("Digite sua escolha: ");
        int escolha = Integer.parseInt(sc.nextLine());
        if (escolha == 1) {
            executarInteracao(sc, interacaoInicial);
        }
    }

    private static void executarInteracao(Scanner sc, Interacao interacaoInicial) {
        System.out.println("Narrativa: ");
        System.out.println(interacaoInicial.getTextoNarrativo());
        System.out.println("Ações");
        System.out.println("[0] Sair");
        interacaoInicial.getOpcoesParaEscolha().forEach(interacao -> {
            System.out.println("[" + (interacaoInicial.getOpcoesParaEscolha().indexOf(interacao) + 1) + "] " +  interacao.getTituloAtivador());
        });
        System.out.println("Digite sua escolha: ");
        int escolha = Integer.parseInt(sc.nextLine());
        if (escolha != 0) {
            Interacao interacaoSelecionada = interacaoInicial.getOpcoesParaEscolha().get(escolha - 1);
            executarInteracao(sc, interacaoSelecionada);
        }
    }
}
