package api;

public class Client {
    private int id;
    private String name;
    private String cpf;
    private String telefone;
    private String observacao;
    private String activeStatus;
    private String birthDate;
    private String clinicId;

    public Client(int id, String name, String cpf, String telefone, String observacao , String activeStatus,  String birthDate, String clinicId ) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.telefone = telefone;
        this.observacao = observacao;
        this.activeStatus = activeStatus;
        this.birthDate = birthDate;
        this.clinicId = clinicId;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getObservacao() { return observacao; }
    public String getActiveStatus() { return activeStatus; }
    public String getBirthDate() { return birthDate; }
    public String getClinicId() { return clinicId; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + cpf + " | " + telefone + " | " + observacao;
    }
}
