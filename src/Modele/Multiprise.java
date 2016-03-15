package Modele;

import java.util.ArrayList;
import java.util.List;

public class Multiprise {

	private String mac;
	private List<Prise>prises;
	private float min_temperature;
	private float max_temperature;
	private float min_humidite;
	private float max_humidite;
	private List<Contact> contacts;
	
	public Multiprise(String mac,float min_temperature,float max_temperature,float min_humidite,float max_humidite){
		this.mac=mac;
		this.prises=new ArrayList<Prise>();
		this.min_temperature=min_temperature;
		this.max_temperature=max_temperature;
		this.min_humidite=min_humidite;
		this.max_humidite=max_humidite;
		contacts= new ArrayList<Contact>();
	}
	
	public Multiprise(String mac,float min_temperature,float max_temperature,float min_humidite,float max_humidite, List<Contact> contacts,List<Prise>prises){
		this(mac,min_temperature,max_temperature,min_humidite,max_humidite);
		this.contacts = contacts;
		this.prises = prises;
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
	
	public void setMin_temperature(float min){
		this.min_temperature = min;
	}

	public float getMin_temperature(){
		return this.min_temperature;
	}
	
	public void setMax_temperature(float max){
		this.max_temperature = max;
	}

	public float getMax_temperature(){
		return this.max_temperature;
	}
	
	public void setMin_humidite(float min){
		this.min_humidite = min;
	}

	public float getMin_humidite(){
		return this.min_humidite;
	}
	
	public void setMax_humidite(float max){
		this.max_humidite = max;
	}

	public float getMax_humidite(){
		return this.max_humidite;
	}
}
