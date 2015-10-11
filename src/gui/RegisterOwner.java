package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import businessLogic.ApplicationFacadeInterface;

import java.util.Vector;

import domain.Owner;
import domain.RuralHouse;
import domain.Turist;

public class RegisterOwner extends JFrame {

	private JPanel contentPane;
	private JTextField eIzena;
	private JPasswordField pass1;
	private JPasswordField pass2;
	private JTextField email;
	private JTextField account;
	private JTextField tlf;
	private JTextField izena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterOwner frame = new RegisterOwner();
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
	public RegisterOwner() {
		setTitle("Erregistratu Jabe Bezala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile izena");
		lblErabiltzaileIzena.setBounds(29, 56, 125, 16);
		contentPane.add(lblErabiltzaileIzena);
		
		eIzena = new JTextField();
		eIzena.setBounds(189, 53, 198, 22);
		contentPane.add(eIzena);
		eIzena.setColumns(10);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(29, 91, 56, 16);
		contentPane.add(lblPasahitza);
		
		JLabel lblErrepikatuPasahitza = new JLabel("Errepikatu pasahitza");
		lblErrepikatuPasahitza.setBounds(29, 126, 125, 16);
		contentPane.add(lblErrepikatuPasahitza);
		
		pass1 = new JPasswordField();
		pass1.setBounds(189, 88, 198, 22);
		contentPane.add(pass1);
		
		pass2 = new JPasswordField();
		pass2.setBounds(189, 123, 198, 22);
		contentPane.add(pass2);
		
		JLabel lblEmaila = new JLabel("eMail-a");
		lblEmaila.setBounds(29, 159, 56, 16);
		contentPane.add(lblEmaila);
		
		email = new JTextField();
		email.setBounds(189, 156, 198, 22);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblKontuKorrontea = new JLabel("Kontu korrontea");
		lblKontuKorrontea.setBounds(29, 194, 125, 16);
		contentPane.add(lblKontuKorrontea);
		
		account = new JTextField();
		account.setBounds(189, 191, 198, 22);
		contentPane.add(account);
		account.setColumns(10);
		
		JLabel lblTelefonoZenbakia = new JLabel("Telefono zenbakia");
		lblTelefonoZenbakia.setBounds(29, 229, 125, 16);
		contentPane.add(lblTelefonoZenbakia);
		
		tlf = new JTextField();
		tlf.setBounds(189, 226, 198, 22);
		contentPane.add(tlf);
		tlf.setColumns(10);
		
		JButton btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface appInterface;
				Vector<RuralHouse> rh= new Vector<RuralHouse>();
				if((izena!=null) && (eIzena!=null) && (pass1!=null) && (pass2!=null) && (email!=null) && (account!=null) && (tlf!=null)){
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
							}else if (appInterface.erregistratuJabea(izena.getText(), eIzena.getText(), pass1.getText(), pass2.getText(), account.getText(), tlf.getText(), email.getText())){
								desagertu(arg0);
								JFrame login= new LoginWindow();
								login.setVisible(true);
							}
						}catch(Exception e){
							System.out.println(e.toString());
						}
					}
				}
			}
		});
		btnErregistratu.setBounds(229, 276, 143, 25);
		contentPane.add(btnErregistratu);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(29, 23, 56, 16);
		contentPane.add(lblIzena);
		
		izena = new JTextField();
		izena.setBounds(189, 20, 198, 22);
		contentPane.add(izena);
		izena.setColumns(10);
		
		JButton atzera = new JButton("Atzera");
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desagertu(e);
				JFrame login= new LoginWindow();
				login.setVisible(true);
			}
		});
		atzera.setBounds(45, 276, 97, 25);
		contentPane.add(atzera);
	}
	
	private void desagertu(ActionEvent e){
		this.setVisible(false);
	}

}
