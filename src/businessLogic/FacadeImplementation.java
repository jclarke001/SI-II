package businessLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.sql.SQLException;
import java.util.Vector;

import dataAccess.DataAccessInterface;
import domain.Admin;
import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.Turist;
import domain.RuralHouse;
import domain.Restaurant;
import exceptions.BadDates;
import exceptions.DB4oManagerCreationException;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;
import gui.Launcher;


public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Vector<Owner> owners;
	Vector<RuralHouse> ruralHouses;
	DataAccessInterface dB4oManager;
 

	public FacadeImplementation() throws RemoteException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, DB4oManagerCreationException {
		
	}
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws OverlappingOfferExists, BadDates, RemoteException, Exception {
		if (firstDay.compareTo(lastDay)>=0) throw new BadDates();
		ruralHouses=null;
		owners=null;
		boolean b = dB4oManager.existsOverlappingOffer(ruralHouse,firstDay,lastDay); // The ruralHouse object in the client may not be updated
		if (!b) return dB4oManager.createOffer(ruralHouse,firstDay,lastDay,price);			
		return null;
	}

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	public Booking createBooking(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked {
		ruralHouses=null;
		owners=null;
		return dB4oManager.createBooking(ruralHouse,firstDate,lastDate,bookTelephoneNumber);
	}


	/**
	 * This method existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception {
		
		/*if (owners!=null) { System.out.println("Owners obtained directly from business logic layer");
							return owners; }*/
		return owners=dB4oManager.getOwners();
	}
	
	public Vector<Turist> getTurists() throws RemoteException{
		return dB4oManager.getTurists();
	}
	
	public Owner getOwner(String login) throws RemoteException{
		Owner jabea= dB4oManager.getOwnerJustByLogin(login);
		return jabea;
	}
	
	public Turist getTurist(String login) throws RemoteException{
		Turist turista= dB4oManager.getTuristJustByLogin(login);
		return turista;
	}
	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
	Exception {
		
		if (ruralHouses!=null) { System.out.println("RuralHouses obtained directly from business logic layer");
								 return ruralHouses; }
		else return ruralHouses=dB4oManager.getAllRuralHouses();

	}
	
	public RuralHouse getRuralHouse(String name) throws RemoteException{
		RuralHouse etxea= dB4oManager.getRuralHouseByName(name);
		return etxea;
	}
	
	public void close() throws RemoteException{
		dB4oManager.close();

	}


	@Override
	public void setDataAccess(DataAccessInterface dai) throws RemoteException {
		dB4oManager=dai;
		// TODO Auto-generated method stub
		
	}
	
	public boolean logeatu(String s1, String s2) throws RemoteException {
		boolean b=false;
		Vector<Turist> logger=dB4oManager.getTurists();
		Vector<Owner> toplogger= dB4oManager.getOwners();
		int i=0,j=0;
		while(i<logger.size() && !b){
			if((logger.get(i).getLogin().compareTo(s1)==0) && (logger.get(i).getPassword().compareTo(s2)==0)){
				b=true;
				Launcher.pribilegiatu=false;
			}else{
				i++;
			}
		}
		
		if(i==logger.size()){
			while(j<toplogger.size() && !b){
				if((toplogger.get(j).getLogin().compareTo(s1)==0) && (toplogger.get(j).getPassword().compareTo(s2)==0)){
					b=true;
					Launcher.pribilegiatu=true;
				}else{
					j++;
				}
			}
		}
		
		return b;
		/*boolean b= false;
		Turist t= dB4oManager.getTuristByName(s1, s2);
		Owner o;
		if(t!=null){
			b=true;
			Launcher.pribilegiatu=false;
		}else{
			o=dB4oManager.getOwnerByName(s1, s2);
			if(o!=null){
				b=true;
				Launcher.pribilegiatu= true;
			}
		}
		return b;*/
	}

	public boolean logeatuAdmin(String s1, String s2){
		boolean b= false;
		Admin admin= dB4oManager.getAdmin();
		if ((admin.geteIzena().compareTo(s1)==0) && (admin.getPassword().compareTo(s2)==0)){
			b= true;
		}
		return b;
	}
	
	public boolean erregistratuTurista(String izena, String eIzena, String pass1, String pass2, String email) throws RemoteException {
		boolean b= false;
		if(pass1.compareTo(pass2)==0){
			dB4oManager.storeTurista(izena, eIzena, pass1, email);
			b=true;
		}
		return b;
	}

    public boolean erregistratuJabea( String Izena, String eIzena, String pass1, String pass2, String account, String tlf, String email) throws RemoteException {
    	boolean b= false;
    	System.out.println(pass1 + " "+ pass2);
    	if(pass1.compareTo(pass2)==0){
    		dB4oManager.storeJabea(Izena, eIzena, pass1, account, tlf, email);
    		b= true;
    		
    	}
    	return b;
    	
    }
    
    public void landetxeaSortu(String sortzaile, String izena, String helbidea, String deskribapena, String hiria) throws RemoteException {
    	dB4oManager.storeLandetxea(sortzaile, izena, helbidea, deskribapena, hiria);
    }
    
    /*public boolean turistOrOwner(String eIzena){
    	boolean b= false;
    	Turist t= dB4oManager.getTuristJustByName(eIzena);
		Owner o;
		if(t!=null){
			b=true;
		}else{
			o=dB4oManager.getOwnerJustByName(eIzena);
			if(o!=null){
				b=true;
			}
		}
		return b;
    	
    }*/
    
    
    public Vector<RuralHouse> getRuralHouses(String city) throws RemoteException{
    	Vector<RuralHouse> landetxeak;
    	landetxeak=dB4oManager.getRuralHousesByCity(city);
    	return landetxeak;
    }
    
    public void jabeaEzabatu(String login) throws RemoteException{
    	dB4oManager.removeOwner(login);
    }
    
    public void saveComment(String houseName, String izena, String iritzia) throws RemoteException{
    	//RuralHouse etxea=dB4oManager.getRuralHouseByName(houseName);
    	dB4oManager.storeComment(houseName, izena, iritzia);
    }
    
    public Vector<Restaurant> getRestaurants(String city) throws RemoteException{
    	return dB4oManager.getRestaurants(city);
    }
    
    public Restaurant getRestaurantByName(String name) throws RemoteException{
    	return dB4oManager.getRestaurantByName(name);
    }
    
    public boolean jatetxeaErreserbatu(Restaurant jatetxe, String per, String erabiltzaile, String izena, Date data, String tlf){
    	
    	return dB4oManager.jatetxeaErreserbatu(jatetxe, per, erabiltzaile, izena, data, tlf);
    		
    }
}

