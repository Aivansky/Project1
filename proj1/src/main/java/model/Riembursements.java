package model;

public class Riembursements {
	private int id;
	private int status;
	private int amount;
	private String description;
	private String type;
	
	public Riembursements(int id, int amount, String description, String type ){
		super();
		this.id = id;

		this.amount = amount;
		this.description = description;
		this.type = type;
	}
		
	public Riembursements(int amount, String description, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setName(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setEmail(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setCountry(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = 0;
	}

	
	
}
