package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import domain.*;
import java.util.Vector;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow frame = new AdminWindow();
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
	public AdminWindow() {
		setTitle("Sistemaren kudeaketa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jEzabatu = new JButton("Jabea Ezabatu");
		jEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface app;
				app= MainWindow.getBusinessLogic();
				try{
					Vector<Owner> owners= app.getOwners();
					for(int i=0; i<owners.size(); i++){
						System.out.println(owners.get(i).toString());
					}
					JFrame removeOwnerWindow= new RemoveOwnerWindow(owners);
					removeOwnerWindow.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		jEzabatu.setBounds(0, 0, 796, 78);
		contentPane.add(jEzabatu);
	}
}
