package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
