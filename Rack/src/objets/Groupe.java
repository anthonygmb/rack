package objets;

import java.util.ArrayList;
import java.util.List;

public class Groupe {

	private String nom_groupe;
	private String carac_groupe;
	private String pays;
	private String region_groupe;
	private List<Titre> liste_titre = new ArrayList<Titre>();
	private List<Personne> liste_personne = new ArrayList<Personne>();
	private List<Rencontre> liste_rencontre = new ArrayList<Rencontre>();

	public Groupe() {
	}

	public String getNom_groupe() {
		return this.nom_groupe;
	}

	public void setNom_groupe(String value) {
		this.nom_groupe = value;
	}

	public String getCarac_groupe() {
		return this.carac_groupe;
	}

	public void setCarac_groupe(String value) {
		this.carac_groupe = value;
	}

	public String getRegion_groupe() {
		return this.region_groupe;
	}

	public void setRegion_groupe(String value) {
		this.region_groupe = value;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public List<Titre> getTitre() {
		return this.liste_titre;
	}

	public void setTitre(List<Titre> value) {
		this.liste_titre = value;
	}

	public List<Personne> getPersonne() {
		return this.liste_personne;
	}

	public void setPersonne(List<Personne> value) {
		this.liste_personne = value;
	}
	
	public List<Rencontre> getListe_rencontre() {
		return liste_rencontre;
	}

	public void setListe_rencontre(List<Rencontre> liste_rencontre) {
		this.liste_rencontre = liste_rencontre;
	}
}
