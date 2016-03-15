package Modele;

import java.util.ArrayList;
import java.util.List;

public class Multiprise {
	
	String mac;
	List<Prise>prises;
	float min_temperature;
	float max_temperature;
	float min_humidite;
	float max_humidite;
	List<Contact> contacts;
	public Multiprise(String mac,float min_temperature,float max_temperature,float min_humidite,float max_humidite){
		this.mac=mac;
		this.prises=new ArrayList<Prise>();
		this.min_temperature=min_temperature;
		this.max_temperature=max_temperature;
		this.min_humidite=min_humidite;
		this.max_humidite=max_humidite;
		contacts=null;
	}
	
	public List<Prise> getPrises(){
		return this.prises;
	}
	
	public String getMac(){
		return this.mac;
	}
	
	public void setPrises (List<Prise> prises){
		this.prises=prises;
	}
	
	public void setContact(List<Contact> contact){
		this.contacts=contact;
	}
	
	public List<Contact> getContact(){
		return this.contacts;
	}
}
