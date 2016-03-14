package Modele;

public class Contact {
String mac;
String prenom;
String nom;
String mail;

public Contact(String mac, String prenom, String nom, String mail){
	this.mac=mac;
	this.prenom=prenom;
	this.nom=nom;
	this.mail=mail;
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
}
