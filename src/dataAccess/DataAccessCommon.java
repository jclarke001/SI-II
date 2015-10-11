package dataAccess;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import configuration.ConfigXML;
import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.Turist;
import domain.RuralHouse;
import domain.Admin;
import domain.Iruzkina;
import domain.Restaurant;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;
import gui.Launcher;

public class DataAccessCommon implements DataAccessInterface {
	protected static ObjectContainer  db;
	private int bookingNumber=0; // if it is "static" then it is not serialized
	private int offerNumber=0;   // if it is "static" then it is not serialized

	protected static DB4oManagerAux theDB4oManagerAux;
	ConfigXML c;

	public DataAccessCommon()  {
		theDB4oManagerAux=new DB4oManagerAux(0,0);
		c=ConfigXML.getInstance();
		System.out.println("Creating DB4oManager instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());
		}


	
	 class DB4oManagerAux {
		int bookingNumber;
		int offerNumber;
		DB4oManagerAux(int bookingNumber,int offerNumber){
			this.bookingNumber=bookingNumber;
			this.offerNumber=offerNumber;
		}
	}


	
	
	public void initializeDB(){
		

		 Owner jon = new Owner("Jon", "Jonlog", "passJon","661234543","jon@gmail.com");
		// Owner alfredo = new Owner("Alfredo","AlfredoLog", "passAlfredo", "987456321", "alfedhitchcock@gmail.com");
		 Owner jesus = new Owner("Jesús", "Jesuslog", "passJesus", "666254632", "jisusthegod@gmail.com");
		 Owner josean = new Owner("Josean","JoseanLog", "passJosean", "681594587", "josean@gmail.com");
	     Admin mineko = new Admin("minekolog", "golokenim");
		 Owner eneko= new Owner("eneko", "waka420", "lionzion420", "678954367","eneko@gmail.com");
		 
		 jon.addRuralHouse("Ezkioko etxea", "Iberotarren plaza, 6700, Ezkio", 1, "Etxe oso modernoa eta egokitua edozein pertsonarentzako" ,"Ezkio");
	     jon.addRuralHouse("Etxetxikia", "Monte del Perdon kalea, 54000, Pamplona", 2,"Naturaren erdian kokatutako etxe polit-polita", "Iruña");
	     jesus.addRuralHouse("Udaletxea", "Gughenheim etorbidea, 2, 14000, Bilbo", 3,"Etxe bohemio eta erosoa, hiri zentroan kokatua" ,"Bilbo");
	     josean.addRuralHouse("Gaztetxea", "Zumalakarregi kalea, 10-2.A, Ormaiztegi", 4,"Rock-a gustoko duten pertsonentzako" ,"Ormaiztegi");
	     josean.addRuralHouse("Errandonea", "Jaizkibel kalea, 24, Hondarribia", 5,"Aparteko lekua familiarekin egoteko eta naturaz gozatzeko" ,"Hondarribia");
	     jesus.addRuralHouse("Urbilenea", "Kostalde etorbidea, 12, 20500, Hondarribia", 6,"Etxe estandarra. Bi logela, egongela bakarra bazkaltzeko mahaiarekin, sukaldea eta komuna." ,"Hondarribia");
	     eneko.addRuralHouse("BetiGoxo", "Heriotz enparantza, 11-2.B, Donostia", 7,"Etxe erosoa eta hiriko zentroan. Kalera irtetzeko eta pintxo batzuk jateko arazorik gabe!" ,"Donostia");
	     eneko.addRuralHouse("TheCrew", "Gipuzkoa enparantza, 43-4.B, Donostia", 8,"Toki ospetsua eta lasaitasunez gozatzekoa. Etxea ondo ekipatuta dago. Edozein multimedia tresna, urezko ohea, jaccuzzi..","Donostia");
	     eneko.addRuralHouse("MoonShine", "Gasteiz kalea, 10-2.A, Donostia", 9,"Koadrilakin etortzeko etxe paregabea" ,"Donostia");
	     eneko.addRuralHouse("Igeldo", "Ostruken kalea, 21-3.A, Donostia", 10,"Etxean bazina bezala." ,"Donostia");
	     
		 jon.setBankAccount("12345677");
		 //alfredo.setBankAccount("77654321");
		 jesus.setBankAccount("12344321");
		 josean.setBankAccount("43211234");
		 eneko.setBankAccount("654789123");
		 
		 Restaurant r1= new Restaurant("Ezkio", "Ilcapo", 40, "pizzeria", 10, "657685345");
		 Restaurant r2= new Restaurant("Ezkio", "Apaolaza", 90, "sagardotegia", 27.5, "657987345");
		 Restaurant r3= new Restaurant("Ezkio", "Fishermann's boat", 50, "Arrain jatetxea", 14, "657655345");
		 Restaurant r4= new Restaurant("Pamplona", "Mendate", 75, "erretegia", 19.9, "624685345");
		 Restaurant r5= new Restaurant("Pamplona", "Lumber", 35, "Menu mikologikoak", 25, "651015345");
		 Restaurant r6= new Restaurant("Bilbo", "Bereziartua", 80, "sagardotegia", 35, "690985345");
		 Restaurant r7= new Restaurant("Bilbo", "BurguerKing", 100, "Hamburguesak", 10, "657032345");
		 Restaurant r8= new Restaurant("Bilbo", "Olatu", 65, "itsaskitegi", 50, "663085345");
		 Restaurant r9= new Restaurant("Bilbo", "Bilbo", 45, "Sukaldaritza modernoa", 95, "612385345");
		 Restaurant r10= new Restaurant("Bilbo", "EuskalBBQ", 75, "erretegia", 20.95, "657089345");

	 
		 
		 db.store(jon);
		// db.store(alfredo);
		 db.store(jesus);
		 db.store(josean);
		 db.store(mineko);
		 db.store(eneko);
		 db.store(r1);
		 db.store(r2);
		 db.store(r3);
		 db.store(r4);
		 db.store(r5);
		 db.store(r6);
		 db.store(r7);
		 db.store(r8);
		 db.store(r9);
		 db.store(r10);
		 db.commit();
	}
	
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price) {

	try {
		
		//if (c.isDatabaseLocal()==false) openObjectContainer();
		
		
		RuralHouse proto = new RuralHouse(null,null,ruralHouse.getHouseNumber(),null,null,null);
		ObjectSet<RuralHouse> result = db.queryByExample(proto);
		RuralHouse rh=result.next();
		Offer o=rh.createOffer(theDB4oManagerAux.offerNumber++,firstDay, lastDay, price);
		db.store(theDB4oManagerAux); // To store the new value for offerNumber
		db.store(o);
		db.commit(); 
		return o;
	}
	catch (com.db4o.ext.ObjectNotStorableException e){
		System.out.println("Error: com.db4o.ext.ObjectNotStorableException in createOffer");
		return null;
	}
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
		
		try {

			//if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse proto = new RuralHouse(ruralHouse.getName(), ruralHouse.getAddress(), ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity());
			 ObjectSet<RuralHouse> result = db.queryByExample(proto);
			 RuralHouse rh=result.next();

			Offer offer;
			offer = rh.findOffer(firstDate, lastDate);

			if (offer!=null) {
				offer.createBooking(theDB4oManagerAux.bookingNumber++, bookTelephoneNumber);
				db.store(theDB4oManagerAux); // To store the new value for bookingNumber
				db.store(offer);
				db.commit();
				return offer.getBooking();
			}
			return null;

		} catch (com.db4o.ext.ObjectNotStorableException e){
				System.out.println("Error: com.db4o.ext.ObjectNotStorableException in createBooking");
				return null;
			}
		catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}


