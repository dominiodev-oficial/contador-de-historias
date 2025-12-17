package br.com.dominiodev.contador_de_historias;

import java.util.ArrayList;
import java.util.Scanner;

public class Criador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Interacao interacaoInicial = criarInteracao(sc);
        mostrarMenu(sc,  interacaoInicial);
    }

    private static Interacao criarInteracao(Scanner sc) {
        System.out.println("Informe o título ativador da interação: ");
        String tituloAtivador = sc.nextLine();

        System.out.println("Informe o texto narrativo da interação: ");
        String textoNarrativo = sc.nextLine();

        return Interacao.builder()
                .withTituloAtivador(tituloAtivador)
                .withTextoNarrativo(textoNarrativo)
                .withOpcoesParaEscolha(new ArrayList<>());
    }

    private static void mostrarMenu(Scanner sc, Interacao interacaoInicial) {
        int menuSelecionado = -1;
        Interacao interacaoSelecionada = interacaoInicial;
        while (menuSelecionado != 0) {
            System.out.println("Menu");
            System.out.println("0. Fechar");
            System.out.println("1. Lista de interacoes");
            System.out.println("2. Entrar na interacao");
            System.out.println("3. Sair da interacao");
            System.out.println("4. Incluir interacao");
            System.out.println("5. Excluir interacao");
            System.out.println("Digite sua escolha: ");
            menuSelecionado = Integer.parseInt(sc.nextLine());

            if (menuSelecionado == 1) {
                listaDeInteracoes(interacaoSelecionada);
            } else if (menuSelecionado == 2) {
                interacaoSelecionada = entrarNaInteracao(sc, interacaoSelecionada);
            } else if (menuSelecionado == 3) {
                interacaoSelecionada = sairDaInteracao(interacaoInicial);
            } else if (menuSelecionado == 4) {
                incluirInteracao(sc, interacaoSelecionada);
            } else if (menuSelecionado == 5) {
                excluirInteracao(sc, interacaoSelecionada);
            }
        }
    }

    private static void listaDeInteracoes(Interacao interacaoSelecionada) {
        System.out.println("Lista de interacoes");
        interacaoSelecionada.getOpcoesParaEscolha().forEach(interacao -> {
            System.out.println("=================================================");
            System.out.println("Código: " + interacaoSelecionada.getOpcoesParaEscolha().indexOf(interacao));
            System.out.println("Título ativador: " + interacao.getTituloAtivador());
            System.out.println("Texto narrativo: " + interacao.getTextoNarrativo());
            System.out.println("=================================================");
        });
    }

    private static Interacao entrarNaInteracao(Scanner sc, Interacao interacaoSelecionada) {
        listaDeInteracoes(interacaoSelecionada);
        System.out.println("Digite o código da interacao: ");
        int codigoInteracao = Integer.parseInt(sc.nextLine());
        return interacaoSelecionada.getOpcoesParaEscolha().get(codigoInteracao);
    }

    private static Interacao sairDaInteracao(Interacao interacaoInicial) {
        System.out.println("Retornando para a interacao inicial.");
        return interacaoInicial;
    }

    private static void incluirInteracao(Scanner sc, Interacao interacaoSelecionada) {
        System.out.println("Nota interacao");
        Interacao novaInteracao = criarInteracao(sc);
        interacaoSelecionada.adicionarInteracao(novaInteracao);
        System.out.println("Interacao incluida com sucesso!");
    }

    private static void excluirInteracao(Scanner sc, Interacao interacaoSelecionada) {
        listaDeInteracoes(interacaoSelecionada);
        System.out.println("Digite o código da interacao que deseja excluir: ");
        int codigoInteracao = Integer.parseInt(sc.nextLine());
        interacaoSelecionada.excluirInteracao(codigoInteracao);
        System.out.println("Interacao excluida com sucesso!");
    }
}
