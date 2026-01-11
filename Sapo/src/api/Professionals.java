package api;

public class Professionals {
    private String nameProfessional;
    private Integer clinicId;
    private String cro;
    private String professionalCpf;
    private String birthdate;

    public Professionals(String nameProfessional, String professionalCpf, String cro, String birthdate) {
        this.nameProfessional = nameProfessional;
        this.professionalCpf = professionalCpf;
        this.cro = cro;
        this.birthdate = birthdate;
    }

    public String getNameProfessional() { return nameProfessional; }
    public String getCro() { return cro; }
    public String getProfessionalCpf() { return professionalCpf; }
    public String getBirthdate() { return birthdate;}
    public Integer getClinicId() {return clinicId;}
}
