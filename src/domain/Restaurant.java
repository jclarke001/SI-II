package domain;

import java.util.Vector;
public class Restaurant {
	
	private String city;
	private String name;
	private int capacity;
	private String type;
	private double price;
	private String telephone;
	private Vector<Booking> bookings;
	
	public Restaurant(String city, String name, int capacity, String type, double price, String telephone){
		this.city=city;
		this.name= name;
		this.capacity= capacity;
		this.type= type;
		this.price= price;
		this.telephone= telephone;
		this.bookings= new Vector<Booking>();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public Vector<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Vector<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void addBooking(Booking booking){
		this.bookings.add(booking);
	}
	
	public String toString(){
		return this.name + ", " + this.type;
	}

}
