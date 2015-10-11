package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import businessLogic.ApplicationFacadeInterface;

public class CreateRuralHouse extends JFrame {

	private JPanel contentPane;
	private JTextField hiria;
	private JTextField izena;
	private JTextField helbidea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateRuralHouse frame = new CreateRuralHouse();
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
	public CreateRuralHouse() {
		setTitle("Landetxea sortu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Izena");
		lblNewLabel.setBounds(31, 24, 56, 16);
		contentPane.add(lblNewLabel);
		
		izena = new JTextField();
		izena.setBounds(202, 21, 231, 22);
		contentPane.add(izena);
		izena.setColumns(10);
		
		JLabel helbide = new JLabel("Helbidea");
		helbide.setBounds(31, 69, 56, 16);
		contentPane.add(helbide);
		
		helbidea = new JTextField();
		helbidea.setBounds(202, 66, 231, 22);
		contentPane.add(helbidea);
		helbidea.setColumns(10);
		
		JLabel deskribapena = new JLabel("Deskribapena");
		deskribapena.setBounds(31, 120, 108, 16);
		contentPane.add(deskribapena);
		
		JLabel city = new JLabel("Hiria");
		city.setBounds(31, 239, 56, 16);
		contentPane.add(city);
		
		hiria = new JTextField();
		hiria.setBounds(202, 236, 231, 22);
		contentPane.add(hiria);
		hiria.setColumns(10);
		
		JTextArea deskrib = new JTextArea();
		deskrib.setLineWrap(true);
		deskrib.setBounds(202, 117, 231, 91);
		contentPane.add(deskrib);
		
		JButton sortu = new JButton("Sortu");
		sortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface appInterface;
				if((helbidea!=null) && (deskrib!=null) && (izena!=null) && (hiria!=null)){
					appInterface= MainWindow.getBusinessLogic();
					try{
						appInterface.landetxeaSortu(Launcher.erabiltzailea, izena.getText(), helbidea.getText(), deskrib.getText(), hiria.getText());
						Launcher.createRuralHouse.setVisible(false);
						Launcher.mainWindow.setVisible(true);
					} catch (Exception e) {
						
					}
				}
				
			}
		});
		sortu.setBounds(281, 289, 97, 25);
		contentPane.add(sortu);
		
		JButton atzera = new JButton("Atzera");
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Launcher.createRuralHouse.setVisible(false);
				Launcher.mainWindow.setVisible(true);				
			}
		});
		atzera.setBounds(67, 289, 97, 25);
		contentPane.add(atzera);
		
		
		
		
	}
}
