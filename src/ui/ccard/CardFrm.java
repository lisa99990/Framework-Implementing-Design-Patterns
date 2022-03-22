package ui.ccard;

import bank.CheckingAccount;
import bank.SavingAccount;
import creditcard.BronzeCreditCardAccount;
import creditcard.CreditCardAccount;
import creditcard.GoldCreditCardAccount;
import creditcard.SilverCreditCardAccount;
import framework.*;
import ui.bank.JDialogGenReport;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A basic JFC based application.
 */
public class CardFrm extends javax.swing.JFrame {
    /****
     * init variables in the object
     ****/
    String clientName, street, city, zip, state, accountType, amountDeposit, expdate, ccnumber, email, dateOfBirth;
    boolean newaccount;
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    CardFrm thisframe;
    private Object rowdata[];
    AccountDatabaseFactory accountFactoryImpl;
    AccountService accountService;
    int NoEmployees;
    Customer customer;
    Account account;

    public CardFrm() {
        thisframe = this;

        setTitle("Credit-card processing Application.");
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
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
        model.addColumn("Name");
        model.addColumn("CC number");
        model.addColumn("Exp date");
        model.addColumn("Type");
        model.addColumn("Balance");
        rowdata = new Object[7];
        newaccount = false;


        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 444, 160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

        JButton_NewCCAccount.setText("Add Credit-card account");
        JPanel1.add(JButton_NewCCAccount);
        JButton_NewCCAccount.setBounds(24, 20, 192, 33);

        JButton_NewCCCompAccount.setText("Add Company Credit-card account");
        JPanel1.add(JButton_NewCCCompAccount);
        JButton_NewCCCompAccount.setBounds(24, 54, 192, 33);

        JButton_AddInterest.setText("Add Interest");
        JPanel1.add(JButton_AddInterest);
        JButton_AddInterest.setBounds(240, 54, 192, 33);

        JButton_GenBill.setText("Generate Monthly bills");
        JButton_GenBill.setActionCommand("jbutton");
        JPanel1.add(JButton_GenBill);
        JButton_GenBill.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Charge");

        JPanel1.add(JButton_Withdraw);
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 31);


