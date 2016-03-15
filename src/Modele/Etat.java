package Modele;

public class Etat {
int id;
boolean allume;
int id_prise;

public Etat (int id, boolean allume, int id_prise){
	this.id=id;
	this.allume=allume;
	this.id_prise=id_prise;
}

public Etat() {
	// TODO Auto-generated constructor stub
}

public int getId(){
	return this.id;
}

public int getIdPrise(){
	return this.id_prise;
}

public boolean getAllume(){
	return this.allume;
}
}
