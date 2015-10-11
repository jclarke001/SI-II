package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import businessLogic.ApplicationFacadeInterface;

public class TypeRegister extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	//buttonGroup.add(rdbtnJabea);
	//buttonGroup.add(rdbtnTurista);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TypeRegister frame = new TypeRegister();
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
	public TypeRegister() {
		setTitle("Nola erregistratu?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAukeratuZureKontu = new JLabel("Aukeratu zure kontu mota:");
		lblAukeratuZureKontu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAukeratuZureKontu.setBounds(77, 25, 208, 30);
		contentPane.add(lblAukeratuZureKontu);
		
		JTextArea infoJabea = new JTextArea();
		infoJabea.setLineWrap(true);
		infoJabea.setEnabled(false);
		infoJabea.setText("Jabeak: Bere landetxeen kudeaketa besteen landetxeetan erreserbak egiteko aukera.");
		infoJabea.setBounds(60, 103, 311, 72);
		contentPane.add(infoJabea);
		
		JTextArea infoTurista = new JTextArea();
		infoTurista.setEnabled(false);
		infoTurista.setLineWrap(true);
		infoTurista.setText("Turistak: Landetxeak erreserbatzeko aukera.");
		infoTurista.setBounds(60, 103, 311, 72);
		contentPane.add(infoTurista);
		
		JRadioButton rdbtnJabea = new JRadioButton("Jabea");
		rdbtnJabea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnJabea.isSelected()){
					infoTurista.setEnabled(false);
					infoTurista.setVisible(false);
					infoJabea.setEnabled(true);
					infoJabea.setVisible(true);
				}
			}
		});
		buttonGroup.add(rdbtnJabea);
		rdbtnJabea.setBounds(87, 64, 127, 25);
		contentPane.add(rdbtnJabea);
		
		
		
		JRadioButton rdbtnTurista = new JRadioButton("Turista");
		rdbtnTurista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnTurista.isSelected()){
					infoJabea.setEnabled(false);
					infoJabea.setVisible(false);
					infoTurista.setEnabled(true);
					infoTurista.setVisible(true);
				}
			}
		});
		buttonGroup.add(rdbtnTurista);
		rdbtnTurista.setBounds(218, 64, 127, 25);
		contentPane.add(rdbtnTurista);
		
		
		
		
		
		JButton btnNewButton = new JButton("Berretsi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(rdbtnTurista.isSelected()){
					desagertu(arg0);
					JFrame registroTurista= new RegisterTurist();
					registroTurista.setVisible(true);
					//Launcher.registerTurist.setVisible(true);
				} else if(rdbtnJabea.isSelected()){
					desagertu(arg0);
					JFrame registroJabea= new RegisterOwner();
					registroJabea.setVisible(true);
					//Launcher.registerOwner.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(244, 204, 127, 36);
		contentPane.add(btnNewButton);
		
		JButton atzera = new JButton("Atzera");
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desagertu(e);
				JFrame login= new LoginWindow();
				login.setVisible(true);
			}
		});
		atzera.setBounds(60, 204, 107, 36);
		contentPane.add(atzera);
	}
	
	private void desagertu(ActionEvent e){
		this.setVisible(false);
	}

}
