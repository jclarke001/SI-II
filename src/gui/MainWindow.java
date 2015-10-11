package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import configuration.ConfigXML;
import businessLogic.ApplicationFacadeInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private JButton boton2 = null;
	private JButton boton3 = null;
	protected JButton boton4 = null;

    private static ApplicationFacadeInterface appFacadeInterface;
	
	public static ApplicationFacadeInterface getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterface afi){
		appFacadeInterface=afi;
	}
	protected JLabel lblNewLabel;
	

	/**
	 * This is the default constructor
	 */
	public MainWindow() {
		super();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				try {
					if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(512, 709);
		this.setContentPane(getJContentPane());
		this.setTitle("Landetxeak");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			
			JButton boton4 = new JButton("Landetxea sortu");
			boton4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Launcher.pribilegiatu){
						Launcher.mainWindow.setVisible(false);
						Launcher.createRuralHouse.setVisible(true);
					}
				}
			});
			
			JButton boton5 = new JButton("Landetxea bilatu");
			boton5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Launcher.mainWindow.setVisible(false);
					JFrame searchWindow= new SearchWindow();
					searchWindow.setVisible(true);
				}
			});
			GroupLayout gl_jContentPane = new GroupLayout(jContentPane);
			gl_jContentPane.setHorizontalGroup(
				gl_jContentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_jContentPane.createSequentialGroup()
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(boton5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, gl_jContentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(getLblNewLabel(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
								.addComponent(getBoton3(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
								.addComponent(getBoton2(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
								.addComponent(getBoton1(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
							.addComponent(boton4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_jContentPane.setVerticalGroup(
				gl_jContentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addComponent(getLblNewLabel(), GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(getBoton3(), GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getBoton2(), GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getBoton1(), GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(boton4, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(boton5, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
			);
			jContentPane.setLayout(gl_jContentPane);
		}
		return jContentPane;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton1() {
		if (boton1 == null) {
			boton1 = new JButton();
			boton1.setText("Landetxea erreserbatu");
			boton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la univerdad
					JFrame a = new BookRuralHouseGUI();
					a.setVisible(true);
				}
			});
		}
		return boton1;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (boton2 == null) {
			boton2 = new JButton();
			boton2.setText("Ofertak sortu");
			boton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Launcher.pribilegiatu){
						// C?digo cedido por la universidad
						JFrame a = new SetAvailabilityGUI();
						a.setVisible(true);
					}
				}
			});
		}
		return boton2;
	}
	
	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setText("Ofertak kontsultatu");
			boton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la universidad
					//JFrame a = new QueryAvailabilityWindow();
					JFrame a = new QueryAvailabilityGUI();

					a.setVisible(true);
				}
			});
		}
		return boton3;
	}
	

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Aukeratu ezazu:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

