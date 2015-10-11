package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import businessLogic.ApplicationFacadeInterface;
import javax.swing.JLabel;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField erabiltzailea;
	private JPasswordField pasahitza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
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
	public LoginWindow() {
		setTitle("Identifikatu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		erabiltzailea = new JTextField();
		erabiltzailea.setBounds(196, 46, 211, 22);
		contentPane.add(erabiltzailea);
		erabiltzailea.setColumns(10);
		
		pasahitza = new JPasswordField();
		pasahitza.setBounds(196, 103, 211, 22);
		contentPane.add(pasahitza);
		
		JButton btnNewButton = new JButton("Logeatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ApplicationFacadeInterface appInterface;
				appInterface= MainWindow.getBusinessLogic();
				if((erabiltzailea!=null) && (pasahitza!=null)){
					try{
						if(appInterface.logeatu(erabiltzailea.getText().toString(), pasahitza.getText().toString())){
							Launcher.erabiltzailea= erabiltzailea.getText();
							desagertu(arg0);
							Launcher.mainWindow.setVisible(true);
						}else if(appInterface.logeatuAdmin(erabiltzailea.getText(), pasahitza.getText())){
							desagertu(arg0);
							Launcher.adminWindow.setVisible(true);
						}else{
							JFrame datuOkerrak= new AdviseWindow("Erabiltzaile izena edo pasahitza okerra!");
							datuOkerrak.setVisible(true);
						}
					}catch (Exception e){
					System.out.println(e.toString());
					}
				}
			}
		});
		btnNewButton.setBounds(269, 188, 97, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Erregistratu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//EGITEKO!!!!!!!!!!!!!!!!!!!!!!!!!!!
				Launcher.loginWindow.setVisible(false);
				JFrame registroMota= new TypeRegister();
				registroMota.setVisible(true);
				//Launcher.typeRegister.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(45, 188, 115, 40);
		contentPane.add(btnNewButton_1);
		
		JLabel txtrEzDuzuKonturik = new JLabel();
		txtrEzDuzuKonturik.setFont(new Font("Monospaced", Font.ITALIC, 11));
		txtrEzDuzuKonturik.setText("Ez duzu konturik?");
		txtrEzDuzuKonturik.setBounds(31, 157, 140, 22);
		contentPane.add(txtrEzDuzuKonturik);
		
		JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile Izena");
		lblErabiltzaileIzena.setBounds(12, 49, 148, 16);
		contentPane.add(lblErabiltzaileIzena);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(12, 106, 103, 16);
		contentPane.add(lblPasahitza);
	}
	private void desagertu(ActionEvent e){
		this.setVisible(false);
	}
}
