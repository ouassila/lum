package Modele;

public class Contact {
	
	private int id;
	private String mac;
	private String mail;
	private String telephone;

	public Contact(String mac, String mail, String telephone){
		this.setMac(mac);
		this.setMail(mail);
		this.telephone = telephone;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
}
