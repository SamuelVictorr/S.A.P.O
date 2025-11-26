package api;

public class Schedule {
    private int idSchedule;
    private String diahora;
    private String typeTreatment;
    private String nameDentist;
    private String idDentista;
    private String nameClient;
    private String statusTreatment;
    private String details;

    public Schedule(int idSchedule, String diaHora, String typeTreatment, String nameDentist, String idDentista ,String idClient, String statusTreatment, String details) {
        this.idSchedule = idSchedule;
        this.diahora = diaHora;
        this.typeTreatment= typeTreatment;
        this.nameDentist = nameDentist;
        this.idDentista = idDentista;
        this.nameClient = idClient;
        this.statusTreatment = statusTreatment;
        this.details = details;

    }

    // Getters
    public int getIdSchedule() { return idSchedule; }
    public String getDiaHora() { return diahora; }
    public String getTypeTreatment() { return typeTreatment; }
    public String getNameDentist() { return nameDentist; }
    public String getIdClient() { return nameClient; }
    public String getStatusTreatment() { return statusTreatment; }
    public String getDetails() { return details; }
    public String getIdDentista() { return idDentista; }

}
