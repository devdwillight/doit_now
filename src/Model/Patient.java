
package Model;

import java.time.LocalDate;



public class Patient implements Comparable<Patient>{
    String PatientID, Fullname, Phone;
    LocalDate Dateofbirth;

    public Patient(String PatientID, String Fullname, LocalDate Dateofbirth, String Phone) {
        this.PatientID = PatientID;
        this.Fullname = Fullname;
        this.Phone = Phone;
        this.Dateofbirth = Dateofbirth;
    }

    public Patient() {
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone, String newPhone) {
        if (Phone.equalsIgnoreCase(this.Phone)){
            this.Phone = newPhone;
        }
    }

    public LocalDate getDateofbirth() {
        return Dateofbirth;
    }

    public void setDateofbirth(LocalDate Dateofbirth) {
        this.Dateofbirth = Dateofbirth;
    }


    @Override
    public String toString() {
        return "Patient{" + "PatientID=" + PatientID + ", Fullname=" + Fullname + ", Phone=" + Phone + ", Dateofbirth=" + Dateofbirth + '}';
    }

    @Override
    public int compareTo(Patient o) {
       return this.Dateofbirth.compareTo(o.Dateofbirth);
    }
    
    
    
}