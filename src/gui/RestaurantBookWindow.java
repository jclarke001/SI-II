package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import domain.Restaurant;
import domain.Booking;
import businessLogic.ApplicationFacadeInterface;

public class RestaurantBookWindow extends JFrame {

	private JPanel contentPane;
	private JTextField jatetxea;
	private JTextField perKop;
	private JTextField errIzena;
	private JTextField tlf;
	private JTextField data;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestaurantBookWindow frame = new RestaurantBookWindow();
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
	public RestaurantBookWindow(Restaurant restaurant) {
		init(restaurant);
	}
	
	private void init(Restaurant restaurant){
		setTitle("Jatetxea erreserbatu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJatetxea = new JLabel("Jatetxea");
		lblJatetxea.setBounds(46, 75, 76, 16);
		contentPane.add(lblJatetxea);
		
		jatetxea = new JTextField();
		jatetxea.setEditable(false);
		jatetxea.setBounds(194, 72, 398, 22);
		contentPane.add(jatetxea);
		jatetxea.setColumns(10);
		
		JLabel lblPertsonaKopurua = new JLabel("Pertsona kopurua");
		lblPertsonaKopurua.setBounds(46, 141, 130, 16);
		contentPane.add(lblPertsonaKopurua);
		
		perKop = new JTextField();
		perKop.setBounds(194, 138, 116, 22);
		contentPane.add(perKop);
		perKop.setColumns(10);
		
		JLabel lblErreserbarenIzena = new JLabel("Erreserbaren izena");
		lblErreserbarenIzena.setBounds(46, 214, 116, 16);
		contentPane.add(lblErreserbarenIzena);
		
		errIzena = new JTextField();
		errIzena.setBounds(194, 211, 385, 22);
		contentPane.add(errIzena);
		errIzena.setColumns(10);
		
		JLabel lblData = new JLabel("Data (yyyy/mm/dd)");
		lblData.setBounds(46, 278, 116, 16);
		contentPane.add(lblData);
		
		data = new JTextField();
		data.setBounds(194, 275, 195, 22);
		contentPane.add(data);
		data.setColumns(10);
		
		JLabel lblKontaktuzkoTelefonoa = new JLabel("Kontaktuzko telefonoa");
		lblKontaktuzkoTelefonoa.setBounds(46, 336, 130, 16);
		contentPane.add(lblKontaktuzkoTelefonoa);
		
		tlf = new JTextField();
		tlf.setBounds(194, 333, 195, 22);
		contentPane.add(tlf);
		tlf.setColumns(10);
		
		jatetxea.setText(restaurant.toString());
		
		JButton btnBerretsi = new JButton("Berretsi");
		btnBerretsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface app;
				app= MainWindow.getBusinessLogic();
				if((perKop!=null) && (errIzena!=null) && (data!=null) && (tlf!=null)){
					try{
						String izena=jatetxea.getText().toString().split(", ")[0];
						Restaurant errJatetxea=app.getRestaurantByName(izena);
						Vector<Booking> jb= errJatetxea.getBookings();
						if(Integer.parseInt(perKop.getText())>errJatetxea.getCapacity()){
							JFrame adviseWindow= new AdviseWindow("Ez dago hainbeste lekurik jatetxe honetan!");
							adviseWindow.setVisible(true);
						}else{
							String[] dataArray= data.getText().toString().split("/");
							Date d= new Date(Integer.parseInt(dataArray[0]), Integer.parseInt(dataArray[1]), Integer.parseInt(dataArray[2]));
							Vector<Booking> dataAztertu= new Vector<Booking>();
							for(int i=0; i<jb.size(); i++){
								if(jb.get(i).getBookingDate().compareTo(d)==0){
									dataAztertu.add(jb.get(i));
								}
							}
							int okupatutakoKop=0;
							for(int j=0; j<dataAztertu.size(); j++){
								okupatutakoKop= okupatutakoKop + dataAztertu.get(j).getPerKop();
							}
							System.out.println(okupatutakoKop);
							if(okupatutakoKop==errJatetxea.getCapacity()){
								JFrame beteta= new AdviseWindow("Beteta dago!");
								beteta.setVisible(true);
							}else if((okupatutakoKop+Integer.parseInt(perKop.getText()))>errJatetxea.getCapacity()){
								JFrame lekurikEz= new AdviseWindow("Ez dago hainbeste leku jatetxean egun horretan..");
								lekurikEz.setVisible(true);
							}else{
								app.jatetxeaErreserbatu(errJatetxea, perKop.getText(), Launcher.erabiltzailea, errIzena.getText(), d, tlf.getText());
								desagertu(e);
							}
						}
					}catch(Exception f){
						f.printStackTrace();
					}
					
				}
			}
		});
		btnBerretsi.setBounds(482, 426, 110, 34);
		contentPane.add(btnBerretsi);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desagertu(arg0);
			}
		});
		btnAtzera.setBounds(122, 426, 110, 34);
		contentPane.add(btnAtzera);
	}
	
	private void desagertu(ActionEvent arg0){
		this.setVisible(false);
	}
}
