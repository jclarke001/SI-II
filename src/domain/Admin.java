package domain;

public class Admin {

	private String eIzena;
	private String password;
	
	public Admin(String eIzena, String password){
		this.eIzena= eIzena;
		this.password= password;
	}

	public String geteIzena() {
		return eIzena;
	}

	public void seteIzena(String eIzena) {
		this.eIzena = eIzena;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
