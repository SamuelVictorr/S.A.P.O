package api;

public class Schedule {
    private int idSchedule;
    private String diahora;
    private String typeTreatment;
    private String nameDentist;
    private String idDentista;
    private String idClient;
    private String statusTreatment;
    private String details;

    public Schedule(int idSchedule, String diaHora, String idClient, String nameDentist, String statusTreatment, String typeTreatment, String details, String idDentista) {
        this.idSchedule = idSchedule;
        this.diahora = diaHora;
        this.typeTreatment= typeTreatment;
        this.nameDentist = nameDentist;
        this.idDentista = idDentista;
        this.idClient = idClient;
        this.statusTreatment = statusTreatment;
        this.details = details;
    }

    // Getters
    public int getIdSchedule() { return idSchedule; }
    public String getDiaHora() { return diahora; }
    public String getTypeTreatment() { return typeTreatment; }
    public String getNameDentist() { return nameDentist; }
    public String getIdClient() { return idClient; }
    public String getStatusTreatment() { return statusTreatment; }
    public String getDetails() { return details; }
    public String getIdDentista() { return idDentista; }

}
