package employeemangementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Raiyan
 */
public class ViewEmployee extends javax.swing.JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId;
    JButton Search, Print, Update, Back, Sort;

    ViewEmployee() {
        getContentPane().setBackground(Color.gray);
        setLayout(null);
        JLabel searchlbl = new JLabel("Search By Employee Id");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);

        cemployeeId = new Choice();
        cemployeeId.setBounds(180, 20, 150, 20);
        add(cemployeeId);
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while (rs.next()) {
                cemployeeId.add(rs.getString("empId"));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            System.out.println(e);
        }
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        Search = new JButton("Search");
        Search.setBounds(20, 70, 80, 20);
        Search.addActionListener(this);
        add(Search);
        Print = new JButton("Print");
        Print.setBounds(120, 70, 80, 20);
        Print.addActionListener(this);
        add(Print);
        Update = new JButton("Update");
        Update.setBounds(220, 70, 80, 20);
        Update.addActionListener(this);
        add(Update);
        Back = new JButton("Back");
        Back.setBounds(320, 70, 80, 20);
        Back.addActionListener(this);
        add(Back);

        Sort = new JButton("Sort");
        Sort.setBounds(420, 70, 80, 20);
        Sort.addActionListener(this);
        add(Sort);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == Search) {
            String query = "select *from employee where empId ='" + cemployeeId.getSelectedItem() + "'";
            try {

                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (evt.getSource() == Print) {
            try {
                table.print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (evt.getSource() == Update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem()).setVisible(true);
        } else if (evt.getSource() == Back) {
            setVisible(false);
            new Home().setVisible(true);
        } else if (evt.getSource() == Sort) {
            String query = "select *from employee order by salary desc";
            try {

                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                System.out.println(e);

            }

        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }

}
