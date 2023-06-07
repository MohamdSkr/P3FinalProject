package Modul;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Appointment {

    private int id;
    private Date date;
    private String day;
    private String time;
    private String status;
    private String patientName;
    private String comment;

    public String getPatientName() {
        return patientName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Appointment() {
    }

    public Appointment(Date date, String day, String time, String status) {
        this.date = date;
        this.day = day;
        this.time = time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", date=" + date + ", day=" + day + ", time=" + time + ", status=" + status + '}';
    }

    public static ArrayList<Appointment> getAllAppointment() throws SQLException {

        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<Appointment> appointments = new ArrayList<>();
        String query = "Select * From appointment where status = 'free'";
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Appointment ap = new Appointment();
            ap.setId(rs.getInt("id"));
            ap.setDate(rs.getDate("appointmentDate"));
            ap.setDay(rs.getString("appointmentDay"));
            ap.setTime(rs.getString("appointmentTime"));
            ap.setStatus(rs.getString("status"));
            appointments.add(ap);
        }

        if (ps != null) {
            ps.close();
        }

        conn.close();

        return appointments;
    }

    public int add() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query
                = "INSERT INTO appointment (appointmentDate, appointmentDay, appointmentTime, status) "
                + "VALUES (?, ?, ?, ?)";
        ps = conn.prepareStatement(query);
        ps.setDate(1, this.getDate());
        ps.setString(2, this.getDay());
        ps.setString(3, this.getTime());
        ps.setString(4, this.getStatus());
        numOfResult = ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return numOfResult;
    }

    public int update() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query
                = "update appointment set appointmentDate = ?, appointmentDay = ?, appointmentTime = ?, status = ? "
                + "Where id = " + this.getId();
        ps = conn.prepareStatement(query);
        ps.setDate(1, this.getDate());
        ps.setString(2, this.getDay());
        ps.setString(3, this.getTime());
        ps.setString(4, this.getStatus());
        numOfResult = ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return numOfResult;
    }

    public int delete() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query = "Delete From appointment Where id = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, this.getId());
        numOfResult = ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return numOfResult;
    }

    public boolean found() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query
                = "SELECT * "
                + "FROM appointment a "
                + "WHERE a.appointmentDate = ? and a.appointmentTime = ?";
        ps = conn.prepareStatement(query);
        ps.setDate(1, this.getDate());
        ps.setString(2, this.getTime());
        ResultSet rs = ps.executeQuery();
        boolean found = rs.next();
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return found;
    }

}
