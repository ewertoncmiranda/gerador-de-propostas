package miranda.app.geradorpropostas;

public class GeradorDePropostas {

    public static Proposta gerarPropostaAprovada(){
        return  new Proposta("Marquento de Sarava Cuiuyu", "REPROVADA","031.997.177-98","002");
    }

    public static Proposta gerarPropostaReprovada(){
        return  new Proposta("Rebeca Blantely di Piroky", "APROVADA","123.145.187-54","001");

    }



}
