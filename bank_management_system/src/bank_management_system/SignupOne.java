package bank_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nameField, fnameField, emailField, addressField,
            cityField, stateField, pinField;
    JDateChooser dateChooser;
    JButton next;
    JRadioButton male, female, otherGender;
    JRadioButton married, unmarried, otherMarital;

    SignupOne() {

        setLayout(null);

        // Generate Random Form Number
        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION FORM NO: " + random);
        formno.setFont(new Font("Arial", Font.BOLD, 28));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel details = new JLabel("Page 1: Personal Details");
        details.setFont(new Font("Arial", Font.BOLD, 20));
        details.setBounds(290, 70, 400, 30);
        add(details);

        // Name
        addLabel("Name:", 100, 130);
        nameField = addTextField(300, 130);

        // Father's Name
        addLabel("Father's Name:", 100, 180);
        fnameField = addTextField(300, 180);

        // DOB
        addLabel("Date of Birth:", 100, 230);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 230, 400, 30);
        add(dateChooser);

        // Gender
        addLabel("Gender:", 100, 280);
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        otherGender = new JRadioButton("Other");

        male.setBounds(300, 280, 100, 30);
        female.setBounds(420, 280, 100, 30);
        otherGender.setBounds(560, 280, 100, 30);
        
        add(male);
        add(female);
        add(otherGender);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(otherGender);

        // Email
        addLabel("Email:", 100, 330);
        emailField = addTextField(300, 330);

        // Marital Status
        addLabel("Marital Status:", 100, 380);
        married = new JRadioButton("Married");
        unmarried = new JRadioButton("Unmarried");
        otherMarital = new JRadioButton("Other");

        married.setBounds(300, 380, 100, 30);
        unmarried.setBounds(420, 380, 120, 30);
        otherMarital.setBounds(560, 380, 100, 30);

        add(married);
        add(unmarried);
        add(otherMarital);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(otherMarital);

        // Address
        addLabel("Address:", 100, 430);
        addressField = addTextField(300, 430);

        // City
        addLabel("City:", 100, 480);
        cityField = addTextField(300, 480);

        // State
        addLabel("State:", 100, 530);
        stateField = addTextField(300, 530);

        // Pincode
        addLabel("Pincode:", 100, 580);
        pinField = addTextField(300, 580);

        // Next Button
        next = new JButton("Next");
        next.setBounds(620, 640, 100, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 750);
        setLocation(350, 10);
        setVisible(true);
    }

    // Helper method for labels
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(x, y, 200, 30);
        add(label);
    }

    // Helper method for text fields
    private JTextField addTextField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 400, 30);
        add(field);
        return field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String formno = String.valueOf(random);
        String name = nameField.getText();
        String fname = fnameField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String pin = pinField.getText();

        String gender = null;
        if (male.isSelected()) gender = "Male";
        else if (female.isSelected()) gender = "Female";
        else if (otherGender.isSelected()) gender = "Other";

        String marital = null;
        if (married.isSelected()) marital = "Married";
        else if (unmarried.isSelected()) marital = "Unmarried";
        else if (otherMarital.isSelected()) marital = "Other";

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else {

                Conn c = new Conn();

                String query = "INSERT INTO signup VALUES('"
                        + formno + "','" + name + "','" + fname + "','" + dob + "','"
                        + gender + "','" + email + "','" + marital + "','"
                        + address + "','" + city + "','" + state + "','" + pin + "')";

                c.s.executeUpdate(query);

                //JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
                new SignupTwo(formno);
                setVisible(false);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}