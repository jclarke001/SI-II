package dataAccess;

import java.util.Date;
import java.util.Vector;


import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.Turist;
import domain.RuralHouse;
import domain.Admin;
import domain.Restaurant;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;

public interface DataAccessInterface {
	/*
	 * Metodo honek oferta berri bat sortzen du ruralHouse-rentzat pasatako datuekin(firstDay, lastDay, price)
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price);

	/*
	 * Metodo honek ereserba bat sortzen du ruralHouse landetxean.
	 */
	public Booking createBooking(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked;
	
	/*
	 * Metodo honek datu-basean dauden Turista guztiak itzultzen ditu.
	 */
	public Vector<Turist> getTurists();
	
	/*
	 * Metodo honek datu-basean dauden Jabe guztiak itzultzen ditu.
	 */
	public Vector<Owner> getOwners();
	
	/*
	 * Metodo honek kudeatzailearen informazioa bueltatzen du.
	 */
	public Admin getAdmin();
	
	/*
	 * Metodo honek datu-basean dauden landetxe guztiak itzultzen ditu.
	 */
	public Vector<RuralHouse> getAllRuralHouses();
	
	/*
	 * Metodo honek city hiriko landetxe guztiak bueltatzen ditu.
	 */
	public Vector<RuralHouse> getRuralHousesByCity(String city);
	
	/*
	 * Metodo honek name izeneko landetxea bueltatzen du.
	 */
	public RuralHouse getRuralHouseByName(String name);
	
	/*
	 * Metodo honek adierazten du ea badagoen horrelako ofertarik landetxe horretarako.
	 */
	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws OverlappingOfferExists;

	/*
	 * Metodo honek datu-basea ixten du.
	 */
	public void close();
	
	/*
	 * Metodo honek s1 izeneko eta s2 pasahitzako Turista itzultzen du.
	 */
	public Turist getTuristByName(String s1, String s2);
	
	/*
	 * Metodo honek s1 izeneko Turista itzultzen du.
	 */
	public Turist getTuristJustByLogin(String s1);
	
	/*
	 * Metodo honek s1 izeneko eta s2 pasahitzako Jabea itzultzen du.
	 */
	public Owner getOwnerByName(String s1, String s2);
	
	/*
	 * Metodo honek s1 izeneko Jabea itzultzen du.
	 */
	public Owner getOwnerJustByLogin(String s1);
	
	/*
	 * Metodo honek pasatako datu berriekin Turista berri bat sortzen du eta datu-basean gordetzen du.
	 */
	public void storeTurista(String izena, String eIzena, String pass1, String email);
	
	/*
	 * Metodo honek pasatako datu berriekin Jabea berri bat sortzen du eta datu-basean gordetzen du.
	 */
	public void storeJabea(String Izena, String eIzena, String pass1, String account, String tlf, String email);
	
	/*
	 * Metodo honek pasatako datu berriekin RuralHouse berri bat sortzen du eta datu-basean gordetzen du.
	 */
	public void storeLandetxea(String sortzaile, String izena, String helbidea, String deskribapena, String hiria);
	
	/*
	 * Metodo honek login izeneko Jabea ezabatzen du.
	 */
	public void removeOwner(String login);
	
	/*
	 * Metodo honek datu berriekin Iruzkin berri bat sortzen du eta datu-basean gordetzen du.
	 */
	public void storeComment(String houseName, String izena, String iritzia);
	
	/*
	 * Metodo honek city hiriko jatetxeak itzultzen ditu.
	 */
	public Vector<Restaurant> getRestaurants(String city);
	
	/*
	 * Metodo honek name izeneko jatetxea itzultzen du.
	 */
	public Restaurant getRestaurantByName(String name);
	
	/*
	 * Metodo honek datu-basean existitzen diren jatetxe guztiak itzultzen ditu.
	 */
	public Vector<Restaurant> getAllRestaurants();
	
	/*
	 * Metodo honek erreserba berri bat sortzen du jatetxe jatetxerako emandako datuekin.
	 */
	public boolean jatetxeaErreserbatu(Restaurant jatetxe, String per, String erabiltzaile, String izena, Date data, String tlf);
}
