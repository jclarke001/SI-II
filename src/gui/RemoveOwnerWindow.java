package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import domain.*;
import businessLogic.ApplicationFacadeInterface;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class RemoveOwnerWindow extends JFrame {

	private JPanel contentPane;
	private JComboBox ownerBox;
	private DefaultComboBoxModel ownerInfo= new DefaultComboBoxModel();
	private JLabel izena;
	private JTextArea replyIzena;
	private JLabel eIzena;
	private JTextArea replyEIZena;
	private JLabel pasahitza;
	private JTextArea replyPasahitza;
	private JLabel telefonoa;
	private JTextArea replyTelefonoa;
	private JLabel email;
	private JTextArea replyEmail;
	
	// Launch the application.

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveOwnerWindow frame = new RemoveOwnerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	 // Create the frame.

	public RemoveOwnerWindow(Vector<Owner> owners) {
		setTitle("Jabea ezabatu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Zein jabe ezabatu nahi duzu?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 41, 470, 29);
		contentPane.add(lblNewLabel);
		
		ownerBox = new JComboBox();	
		ownerBox.setBounds(12, 83, 390, 22);
		contentPane.add(ownerBox);
		
		JLabel izena = new JLabel("Izena");
		izena.setVisible(false);
		izena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		izena.setBounds(12, 144, 56, 16);
		contentPane.add(izena);
		
		JTextArea replyIzena = new JTextArea();
		replyIzena.setEditable(false);
		replyIzena.setVisible(false);
		replyIzena.setBounds(129, 141, 273, 22);
		contentPane.add(replyIzena);
		
		JLabel eIzena = new JLabel("Erabiltzaile izena");
		eIzena.setVisible(false);
		eIzena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eIzena.setBounds(12, 198, 146, 16);
		contentPane.add(eIzena);
		
		JTextArea replyEIzena = new JTextArea();
		replyEIzena.setEditable(false);
		replyEIzena.setVisible(false);
		replyEIzena.setBounds(188, 196, 214, 22);
		contentPane.add(replyEIzena);
		
		JLabel pasahitza = new JLabel("Pasahitza");
		pasahitza.setVisible(false);
		pasahitza.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pasahitza.setBounds(12, 253, 97, 16);
		contentPane.add(pasahitza);
		
		JTextArea replyPasahitza = new JTextArea();
		replyPasahitza.setEditable(false);
		replyPasahitza.setVisible(false);
		replyPasahitza.setBounds(129, 251, 273, 22);
		contentPane.add(replyPasahitza);
		
		JLabel telefonoa = new JLabel("Telefonoa");
		telefonoa.setVisible(false);
		telefonoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		telefonoa.setBounds(12, 310, 104, 16);
		contentPane.add(telefonoa);
		
		JTextArea replyTelefonoa = new JTextArea();
		replyTelefonoa.setEditable(false);
		replyTelefonoa.setVisible(false);
		replyTelefonoa.setBounds(129, 308, 273, 22);
		contentPane.add(replyTelefonoa);
		
		JLabel email = new JLabel("email");
		email.setVisible(false);
		email.setFont(new Font("Tahoma", Font.PLAIN, 16));
		email.setBounds(12, 366, 56, 16);
		contentPane.add(email);
		
		JTextArea replyEmail = new JTextArea();
		replyEmail.setEditable(false);
		replyEmail.setVisible(false);
		replyEmail.setBounds(129, 364, 273, 22);
		contentPane.add(replyEmail);
		
		JButton visualizeButton = new JButton("Bistaratu jabeak");
		visualizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					for(int i=0; i<owners.size(); i++){
						((DefaultComboBoxModel) ownerInfo).addElement(owners.get(i).toString());
					}
					ownerBox.setModel(ownerInfo);
					ownerBox.setVisible(true);
			}
		});
		visualizeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		visualizeButton.setBounds(237, 43, 165, 25);
		contentPane.add(visualizeButton);
		
		
		
		ownerBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ApplicationFacadeInterface app;
					app= MainWindow.getBusinessLogic();
					try{
						String[] name= ownerInfo.getSelectedItem().toString().split(", ");
						Owner owner= app.getOwner(name[1]);
						replyIzena.setText(owner.getName());
						replyEIzena.setText(owner.getLogin());
						replyPasahitza.setText(owner.getPassword());
						replyTelefonoa.setText(owner.getTlfNumber());
						replyEmail.setText(owner.getEmail());
						izena.setVisible(true);
						replyIzena.setVisible(true);
						eIzena.setVisible(true);
						replyEIzena.setVisible(true);
						pasahitza.setVisible(true);
						replyPasahitza.setVisible(true);
						telefonoa.setVisible(true);
						replyTelefonoa.setVisible(true);
						email.setVisible(true);
						replyEmail.setVisible(true);
					}catch(Exception f){
						System.out.println(f.toString());
					}
				}
			});
			
			
			
			
		
		
		JButton removeButton = new JButton("Ezabatu");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade;
				facade= MainWindow.getBusinessLogic();
				try{
					String[] name= ownerInfo.getSelectedItem().toString().split(", ");
					facade.jabeaEzabatu(name[1]);
					sistemaKudeaketaLeihoa(e);
				}catch(Exception g){
					System.out.println(g.toString());
				}
				
			}
		});
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removeButton.setBounds(269, 460, 97, 25);
		contentPane.add(removeButton);
		
		JButton atzera = new JButton("Atzera");
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistemaKudeaketaLeihoa(e);
			}
		});
		atzera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atzera.setBounds(44, 461, 97, 25);
		contentPane.add(atzera);
		
		
		
		
	}
	
	private void sistemaKudeaketaLeihoa(ActionEvent e){
		this.setVisible(false);
	}
}