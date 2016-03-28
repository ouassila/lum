package Controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
			Multiprise multiprise = M_Data.getInstance().getMultipriseDetail("08:00:27:d1:76:e4");
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
			System.out.println(source);
			System.out.println("connexion");
			System.out.println(request.getParameter("min_temp"));
			float min_temp = Float.parseFloat(request.getParameter("min_temp"));
			float max_temp = Float.parseFloat(request.getParameter("max_temp"));
			float max_humd = Float.parseFloat(request.getParameter("max_humd"));
			float min_humd = Float.parseFloat(request.getParameter("min_humd"));

			String[] telephone = request.getParameterValues("telephone[]");
			String[] email = request.getParameterValues("email[]");
			for(int i = 0 ; i < telephone.length ; i++){

				Contact contact = M_Data.getInstance().getContactByEmail(email[i], mac);

				if(contact != null){
					result=false;				 }
				else{
					System.out.println("contact nul");
					contact = new Contact(mac,email[i],telephone[i]);
					result = M_Data.getInstance().InsertContact(contact);
				}
			}

			M_Data.getInstance().updateMultiprise(new Multiprise(mac,min_temp, max_temp,min_humd,max_humd));

			Environnement environnement = M_Data.getInstance().getLastEnvironnement(mac);
			System.out.println(result);
			if (result==false){
				request.setAttribute("retour", "Erreur : l'adresse mail et/ou le numéro de téléphone est déjà existant");
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

			Multiprise multiprise = M_Data.getInstance().getMultipriseDetail("08:00:27:d1:76:e4");
			Environnement environnement = M_Data.getInstance().getLastEnvironnement("08:00:27:d1:76:e4");

			request.setAttribute("multiprise", multiprise);
			request.setAttribute("environnement", environnement);
			request.setAttribute("suivi",  M_Data.getInstance().getConsoPrise("08:00:27:d1:76:e4"));

			System.out.println(result);
			//request.setAttribute("retour", result);

			RequestDispatcher dispatch = request.getRequestDispatcher ("/Vue/accueil.jsp");
			dispatch.forward (request, response);	

		}
	}
}
