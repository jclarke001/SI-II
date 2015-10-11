package gui;

import java.awt.Color;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;


import javax.swing.UIManager;

import configuration.ConfigXML;
import dataAccess.DataAccessLocal;
import dataAccess.DataAccessRemote;
import exceptions.DB4oManagerCreationException;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;

public class Launcher {
	
	public static LoginWindow loginWindow;
	public static MainWindow mainWindow;
	public static TypeRegister typeRegister;
	public static RegisterTurist registerTurist;
	public static RegisterOwner registerOwner;
	public static CreateRuralHouse createRuralHouse;
	public static SearchWindow searchWindow;
	public static AdminWindow adminWindow;
	public static RemoveOwnerWindow removeOwnerWindow;
	public static CommentWindow commentWindow;
	public static writeCommentWindow writeCommentWindow;
	public static String erabiltzailea;
	public static boolean pribilegiatu;
	public static int id;
	public static String houseName;
	
	public static void main(String[] args) {
		
		mainWindow=new MainWindow();
		mainWindow.setVisible(false);
		typeRegister=new TypeRegister();
		typeRegister.setVisible(false);
		registerTurist= new RegisterTurist();
		registerTurist.setVisible(false);
		registerOwner= new RegisterOwner();
		registerOwner.setVisible(false);
		loginWindow= new LoginWindow();
		loginWindow.setVisible(true);
		createRuralHouse= new CreateRuralHouse();
		createRuralHouse.setVisible(false);
		searchWindow= new SearchWindow();
		searchWindow.setVisible(false);
		adminWindow= new AdminWindow();
		adminWindow.setVisible(false);
		ConfigXML c=ConfigXML.getInstance();


		try {
			
			ApplicationFacadeInterface appFacadeInterface;
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			if (c.isBusinessLogicLocal()) {
				
			 appFacadeInterface=new FacadeImplementation();
				
				if (c.isDatabaseLocal()) 
					 appFacadeInterface.setDataAccess(new DataAccessLocal());
			    else 
			    	 appFacadeInterface.setDataAccess(new DataAccessRemote());
			}
			
			else {
				
				System.setProperty("java.security.policy", c.getJavaPolicyPath());				
				System.setSecurityManager(new RMISecurityManager());
				
				final String businessLogicNode = c.getBusinessLogicNode();
				// Remote service name
				String serviceName = "/"+c.getServiceRMI();
				// RMI server port number
				int portNumber = Integer.parseInt(c.getPortRMI());
				// RMI server host IP IP 
				
				appFacadeInterface=(ApplicationFacadeInterface) Naming.lookup("rmi://"+ businessLogicNode + ":" + portNumber + serviceName);
			} 
			MainWindow.setBussinessLogic(appFacadeInterface);

		} catch (java.rmi.ConnectException e) {
			mainWindow.lblNewLabel.setText("No business logic: Run BusinessLogicServer first!!");
			mainWindow.lblNewLabel.setForeground(Color.RED);
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (java.rmi.NotBoundException e) {
			mainWindow.lblNewLabel.setText("No business logic: Maybe problems running BusinessLogicServer");
			mainWindow.lblNewLabel.setForeground(Color.RED);
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (com.db4o.ext.DatabaseFileLockedException e) {
			mainWindow.lblNewLabel.setText("Database locked: Do not run BusinessLogicServer or BusinessLogicServer!!");
			mainWindow.lblNewLabel.setForeground(Color.RED);		
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (DB4oManagerCreationException e) {
			mainWindow.lblNewLabel.setText("No database: Run DB4oManagerServer first!!");
			mainWindow.lblNewLabel.setForeground(Color.RED);		
			System.out.println("Error in StartWindow: "+e.toString());

			
		}catch (Exception e) {
			mainWindow.lblNewLabel.setText("Error: "+e.toString());
			mainWindow.lblNewLabel.setForeground(Color.RED);		
			System.out.println("Error in StartWindow: "+e.toString());
		}
		//a.pack();


	}

}
