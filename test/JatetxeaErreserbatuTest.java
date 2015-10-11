import java.rmi.RemoteException;
import java.util.Date;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import dataAccess.DataAccessLocal;
import domain.Booking;
import domain.Restaurant;
import domain.Turist;

public class JatetxeaErreserbatuTest extends TestCase {

	private Restaurant jatetxea;
	private String hiria;
	private String jIzena;
	private int cap;
	private String mota;
	private double prezioa;
	private String jTlf;
	private Turist bezero;
	private int per_kop;
	private String erabiltzaile;
	private String izena;
	private Date data;
	private String tlf;
	private int erreserbaZbkia;
	private Booking erreserba;
	private static ApplicationFacadeInterface appFacadeInterface;
	private boolean erreserbatuDa, err1, err2;
	
	
	
	public JatetxeaErreserbatuTest(String name){
		super(name);
	}
	
	protected void setUp() throws RemoteException{
		hiria="Donostia";
		jIzena="SteakHouse Texas";
		cap= 95;
		mota= "Erretegia";
		prezioa= 30.0;
		jTlf="943658823";
		data= new Date(2015, 10, 12);
		jatetxea= new Restaurant(hiria, jIzena, cap, mota, prezioa, jTlf);
		bezero= new Turist("Eneko", "okene", "batman", "eneko@gmail.com");	
	}
	
	protected void tearDown() throws RemoteException{
		appFacadeInterface.close();
	}
	
	public void testJatetxeaErreserbatu() throws RemoteException, Exception{
		//Jatetxe baten erreserba
		
		appFacadeInterface= new FacadeImplementation();
		appFacadeInterface.setDataAccess(new DataAccessLocal());
		jatetxea=appFacadeInterface.getRestaurants("Bilbo").get(0);
		per_kop=24;
		data= new Date(2015, 10, 13);
		tlf= "688798712";
		bezero= appFacadeInterface.getTurist("Jonlog");
		izena= bezero.getLogin();
		erreserbatuDa= appFacadeInterface.jatetxeaErreserbatu(jatetxea, String.valueOf(per_kop) , izena, "Enekoren koadrila", data, tlf);
		
		assertTrue(erreserbatuDa);
	
	}
	
	public void ErreserbatuKapazitateGehiegi1() throws RemoteException, Exception{
		//Erreserba bakarra eginez, data berean eta kapazitatea gaindituz.
		
		appFacadeInterface= new FacadeImplementation();
		appFacadeInterface.setDataAccess(new DataAccessLocal());
		per_kop= 140;
		data= new Date(2015, 10, 12);
		jatetxea=appFacadeInterface.getRestaurants("Donostia").get(0);
		bezero= appFacadeInterface.getTurist("Jonlog");
		err1= appFacadeInterface.jatetxeaErreserbatu(jatetxea, String.valueOf(per_kop) , bezero.getLogin(), "Enekoren koadrila", data, jatetxea.getTelephone());
		
		assertTrue(err1);
		
	}
	
	public void ErreserbatuKapazitateGehiegi2() throws RemoteException, Exception{
		//Erreserba bat baino gehiago eginez(bi, zehazki). Data berean.
		
		appFacadeInterface= new FacadeImplementation();
		appFacadeInterface.setDataAccess(new DataAccessLocal());
		per_kop= 40;
		data= new Date(2015, 10, 12);
		jatetxea=appFacadeInterface.getRestaurants("Ezkio").get(0);
		bezero= appFacadeInterface.getTurist("Jonlog");
		err1= appFacadeInterface.jatetxeaErreserbatu(jatetxea, String.valueOf(per_kop) , bezero.getLogin(), "Enekoren koadrila", data, jatetxea.getTelephone());
		err2= appFacadeInterface.jatetxeaErreserbatu(jatetxea, "130" , bezero.getLogin(), "Enekoren familia", data, jatetxea.getTelephone());
		
		assertTrue(err1);
		assertTrue(err2);
		
	}
	
	public void erreserbatuJatetxeaTelefonoaGaizki() throws RemoteException, Exception{
		//Jatetxe baten erreserba egitean telefonoa gaizki sartuta.
		appFacadeInterface= new FacadeImplementation();
		appFacadeInterface.setDataAccess(new DataAccessLocal());
		per_kop= 20;
		data= new Date(2015, 10, 12);
		jatetxea=appFacadeInterface.getRestaurants("Ezkio").get(0);
		bezero= appFacadeInterface.getTurist("Jonlog");
		err1= appFacadeInterface.jatetxeaErreserbatu(jatetxea, String.valueOf(per_kop) , bezero.getLogin(), "Enekoren koadrila", data, "/&(&)·$asfweg");
		
		assertTrue(err1);
	}
	
	public static Test suite(){
		TestSuite suite= new TestSuite();
		
		suite.addTest(new JatetxeaErreserbatuTest("testJatetxeaErreserbatu"));
		suite.addTest(new JatetxeaErreserbatuTest("ErreserbatuKapazitateaGehiegi1"));
		suite.addTest(new JatetxeaErreserbatuTest("ErreserbatuKapazitateaGehiegi2"));
		suite.addTest(new JatetxeaErreserbatuTest("erreserbatuJatetxeaTelefonoaGaizki"));
		
		return suite;
	}
	
	public static void main(String args[]){
		junit.textui.TestRunner.run(suite());
	}
}
