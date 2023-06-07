package View;

import java.io.IOException;

public class ViewManager {

    public static BasicLogin basicLogin;
    public static AdminLogin adminLogin;
    public static PatientLogin patientLogin;
    public static AdminDashboard adminDashboard;
    public static PatientRegister patientRegister;
    public static PatientDashboard patientDashboard;

    
    /* Basic Login */
    public static void openBasicLogin() throws IOException {
        if (basicLogin == null) {
            basicLogin = new BasicLogin();
        }
        basicLogin.show();
    }
    public static void closeBasicLogin() {
        if (basicLogin != null) {
            basicLogin.close();
        }
    }

    
    /* Admin Login */
    public static void openAdminLogin() throws IOException{
        if(adminLogin == null)
            adminLogin = new AdminLogin();
        adminLogin.show();
    }
    public static void closeAdminLogin() {
        if(adminLogin != null)
            adminLogin.close();
    }
    
    
    /* Patients Login */
    public static void openPatientLogin() throws IOException{
        if(patientLogin == null)
            patientLogin = new PatientLogin();
        patientLogin.show();
    }
    public static void closePatientLogin() {
        if(patientLogin != null)
            patientLogin.close();
    }
    
    
    /* Patient Register */
    public static void openPatientRegister() throws IOException {
        if(patientRegister == null)
            patientRegister = new PatientRegister();
        patientRegister.show();
    }
    public static void closePatientRegister() {
        if(patientRegister != null)
            patientRegister.close();
    }
    
    
    /* Admin Dahboard */
    public static void openAdminDashboard() throws IOException {
        if(adminDashboard == null)
            adminDashboard = new AdminDashboard();
        adminDashboard.show();
    }
    public static void closeAdminDashboard() {
        if(adminDashboard != null)
            adminDashboard.close();
    }
    
    
    /* Patient Dashboard */
    public static void openPatientDashboard() throws IOException {
        if(patientDashboard == null)
            patientDashboard = new PatientDashboard();
        patientDashboard.show();
    }
    public static void closePatioentDashboard() {
        if(patientDashboard != null)
            patientDashboard.close();
    }
    
    
    
}

