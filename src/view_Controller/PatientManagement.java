/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view_Controller;

import Model.PatientList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import Model.Patient;

/**
 *
 * @author ACER
 */
public class PatientManagement extends menu<String>{
    private Scanner sc = new Scanner(System.in);
    private PatientList patientlist = new PatientList();

    public PatientManagement() {
    }

    public PatientManagement(String td, String[] mc) {
        super(td, mc);
    }
            
    public void searchPatient() {
    String[] sO = {"Search By ID", "Search By Name", "Search By DOB", "Search By Phone"};
    menu<String> searchMenu = new menu<String>("Search Patient Option", sO) {
        @Override
        public void execute(int choice) {
            Predicate<Patient> predicate;
            String ip;
            switch(choice){
                case 1:
                    ip = getString("Enter Id: ");
                    predicate = p ->p.getPatientID().equalsIgnoreCase(ip);
                    break;
                case 2:
                    ip = getString("Enter Name: ");
                    predicate = p -> p.getFullname().equalsIgnoreCase(ip);
                    break;
                case 3:
                    ip = getString("Enter dob: ");
                    predicate = p -> p.getDateofbirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).equalsIgnoreCase(ip);
                    break;
                case 4:
                    ip = getString("Enter phone: ");
                    predicate = p -> p.getPhone().equalsIgnoreCase(ip);
                    break;
                default: 
                    displayMes("Invalid choice");
                    return;
                    
            }
            Patient result = patientlist.searchPatient(predicate);
            if (result != null) {
                System.out.println("Search result:");
                System.out.println(result);
            } else {
                System.out.println("No patient found with the given criteria.");
            }
        }
    };
        searchMenu.run();
    }
    
    public void addPatient(){
        String id = getString("Enter patient ID: ");
        String name = getString("Enter patient name: ");
        LocalDate dob = LocalDate.parse(getString("Enter date of birth (DD/MM/YYYY): "), DateTimeFormatter.ofPattern("dd/MM/yyyy") );
        String phone = getString("Enter phone number: ");
        patientlist.getPlist().add(new Patient(id, name, dob, phone)); 
        displayMes("Patient added successfully.");
    }
    
    public void updatePhoneNumber(){
        String oldphone = getString("Enter oldphone to update phone number: ");
        Predicate<Patient> predicate = p -> p.getPatientID().equalsIgnoreCase(oldphone);
        
        Patient result = patientlist.searchPatient(predicate);
        if (result != null) {
            String newPhone = getString("Enter new phone number: ");
            result.setPhone(oldphone, newPhone);
            displayMes("Phone number updated successfully.");
        } else {
            displayMes("No patient found with : " + oldphone);
        }
    }
    
     private void displayAllPatient() {
         try{
             patientlist.loadDataFromFile("patient.csv");
             for (Patient patient : patientlist.getPlist()) { 
                displayItem(patient);
            }
         } catch (Exception e){
             displayMes(e.getMessage());
         }
            
    }
    
    public String getString(String mes){
        System.out.println(mes);
        return sc.nextLine();
    }
    public void displayMes(String mes){
        System.out.println(mes);
    }
    public <T> void displayItem(T t){
        System.out.println(t.toString());
    }
    
    @Override
    public void execute(int ch) {
        switch (ch) {
            case 1:
                displayAllPatient();
                break;
            case 2:
                searchPatient();
                break;
            case 3:
                addPatient();
                break;
            case 4:
                updatePhoneNumber();
                break;
            case 5:
                displayMes("Exiting...");
                break;
            default:
                displayMes("Invalid choice. Please try again.");
                return;
        }
    }
    
    public static void main(String[] args) {
        String []choice = {"Display all patients", "Search patient", "Add patient", "Update phone number", "Quit"};
        PatientManagement pm  = new PatientManagement("Patients Management", choice);
        pm.run();
    }
}