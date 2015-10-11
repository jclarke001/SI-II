package businessLogic;

import java.rmi.*;
import java.util.Vector;
import java.util.Date;

import dataAccess.DataAccessInterface;
import domain.*;


import exceptions.OfferCanNotBeBooked; 


public interface ApplicationFacadeInterface extends Remote {
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */


	Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception;

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	Booking createBooking(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			String telephoneNumber) throws RemoteException,
			OfferCanNotBeBooked;

	
	/**
	 * This method retrieves the existing  owners 
	 * 
	 * @return a Set of owners
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception;
	
	/**
	 * This method retrieves the existing  rural houses 
	 * 
	 * @return a Set of rural houses
	 */
	
	/*
	 * Metodo honek dauden turistak itzultzen ditu bektore batean.
	 * 
	 */
	public Vector<Turist> getTurists() throws RemoteException;
	
	
	/*
	 * Metodo honek jabe bat itzultzen du. Zehazki login erabiltzaile izen duena.
	 */
	public Owner getOwner(String login) throws RemoteException;
	
	/*
	 * Metodo honek turista bat itzultzen du. Zehazki login erabiltzaile izen duena.
	 */
	public Turist getTurist(String login) throws RemoteException;
	
	/*
	 * Metodo honek existitzen diren landetxe guztiak bueltatzen ditu.
	 */
	public Vector<RuralHouse> getAllRuralHouses()throws RemoteException,
	Exception;
	
	/*
	 * Metodo honek city hiriko landetxeak bueltatzen ditu.
	 */
	public Vector<RuralHouse> getRuralHouses(String city) throws RemoteException;
	public void close() throws RemoteException;

	/*
	 * Metodo honek name izeneko landetxea bueltatzen du
	 */
	public RuralHouse getRuralHouse(String name) throws RemoteException;
	
    public void setDataAccess(DataAccessInterface dai) throws RemoteException;
    
    /*
     * Metodo honek konprobatzen du ea s1 izeneko eta s2 pasahitzako erabiltzailea(jabea edo turista) existitzen den edo datu basean dagoen.
     */
    public boolean logeatu(String s1, String s2) throws RemoteException;
    
    /*
     * Metodo honek konprobatzen du ea s1 eta s2 datuak (biak String-ak) kueatzailearen izena eta pasahitza diren.
     */
    public boolean logeatuAdmin(String s1, String s2) throws RemoteException;
    
    /*
     * Metodo honek emandako bi pasahitzak berddinak diren konprobatzen du eta datu atzipeneko storeTurista metodoa deitzen du.
     */
    public boolean erregistratuTurista(String izena, String eIzena, String pass1, String pass2, String email)throws RemoteException;

    /*
     * Metodo honek emandako bi pasahitzak berddinak diren konprobatzen du eta datu atzipeneko storeJabea metodoa deitzen du.
     */
    public boolean erregistratuJabea( String Izena, String eIzena, String pass1, String pass2, String account, String tlf, String email)throws RemoteException;
    
    /*
     * Metodo honek zuzenean datu atzipeneko storeLandetxea metodoa deitzen du.
     * Landetxe bat sortzen du.
     */
    public void landetxeaSortu(String sortzaile, String izena, String helbidea, String deskribapena, String hiria)throws RemoteException;
    
    /*
     * Metodo honek zuzenean datu atzipeneko removeOwner metodoa deitzen du.
     * Jabe bat ezabatzen du.
     */
    public void jabeaEzabatu(String login) throws RemoteException;

    /*
     * Metodo honek zuzenean datu atzipeneko storeComment metodoa deitzen du.
     * Iruzkin bat gordetzen du.
     */
    public void saveComment(String houseName, String izena, String iritzia) throws RemoteException;
    
    /*
     * Metodo honek city hiriko jatetxeak itzultzen ditu.
     */
    public Vector<Restaurant> getRestaurants(String city) throws RemoteException;
    
    /*
     * name izeneko jatetxea itzultzen du.
     */
    public Restaurant getRestaurantByName(String name) throws RemoteException;
    
    /*
     * jatetxea izeneko jatetxean erreserba bat egiten du emandako datuekin.
     */
    public boolean jatetxeaErreserbatu(Restaurant jatetxea, String per, String erailtzaile, String izena, Date data, String tlf) throws RemoteException;
    //public boolean turistOrOwner(String eIzena)throws RemoteException;
}