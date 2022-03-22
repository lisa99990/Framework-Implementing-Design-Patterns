package ui.bank;
/*
		A basic implementation of the JDialog class.
*/

import bank.BankAccount;
import creditcard.CreditCardAccount;
import framework.AccountDatabaseFactoryImplementation;
import framework.AccountDatabaseType;
import framework.AccountService;
import framework.AccountServiceImplementation;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class JDialogGenReport extends JDialog {
    AccountService service;

    public JDialogGenReport(Frame parent, AccountService service) {
        super(parent);
        this.service = service;

        // This code is automatically generated by Visual Cafe when you add
        // components to the visual environment. It instantiates and initializes
        // the components. To modify the code, only use code syntax that matches
        // what Visual Cafe can generate, or Visual Cafe may be unable to back
        // parse your Java file into its visual environment.
        //{{INIT_CONTROLS
        getContentPane().setLayout(null);
        setSize(405, 367);
        setVisible(false);
        getContentPane().add(JScrollPane1);
        JScrollPane1.setBounds(24, 24, 358, 240);
        JScrollPane1.getViewport().add(JTextField1);
        JTextField1.setBounds(0, 0, 355, 237);
        JButton_OK.setText("OK");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(156, 276, 96, 24);

        //------------------------------
        Set<String> keys = service.getAllAccounts().keySet();
        String[] accounts = keys.toArray(new String[0]);

        List<String> res = service.generateReport(accounts, LocalDate.of(2010, 10, 10), LocalDate.of(2033, 10, 10));

        StringBuilder sb = new StringBuilder();
        sb.append("Bank Report");
        sb.append("\n====================================\n");

        /*for (String s : res) {
            sb.append(s);
            sb.append("\n-------------------------------------\n");
        }
        JTextField1.setText(sb.toString());
        System.out.println(sb);*/

        String[] arr;
        int index = 0;
        for (String s : res) {
            arr = s.split("\\{");
            BankAccount account = (BankAccount) service.getAccount(accounts[index++]);
            double currentBalance = account.getBalance();
            sb.append("CardHolder: " + account.getCustomer().getName());
            sb.append("\nAccount Number: " + account.getAccountNumber());
            sb.append("\nCurrent Balance: " + currentBalance + "\n");
            for (String a : arr) {
                a = a.replace("Transaction", "");
                a = a.replace("{", "");
                a = a.replace("}", "");
                a = a.replace("[", "");
                a = a.replace("]", "");
                sb.append(a);
                sb.append("\n");
            }
            sb.append("\n---------------------------------------\n");
        }
        JTextField1.setText(sb.toString());
        //System.out.println(sb);
        //------------------------------


        //{{REGISTER_LISTENERS
        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
        //}}
    }

    public JDialogGenReport() {
        this((Frame) null, null);
    }


    //{{DECLARE_CONTROLS
    JScrollPane JScrollPane1 = new JScrollPane();
    JTextArea JTextField1 = new JTextArea();
    JButton JButton_OK = new JButton();
    //}}


    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_OK)
                JButtonOK_actionPerformed(event);
        }
    }

    void JButtonOK_actionPerformed(java.awt.event.ActionEvent event) {
        dispose();
    }
}