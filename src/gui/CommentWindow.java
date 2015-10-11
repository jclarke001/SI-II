package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import domain.RuralHouse;
import domain.Iruzkina;

import businessLogic.ApplicationFacadeInterface;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CommentWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommentWindow frame = new CommentWindow();
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
	public CommentWindow(Vector<Iruzkina> iruzkinak) {
		try{
			//lehenengoIruzkinaBistaratu();
			iruzkinakBistaratu(iruzkinak);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		
	}
	
	private void iruzkinakBistaratu(Vector<Iruzkina> iruzkinak){
		int i=0;
		
		setTitle("Iruzkinak");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField erabiltzaile = new JTextField("");
		erabiltzaile.setEditable(false);
		erabiltzaile.setBounds(37, 13, 479, 43);
		contentPane.add(erabiltzaile);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(37, 69, 479, 223);
		contentPane.add(textArea);
		
		
		
		JLabel kontagailu = new JLabel("");
		kontagailu.setVisible(false);
		kontagailu.setBounds(332, 326, 54, 48);
		contentPane.add(kontagailu);
		
		erabiltzaile.setText(iruzkinak.get(0).getName() + "             " + iruzkinak.get(0).getData().toString());
		textArea.setText(iruzkinak.get(0).getComment());
		kontagailu.setText("0/" + iruzkinak.size());
		
		JButton atzera = new JButton("Atzera");
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atzeraJoan(e);
			}
		});
		atzera.setBounds(37, 338, 97, 25);
		contentPane.add(atzera);
					
		
		JButton aurrekoa = new JButton("Aurrekoa");
		aurrekoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					erabiltzaile.setText(iruzkinak.get(Integer.parseInt(kontagailu.getText().split("/")[0])-1).getName()+ "             " + iruzkinak.get(Integer.parseInt(kontagailu.getText().split("/")[0])-1).getData().toString());
					textArea.setText(iruzkinak.get(Integer.parseInt(kontagailu.getText().split("/")[0])-1).getComment());
					kontagailu.setText(Integer.toString(Integer.parseInt(kontagailu.getText().split("/")[0])-1) + "/" + iruzkinak.size());
				}catch(Exception e){
					JFrame adviseWindow= new AdviseWindow("Landetxe honen idatzitako lehenengo iruzkina da, ez daude aurretikan gehiagorik!");
					adviseWindow.setVisible(true);
				}
			}
		});
		aurrekoa.setBounds(186, 338, 97, 25);
		contentPane.add(aurrekoa);
		
		JButton hurrengoa = new JButton("Hurrengoa");
		hurrengoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					erabiltzaile.setText(iruzkinak.get(Integer.parseInt(kontagailu.getText().split("/")[0])+1).getName()+ "             " + iruzkinak.get(Integer.parseInt(kontagailu.getText().split("/")[0])+1).getData().toString());
					textArea.setText(iruzkinak.get(Integer.parseInt(kontagailu.getText().split("/")[0])+1).getComment());
					kontagailu.setText(Integer.toString(Integer.parseInt(kontagailu.getText().split("/")[0])+1) + "/" + iruzkinak.size());
				}catch(Exception e){
					JFrame adviseWindow= new AdviseWindow("Ez daude landetxe honen inguruko iruzkin gehiagorik!");
					adviseWindow.setVisible(true);
				}
			}
		});
		hurrengoa.setBounds(419, 338, 97, 25);
		contentPane.add(hurrengoa);
			
			
	}
	private void atzeraJoan(ActionEvent e){
		this.setVisible(false);
	}
}
