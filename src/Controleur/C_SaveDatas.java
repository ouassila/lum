package Controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.Contact;
import Modele.Environnement;
import Modele.M_Data;
import Modele.Multiprise;

public class C_SaveDatas extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//System.out.println("oki");


		//RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/Accueil.jsp");
		//dispatcher.forward(request,response); 
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String operation = request.getParameter("operation");			

		if (operation.equals("save")){
			String mac = request.getParameter("mac");
			Multiprise multiprise = M_Data.getInstance().getMultipriseDetail(M_Data.MAC_MULTIPRISE);
			boolean result = false;
			List <String> etats = new ArrayList<String>();
			for (int i = 1 ; i < multiprise.getPrises().size() +1 ; i++){	

				String etat = request.getParameter("etat_"+i);
				Boolean allume;
				if(etat==null){
					allume=false;
				}
				else {
					allume=true;
				}
				int id = Integer.parseInt(request.getParameter("id_"+i));
				if (M_Data.getInstance().updatePrise(id, allume)){
					etats.add(request.getParameter("etat_"+i));
				}
				M_Data.getInstance().insertEtat(allume, id, new Timestamp(Calendar.getInstance().getTime().getTime()));
			}
			String adresseUrl = "http://"+M_Data.IP_MULTIPRISE+"/req.php?";
			for (int i =0; i<etats.size();i++){
				int numeroPrise = i+1;
				String etat="off";
				if(etats.get(i)!=null && etats.get(i).equals("on")){
					etat = "on";
				}
				if (i==0)
					adresseUrl = adresseUrl + "lum" +numeroPrise+ "=" +etat;
				else {
					adresseUrl = adresseUrl + "&lum" +numeroPrise+ "=" +etat;
				}
			}
			String source ="";
			URL oracle = new URL(adresseUrl);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							yc.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				source +=inputLine;
			in.close();

			float min_temp = Float.parseFloat(request.getParameter("min_temp"));
			float max_temp = Float.parseFloat(request.getParameter("max_temp"));
			float max_humd = Float.parseFloat(request.getParameter("max_humd"));
			float min_humd = Float.parseFloat(request.getParameter("min_humd"));

			String[] telephone = request.getParameterValues("telephone[]");
			String[] email = request.getParameterValues("email[]");
			String erreur = "";
			for(int i = 0 ; i < telephone.length ; i++){

				Contact contact = M_Data.getInstance().getContactByEmail(email[i],telephone[i], mac);
				if(email[i].equals("") && !telephone[i].equals("") || telephone[i].equals("") && !email[i].equals("")){
					result = false;
					erreur = "L'adresse mail ou le numéro de téléphone est invalide";
				}
				
			
					else{
						if(contact == null && !email[i].equals("")&& !telephone[i].equals("")){
							contact = new Contact(mac,email[i],telephone[i]);
							result = M_Data.getInstance().InsertContact(contact);
							erreur = "L'adresse mail et/ou le numéro de téléphone sont déjà existant";

						}
					}
				
			}

			M_Data.getInstance().updateMultiprise(new Multiprise(mac,min_temp, max_temp,min_humd,max_humd));

			Environnement environnement = M_Data.getInstance().getLastEnvironnement(mac);
			if (result == false){
				Cookie myCookie = new Cookie("erreur", erreur);
				response.addCookie(myCookie);
				//request.setAttribute("retour", erreur);
			}
			request.setAttribute("multiprise", multiprise);
			request.setAttribute("environnement", environnement);
			request.setAttribute("suivi",  M_Data.getInstance().getConsoPrise(mac));

			RequestDispatcher dispatch = request.getRequestDispatcher ("/Vue/accueil.jsp");
			dispatch.forward (request, response);	
		}
		if (operation.equals("remove")){
			String id = request.getParameter("id");

			boolean result = M_Data.getInstance().deleteContact(id);

			Multiprise multiprise = M_Data.getInstance().getMultipriseDetail(M_Data.MAC_MULTIPRISE);
			Environnement environnement = M_Data.getInstance().getLastEnvironnement(M_Data.MAC_MULTIPRISE);

			request.setAttribute("multiprise", multiprise);
			request.setAttribute("environnement", environnement);
			request.setAttribute("suivi",  M_Data.getInstance().getConsoPrise(M_Data.MAC_MULTIPRISE));


			//request.setAttribute("retour", result);

			RequestDispatcher dispatch = request.getRequestDispatcher ("/Vue/accueil.jsp");
			dispatch.forward (request, response);	

		}
	}
}
