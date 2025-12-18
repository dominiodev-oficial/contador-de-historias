package br.com.dominiodev.contador_de_historias;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Criador {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu inicial");
        System.out.println("[1] Iniciar nova história");
        System.out.println("[2] Carregar existente");
        System.out.println("Digite sua escolha: ");
        int escolha = Integer.parseInt(sc.nextLine());
        Interacao interacaoInicial = escolha == 1 ? criarInteracao(sc) : carrecarInteracaoInicial(sc);
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
            System.out.println("[0] Fechar");
            System.out.println("[1] Lista de interacoes");
            System.out.println("[2] Entrar na interacao");
            System.out.println("[3] Sair da interacao");
            System.out.println("[4] Incluir interacao");
            System.out.println("[5] Excluir interacao");
            System.out.println("[6] Salvar historia");
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
            } else if (menuSelecionado == 6) {
                salvarHistoria(sc, interacaoInicial);
            }
        }
    }

    private static void listaDeInteracoes(Interacao interacaoSelecionada) {
        System.out.println("Lista de interacoes");
        if (interacaoSelecionada.getOpcoesParaEscolha().isEmpty()) {
            System.out.println("=================================================");
            System.out.println("Nenhuma interacao foi encontrada");
            System.out.println("=================================================");
        }
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
        if (interacaoSelecionada.getOpcoesParaEscolha().isEmpty()) {
            return interacaoSelecionada;
        }
        System.out.println("Digite o código da interacao: ");
        int codigoInteracao = Integer.parseInt(sc.nextLine());
        return interacaoSelecionada.getOpcoesParaEscolha().get(codigoInteracao);
    }

    private static Interacao sairDaInteracao(Interacao interacaoInicial) {
        System.out.println("=================================================");
        System.out.println("Retornando para a interacao inicial.");
        System.out.println("=================================================");
        return interacaoInicial;
    }

    private static void incluirInteracao(Scanner sc, Interacao interacaoSelecionada) {
        System.out.println("Nova interacao");
        Interacao novaInteracao = criarInteracao(sc);
        interacaoSelecionada.adicionarInteracao(novaInteracao);
        System.out.println("Interacao incluida com sucesso!");
    }

    private static void excluirInteracao(Scanner sc, Interacao interacaoSelecionada) {
        listaDeInteracoes(interacaoSelecionada);
        if (interacaoSelecionada.getOpcoesParaEscolha().isEmpty()) {
            return;
        }
        System.out.println("Digite o código da interacao que deseja excluir: ");
        int codigoInteracao = Integer.parseInt(sc.nextLine());
        interacaoSelecionada.excluirInteracao(codigoInteracao);
        System.out.println("Interacao excluida com sucesso!");
    }

    private static void salvarHistoria(Scanner sc, Interacao interacaoInicial) {
        try {
            System.out.println("Informe o nome do arquivo que será salvo: ");
            String nomeArquivo = sc.nextLine();

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(nomeArquivo + ".json"), interacaoInicial);

            System.out.println("Historia salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar historia: " + e.getMessage());
        }
    }

    private static Interacao carrecarInteracaoInicial(Scanner sc) throws Exception {
        try {
            System.out.println("Informe o nome do arquivo que deseja carregar: ");
            String nomeArquivo = sc.nextLine();
            ObjectMapper mapper = new ObjectMapper();
            Interacao interacaoInicial = mapper.readValue(new File(nomeArquivo + ".json"), Interacao.class);
            System.out.println("Interação carregada com sucesso!");
            return  interacaoInicial;
        } catch (Exception e) {
            System.out.println("Erro ao carregar interacao: " + e.getMessage());
            throw e;
        }
    }
}
