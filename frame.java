package com.company;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.LineBreakMeasurer;
import java.io.*;

public class frame extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem loudItem;
    private JMenuItem saveItem;
    private JTextField invoiceDate ;
    private JTextField cusName;
    private JTextArea ta;
    Object[][] charTable = {{"","","",""},{"","","",""},{"","","",""}};
    String[] colNames = {"No", "Date", "Costumer","Total"};
    Object[][] invoiceCol = {{"","","","",""},{"","","","",""},{"","","","",""}};
    String[] invoiceRow = {"No", "Item Price","Count", "Item Name","Item Total"};
    private JPanel pan1;
    private JPanel pan2;
    private JPanel pan3;
    private JPanel pan4;
    private JButton saveB;
    private JButton cancelB;
    private JButton createB;
    private JButton deleateB;
    private JTable tab1;



    public frame(){
        super("Design Preview");
        setLayout(new GridLayout(1,2));
        menuBar = new JMenuBar();


        loudItem = new JMenuItem("Loud",'l');
        loudItem.setAccelerator(KeyStroke.getKeyStroke('0', KeyEvent.CTRL_DOWN_MASK));
        loudItem.addActionListener(this);
        loudItem.setActionCommand("L");
        saveItem = new JMenuItem("Save",'S');
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(this);
        saveItem.setActionCommand("S");
        file =new JMenu("File");
        file.add(saveItem);
        file.add(loudItem);
        menuBar.add(file);

        setJMenuBar(menuBar);
         pan1 = new JPanel();
        add(pan1);
        pan1.setLayout(new FlowLayout());
        tab1=new JTable(charTable,colNames);
        pan1.add(new JScrollPane(tab1));
        createB = new JButton("Create invoice");
        createB.addActionListener(this);
        createB.setActionCommand("r");
        pan1.add(createB);
        saveItem.addActionListener(this);
        saveItem.setActionCommand("m");
        deleateB = new JButton("Delete invoice");
        deleateB.addActionListener(this);
        deleateB.setActionCommand("d");
        pan1.add(deleateB);/*End of pan1*/
        pan2 =new JPanel();
        pan2.setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
        add(pan2);/*
        pan2 includes table , 2 textfields and 2 buttons

        */
        pan2.setLayout(new FlowLayout());
        pan2.add(new JScrollPane(new JLabel("INvoise Number    ")));
        pan2.add(new JScrollPane(new JLabel("23")));

        invoiceDate = new JTextField(30);
        pan2.add(new JScrollPane(new JLabel("Invoice Date")));
        pan2.add(invoiceDate);
        cusName = new JTextField(30);
        /*
        * customer label  and date i failed to make them like photo i'v tried many layout but still failed
        * */
        pan2.add(new JLabel("Customer Name"));
        pan2.add(cusName);

        pan2.add(new JLabel("Invoice Item"));
        pan2.add(new JScrollPane(new JTable(invoiceCol,invoiceRow)));
        /*save and cancle Button creation*/
        saveB =new JButton("Save");
        saveB.addActionListener(this);
        saveB.setActionCommand("v");
        pan2.add(saveB);

        cancelB =new JButton("Cancle");
        cancelB.addActionListener(this);
        cancelB.setActionCommand("c");
        pan2.add(cancelB);




        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,1000);


    }
    public static void main(String args[]){
        frame fr= new frame();
        fr.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "L":
                loudInvoice();
                break;
            case "S":
                saveInvoice();
                break;
            case "r":
                createB();
                break;
            case "v":
                saveInvoice();
                break;
            case "c":
                System.exit(0);
                break;
            case "d":
                deleateB();
                break;



        }

    }
    void loudInvoice(){
        JFileChooser fl = new JFileChooser();
        int result= fl.showOpenDialog(this);
        if (result==JFileChooser.APPROVE_OPTION){
            String path = fl.getSelectedFile().getPath();

            FileInputStream fis = null;
            try {
                 fis = new FileInputStream(path);
                 int size = fis.available();
                byte[] b = new byte[size];
                fis.read(b);
                invoiceDate.setText(new String(b));

            }
            catch (FileNotFoundException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"File Not Found");
            }
            catch (IOException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"File Error");

            }
            finally {



            try {fis.close();}
            catch (IOException e){}}


        }
    }
    void saveInvoice(){ JFileChooser fl = new JFileChooser();
        int result= fl.showSaveDialog(this);
        if (result==JFileChooser.APPROVE_OPTION){
            String path = fl.getSelectedFile().getPath();

            FileOutputStream fis = null;
            try {
                fis = new FileOutputStream(path);
                byte[] b = invoiceDate.getText().getBytes();
                fis.write(b);


            }
            catch (FileNotFoundException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"File Not Found");
            }
            catch (IOException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"File Error");

            }
            finally {

                try {fis.close();}
                catch (IOException e){}}
        }}
    void createB(){
        /* create invoice function   */
        try {
            BufferedReader bu=new BufferedReader(new InputStreamReader(System.in));
          String  fileName =cusName.getText();
            File nF = new File(fileName+".csv");
            if (nF.createNewFile()){
                JOptionPane.showMessageDialog(this,fileName+"File created");

            }else {
                JOptionPane.showMessageDialog(this,"Error occured");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error");
        }

    }
    void deleateB(){
        /* Delete Function*/
        JFileChooser fl = new JFileChooser();
        int result= fl.showOpenDialog(this);
        if (result==JFileChooser.APPROVE_OPTION){
            String path = fl.getSelectedFile().getPath();
            File mf= new File(path);
        if   (mf.delete()) {
            JOptionPane.showMessageDialog(this,path+"the file has been deleated");


        }




        }
    }

    }





