package Modele;

public class Contact {
	
	private String mac;
	private String prenom;
	private String nom;
	private String mail;
	private String telephone;

	public Contact(String mac, String prenom, String nom, String mail, String telephone){
		this.mac = mac;
		this.prenom = prenom;
		this.nom = nom;
		this.mail = mail;
		this.telephone = telephone;
	}

	public String getNom(){
		return this.nom;
	}

	public String getPreNom(){
		return this.prenom;
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
