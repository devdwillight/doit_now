
package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Predicate;

public class PatientList {
    final ArrayList<Patient> plist = new ArrayList<>();

    public PatientList() {
    }

    public ArrayList<Patient> getPlist() {
        return plist;
    }
    
    
    
    public void loadDataFromFile(String Filename) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(Filename));
        String line;
        while((line=br.readLine()) != null){
            String[] parts = line.split("//,");
            if (parts.length == 4){
            plist.add(new Patient(parts[0], parts[1], LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), parts[3]));
            }
            
        }
    }
    
    public Patient searchPatient(Predicate<Patient> predicate){
        for (Patient p : plist){
            if (predicate.test(p)){
                return p;
            }
        }
        return null;
    }
    
    public void addPatient(Patient newPatient){
        if (searchPatient(p -> p.getPhone().equals(newPatient.getPhone())) == null){
            plist.add(newPatient);
        }
    }
    
    
}