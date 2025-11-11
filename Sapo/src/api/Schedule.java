package api;

public class Schedule {
    private int idSchedule;
    private String diaHora;
    private String typeTreatment;
    private String nameDentist;
    private String idClinic;
    private String idClient;
    private String statusTreatment;
    private String details;

    public Schedule(int idSchedule, String diaHora, String typeTreatment, String nameDentist, String idClinic, String idClient, String statusTreatment, String details) {
        this.idSchedule = idSchedule;
        this.diaHora = diaHora;
        this.typeTreatment= typeTreatment;
        this.nameDentist = nameDentist;
        this.idClinic = idClinic;
        this.nameDentist = idClinic;
        this.idClient = idClient;
        this.statusTreatment = statusTreatment;
        this.details = details;

    }

    // Getters
    public int getIdSchedule() { return idSchedule; }
    public String getDiaHora() { return diaHora; }
    public String getTypeTreatment() { return typeTreatment; }
    public String getNameDentist() { return nameDentist; }
    public String getIdClinic() { return idClinic; }
    public String getIdClient() { return idClient; }
    public String getStatusTreatment() { return statusTreatment; }
    public String getDetails() { return details; }

    @Override
    public String toString() {
        return idSchedule + " | " + diaHora + " | " + typeTreatment + " | " + nameDentist + " | " + idClinic + " | " + idClient + " | " + statusTreatment + " | " + details;
    }
}
