package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class M_Data {
	private Connection connection;	
	private static M_Data Instance;
	private M_Data (){
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			connection = DriverManager.getConnection("jdbc:mysql://192.168.1.11:3306/lumbd"
					,"insta","uBsY3M5vXUfrB2Gn");	

		}
		catch (Exception e) {		
			e.printStackTrace();
		}
	}

	public static synchronized M_Data getInstance()
	{
		if(Instance==null)
		{
			Instance = new M_Data();
		}
		return Instance;
	}

	public boolean insertMultiprise(String mac){
		if (getMultiprise(mac)==null){
			String requeteInsert = "Insert into Multiprise values (mac = ?)";
			boolean test=false;
			try  {
				PreparedStatement requete = connection.prepareStatement(requeteInsert);
				requete.setString(1,mac);

				synchronized(this){
					requete.executeUpdate();
					test=true;

				}
			}
			catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			return test;
		}
		else {return true;}
	}

	private String getMultiprise(String mac) {
		// TODO Auto-generated method stub
		String adresseMac=null;

		String requeteConnexion = "select * from Multiprise where mac=?";

		try {
			PreparedStatement requete = connection.prepareStatement(requeteConnexion);
			requete.setString(1,mac);
			ResultSet resultat = requete.executeQuery();

			if (resultat.next()) {
				adresseMac =resultat.getString("mac");
			}			
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return adresseMac;		
	}

	public boolean insertData( String mac, String temperature, String humidite, String date){

		String requeteInsert = " Insert into Environnement (mac, temperature, humidite, date) values (?,?,?,?)";
		boolean test=false;
		if (insertMultiprise(mac)){
			try  {

				PreparedStatement requete = connection.prepareStatement(requeteInsert);
				requete.setString(1,mac);
				requete.setString(2,temperature);
				requete.setString(3,humidite);
				requete.setString(4,date);
				System.out.println(requeteInsert);

				synchronized(this){
					requete.executeUpdate();
					test=true;

				}
			}
			catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		return test;
	}

	public List<Contact> getContact(String mac){
		List<Contact> contact = new ArrayList<Contact>();
		String requeteContact = "Select * from Contact where mac = ?";
		try  {
			PreparedStatement requete = connection.prepareStatement(requeteContact);
			requete.setString(1,mac);

			ResultSet resultat = requete.executeQuery();

			while (resultat.next()) {
				contact.add(new Contact (resultat.getString("mac"),resultat.getString("prenom"),resultat.getString("nom"),resultat.getString("mail"),resultat.getString("telephone")));
			}			
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return contact;		
	}
	
	public Environnement getLastEnvironnement(String mac){
		
		Environnement environnement = null;
		String requeteEnvironnement = "Select * from Environnement where mac = ? and date = (SELECT Max(date) from Environnement)";
		
		try  {
			PreparedStatement requete = connection.prepareStatement(requeteEnvironnement);
			requete.setString(1,mac);

			ResultSet resultat = requete.executeQuery();

			while (resultat.next()) {
				environnement = new Environnement (resultat.getInt("id"),resultat.getFloat("temperature"),resultat.getFloat("humidite"),resultat.getDate("date"),resultat.getString("mac"));
			}			
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return environnement;		
	}

	public Multiprise getMultipriseDetail(String mac){
		Multiprise multiprise = null;

		String requeteConnexion = "select * from Multiprise where mac=?";

		try {
			PreparedStatement requete = connection.prepareStatement(requeteConnexion);
			requete.setString(1,mac);
			ResultSet resultat = requete.executeQuery();

			if (resultat.next()) {
				multiprise = new Multiprise (resultat.getString("mac"),resultat.getFloat("min_temperature"),resultat.getFloat("max_temperature"),resultat.getFloat("min_humidite"),resultat.getFloat("max_humidite"));
				multiprise.setPrises(this.getPrisesOfMultiprise(mac));
				multiprise.setContact(this.getContact(mac));
			}			
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return multiprise;		
	}

	private List<Prise> getPrisesOfMultiprise(String mac) {
		// TODO Auto-generated method stub
		List<Prise> prises = new ArrayList<Prise>();
		String requetePrise = "Select * from Prise where mac = ?";
		try  {
			PreparedStatement requete = connection.prepareStatement(requetePrise);
			requete.setString(1,mac);

			ResultSet resultat = requete.executeQuery();

			while (resultat.next()) {
				Prise prise=new Prise (resultat.getInt("id"),resultat.getString("mac"), resultat.getBoolean("etat"));
				prise.setEtat(this.getEtat(prise.getId()));
				prises.add(prise);
			}			
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return prises;	
	}

	private List<Etat> getEtat(int id) {
		// TODO Auto-generated method stub
		List <Etat> etat= new ArrayList<Etat>();
		String requeteEtat = "select * from Etat where id_prise=?";

		try {
			PreparedStatement requete = connection.prepareStatement(requeteEtat);
			requete.setInt(1,id);
			ResultSet resultat = requete.executeQuery();

			while (resultat.next()) {
				etat.add(new Etat(resultat.getInt("id"),resultat.getBoolean("allume"),resultat.getInt("id_prise")));
			}			
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return etat;
	}
	
	public boolean InsertEtat(Etat etat){
		boolean test=false;
		String requeteEtat = "Insert into Etat (allume,id_prise,date) values (?,?,?)";
		try {
			PreparedStatement requete = connection.prepareStatement(requeteEtat);
			requete.setBoolean(1,etat.getAllume());
			requete.setInt(2,etat.getIdPrise());
			requete.setTimestamp(3, new Timestamp(new Date().getTime()));
			
			requete.executeUpdate();
			test=true;
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return test;
		
	}
	
	public boolean UpdateMultiprise(Multiprise multiprise){
		return false;
		
	}

}
