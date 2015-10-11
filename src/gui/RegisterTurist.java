package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import domain.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import businessLogic.ApplicationFacadeInterface;

public class RegisterTurist extends JFrame {

	private JPanel contentPane;
	private JTextField izena;
	private JTextField eIzena;
	private JTextField email;
	private JPasswordField pass1;
	private JPasswordField pass2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterTurist frame = new RegisterTurist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterTurist() {
		setTitle("Erregistratu Turista bezala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(12, 43, 34, 22);
		contentPane.add(lblIzena);
		
		JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile Izena");
		lblErabiltzaileIzena.setBounds(12, 78, 112, 16);
		contentPane.add(lblErabiltzaileIzena);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(12, 108, 56, 16);
		contentPane.add(lblPasahitza);
		
		JLabel lblErrepikatuPasahitza = new JLabel("Errepikatu Pasahitza");
		lblErrepikatuPasahitza.setBounds(12, 137, 122, 16);
		contentPane.add(lblErrepikatuPasahitza);
		
		JLabel lblEmaila = new JLabel("eMail-a");
		lblEmaila.setBounds(12, 166, 56, 16);
		contentPane.add(lblEmaila);
		
		izena = new JTextField();
		izena.setBounds(147, 43, 215, 22);
		contentPane.add(izena);
		izena.setColumns(10);
		
		eIzena = new JTextField();
		eIzena.setBounds(147, 75, 215, 22);
		contentPane.add(eIzena);
		eIzena.setColumns(10);
		
		email = new JTextField();
		email.setBounds(147, 163, 215, 22);
		contentPane.add(email);
		email.setColumns(10);
		
		pass1 = new JPasswordField();
		pass1.setBounds(147, 105, 215, 22);
		contentPane.add(pass1);
		
		pass2 = new JPasswordField();
		pass2.setBounds(146, 134, 216, 22);
		contentPane.add(pass2);
		
		JButton btnNewButton = new JButton("Erregistratu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface appInterface;
				if((izena!=null) && (eIzena!=null) && (email!=null) && (pass1!=null) && (pass2!=null)){
					if(pass1.getText().compareTo(pass2.getText())!=0){
						JFrame desberdinak= new AdviseWindow("Pasahitzak desberdinak dira!");
						desberdinak.setVisible(true);
					}else{
						appInterface= MainWindow.getBusinessLogic();
						try{
							Turist turista= appInterface.getTurist(eIzena.getText());
							Owner jabea= appInterface.getOwner(eIzena.getText());
							if(turista.getLogin()!=null || jabea.getLogin()!=null){
								JFrame erabilpean= new AdviseWindow("Erabiltzaile izen hori existitzen da jadanik..");
								erabilpean.setVisible(true);
							}else if(appInterface.erregistratuTurista(izena.getText(), eIzena.getText(), pass1.getText(), pass2.getText(), email.getText())){
								desagertu(arg0);
								JFrame login= new LoginWindow();
								login.setVisible(true);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton.setBounds(224, 208, 107, 32);
		contentPane.add(btnNewButton);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desagertu(e);
				JFrame login= new LoginWindow();
				login.setVisible(true);
			}
		});
		btnAtzera.setBounds(51, 212, 97, 25);
		contentPane.add(btnAtzera);
	}
	
	private void desagertu(ActionEvent e){
		this.setVisible(false);
	}

}
