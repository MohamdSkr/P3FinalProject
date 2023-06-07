package Modul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    public static User signPatient = null;
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String gender;
    private String role;

    public User(String username, String password, String firstName, String lastName, int age, String email, String phone, String gender, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email + ", phone=" + phone + ", gender=" + gender + '}';
    }

    public int add() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query = "INSERT INTO users(username, password, firstname, lastname, age, email, phone, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ps = conn.prepareStatement(query);
        ps.setString(1, this.getUsername());
        ps.setString(2, this.getPassword());
        ps.setString(3, this.getFirstName());
        ps.setString(4, this.getLastName());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setString(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setString(9, this.getRole());
        numOfResult = ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return numOfResult;
    }

    public static ArrayList<User> getAllPatient() throws SQLException {

        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<User> patients = new ArrayList<>();
        String query = "Select * From users where role = 'patient'";
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User p = new User();
            p.setId(rs.getInt("id"));
            p.setUsername(rs.getString("username"));
            p.setPassword(rs.getString("password"));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setAge(rs.getInt("age"));
            p.setPhone(rs.getString("phone"));
            p.setEmail(rs.getString("email"));
            p.setGender(rs.getString("gender"));
            p.setRole(rs.getString("role"));
            patients.add(p);
        }

        if (ps != null) {
            ps.close();
        }

        conn.close();

        return patients;
    }

    public static ArrayList<User> search(String fristNameSearch) throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        ArrayList<User> patients = new ArrayList<>();
        String query = "Select * From users Where firstName = '" + fristNameSearch + "'";
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User p = new User();
            p.setId(rs.getInt("id"));
            p.setUsername(rs.getString("username"));
            p.setPassword(rs.getString("password"));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setAge(rs.getInt("age"));
            p.setPhone(rs.getString("phone"));
            p.setEmail(rs.getString("email"));
            p.setGender(rs.getString("gender"));
            p.setRole(rs.getString("role"));
            patients.add(p);
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return patients;
    }

    public int update() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        PreparedStatement ps = null;
        int numOfResult = 0;
        String query = "update users set username = ?, password = ?, firstname = ?, lastname = ?, age = ?, email = ?, phone = ?, gender = ?, role = ? Where id = " + this.getId();
        ps = conn.prepareStatement(query);
        ps.setString(1, this.getUsername());
        ps.setString(2, this.getPassword());
        ps.setString(3, this.getFirstName());
        ps.setString(4, this.getLastName());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setString(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setString(9, this.getRole());
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
        String query = "Delete From users Where id = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, this.getId());
        numOfResult = ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return numOfResult;
    }

    public static User getAdminByUsername(String username, String password) throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        String query = "Select * From users Where role = 'admin' and username = '" + username + "' and password = '"+password+"'";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        User p = new User();
        while (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setUsername(rs.getString("username"));
            p.setPassword(rs.getString("password"));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setAge(rs.getInt("age"));
            p.setPhone(rs.getString("phone"));
            p.setEmail(rs.getString("email"));
            p.setGender(rs.getString("gender"));
            p.setRole(rs.getString("role"));
        }
        if(p.getUsername() == null)
            p = null;
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return p;
    }
    
    public static User getPatientByUsername(String username, String password) throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        String query = "Select * From users Where role = 'patient' and username = '" + username + "' and password = '"+password+"'";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        User p = new User();
        while (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setUsername(rs.getString("username"));
            p.setPassword(rs.getString("password"));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setAge(rs.getInt("age"));
            p.setPhone(rs.getString("phone"));
            p.setEmail(rs.getString("email"));
            p.setGender(rs.getString("gender"));
            p.setRole(rs.getString("role"));
        }
        if(p.getUsername() == null)
            p = null;
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return p;
    }

    public boolean found() throws SQLException {
        Connection conn = ClinicDatabase.getInstance().getConnection();
        String query = "Select * From users Where role =  ? and username = ? and firstName = ? and lastName = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, getRole());
        ps.setString(2, getUsername());
        ps.setString(3, getFirstName());
        ps.setString(4, getLastName());
        ResultSet rs = ps.executeQuery();
        boolean found = rs.next();
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return found;
    }
    
}
