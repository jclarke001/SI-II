package domain;
import java.util.Date;

public class Iruzkina {

	private String name;
	private Date data;
	private String comment;
	
	public Iruzkina (String name, Date data, String comment){
		this.name= name;
		this.data= data;
		this.comment= comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