        JButton_GenBill.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_NewCCAccount.addActionListener(lSymAction);
        JButton_NewCCCompAccount.addActionListener(lSymAction);
        JButton_AddInterest.addActionListener(lSymAction);
        JButton_GenBill.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);
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
            (new CardFrm()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }


    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_NewCCAccount = new javax.swing.JButton();
    javax.swing.JButton JButton_NewCCCompAccount = new javax.swing.JButton();
    javax.swing.JButton JButton_AddInterest = new javax.swing.JButton();
    javax.swing.JButton JButton_GenBill = new javax.swing.JButton();
    javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
    javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();


    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == CardFrm.this)
                BankFrm_windowClosing(event);
        }
    }

    void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        BankFrm_windowClosing_Interaction1(event);
    }

    void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit)
                JButtonExit_actionPerformed(event);
            else if (object == JButton_NewCCAccount)
                JButtonNewCCAC_actionPerformed(event);
            else if (object == JButton_NewCCCompAccount)
                JButtonNewCCCompAC_actionPerformed(event);
            else if (object == JButton_AddInterest)
                JButtonAddInterest_actionPerformed(event);
            else if (object == JButton_GenBill)
                JButtonGenerateBill_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);
        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    void JButtonNewCCAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/

        JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(thisframe);
        ccac.setBounds(450, 20, 300, 380);
        ccac.show();

        if (newaccount) {
            // add row to table

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
            if (accountType.equalsIgnoreCase("Gold")) {
                account = new GoldCreditCardAccount(ccnumber, customer, LocalDate.now().plusYears(5));
            } else if (accountType.equalsIgnoreCase("Silver")) {
                account = new SilverCreditCardAccount(ccnumber, customer, LocalDate.now().plusYears(5));
            } else if (accountType.equalsIgnoreCase("Bronze")) {
                account = new BronzeCreditCardAccount(ccnumber, customer, LocalDate.now().plusYears(5));
            }
            accountService.createAccount(account);
            rowdata[0] = clientName;
            rowdata[1] = ccnumber;
            rowdata[2] = expdate;
            rowdata[3] = accountType;
            rowdata[4] = accountService.getAccount(ccnumber).getBalance();

            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }


    }

    void JButtonNewCCCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object
		 set the boundaries and show it
		*/

        JDialog_AddCompAcc ccac = new JDialog_AddCompAcc(thisframe);
        ccac.setBounds(450, 20, 300, 380);
        ccac.show();

        if (newaccount) {
            // add row to table

            Address address = new Address(street, city, zip, State.AL, email);

            String customerUuid = UUID.randomUUID().toString();
            customer = new Company(customerUuid, clientName, NoEmployees, address);
            if (accountType.equalsIgnoreCase("Gold")) {
                account = new GoldCreditCardAccount(ccnumber, customer, LocalDate.now().plusYears(5));
            } else if (accountType.equalsIgnoreCase("Silver")) {
                account = new SilverCreditCardAccount(ccnumber, customer, LocalDate.now().plusYears(5));
            } else if (accountType.equalsIgnoreCase("Bronze")) {
                account = new BronzeCreditCardAccount(ccnumber, customer, LocalDate.now().plusYears(5));
            }
            accountService.createAccount(account);
            rowdata[0] = clientName;
            rowdata[1] = ccnumber;
            rowdata[2] = expdate;
            rowdata[3] = accountType;
            rowdata[4] = accountService.getAccount(ccnumber).getBalance();
            ;

            model.addRow(rowdata);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }

    }

    void JButtonAddInterest_actionPerformed(java.awt.event.ActionEvent event) {
        JOptionPane.showMessageDialog(JButton_AddInterest, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
        accountService.addInterestToAllAccount();
//		  for(int i = 0; i < model.getRowCount();i++){
//			  model.removeRow(i);
//		  }
        model.setRowCount(0);
        accountService.getAllAccounts().values().forEach(acct -> {
            String type = "gold";
            if (acct.getClass() == SilverCreditCardAccount.class)
                type = "silver";
            if (acct.getClass() == BronzeCreditCardAccount.class)
                type = "bronze";
            Object[] data = {acct.getCustomer().getName(), acct.getAccountNumber(), ((CreditCardAccount) acct).getExpirationDate().toString(),
                    type, acct.getBalance()};

            model.addRow(data);
            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
        });

    }


    void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event) {
        JDialogGenBill billFrm = new JDialogGenBill(this, accountService);
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();


    }

    void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String ccnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(thisframe, ccnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            // compute new amount
            Double deposit = Double.parseDouble(amountDeposit);
            accountService.deposit(ccnr, deposit);
            model.setValueAt(accountService.getAccount(ccnr).getBalance(), selection, 4);
            model.fireTableCellUpdated(selection, 4);
            model.fireTableDataChanged();
        }


    }

    void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String ccnr = (String) model.getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(thisframe, ccnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount
            Double amount = Double.parseDouble(amountDeposit);


            if (amount < 0) {
                JOptionPane.showMessageDialog(JButton_Withdraw, "Amount must be positive", "Warning: negative amount", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (accountService.getAccount(ccnr).getBalance() + amount > ((CreditCardAccount) accountService.getAccount(ccnr)).getLimit()) {
                JOptionPane.showMessageDialog(JButton_Withdraw, "Crossed limit", "Warning: Insufficient balance", JOptionPane.ERROR_MESSAGE);
                return;
            }
            accountService.deduct(ccnr, amount);
            model.setValueAt(String.valueOf(accountService.getAccount(ccnr).getBalance()), selection, 4);
        }
    }

    private void start() {
        System.out.println("Application started: Creating Factory for database.");
        accountFactoryImpl = new AccountDatabaseFactoryImplementation();
        accountService = accountFactoryImpl.createAccountService(AccountDatabaseType.IN_MEMORY);
    }

}
