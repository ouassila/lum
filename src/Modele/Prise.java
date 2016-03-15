package Modele;

public class Prise {
int id;
String mac;
Etat etat;
public Prise (int id, String mac){
	this.id=id;
	this.mac=mac;
	etat=null;
}

public void setEtat(Etat etat){
	this.etat=etat;
}

public Etat getEtat(){return this.etat;}

public int getId(){
	return this.id;
}

public void setId(int id){
	this.id=id;
}

public String getMac(){
	return this.mac;
}

public void setMac(String mac){
	this.mac=mac;
}
}
