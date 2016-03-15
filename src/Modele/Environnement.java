package Modele;

import java.util.Date;

public class Environnement {
	private int id;
	private float temperature, humidite;
	private Date date;
	private String mac;

	public Environnement (int id, float temperature, float humidite, Date date, String mac){
		this.id = id;
		this.temperature = temperature;
		this.humidite = humidite;
		this.date = date;
		this.mac = mac;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidite() {
		return humidite;
	}

	public void setHumidite(float humidite) {
		this.humidite = humidite;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getId() {
		return id;
	}
}
