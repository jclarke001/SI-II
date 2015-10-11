package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import businessLogic.ApplicationFacadeInterface;
import domain.*;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class SearchWindow extends JFrame {

	private JPanel contentPane;
	private JLabel lblHiria;
	private JTextField city;
	private JComboBox comboBox;
	private DefaultComboBoxModel HouseInfo= new DefaultComboBoxModel();
	private JButton btnBilatu;
	private JLabel izena;
	private JLabel helbidea;
	private JLabel deskribapena;
	private JLabel eskaintzak;
	private JTextArea replyIzena;
	private JTextArea replyHelbidea;
	private JTextArea replyDeskribapena;
	private JLabel replyEskaintzak;
	private JLabel tlf;
	private JLabel email;
	private JTextArea replyTlf;
	private JTextArea replyEmail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchWindow frame = new SearchWindow();
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
	public SearchWindow() {
		setTitle("Landetxea bilatu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHiria = new JLabel("Hiria");
		lblHiria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHiria.setBounds(50, 57, 56, 16);
		contentPane.add(lblHiria);
		
		city = new JTextField();
		city.setBounds(291, 54, 304, 22);
		contentPane.add(city);
		city.setColumns(10);
		
		comboBox= new JComboBox();
		
		comboBox.setBounds(50, 86, 545, 22);
		contentPane.add(comboBox);
		
		btnBilatu = new JButton("Bilatu");
		btnBilatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface appInterface;
				if (city!=null){
					appInterface=MainWindow.getBusinessLogic();
					try{
						if(comboBox.getComponentCount()>0){
						comboBox.removeAllItems();
						}
						Vector<RuralHouse> landetxeak=appInterface.getRuralHouses(city.getText());
						if(landetxeak.get(0).getName()==null){
							JFrame ezDago= new AdviseWindow("Ez dago landetxerik hiri/herri horretan..");
							ezDago.setVisible(true);
						}else{
							for(int i=0; i<landetxeak.size(); i++){
								((DefaultComboBoxModel) HouseInfo).addElement(landetxeak.get(i).toString());
							}
							comboBox.setModel(HouseInfo);
						}
					}catch(Exception e){
						System.out.println(e.toString());
					}
				}
			}
		});
		btnBilatu.setBounds(179, 633, 97, 25);
		contentPane.add(btnBilatu);
		
		JLabel izena = new JLabel("Izena");
		izena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		izena.setVisible(false);
		izena.setBounds(50, 141, 56, 16);
		contentPane.add(izena);
		
		JTextArea replyIzena = new JTextArea();
		replyIzena.setVisible(false);
		replyIzena.setEditable(false);
		replyIzena.setBounds(179, 135, 416, 22);
		contentPane.add(replyIzena);
		
		JLabel helbidea = new JLabel("Helbidea");
		helbidea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		helbidea.setVisible(false);
		helbidea.setBounds(50, 186, 56, 16);
		contentPane.add(helbidea);
		
		JTextArea replyHelbidea = new JTextArea();
		replyHelbidea.setVisible(false);
		replyHelbidea.setEditable(false);
		replyHelbidea.setBounds(179, 180, 416, 22);
		contentPane.add(replyHelbidea);
		
		JLabel deskribapena = new JLabel("Deskribapena");
		deskribapena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deskribapena.setVisible(false);
		deskribapena.setBounds(50, 232, 117, 16);
		contentPane.add(deskribapena);
		
		JTextArea replyDeskribapena = new JTextArea();
		replyDeskribapena.setLineWrap(true);
		replyDeskribapena.setVisible(false);
		replyDeskribapena.setEditable(false);
		replyDeskribapena.setBounds(179, 226, 416, 210);
		contentPane.add(replyDeskribapena);
		
		JLabel tlf = new JLabel("Telefonoa");
		tlf.setVisible(false);
		tlf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tlf.setBounds(50, 473, 97, 16);
		contentPane.add(tlf);
		
		JTextArea replyTlf = new JTextArea();
		replyTlf.setVisible(false);
		replyTlf.setEditable(false);
		replyTlf.setBounds(179, 467, 416, 22);
		contentPane.add(replyTlf);
		
		JLabel email = new JLabel("email-a");
		email.setVisible(false);
		email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		email.setBounds(50, 531, 85, 16);
		contentPane.add(email);
		
		JTextArea replyEmail = new JTextArea();
		replyEmail.setVisible(false);
		replyEmail.setEditable(false);
		replyEmail.setBounds(179, 529, 416, 22);
		contentPane.add(replyEmail);
		
		
		
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface app;
				app= MainWindow.getBusinessLogic();
						try{
							String[] info= HouseInfo.getSelectedItem().toString().split("=>");							
							RuralHouse house= app.getRuralHouse(info[0]);
							replyIzena.setText(house.getName());
							replyHelbidea.setText(house.getAddress());
							replyDeskribapena.setText(house.getDescription());
							replyTlf.setText(house.getOwner().getTlfNumber());
							replyEmail.setText(house.getOwner().getEmail());
							izena.setVisible(true);
							helbidea.setVisible(true);
							deskribapena.setVisible(true);
							tlf.setVisible(true);
							email.setVisible(true);
							replyIzena.setVisible(true);
							replyHelbidea.setVisible(true);
							replyDeskribapena.setVisible(true);
							replyTlf.setVisible(true);
							replyEmail.setVisible(true);
						}catch(Exception e){
							System.out.println(e.toString());
						}
			}
			});
		JButton iritziaSortu = new JButton("Iritzia eman");
		iritziaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Launcher.houseName=comboBox.getSelectedItem().toString().split("=>")[0];
				//Launcher.writeCommentWindow.setVisible(true);
				JFrame writeCommentWindow= new writeCommentWindow(comboBox.getSelectedItem().toString().split("=>")[0]);
				writeCommentWindow.setVisible(true);
			}
		});
		iritziaSortu.setBounds(291, 633, 136, 25);
		contentPane.add(iritziaSortu);
		
		JButton iritziak = new JButton("Iritziak bistaratu");
		iritziak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String houseName= comboBox.getSelectedItem().toString().split("=>")[0];
				ApplicationFacadeInterface app;
				app= MainWindow.getBusinessLogic();
				try{
					RuralHouse etxea= app.getRuralHouse(houseName);
					Vector<Iruzkina> iruzkinak= etxea.getComments();
					JFrame commentWindow= new CommentWindow(iruzkinak);
					commentWindow.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
		iritziak.setBounds(439, 633, 156, 25);
		contentPane.add(iritziak);
		
		JButton atzera = new JButton("Atzera");
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desagertu(arg0);
				Launcher.mainWindow.setVisible(true);
			}
		});
		atzera.setBounds(50, 633, 97, 25);
		contentPane.add(atzera);
		
		JButton btnNewButton = new JButton("Jatetxeak bistaratu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String city= comboBox.getSelectedItem().toString().split(":")[1];
				ApplicationFacadeInterface app;
				app= MainWindow.getBusinessLogic();
				try{
					Vector<Restaurant> restaurants= app.getRestaurants(city.getText());
					JFrame restaurantWindow= new RestaurantsWindow(restaurants);
					restaurantWindow.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(439, 592, 156, 25);
		contentPane.add(btnNewButton);
	
	/*JButton comment = new JButton("Iruzkinak..");
		comment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		comment.setBounds(498, 633, 97, 25);
		contentPane.add(comment);*/
	}
	
	private void desagertu (ActionEvent e){
		this.setVisible(false);
	}
}
