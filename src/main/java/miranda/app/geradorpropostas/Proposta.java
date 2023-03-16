package miranda.app.geradorpropostas;

public class Proposta {

    public Proposta(String nome, String status, String documento, String id) {
        this.nome = nome;
        this.status = status;
        this.documento = documento;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "nome='" + nome + '\'' +
                ", status='" + status + '\'' +
                ", documento='" + documento + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String nome;
    private String status ;
    private String documento;
    private String id;
}
