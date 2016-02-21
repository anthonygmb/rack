package objets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rencontre {

	public String ville_renc;
	public String lieu_renc;
	public String nom_renc;
	public Date date_deb_renc;
	public Date date_fin_renc;
	public String periodicite_renc;
	public String nb_pers_attendues;
	public List<Organisateur> organisateur = new ArrayList<Organisateur>();
	public List<Programme> programme = new ArrayList<Programme>();

	public Rencontre() {
	}

	public String getVille_renc() {
		return this.ville_renc;
	}

	public void setVille_renc(String value) {
		this.ville_renc = value;
	}

	public String getLieu_renc() {
		return this.lieu_renc;
	}

	public void setLieu_renc(String value) {
		this.lieu_renc = value;
	}

	public String getNom_renc() {
		return this.nom_renc;
	}

	public void setNom_renc(String value) {
		this.nom_renc = value;
	}

	public Date getDate_deb_renc() {
		return this.date_deb_renc;
	}

	public void setDate_deb_renc(Date value) {
		this.date_deb_renc = value;
	}

	public Date getDate_fin_renc() {
		return this.date_fin_renc;
	}

	public void setDate_fin_renc(Date value) {
		this.date_fin_renc = value;
	}

	public String getPeriodicite_renc() {
		return this.periodicite_renc;
	}

	public void setPeriodicite_renc(String value) {
		this.periodicite_renc = value;
	}

	public String getNb_pers_attendues() {
		return this.nb_pers_attendues;
	}

	public void setNb_pers_attendues(String value) {
		this.nb_pers_attendues = value;
	}

	public List<Organisateur> getOrganisateur() {
		return this.organisateur;
	}

	public void setOrganisateur(List<Organisateur> value) {
		this.organisateur = value;
	}

	public List<Programme> getProgramme() {
		return programme;
	}

	public void setProgramme(List<Programme> programme) {
		this.programme = programme;
	}
}
