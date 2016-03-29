package Controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modele.Environnement;
import Modele.M_Data;
import Modele.Multiprise;

public class C_ShowCharts extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String source ="";
		URL oracle = new URL("http://172.16.15.94/req.php?lum1=off");
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null){
			source +=inputLine;
		}

		in.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String operation = request.getParameter("operation");			
		
		if (operation.equals("show")){
			String datas = request.getParameter("datas");
			String periode = request.getParameter("periode");
			String mac = request.getParameter("mac");			

			String dateDeb = periode.split("#")[0];
			String dateFin = periode.split("#")[1];
			List<Map<String, String>> resultat = new ArrayList<Map<String, String>>();
			
			if(datas.contains("temperature") || datas.contains("humidite")){	
				resultat = M_Data.getInstance().getHistoriqueEnvironnement(mac, datas, dateDeb, dateFin);
			}
			
			if( datas.contains("etat_")){
				String id_prise = datas.substring(datas.length() - 1); 
				
				resultat = M_Data.getInstance().getHistoriqueEtat(id_prise, dateDeb, dateFin);
			}
			Multiprise multiprise = M_Data.getInstance().getMultipriseDetail(mac);
			Environnement environnement = M_Data.getInstance().getLastEnvironnement(mac);

			request.setAttribute("multiprise", multiprise);
			request.setAttribute("environnement", environnement);
			request.setAttribute("suivi",  M_Data.getInstance().getConsoPrise(M_Data.MAC_MULTIPRISE));
			
			Cookie myCookie = new Cookie("datas", (resultat.toString()).replace("=", ":"));
			response.addCookie(myCookie);
			
			RequestDispatcher dispatch = request.getRequestDispatcher ("/Vue/accueil.jsp");			
			dispatch.forward (request, response);
		}
	}
}
