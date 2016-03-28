package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class M_Data {
	private Connection connection;	
	private static M_Data Instance;
	public static String IP_MULTIPRISE = "192.168.0.18"; // ip de la multiprise à utiliser pour la contacter
	private M_Data (){
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			connection = DriverManager.getConnection("jdbc:mysql://192.168.0.17:3306/lumbd"
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

	public List<Contact> getAllContact(String mac){
		List<Contact> contact = new ArrayList<Contact>();
		String requeteContact = "Select * from Contact where mac = ?";
		try  {
			PreparedStatement requete = connection.prepareStatement(requeteContact);
			requete.setString(1,mac);

			ResultSet resultat = requete.executeQuery();

			while (resultat.next()) {
				contact.add(new Contact (resultat.getInt("id"), resultat.getString("mac"),resultat.getString("mail"),resultat.getString("telephone")));
			}			
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return contact;		
	}

	public Contact getContactByEmail(String email, String mac){
		Contact contact = null;
		String requeteContact = "Select * from Contact where mail = ? and mac = ?";
		try  {
			PreparedStatement requete = connection.prepareStatement(requeteContact);
			requete.setString(1,email);
			requete.setString(2,mac);

			ResultSet resultat = requete.executeQuery();

			while (resultat.next()) {
				contact = new Contact (resultat.getInt("id"),resultat.getString("mac"),resultat.getString("mail"),resultat.getString("telephone"));
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
				multiprise.setContact(this.getAllContact(mac));
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

	public boolean InsertContact(Contact contact){
		String requeteEtat = "Insert into Contact (mail,mac,telephone) values (?,?,?)";
		try {
			PreparedStatement requete = connection.prepareStatement(requeteEtat);
			requete.setString(1,contact.getMail());
			requete.setString(2,contact.getMac());
			requete.setString(3, contact.getTelephone());			
			requete.executeUpdate();

			return true;
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;		
	}

	public boolean updatePrise(int id, boolean etat){
		boolean test=false;
		String requeteUpdate = "Update Prise set etat=? where id=?";
		try {
			PreparedStatement requete = connection.prepareStatement(requeteUpdate);
			requete.setBoolean(1,etat);
			requete.setInt(2,id);		
			requete.executeUpdate();
			test=true;
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return test;

	}

	public boolean updateContact(int id, String mail, String telephone){
		String requeteUpdate = "Update Contact set mail=? , telephone=? where id=?";
		try {
			PreparedStatement requete = connection.prepareStatement(requeteUpdate);
			requete.setString(1, mail);
			requete.setString(2, telephone);
			requete.setInt(3,id);		
			requete.executeUpdate();

			return true;
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;		
	}

	public boolean updateMultiprise(Multiprise multiprise){
		boolean test=false;
		String requeteUpdate = "Update Multiprise set min_temperature=? , max_temperature=? , min_humidite=? , max_humidite=? where mac=?";
		try {
			PreparedStatement requete = connection.prepareStatement(requeteUpdate);
			requete.setFloat(1,multiprise.getMin_temperature());
			requete.setFloat(2,multiprise.getMax_temperature());
			requete.setFloat(3,multiprise.getMin_humidite());
			requete.setFloat(4,multiprise.getMax_humidite());
			requete.setString(5, multiprise.getMac());
			requete.executeUpdate();
			test=true;
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return test;

	}

	public List<Map<String, String>> getHistoriqueEnvironnement(String mac, String value, String dateDeb, String dateFin){
		String requeteEnvironnement = "select "+value+", date from Environnement where mac=? and date between ? and ? Order By date";
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();		

		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.FRANCE);
			Date dateD = format.parse(dateDeb);
			Date dateF = format.parse(dateFin);
			long MILISECOND_PER_DAY = 24 * 60 * 60 * 1000; 
			format.applyPattern("yyyy-MM-dd HH:mm:ss");

			PreparedStatement requete = connection.prepareStatement(requeteEnvironnement);
			requete.setString(1 , mac);			
			requete.setString(2 , format.format(dateD));
			requete.setString(3 , format.format(dateF));

			ResultSet resultat = requete.executeQuery();
			if (MILISECOND_PER_DAY>dateF.getTime()-dateD.getTime()){ // ajd ou hier
				while (resultat.next()) {
					Map<String, String> map = new HashMap<String, String>();
					SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
					map.put('"'+"date"+'"', '"'+ ft.format(resultat.getTimestamp("date"))+'"');
					map.put('"'+"value"+'"', '"'+Integer.toString(Math.round(resultat.getFloat(value)))+'"');

					result.add(map);
				}			
			}
			else {// avant hier
				List<Date> dates = new ArrayList<Date>();
				List<Double> values = new ArrayList<Double>();
				while (resultat.next()) {
					dates.add(resultat.getDate("date"));
					values.add((double) resultat.getFloat(value));
				}
				SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
				for (int i=0; i<dates.size();i++){
					Map<String, String> map = new HashMap<String, String>();
					int nb = 1;

					for (int j=i+1; j<dates.size();j++){

						if(dates.get(i).equals(dates.get(j))){
							values.set(i, values.get(i)+values.get(j));
							dates.remove(j);
							values.remove(j);
							j--;
							nb++;
						}
					}
					if (nb < 1){
						nb = 1;
					}
					map.put('"'+"date"+'"', '"'+ ft.format(dates.get(i))+'"');
					map.put('"'+"value"+'"', '"'+(String.valueOf(Math.round((values.get(i)/nb)))+'"'));
					result.add(map);
				}
			}
		}

		catch (SQLException | ParseException e) {
			System.out.println(e);
			e.printStackTrace();		
		}
		return result;
	}

	public boolean deleteContact(String id) {
		String requeteDelete="Delete From Contact where id = ? ";
		try {
			PreparedStatement requete = connection.prepareStatement(requeteDelete);
			requete.setString(1, id);
			requete.executeUpdate();
			return true;
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	public List<Map<String, String>> getHistoriqueEtat(String id_prise, String dateDeb, String dateFin) {
		String requeteEnvironnement = "select allume, date from Etat where id_prise=? and date between ? and ? order by date";
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();		

		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.FRANCE);
			Date dateD = format.parse(dateDeb);
			Date dateF = format.parse(dateFin);
			format.applyPattern("yyyy-MM-dd HH:mm:ss");

			PreparedStatement requete = connection.prepareStatement(requeteEnvironnement);
			requete.setString(1 , id_prise);			
			requete.setString(2 , format.format(dateD));
			requete.setString(3 , format.format(dateF));

			ResultSet resultat = requete.executeQuery();

			while (resultat.next()) {
				Map<String, String> map = new HashMap<String, String>();
				SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
				map.put('"'+"date"+'"', '"'+ ft.format(resultat.getTimestamp("date"))+'"');

				String allume = (resultat.getBoolean("allume")) ? "1" : "0";
				map.put('"'+"value"+'"', '"'+allume+'"');

				result.add(map);
			}			
		}		
		catch (SQLException | ParseException e) {
			e.printStackTrace();		
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public Map<Integer, HashMap<String, Integer>> getConsoPrise(String mac) {
		// TODO Auto-generated method stub
		Map<Integer, HashMap<String, Integer>> resultat = new HashMap<Integer, HashMap<String, Integer>>();		
		String requetePrises = "Select * from Prise where mac = ?";

		try {
			PreparedStatement requete = connection.prepareStatement(requetePrises);
			requete.setString(1, mac);
			ResultSet prises = requete.executeQuery();

			while (prises.next()) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();

				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				Date last_month = cal.getTime();

				requetePrises = "Select * from Etat where id_prise = ? and date between ? and ? order by date ASC";
				requete = connection.prepareStatement(requetePrises);
				requete.setInt(1, prises.getInt("id"));
				requete.setString(2, dateFormat.format(last_month));
				requete.setString(3, dateFormat.format(now));

				ResultSet details = requete.executeQuery();
				Date first = null;
				boolean allume = false;
				long minutes = 0;

				while (details.next()) {
					HashMap<String, Integer> list = new HashMap<String, Integer>();		

					if(first == null){
						first = details.getTimestamp("date");
						allume = details.getBoolean("allume");
					}

					if(allume == true){
						minutes += (details.getTimestamp("date").getTime() - first.getTime())/60000;							
					}						
					else{
						allume = details.getBoolean("allume");
					}

					list.put("conso", Math.round(minutes));
					resultat.put(prises.getInt("id"), list);
				}
			}
		}		
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return resultat;
	}
}