	/**
	 * This method existing  owners 
	 * 
	 */
	public Vector<Turist> getTurists(){
		try{
			 Turist giri = new Turist(null,null,null,null);
			 ObjectSet<Turist> result = db.queryByExample(giri);
			 Vector<Turist> giris=new Vector<Turist>();
			 while(result.hasNext())				 
				 giris.add(result.next());
			 return giris;
	     } finally {
	         //db.close();
	     }
		
	}
	public Vector<Owner> getOwners()  {
		

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		 try {
			 Owner proto = new Owner(null,null,null,null,null,null);
			 ObjectSet<Owner> result = db.queryByExample(proto);
			 Vector<Owner> owners=new Vector<Owner>();
			 while(result.hasNext())				 
				 owners.add(result.next());
			 return owners;
	     } finally {
	         //db.close();
	     }
	} 
	
	public Admin getAdmin(){
		Admin proto = new Admin(null, null);
		ObjectSet<Admin> result = db.queryByExample(proto);
		Vector<Admin> emaitza= new Vector<Admin>();
		while(result.hasNext()){
			emaitza.add(result.next());
		}
		return emaitza.get(0);
	}
	
	public Vector<RuralHouse> getAllRuralHouses() {

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		 try {
			 RuralHouse proto = new RuralHouse(null,null,0,null,null,null);
			 ObjectSet<RuralHouse> result = db.queryByExample(proto);
			 Vector<RuralHouse> ruralHouses=new Vector<RuralHouse>();
			 while(result.hasNext()) 
				 ruralHouses.add(result.next());
			 return ruralHouses;
	     } finally {
	         //db.close();
	     }
	}
	
