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
import domain.Turist;
import domain.RuralHouse;
import exceptions.DB4oManagerCreationException;

/**
 * <code>IritziaTest</code> ezaugarri nagusiak
 */

public class IritziaTest extends TestCase {
	private ApplicationFacadeInterface _facadeI;
	private Turist _turist;
	
	public IritziaTest(String izena) {
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
		_facadeI = new FacadeImplementation();
		_facadeI.setDataAccess(new DataAccessLocal());
		_turist = new Turist("Guiri", "Guirilog", "passGuiri", "guiri@gmail.com");
	}
	
	/**
	 * Test amaierak adierazi.
	 * 
	 * Metodo hau testa egikarituta deitzen da.
	 * @throws RemoteException 
	 */
	protected void tearDown() throws RemoteException {
		_facadeI.close();
	}
	
	/**
	 * Testeatu: turistaren datuak.
	 */
	public void testTurist(){

		String tname = _turist.getName();
		String tlogin = _turist.getLogin();
		//String tpassword = _turist.getPassword();
		String temail = _turist.getEmail();
		
		//Izena
		Pattern p1 = Pattern.compile("[a-zA-Z]");
		Matcher m1 = p1.matcher(tname);
		assertTrue(m1.find());
		//Logina
		Pattern p2 = Pattern.compile("[a-zA-Z]");
		Matcher m2 = p2.matcher(tlogin);
		assertTrue(m2.find());
		//Emaila
		Pattern p3 = Pattern.compile("[a-zA-Z0-9].@");
		Matcher m3 = p3.matcher(temail);
		assertTrue(m3.find());
		
	}
	
	/**
	 * Testeatu: ea iruzkinak parametroak errespetatzen dituen.
	 * @throws Exception 
	 * @throws RemoteException 
	 */
	public void testSaveComment() throws RemoteException {
		
		_facadeI.saveComment("BetiGoxo", _turist.getName(), "I did not like the place");
		
		RuralHouse landetxe = _facadeI.getRuralHouse("BetiGoxo");
		boolean existitzenDa = false;
		if (landetxe != null) {
			existitzenDa = true;
		}
		assertTrue(existitzenDa);
		Pattern p4 = Pattern.compile("[a-zA-Z]{1,300}");
		Matcher m4 = p4.matcher("I did not like the place");
		assertTrue(m4.find());	
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new IritziaTest("testTurist"));
		suite.addTest(new IritziaTest("testSaveComment"));

		return suite;
	}
	
	/**
	 * Main metodo nagusia.
	 */
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
}
