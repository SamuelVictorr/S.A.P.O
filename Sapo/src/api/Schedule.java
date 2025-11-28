package api;

public class Schedule {
    private int idSchedule;
    private String diahora;
    private String typeTreatment;
    private String nameDentist;
    private String idDentista;
    private Client client;
    private String statusTreatment;
    private String details;
    private String nameClient;

    public Schedule(int idSchedule, String diaHora, Client client, String nameDentist, String statusTreatment, String treatmentType, String details, String idDentista, String nameClient) {
        this.idSchedule = idSchedule;
        this.diahora = diaHora;
        this.client = client;
        this.nameDentist = nameDentist;
        this.statusTreatment = statusTreatment;
        this.typeTreatment= treatmentType;
        this.details = details;
        this.idDentista = idDentista;
        this.nameClient = nameClient;
    }

    // Getters
    public int getIdSchedule() { return idSchedule; }
    public String getDiaHora() { return diahora; }
    public String getTypeTreatment() { return typeTreatment; }
    public String getNameDentist() { return nameDentist; }
    public Client getClient() { return client; }
    public String getStatusTreatment() { return statusTreatment; }
    public String getDetails() { return details; }
    public String getIdDentista() { return idDentista; }
    public String getNameClient() { return  nameClient; }

}