	public Vector<RuralHouse> getRuralHousesByCity(String city){
		List<RuralHouse> result;
		Vector<RuralHouse> emaitza=new Vector<RuralHouse>();
		RuralHouse proto= new RuralHouse(null, null, 0, null, null, city);
		result= db.queryByExample(proto);
		for(int i=0; i<result.size(); i++){
			emaitza.add(result.get(i));
		}
		if(emaitza.isEmpty()){
			RuralHouse rh= new RuralHouse(null, null, 0, null, null, city);
			emaitza.add(rh);
			return emaitza;
		}else{
			//System.out.println(emaitza.get(0).getAddress().toString());
			return emaitza;
		}
	}
	
	public RuralHouse getRuralHouseByName(String name){
		Vector<RuralHouse> emaitza= new Vector<RuralHouse>();
		RuralHouse proto= new RuralHouse(name, null, 0, null, null, null);
		ObjectSet<RuralHouse> result= db.queryByExample(proto);
		while(result.hasNext()){
			emaitza.add(result.next());
		}
		return emaitza.get(0);
	}
	
	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws  OverlappingOfferExists{
		 try {
			//if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse rhn = (RuralHouse) db.queryByExample(new RuralHouse(null,null,rh.getHouseNumber(),null,null,null)).next();		
			if (rhn.overlapsWith(firstDay, lastDay)!=null) throw new OverlappingOfferExists();
			else return false; 
	     } finally {
	         //db.close();
	     }
	}


	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	public String toString() {
		return "bookingNumber="+bookingNumber+" offerNumber="+offerNumber;
	}
	
	public Turist getTuristByName(String s1, String s2){
		List<Turist> emaitza;
		Turist giri= new Turist(null, s1, s2, null);
		emaitza= db.queryByExample(giri);
		return emaitza.get(0);
	}

	public Turist getTuristJustByLogin(String s1){
		List<Turist> emaitza;
		Turist giri= new Turist(null, s1, null, null);
		emaitza= db.queryByExample(giri);
		if(emaitza.isEmpty()){
			return new Turist(null, null, null, null);
		}else{	
			return emaitza.get(0);
		}
		
	}
	
	public Owner getOwnerByName(String s1, String s2){
		List<Owner> emaitza;
		Owner topgiri= new Owner(null, s1, s2, null, null);
		emaitza= db.queryByExample(topgiri);
		return emaitza.get(0);
	}
	
	public Owner getOwnerJustByLogin(String s1){
		List<Owner> emaitza;
		Owner topgiri= new Owner(null, s1, null, null, null, null);
		emaitza= db.queryByExample(topgiri);
		if(emaitza.isEmpty()){
			return new Owner(null, null, null, null, null, null);
		}else{
			return emaitza.get(0);
		}
	}
	
	public void storeTurista(String izena, String eIzena, String pass1, String email){
		List<Turist> lista;
		Turist findingGiri= new Turist(null, eIzena, null, null);
		Turist giri= new Turist(izena, eIzena, pass1, email);
		lista= db.queryByExample(findingGiri);
		if(lista.isEmpty()){
			db.store(giri);
			db.commit();
		}else{
			//ZER JARRI GENEZAKE HEMEN ERRORE BATEN BAT BALDIN BADAGO?
			System.out.println("Erabiltzaile hori jadanik existitzen da.. :(");
		}
	}
	
