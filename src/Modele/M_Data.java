package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class M_Data {
	private Connection connection;	
	private static M_Data Instance;
	private M_Data (){
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			connection = DriverManager.getConnection("jdbc:mysql://172.16.15.79:3306/lumbd"
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

	public float test() {
		// TODO Auto-generated method stub
		float test=0;
String requeteConnexion = "select * from Environnement where id=1";
		
		try {
			PreparedStatement requete = connection.prepareStatement(requeteConnexion);
			//requete.setString(1,login);
			ResultSet resultat = requete.executeQuery();
			
			if (resultat.next()) {
				test =resultat.getFloat("temperature");
			}			
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return test;		
	}
	
	public boolean insertData( String mac, String temperature, String humidite, String date){
		String requeteInsert = " Insert into Environnement (mac, temperature, humidite, date) values (?,?,?,?)";
		boolean test=false;
		try  {
			PreparedStatement requete = connection.prepareStatement(requeteInsert);
			requete.setString(1,mac);
			requete.setString(2,temperature);
			requete.setString(3,humidite);
			requete.setString(4,date);


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
	
	public List<Contact> getContact(String mac){
		List<Contact> contact = new ArrayList<Contact>();
		String requeteContact = "Select * from Contact where mac = ?";
		try  {
			PreparedStatement requete = connection.prepareStatement(requeteContact);
			requete.setString(1,mac);
		
		ResultSet resultat = requete.executeQuery();
		
		while (resultat.next()) {
			contact.add(new Contact (resultat.getString("mac"),resultat.getString("prenom"),resultat.getString("nom"),resultat.getString("mail")));
		}			
	}		
	catch (SQLException e) {
		e.printStackTrace();
	}		
	return contact;	
		

		
	}
	
}
