import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import dataAccess.DataAccessLocal;
import domain.Owner;
import exceptions.DB4oManagerCreationException;

/**
 * <code>ErregistratuTest</code> ezaugarri nagusiak
 */

public class ErregistratuTest extends TestCase {

	private ApplicationFacadeInterface _facadeI;
	private Owner _owner2;

	/**
	 * Eraiki <code>ErregistratuTest</code> instantzia izen egokiarekin.
	 * 
	 * @param izena
	 *            Test case izena.
	 */
	public ErregistratuTest(String izena) {
		super(izena);
	}

	/**
	 * Hasieraketak adierazi.
	 * 
	 * Metodo hau testaren aurretik deitzen da.
	 * @throws DB4oManagerCreationException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws RemoteException 
	 */
	protected void setUp() throws RemoteException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, DB4oManagerCreationException {
		//_facadeI = new FacadeImplementation();
		//_facadeI.setDataAccess(new DataAccessLocal());
		_owner2 = new Owner("Alfredo","AlfredoLog12", "passAlfredo","ESA1234-4567-78-9426-41521322", "987456321", "alfedhitchcock123@gmail.com");
	}

	/**
	 * Test amaierak adierazi.
	 * 
	 * Metodo hau testa egikarituta deitzen da.
	 * @throws RemoteException 
	 */
	protected void tearDown() throws RemoteException {
		//_facadeI.close();
	}

	/**
	 * Testeatu: jabearen datuak
	 */
	public void testJabea(){

		String eIzena = _owner2.getLogin();
		String email = _owner2.getEmail();
		String telf=_owner2.getTlfNumber();
		
		
		//Emaila
		Pattern p2 = Pattern.compile("[a-zA-Z].[0-9].@");
		Matcher m2= p2.matcher(email);
		assertTrue(m2.find());
		//eIzena
		Pattern p3 = Pattern.compile("[a-zA-Z].[0-9]");
		Matcher m3= p3.matcher(eIzena);
		assertTrue(m3.find());
		
		//Telefonoa: zenbaki kopurua
		assertEquals(9,telf.length());
		
	}
	
	/**
	 * Testeatu: Jabea erregistratu den ala ez
	 * @throws Exception 
	 * @throws RemoteException 
	 */
	public void testErregistratuJabea() throws RemoteException, Exception {
		_facadeI = new FacadeImplementation();
		_facadeI.setDataAccess(new DataAccessLocal("initialize"));
		//_owner2 = new Owner("Alfredo","AlfredoLog12", "passAlfredo","ESA1234-456789-7894261-41521322", "987456321", "alfedhitchcock123@gmail.com");
		
		boolean erregistratuDa =_facadeI.erregistratuJabea(_owner2.getName(), _owner2.getLogin(),
				_owner2.getPassword(), _owner2.getPassword(), _owner2.getBankAccount(), _owner2.getTlfNumber(), _owner2.getEmail());
		
		assertTrue(erregistratuDa);
		_facadeI.close();

	}


	public void testErregistratuJabeaExisititzenDa() throws RemoteException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, DB4oManagerCreationException{
		_facadeI = new FacadeImplementation();
		_facadeI.setDataAccess(new DataAccessLocal("open"));
		//_owner2 = new Owner("Alfredo","AlfredoLog12", "passAlfredo","ESA1234-456789-7894261-41521322", "987456321", "alfedhitchcock123@gmail.com");
		
		boolean erregistratuDa =_facadeI.erregistratuJabea(_owner2.getName(), _owner2.getLogin(),
				_owner2.getPassword(), _owner2.getPassword(), _owner2.getBankAccount(), _owner2.getTlfNumber(), _owner2.getEmail());
		
		assertTrue(erregistratuDa);
		_facadeI.close();
	}

	public static Test suite() {
		 TestSuite suite = new TestSuite();
		suite.addTest(new ErregistratuTest("testJabea"));
		suite.addTest(new ErregistratuTest("testErregistratuJabea"));
		suite.addTest(new ErregistratuTest("testErregistratuJabeaExisititzenDa"));


		return suite;
	}

	/**
	 * Main metodo nagusia.
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
}
