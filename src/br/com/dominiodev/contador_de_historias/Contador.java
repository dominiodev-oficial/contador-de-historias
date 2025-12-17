package br.com.dominiodev.contador_de_historias;

public class Contador {
    public Interacao inicializar() {
        return Interacao.builder()
                .withTituloAtivador("Iniciar");
    }
}
