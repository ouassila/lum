package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Historique {
private List<Map<String, String>> resultat;
private String test;

public Historique(){
	resultat = new ArrayList<Map<String, String>>();
	setTest("");
}
public List<Map<String, String>> getResultat() {
	return resultat;
}

public void setResultat(List<Map<String, String>> resultat) {
	this.resultat = resultat;
}
public String getTest() {
	return test;
}
public void setTest(String test) {
	this.test = test;
}
}
