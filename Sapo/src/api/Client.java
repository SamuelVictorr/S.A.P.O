package api;

public class Client {
    private int id;
    private String name;
    private String cpf;
    private String telefone;
    private String observacao;

    public Client(int id, String name, String cpf, String telefone, String observacao) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.telefone = telefone;
        this.observacao = observacao;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getObservacao() { return observacao; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + cpf + " | " + telefone + " | " + observacao;
    }
}
