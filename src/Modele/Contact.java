package Modele;

public class Contact {
	
	private String mac;
	private String mail;
	private String telephone;

	public Contact(String mac, String mail, String telephone){
		this.mac = mac;
		this.mail = mail;
		this.telephone = telephone;
	}


	
	public String getMail(){
		return this.mail;
	}
	public String getMac(){
		return this.mac;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
