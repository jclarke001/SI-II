package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import businessLogic.ApplicationFacadeInterface;

public class writeCommentWindow extends JFrame {

	private JPanel contentPane;
	private JTextField erabiltzaile;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					writeCommentWindow frame = new writeCommentWindow();
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
	public writeCommentWindow(String houseName) {
		
		try{
			iruzkinaIdatzi(houseName);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void iruzkinaIdatzi(String houseName){
		
		setTitle("Zure iruzkina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Erabiltzaile izena");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(12, 28, 145, 36);
		contentPane.add(lblUsername);
		
		erabiltzaile = new JTextField();
		erabiltzaile.setBounds(194, 36, 205, 22);
		contentPane.add(erabiltzaile);
		erabiltzaile.setColumns(10);
		
		JLabel lblOpinion = new JLabel("Iritzia");
		lblOpinion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOpinion.setBounds(12, 95, 100, 16);
		contentPane.add(lblOpinion);
		
		JTextArea iritzia = new JTextArea();
		iritzia.setLineWrap(true);
		iritzia.setBounds(194, 93, 327, 154);
		contentPane.add(iritzia);
		
		JButton btnSave = new JButton("Gorde");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((erabiltzaile.getText()!=null) && (iritzia.getText())!=null){
					ApplicationFacadeInterface app;
					app= MainWindow.getBusinessLogic();
					try{
						app.saveComment(houseName, erabiltzaile.getText(), iritzia.getText());
						leihoaDesagertu(e);	
					}catch(Exception e1){
						System.out.println(e1.toString());
					}
				}
			}
		});
		btnSave.setBounds(302, 292, 97, 25);
		contentPane.add(btnSave);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leihoaDesagertu(e);
			}
		});
		btnAtzera.setBounds(72, 292, 97, 25);
		contentPane.add(btnAtzera);
	}
	
	private void leihoaDesagertu(ActionEvent e){
		this.setVisible(false);
	}
}
