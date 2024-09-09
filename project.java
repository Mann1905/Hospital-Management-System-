import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
class sleep {
    public static void a() {
        System.out.print("\t Welcome to ");
        for (int i = 0; i < 5; i++) {
            try {
                System.out.print(" . ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }
    public static void d() {
        System.out.print("\t Adding Details ");
        for (int i = 0; i < 4; i++) {
            try {
                System.out.print(" . ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }
    public static void h() {
        System.out.print("\t Exiting ");
        for (int i = 0; i < 4; i++) {
            try {
                System.out.print(" . ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }
    public static void g() {
        System.out.print("\t Conforming Appointment ");
        for (int i = 0; i < 5; i++) {
            try {
                System.out.print(" . ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }
    public static void f() {
        System.out.print("\t Opening Details ");
        for (int i = 0; i < 3; i++) {
            try {
                System.out.print(" . ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }
    public static void b() {
        System.out.print("");
        for (int i = 0; i < 3; i++) {
            try {
                System.out.print("");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }
    public static void c() {
        System.out.print("Opening patient portal");
        for (int i = 0; i < 2; i++) {
            try {
                System.out.print(" .");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }
    public static void e() {
        System.out.print("Opening doctor portal");
        for (int i = 0; i < 2; i++) {
            try {
                System.out.print(" .");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); 
    }
}


class AppointmentManager {
    private ArrayList<Appointment> appointments;
    private HashMap<String, Integer> appointmentCountByDate;

    public AppointmentManager() {
        appointments = new ArrayList<>();
        appointmentCountByDate = new HashMap<>();
    }
  
   
    public boolean cancelAppointment(String patientName, int appointmentIndex) {
        if (appointmentIndex < 0 || appointmentIndex >= appointments.size()) {
            System.out.println("Invalid appointment index.");
            return false;
        }
        
        Appointment appointment = appointments.get(appointmentIndex);
        if (appointment.getPatient().getName().equalsIgnoreCase(patientName)) {
            // Decrease appointment count for the canceled date
            appointmentCountByDate.put(appointment.getDate(), appointmentCountByDate.get(appointment.getDate()) - 1);
            appointments.remove(appointmentIndex);
            System.out.println("Appointment for " + patientName + " on " + appointment.getDate() + " has been canceled.");
            return true;
        }
        
        System.out.println("Appointment not found for " + patientName + " at the specified index.");
        return false;
    }
    
    public void listAppointmentsForPatient(String patientName) {
        boolean found = false;
        System.out.println("Appointments for " + patientName + ":");
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getPatient().getName().equalsIgnoreCase(patientName)) {
                System.out.println((i + 1) + ". " + appointment.getDate() + " with Dr. " + appointment.getDoctor().getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for " + patientName);
        }
    }
    
  
    
    public void scheduleAppointment(Patient patient, Doctor doctor, String date) {
        Scanner scanner = new Scanner(System.in);
    
        while (!isValidDate(date)) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            System.out.print("Enter appointment date (YYYY-MM-DD): ");
            date = scanner.nextLine();
        }
    
        // Check if the doctor has reached the maximum appointments for the given date
        int maxAppointmentsPerDoctorPerDate = 2;
        int appointmentsCount = 0;
    
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date) && appointment.getDoctor().getName().equalsIgnoreCase(doctor.getName())) {
                appointmentsCount++;
            }
        }
    
        if (appointmentsCount >= maxAppointmentsPerDoctorPerDate) {
            System.out.println("Maximum appointments reached for " + doctor.getName() + " on " + date + ". Please choose another date or doctor.");
            return;
        }
    
        // Schedule the appointment
        appointments.add(new Appointment(patient, doctor, date));
    
        // Update appointment count for the chosen date and doctor
        appointmentCountByDate.put(date, appointmentCountByDate.getOrDefault(date, 0) + 1);
    
        System.out.println("Appointment scheduled successfully!");
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date); // Attempt to parse the date
            return true; // Valid date format
        } catch (DateTimeParseException e) {
            return false; // Invalid date format
        }
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}

class Patient {
    private String name;
    private int age;
    private String phoneNumber;
    private String illness;

    public Patient(String name, int age, String phoneNumber, String illness) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.illness = illness;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIllness() {
        return illness;
    }
}




class Doctor {
    private String name;
    private String specialization;
    private String phoneNumber;

    public Doctor(String name, String specialization, String phoneNumber) {
        this.name = name;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}



class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(Patient patient, Doctor doctor, String date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }
}



class PatientManager {
    private ArrayList<Patient> patients;

    public PatientManager() {
        patients = new ArrayList<>();
    }

    public void addPatient(String name, int age, String phoneNumber, String illness) {
        while (!isValidPhoneNumber(phoneNumber)) {
            System.out.print("\t\t\t\t\tInvalid phone number. Please enter a 10-digit phone number: ");
            Scanner scanner = new Scanner(System.in);
            phoneNumber = scanner.nextLine();
        }
        patients.add(new Patient(name, age, phoneNumber, illness));
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }
}



class DoctorManager {
    private ArrayList<Doctor> doctors;

    public DoctorManager() {
        doctors = new ArrayList<>();
    }

    public void addDoctor(String name, String specialization, String phoneNumber) {
        while (!isValidPhoneNumber(phoneNumber)) {
            System.out.print("\t\t\t\t\tInvalid phone number. Please enter a 10-digit phone number: ");
            Scanner scanner = new Scanner(System.in);
            phoneNumber = scanner.nextLine();
        }
        doctors.add(new Doctor(name, specialization, phoneNumber));
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }
}


class Hospital {
    private PatientManager patientManager;
    private DoctorManager doctorManager;
    private AppointmentManager appointmentManager;

    public Hospital() {
        patientManager = new PatientManager();
        doctorManager = new DoctorManager();
        appointmentManager = new AppointmentManager();
    }

    public PatientManager getPatientManager() {
        return patientManager;
    }

    public DoctorManager getDoctorManager() {
        return doctorManager;
    }

    public AppointmentManager getAppointmentManager() {
        return appointmentManager;
    }
}


class project {
    
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println();
        sleep.a();
        System.out.println();
        System.out.println("\t\t\t\t\t\t -----------------------------");
        System.out.println("\t\t\t\t\t\t| HOSPITAL MANAGEMENET SYSTEM |");
        System.out.println("\t\t\t\t\t\t -----------------------------");
        System.out.println();
        System.out.println();

        do {
            sleep.b();
            System.out.println("\t\t\t\t\t 1.  Add Patient" );
            System.out.println();
            System.out.println("\t\t\t\t\t 2.  Add Doctor" );
            System.out.println();
            System.out.println("\t\t\t\t\t 3.  Schedule Appointment" );
            System.out.println();
            System.out.println("\t\t\t\t\t 4.  View Patients" );
            System.out.println();
            System.out.println("\t\t\t\t\t 5.  View Doctors" );
            System.out.println();
            System.out.println("\t\t\t\t\t 6. View Appointments" );
            System.out.println();
            System.out.println("\t\t\t\t\t 7.  Cancel Appointment");
            System.out.println();
            System.out.println("\t\t\t\t\t 8.  Exit");
            sleep.b();
            System.out.println();
            System.out.print("\t\t\t\t\tEnter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {

                case 1:

                    System.out.println();
                    sleep.c();
                    System.out.println();
                    System.out.print("\t\t\t\t\tEnter patient name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("\t\t\t\t\tEnter patient age: ");
                    int patientAge = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("\t\t\t\t\tEnter patient phone number: ");
                    String patientPhoneNumber = scanner.nextLine();
                    System.out.print("\t\t\t\t\tEnter patient illness: ");
                    String patientIllness = scanner.nextLine();
                    hospital.getPatientManager().addPatient(patientName, patientAge, patientPhoneNumber, patientIllness);
                    System.out.println();
                    sleep.d();
                    System.out.println("\t\t\t\t\t\t Patient added successfully!");
                    System.out.println();
                    break;

                case 2:

                    System.out.println();
                    sleep.e();
                    System.out.print("\t\t\t\t\tEnter doctor name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("\t\t\t\t\tEnter doctor specialization: ");
                    String doctorSpecialization = scanner.nextLine();
                    System.out.print("\t\t\t\t\tEnter doctor phone number: ");
                    String doctorPhoneNumber = scanner.nextLine();
                    hospital.getDoctorManager().addDoctor(doctorName, doctorSpecialization, doctorPhoneNumber);
                    System.out.println();
                    sleep.d();
                    System.out.println("\t\t\t\t\t\t Doctor added successfully!");
                    System.out.println();
                    break;

                case 3:

                    System.out.println();
                   
                    ArrayList<Patient> patientsList = hospital.getPatientManager().getPatients();
                    ArrayList<Doctor> doctorsList = hospital.getDoctorManager().getDoctors();
                
                    if (patientsList.isEmpty() || doctorsList.isEmpty()) {
                        System.out.println("Please add patients and doctors first.");
                    } else {
                        System.out.println("Available Patients:");
                        for (int i = 0; i < patientsList.size(); i++) {
                            System.out.println(" " + (i + 1) + ". " + patientsList.get(i).getName());
                        }
                        System.out.print("Select patient (1-" + patientsList.size() + "): ");
                        int patientIndex = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.println("Available Doctors:");
                        for (int i = 0; i < doctorsList.size(); i++) {
                            System.out.println(" " + (i + 1) + ". " + doctorsList.get(i).getName());
                        }
                        System.out.print("Select doctor (1-" + doctorsList.size() + "): ");
                        int doctorIndex = scanner.nextInt();
                        scanner.nextLine(); 
                
                        String date = null;
                        boolean isValidDate = false;
                
                        while (!isValidDate) {
                            System.out.print("Enter appointment date (YYYY-MM-DD): ");
                            date = scanner.nextLine();
                
                            // Validate date format
                            try {
                                LocalDate.parse(date); 
                                isValidDate = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("Wrong date format. Please enter the date in YYYY-MM-DD format.");
                                continue; 
                            }
                
                            // Check if appointment for the chosen date and doctor is fully booked
                            int maxAppointmentsPerDoctorPerDate = 2;
                            int appointmentsCount = 0;
                
                            for (Appointment appointment : hospital.getAppointmentManager().getAppointments()) {
                                if (appointment.getDate().equals(date) && appointment.getDoctor().getName().equalsIgnoreCase(doctorsList.get(doctorIndex - 1).getName())) {
                                    appointmentsCount++;
                                }
                            }
                
                            if (appointmentsCount >= maxAppointmentsPerDoctorPerDate) {
                                System.out.println("Appointments for " + date + " with " + doctorsList.get(doctorIndex - 1).getName() + " are fully booked. Please choose another date.");
                                isValidDate = false; 
                            }
                        }
                
                        // Schedule the appointment
                        hospital.getAppointmentManager().scheduleAppointment(
                            patientsList.get(patientIndex - 1),
                            doctorsList.get(doctorIndex - 1),
                            date
                        );
                        System.out.println("Appointment scheduled successfully!");
                    }
                    break;

                case 4:

                    System.out.println();
                    sleep.f();
                    System.out.println();
                    ArrayList<Patient> patientsData = hospital.getPatientManager().getPatients();
                    if (patientsData.isEmpty()) {
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t No patient data available.");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t Patients Information:");
                        System.out.println();
                        for (Patient patient : patientsData) {
                            System.out.println("\t\t\t\t\t Name: "+ patient.getName());
                            System.out.println("\t\t\t\t\t Age: " + patient.getAge());
                            System.out.println("\t\t\t\t\t Phone Number: " + patient.getPhoneNumber());
                            System.out.println("\t\t\t\t\t Illness: " + patient.getIllness());
                            System.out.println();
                            System.out.println("\t\t\t\t\t - - - - - - - - - - - - - -");
                            System.out.println();
                        }
                    }
                    break;

                case 5:

                    System.out.println();
                    sleep.f();
                    System.out.println();
                    ArrayList<Doctor> doctorsData = hospital.getDoctorManager().getDoctors();
                    if (doctorsData.isEmpty()) {
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t\t No doctor data available.");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t Doctors Information:");
                        System.out.println();
                        for (Doctor doctor : doctorsData) {
                            System.out.println("\t\t\t\t\t Name: " + doctor.getName());
                            System.out.println("\t\t\t\t\t Specialization: " + doctor.getSpecialization());
                            System.out.println("\t\t\t\t\t Phone Number: " + doctor.getPhoneNumber());
                            System.out.println();
                            System.out.println("\t\t\t\t\t - - - - - - - - - - - - - -");
                            System.out.println();
                        }
                    }
                    break;

                case 6:

                    System.out.println();
                    sleep.f();
                    System.out.println();
                    ArrayList<Appointment> appointmentsData = hospital.getAppointmentManager().getAppointments();
                    if (appointmentsData.isEmpty()) {
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t No appointment data available.");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t Appointments Information:");
                        System.out.println();
                        for (Appointment appointment : appointmentsData) {
                            System.out.println("\t\t\t\t\t Patient: " + appointment.getPatient().getName());
                            System.out.println("\t\t\t\t\t Doctor: " +appointment.getDoctor().getName());
                            System.out.println("\t\t\t\t\t Date: " + appointment.getDate());
                            System.out.println();
                            System.out.println("\t\t\t\t\t - - - - - - - - - - - - - -");
                            System.out.println();
                        }
                    }
                    break;
                case 7:

            
                    
                    System.out.println();
                    sleep.f();
                    System.out.println();
                    System.out.print("\t\t\t\t\tEnter patient name to cancel appointment: ");
                    String cancelPatientName = scanner.nextLine();
                    
                    ArrayList<Appointment> patientAppointments = new ArrayList<>();
                    for (int i = 0; i < hospital.getAppointmentManager().getAppointments().size(); i++) {
                        Appointment appointment = hospital.getAppointmentManager().getAppointments().get(i);
                        if (appointment.getPatient().getName().equalsIgnoreCase(cancelPatientName)) {
                            patientAppointments.add(appointment);
                        }
                    }
                
                    if (patientAppointments.isEmpty()) {
                        System.out.println("No appointments found for " + cancelPatientName);
                    } else {
                        System.out.println("Appointments for " + cancelPatientName + ":");
                        for (int i = 0; i < patientAppointments.size(); i++) {
                            System.out.println((i + 1) + ". " + patientAppointments.get(i).getDate() + " with Dr. " + patientAppointments.get(i).getDoctor().getName());
                        }
                        
                        System.out.print("Select the appointment to cancel (1-" + patientAppointments.size() + "): ");
                        int appointmentIndex = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        if (appointmentIndex < 1 || appointmentIndex > patientAppointments.size()) {
                            System.out.println("Invalid appointment index.");
                        } else {
                            boolean isCanceled = hospital.getAppointmentManager().cancelAppointment(cancelPatientName, appointmentIndex - 1);
                            if (isCanceled) {
                                System.out.println();
                                sleep.g();
                                System.out.println("Appointment canceled successfully.");
                            }
                        }
                    }
                    System.out.println();
                    break;
                

                case 8:

                    System.out.println();
                    sleep.h();
                    System.out.println();

                    System.out.println("\t\t\t\t\t\t\t\t Exiting the system. Goodbye!");
                    System.out.println();
                    break;
                default:
                    System.out.println();
                    System.out.println();
                    sleep.f();
                    System.out.println();
                    System.out.println("\t\t\t\t\t\t Invalid choice. Please try again.");
                    System.out.println();
            }
        } while (choice != 8);

        scanner.close();
    }
}