package ui.bank;

import bank.CheckingAccount;
import bank.SavingAccount;
import framework.*;
import ui.ccard.JDialogGenBill;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 * A basic JFC based application.
 */
public class BankFrm extends JFrame {
    /****
     * init variables in the object
     ****/
    String accountnr, clientName, street, city, zip, state, accountType, clientType, amountDeposit, dateOfBirth, email;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    BankFrm myframe;
    private Object rowdata[];
    AccountDatabaseFactory accountFactoryImpl;
    AccountService accountService;
    int NoEmployees;

    Address address;

    Customer customer;
    Account account;

    public BankFrm() {
        myframe = this;

        setTitle("Bank Application.");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(600, 330);
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 575, 310);
		/*
		/Add five buttons on the pane 
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/
        JScrollPane1 = new JScrollPane();
        model = new DefaultTableModel();
        JTable1 = new JTable(model);
        model.addColumn("AccountNr");
        model.addColumn("Name");
        model.addColumn("City");
        model.addColumn("P/C");
        model.addColumn("Ch/S");
        model.addColumn("Amount");
        rowdata = new Object[8];
        newaccount = false;


        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 444, 160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

        JButton_PerAC.setText("Add personal account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24, 20, 192, 33);
        JButton_CompAC.setText("Add company account");
        JButton_CompAC.setActionCommand("jbutton");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Withdraw");

        JButton_Report.setBounds(468, 60, 96, 33);
        JButton_Report.setText("Report");
        JPanel1.add(JButton_Report);

        JPanel1.add(JButton_Withdraw);
        JButton_Addinterest.setBounds(448, 20, 106, 33);
        JButton_Addinterest.setText("Add interest");
        JPanel1.add(JButton_Addinterest);
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 31);
        // lineBorder1.setRoundedCorners(true);
        // lineBorder1.setLineColor(java.awt.Color.green);
        //$$ lineBorder1.move(24,312);

        JButton_PerAC.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_PerAC.addActionListener(lSymAction);
        JButton_CompAC.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);
        JButton_Addinterest.addActionListener(lSymAction);
        JButton_Report.addActionListener(lSymAction);
        start();

    }


    /*****************************************************
     * The entry point for this application.
     * Sets the Look and Feel to the System Look and Feel.
     * Creates a new JFrame1 and makes it visible.
     *****************************************************/
    static public void main(String args[]) {
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            (new BankFrm()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }


    JPanel JPanel1 = new JPanel();
    JButton JButton_PerAC = new JButton();
    JButton JButton_CompAC = new JButton();
    JButton JButton_Deposit = new JButton();
    JButton JButton_Withdraw = new JButton();
    JButton JButton_Report = new JButton();
    JButton JButton_Addinterest = new JButton();
    JButton JButton_Exit = new JButton();

    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(WindowEvent event) {
            Object object = event.getSource();
            if (object == BankFrm.this)
                BankFrm_windowClosing(event);
        }
    }

    void BankFrm_windowClosing(WindowEvent event) {
        // to do: code goes here.

        BankFrm_windowClosing_Interaction1(event);
    }

    void BankFrm_windowClosing_Interaction1(WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit)
                JButtonExit_actionPerformed(event);
            else if (object == JButton_PerAC)
                JButtonPerAC_actionPerformed(event);
            else if (object == JButton_CompAC)
                JButtonCompAC_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);
            else if (object == JButton_Addinterest)
                JButtonAddinterest_actionPerformed(event);
            else if (object == JButton_Report)
                JButton_Report_actionPerformed(event);
        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(ActionEvent event) {
        System.exit(0);
    }

    void JButtonPerAC_actionPerformed(ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/

        JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            // add row to table
            rowdata[0] = accountnr;
            rowdata[1] = clientName;
            rowdata[2] = city;
            rowdata[3] = "P";
            rowdata[4] = accountType;
            rowdata[5] = "0";

            Address address = new Address(street, city, zip, State.AL, email);
            LocalDate dob = null;
            try {
                dob = LocalDate.parse(dateOfBirth);
            } catch (DateTimeParseException e) {
                System.out.println("Default dob added");
                dob = LocalDate.parse("2000-12-12");
            }
            String customerUuid = UUID.randomUUID().toString();
            customer = new Person(customerUuid, clientName, dob, address);
            if (accountType.equalsIgnoreCase("s")) {
                System.out.println();
                account = new SavingAccount(accountnr, customer);
            } else {
                account = new CheckingAccount(accountnr, customer);
            }
            accountService.createAccount(account);

            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }


    }

    void JButtonCompAC_actionPerformed(ActionEvent event) {
		/*
		 construct a JDialog_AddCompAcc type object 
		 set the boundaries and 
		 show it 
		*/

        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            // add row to table
            rowdata[0] = accountnr;
            rowdata[1] = clientName;
            rowdata[2] = city;
            rowdata[3] = "C";
            rowdata[4] = accountType;
            rowdata[5] = "0";
            address = new Address(street, city, zip, State.IA, email);

            String customerUuid = UUID.randomUUID().toString();
            customer = new Company(customerUuid, clientName, NoEmployees, address);

            if (accountType.equalsIgnoreCase("s")) {
                account = new SavingAccount(accountnr, customer);
            } else {
                account = new CheckingAccount(accountnr, customer);
            }

            accountService.createAccount(account);

            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }

    }

    void JButtonDeposit_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myframe, accnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            Double deposit = Double.parseDouble(amountDeposit);
            accountService.deposit(accnr, deposit);
            model.setValueAt(String.valueOf(accountService.getAccount(accnr).getBalance()), selection, 5);
            model.fireTableCellUpdated(selection, 5);
            model.fireTableDataChanged();
        }


    }

    void JButtonWithdraw_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myframe, accnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount
            Double amount = Double.parseDouble(amountDeposit);


            if (amount < 0) {
                JOptionPane.showMessageDialog(JButton_Withdraw, "Amount must be positive", "Warning: negative amount", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (accountService.getAccount(accnr).getBalance() < amount) {
                JOptionPane.showMessageDialog(JButton_Withdraw, "Insufficient balance", "Warning: Insufficient balance", JOptionPane.ERROR_MESSAGE);
                return;
            }
            accountService.deduct(accnr, amount);
            model.setValueAt(String.valueOf(accountService.getAccount(accnr).getBalance()), selection, 5);
        }


    }

    void JButtonAddinterest_actionPerformed(ActionEvent event) {
        JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
        accountService.addInterestToAllAccount();
//		  for(int i = 0; i < model.getRowCount();i++){
//			  model.removeRow(i);
//		  }
        model.setRowCount(0);
        accountService.getAllAccounts().values().forEach(acct -> {

            Object[] data = {acct.getAccountNumber(), acct.getCustomer().getName(), acct.getCustomer().getAddress().getCity(),
                    acct.getCustomer().getClass() == Company.class ? "C" : "P", acct.getClass() == CheckingAccount.class ? "Ch" : "S", acct.getBalance()};

            model.addRow(data);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
        });
    }

    void JButton_Report_actionPerformed(ActionEvent event) {
        //System.out.println("Got it");
        JDialogGenReport reportFrm = new JDialogGenReport(this, accountService);
        reportFrm.setBounds(450, 20, 400, 350);
        reportFrm.show();
    }

    private void start() {
        System.out.println("Application started: Creating Factory for database.");
        accountFactoryImpl = new AccountDatabaseFactoryImplementation();
        accountService = accountFactoryImpl.createAccountService(AccountDatabaseType.IN_MEMORY);
    }
}
