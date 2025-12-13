package api;

public class Schedule {
    private int idSchedule;
    private String diahora;
    private String typeTreatment;
    private String nameDentist;
    private String idDentista;
    private String statusTreatment;
    private String details;
    private String nameClient;

    //Constructor, it set format to the class itself pattern
    public Schedule(int idSchedule, String diaHora, String nameDentist, String statusTreatment, String treatmentType, String details, String nameClient) {
        this.idSchedule = idSchedule;
        this.diahora = diaHora;
        this.nameDentist = nameDentist;
        this.statusTreatment = statusTreatment;
        this.typeTreatment= treatmentType;
        this.details = details;
        this.nameClient = nameClient;
    }

    // Getters
    public int getIdSchedule() { return idSchedule; }
    public String getDiaHora() { return diahora; }
    public String getTypeTreatment() { return typeTreatment; }
    public String getNameDentist() { return nameDentist; }
    public String getStatusTreatment() { return statusTreatment; }
    public String getDetails() { return details; }
    public String getNameClient() { return  nameClient; }

}