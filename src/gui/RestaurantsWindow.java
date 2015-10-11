package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.util.Vector;

import domain.Restaurant;

import javax.swing.DefaultComboBoxModel;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RestaurantsWindow extends JFrame {

	private JPanel contentPane;
	private JTextField izena;
	private JTextField capacity;
	private JTextField type;
	private JTextField prezioa;
	private JTextField tlf;
	private DefaultComboBoxModel jatetxeak= new DefaultComboBoxModel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestaurantsWindow frame = new RestaurantsWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public RestaurantsWindow(Vector<Restaurant> restaurants) {
		bistaratu(restaurants);
	}
	
	private void bistaratu(Vector<Restaurant> restaurants){
		setTitle("Jatetxeak");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(58, 74, 446, 22);
		contentPane.add(comboBox);
		
		JLabel lblAukeratuEzazuJatetxe = new JLabel("Aukeratu ezazu jatetxe bat:");
		lblAukeratuEzazuJatetxe.setBounds(58, 30, 216, 16);
		contentPane.add(lblAukeratuEzazuJatetxe);
		
		JLabel lblJatetxearenIzena = new JLabel("Jatetxearen izena");
		lblJatetxearenIzena.setBounds(58, 140, 134, 16);
		contentPane.add(lblJatetxearenIzena);
		
		izena = new JTextField();
		izena.setEditable(false);
		izena.setVisible(false);
		izena.setBounds(228, 137, 276, 22);
		contentPane.add(izena);
		izena.setColumns(10);
		
		JLabel lblJatetxearenKapazitatea = new JLabel("Jatetxearen kapazitatea");
		lblJatetxearenKapazitatea.setBounds(58, 192, 146, 16);
		contentPane.add(lblJatetxearenKapazitatea);
		
		capacity = new JTextField();
		capacity.setEditable(false);
		capacity.setVisible(false);
		capacity.setBounds(228, 189, 276, 22);
		contentPane.add(capacity);
		capacity.setColumns(10);
		
		JLabel lblJatetxeMota = new JLabel("Jatetxe mota");
		lblJatetxeMota.setBounds(58, 247, 134, 16);
		contentPane.add(lblJatetxeMota);
		
		type = new JTextField();
		type.setEditable(false);
		type.setVisible(false);
		type.setBounds(228, 244, 276, 22);
		contentPane.add(type);
		type.setColumns(10);
		
		JLabel lblPrezioapertsonaBakoitza = new JLabel("Prezioa(pertsona bakoitza)");
		lblPrezioapertsonaBakoitza.setBounds(58, 304, 153, 16);
		contentPane.add(lblPrezioapertsonaBakoitza);
		
		prezioa = new JTextField();
		prezioa.setEditable(false);
		prezioa.setVisible(false);
		prezioa.setBounds(228, 304, 276, 22);
		contentPane.add(prezioa);
		prezioa.setColumns(10);
		
		JLabel lblTelefonoa = new JLabel("Telefonoa");
		lblTelefonoa.setBounds(58, 363, 92, 16);
		contentPane.add(lblTelefonoa);
		
		tlf = new JTextField();
		tlf.setEditable(false);
		tlf.setVisible(false);
		tlf.setBounds(228, 360, 276, 22);
		contentPane.add(tlf);
		tlf.setColumns(10);
		
		JButton btnIkusi = new JButton("Ikusi");
		btnIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!restaurants.isEmpty()){
				for(int i=0; i<restaurants.size(); i++){
					((DefaultComboBoxModel) jatetxeak).addElement(restaurants.get(i).toString());
				}
				comboBox.setModel(jatetxeak);
				
				}else{
					JFrame adviseWindow= new AdviseWindow("Hiri/Herri honek ez ditu jatetxerik!");
					adviseWindow.setVisible(true);
				}
			}
		});
		btnIkusi.setBounds(390, 437, 114, 37);
		contentPane.add(btnIkusi);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desagertu(e);
			}
		});
		btnAtzera.setBounds(53, 437, 123, 37);
		contentPane.add(btnAtzera);
		
		JButton btnErresarbatu = new JButton("Erreserbatu");
		btnErresarbatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface app;
				app= MainWindow.getBusinessLogic();
				try{
					Restaurant jatetxe= app.getRestaurantByName(comboBox.getSelectedItem().toString().split(", ")[0]);
					JFrame bookWindow= new RestaurantBookWindow(jatetxe);
					bookWindow.setVisible(true);
				}catch(Exception f){
					f.printStackTrace();
				}
				
			}
		});
		btnErresarbatu.setBounds(228, 437, 100, 37);
		contentPane.add(btnErresarbatu);
	
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getComponentCount()>0){
					ApplicationFacadeInterface app;
					app= MainWindow.getBusinessLogic();		
					try{
						String name=jatetxeak.getSelectedItem().toString().split(", ")[0];
						System.out.println(name);
						Restaurant restaurant= app.getRestaurantByName(name);
						izena.setText(restaurant.getName());
						capacity.setText(Integer.toString(restaurant.getCapacity()));
						type.setText(restaurant.getType());
						prezioa.setText(Double.toString(restaurant.getPrice()));
						tlf.setText(restaurant.getTelephone());
						izena.setVisible(true);
						capacity.setVisible(true);
						type.setVisible(true);
						prezioa.setVisible(true);
						tlf.setVisible(true);					
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{
					JFrame adviseWindow= new AdviseWindow("Hiri/Herri honek ez ditu jatetxerik!");
					adviseWindow.setVisible(true);
				}
			}
		});
		
	}
	
	private void desagertu(ActionEvent e){
		this.setVisible(false);
	}
}
