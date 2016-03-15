package Modele;

import java.util.List;

public class Prise {
	private int id;
	private String mac;
	private List<Etat> etat;
	private boolean allume ;

	public Prise (int id, String mac, boolean b){
		this.id=id;
		this.mac=mac;
		this.etat=null;
		this.allume=b;
	}

	public void setEtat(List<Etat> etat){
		this.etat=etat;
	}

	public List<Etat> getEtat(){
		return this.etat;
	}

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

	public boolean isAllume() {
		return allume;
	}

	public void setAllume(boolean allume) {
		this.allume = allume;
	}
}
