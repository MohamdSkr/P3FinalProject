package Modul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookedAppointment {

    private int id;
    private int appointmentID;
    private int userID;
    private String status;
    private String comment;

    public BookedAppointment() {
    }

    public BookedAppointment(int appointmentID, int userID, String status, String comment) {
        this.appointmentID = appointmentID;
        this.userID = userID;
        this.status = status;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static int add(User patient, Appointment appointment) throws SQLException {

        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query = "INSERT INTO booked_appointment(appointmentID, userID, status, comment) VALUES (?, ?, ?, ?)";
        ps = conn.prepareStatement(query);
        ps.setInt(1, appointment.getId());
        ps.setInt(2, patient.getId());
        ps.setString(3, "waiting");
        ps.setString(4, "");
        numOfResult = ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return numOfResult;
    }

    public static void delete(User patient, Appointment appointment) throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query
                = "delete "
                + "From booked_appointment "
                + "WHERE appointmentID = ? AND userID = ?;";
        ps = conn.prepareStatement(query);
        ps.setInt(1, appointment.getId());
        ps.setInt(2, patient.getId());
        ps.executeUpdate();
        
        // Return Appointment Free Status
        String sql
                = "UPDATE appointment set status = ? "
                + "WHERE id = ?";
        PreparedStatement psReturn = conn.prepareStatement(sql);
        psReturn.setString(1, "free");
        psReturn.setInt(2, appointment.getId());
        psReturn.executeUpdate();
        
        if (ps != null) {
            ps.close();
        }
        
        if (psReturn != null) {
            psReturn.close();
        }
        conn.close();
    }

    public static ArrayList<BookedAppointment> getBookedAppointment() throws SQLException {

        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<BookedAppointment> appointments = new ArrayList<>();
        String query = "Select * From booked_appointment";
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BookedAppointment ba = new BookedAppointment();
            ba.setId(rs.getInt("id"));
            ba.setAppointmentID(rs.getInt("appointmentID"));
            ba.setUserID(rs.getInt("userID"));
            ba.setStatus(rs.getString("status"));
            ba.setComment(rs.getString("doctorComment"));
            appointments.add(ba);
        }

        if (ps != null) {
            ps.close();
        }

        conn.close();

        return appointments;
    }

    public static ArrayList<Appointment> getBookedAppointmentsByID(User patient) throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<Appointment> appointments = new ArrayList<>();
        String query = "Select a.id as id, a.appointmentDate as date, a.appointmentDay as day, a.appointmentTime as time, b.status as status From appointment a join booked_appointment b on a.id = b.appointmentID Where b.status = 'waiting' and b.userID = " + patient.getId();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Appointment a = new Appointment();
            a.setId(rs.getInt("id"));
            a.setDate(rs.getDate("date"));
            a.setDay(rs.getString("day"));
            a.setTime(rs.getString("time"));
            a.setStatus(rs.getString("status"));
            appointments.add(a);
        }

        if (ps != null) {
            ps.close();
        }

        conn.close();

        return appointments;
    }

    public static ArrayList<AppointmentDetails> getBookedAppointmentsDetails() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<AppointmentDetails> appointments = new ArrayList<>();
        String query = "Select appointmentID, userID, concat(u.firstname , ' ' , u.lastname) as patientName, a.appointmentDate as date, a.appointmentDay as day, a.appointmentTime as time, b.status as status From appointment a join booked_appointment b on a.id = b.appointmentID JOIN users u ON u.id = b.userID Where b.status = 'waiting'";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            AppointmentDetails a = new AppointmentDetails();
            a.setPatientID(rs.getInt("userID"));
            a.setAppointmentID(rs.getInt("appointmentID"));
            a.setDate(rs.getDate("date"));
            a.setDay(rs.getString("day"));
            a.setTime(rs.getString("time"));
            a.setStatus(rs.getString("status"));
            a.setPatientName(rs.getString("patientName"));
            appointments.add(a);
        }

        if (ps != null) {
            ps.close();
        }

        conn.close();

        return appointments;
    }

    public static ArrayList<AppointmentDetails> getFinisedAppointmentsDetails() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<AppointmentDetails> appointments = new ArrayList<>();
        String query = "Select appointmentID, userID, comment, concat(u.firstname , ' ' , u.lastname) as patientName, a.appointmentDate as date, a.appointmentDay as day, a.appointmentTime as time, b.status as status From appointment a join booked_appointment b on a.id = b.appointmentID JOIN users u ON u.id = b.userID Where b.status = 'finished'";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            AppointmentDetails a = new AppointmentDetails();
            a.setPatientID(rs.getInt("userID"));
            a.setAppointmentID(rs.getInt("appointmentID"));
            a.setDate(rs.getDate("date"));
            a.setDay(rs.getString("day"));
            a.setTime(rs.getString("time"));
            a.setStatus(rs.getString("status"));
            a.setPatientName(rs.getString("patientName"));
            a.setComment(rs.getString("comment"));
            appointments.add(a);
        }

        if (ps != null) {
            ps.close();
        }

        conn.close();

        return appointments;
    }

    public static ArrayList<AppointmentDetails> getFinshedAppointmentByID(int patientID) throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<AppointmentDetails> appointments = new ArrayList<>();
        String query = "Select appointmentID, appointmentDate, appointmentDay, appointmentTime, b.status as status, comment From booked_appointment b join appointment a on b.appointmentID = a.id where b.status = 'finished' and userID = " + patientID;
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            AppointmentDetails a = new AppointmentDetails();
            a.setAppointmentID(rs.getInt("appointmentID"));
            a.setDate(rs.getDate("appointmentDate"));
            a.setStatus(rs.getString("status"));
            a.setComment(rs.getString("comment"));
            a.setTime(rs.getString("appointmentTime"));
            a.setDay(rs.getString("appointmentDay"));
            appointments.add(a);
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return appointments;
    }

    public static void finshAppointment(AppointmentDetails appointmentDetails) throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        String query = "update booked_appointment set status = 'finished', comment = ? Where userID = ? and appointmentID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, appointmentDetails.getComment());
        ps.setInt(2, appointmentDetails.getPatientID());
        ps.setInt(3, appointmentDetails.getAppointmentID());
        ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
    }

    public static void updateFinishedComment(AppointmentDetails appointmentDetails) throws SQLException {

        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query = "update booked_appointment set comment = ? Where userID = ? and appointmentID = ?";
        ps = conn.prepareStatement(query);
        ps.setString(1, appointmentDetails.getComment());
        ps.setInt(2, appointmentDetails.getPatientID());
        ps.setInt(3, appointmentDetails.getAppointmentID());
        numOfResult = ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();

    }

    public static ArrayList<AppointmentDetails> search(String firstNameSearch, String status) throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<AppointmentDetails> appointments = new ArrayList<>();
        String query
                = "SELECT appointmentID, userID, concat(u.firstname, ' ', u.lastname) as patientName, a.appointmentDate as date, a.appointmentDay as day, a.appointmentTime as time, b.status as status "
                + "FROM appointment a "
                + "JOIN booked_appointment b ON a.id = b.appointmentID "
                + "JOIN users u ON u.id = b.userID "
                + "WHERE u.firstName like ? AND b.status = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, firstNameSearch + "%");
        ps.setString(2, status);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            AppointmentDetails a = new AppointmentDetails();
            a.setPatientID(rs.getInt("userID"));
            a.setAppointmentID(rs.getInt("appointmentID"));
            a.setDate(rs.getDate("date"));
            a.setDay(rs.getString("day"));
            a.setTime(rs.getString("time"));
            a.setStatus(rs.getString("status"));
            a.setPatientName(rs.getString("patientName"));
            appointments.add(a);
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return appointments;
    }

}
