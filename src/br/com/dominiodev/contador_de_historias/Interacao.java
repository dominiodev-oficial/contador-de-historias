package br.com.dominiodev.contador_de_historias;

import java.util.List;

public class Interacao {
    private String tituloAtivador; // Exemplo (iniciar, aceitar, recusar) ele vai ativar essa interacao
    private String textoNarrativo; // Texto que vai aparecer para narrar a história, Exemplo: Lucas e seus amigos se encontram no restaurante
    private List<Interacao> opcoesParaEscolha; // Exemplo (continuar ou parar) exibe o tituloAtivador de cada Interação possível

    public static Interacao builder() {
        return new Interacao();
    }

    public Interacao withTituloAtivador(String tituloAtivador) {
        this.tituloAtivador = tituloAtivador;
        return this;
    }

    public Interacao withTextoNarrativo(String textoNarrativo) {
        this.textoNarrativo = textoNarrativo;
        return this;
    }

    public Interacao withOpcoesParaEscolha(List<Interacao> opcoesParaEscolha) {
        this.opcoesParaEscolha = opcoesParaEscolha;
        return this;
    }
}
