package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdviseWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdviseWindow frame = new AdviseWindow();
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
	public AdviseWindow(String message) {
		setTitle("ERROR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea mezua = new JTextArea();
		mezua.setEditable(false);
		mezua.setBackground(SystemColor.control);
		mezua.setLineWrap(true);
		mezua.setBounds(30, 41, 372, 134);
		contentPane.add(mezua);
		
		mezua.setText(message);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desagertuJFrame(e);
			}
		});
		btnOk.setBounds(169, 188, 97, 25);
		contentPane.add(btnOk);
	}
	
	private void desagertuJFrame(ActionEvent e){
		this.setVisible(false);
	}
}