	public void storeJabea(String Izena, String eIzena, String pass1, String account,String tlf, String email){
		List<Owner> lista;
		Owner findingGiri= new Owner(null, eIzena, null, null, null, null);
		Owner topgiri= new Owner(Izena, eIzena, pass1, account, tlf, email);
		lista= db.queryByExample(findingGiri);
		if(lista.isEmpty()){
			db.store(topgiri);
			db.commit();	
		}else{
			//ZER JARRI GENEZAKE HEMEN ERRORE BATEN BAT BALDIN BADAGO?
			System.out.println("Erabiltzaile hori jadanik existitzen da.. :(");
		}
		
	}
	
	public void storeLandetxea(String sortzaile, String izena, String helbidea, String deskribapena, String hiria){
		Owner o= new Owner(null,sortzaile,null,null,null,null);
		Vector<RuralHouse> lista=getAllRuralHouses();
		List<Owner> jabeLista= db.queryByExample(o);
		jabeLista.get(0).addRuralHouse(izena, helbidea, (lista.size()+1), deskribapena, hiria);
		db.store(jabeLista.get(0));
		db.commit();
	}
	
	public void removeOwner(String login){
		List<Owner> emaitza;
		Owner proto= new Owner(null, login, null, null, null, null);
		emaitza= db.queryByExample(proto);
		db.delete(emaitza.get(0));
		db.commit();
	}

	public void storeComment(String houseName, String izena, String iritzia){
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		   //get current date time with Date()
		   Date data = new Date();
		   dateFormat.format(data);
		   //System.out.println(dateFormat.format(data));
		   RuralHouse etxea= getRuralHouseByName(houseName);
		   Iruzkina c= new Iruzkina(izena, data, iritzia);
		   etxea.addComment(c);
		   db.store(etxea);
		   db.commit();
	}
	
	public Vector<Restaurant> getRestaurants(String city){
		Vector<Restaurant> restaurants= new Vector<Restaurant>();
		List<Restaurant> emaitza;
		Restaurant proto= new Restaurant(city, null, 0, null, 0, null);
		emaitza= db.queryByExample(proto);
		for(int i=0; i<emaitza.size(); i++){
			restaurants.add(emaitza.get(i));
		}
		return restaurants;
		
	}
	
	public Restaurant getRestaurantByName(String name){
		Restaurant jatetxea= null;
		Vector<Restaurant> jatetxeak= getAllRestaurants();
		for(int i=0; i<jatetxeak.size(); i++){
			if(jatetxeak.get(i).getName().compareTo(name)==0){
				jatetxea= jatetxeak.get(i);
			}
		}
		return jatetxea;
		/*Restaurant proto= new Restaurant(name, null, 0, null, 0, null);
		emaitza=db.queryByExample(proto);
		return emaitza.get(0);*/
	}
	
	public Vector<Restaurant> getAllRestaurants(){
		Vector<Restaurant> restaurants= new Vector<Restaurant>();
		List<Restaurant> emaitza;
		Restaurant proto= new Restaurant(null, null, 0, null, 0, null);
		emaitza= db.queryByExample(proto);
		for(int i=0; i<emaitza.size(); i++){
			restaurants.add(emaitza.get(i));
		}
		return restaurants;
	}
	
	public boolean jatetxeaErreserbatu(Restaurant jatetxe, String per, String erabiltzaile, String izena, Date data, String tlf){
		List<Restaurant> emaitza;
		//List<Owner> emaitzaJabe;
		emaitza= db.queryByExample(jatetxe);
		if(!emaitza.isEmpty()){
		
		//emaitza.get(0).setCapacity(emaitza.get(0).getCapacity()-Integer.parseInt(per));
		Booking b= new Booking(theDB4oManagerAux.bookingNumber++, tlf, Integer.parseInt(per), data);
		emaitza.get(0).addBooking(b);
		//System.out.println(Integer.toString(b.getBookingNumber()) + " " + b.getTelephone().toString() + " " + b.getRestaurant().getName().toString());
		db.store(emaitza.get(0));
		db.store(b);
		db.store(theDB4oManagerAux);
		db.commit();
			return true;
		}else
			return false;
	}
}
