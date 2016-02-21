package objets;

public class Titre {

	public String titre;
	public int annee;
	public Object duree;
	public String type;
	public boolean reprise_titre;
	public String auteur;

	public Titre() {
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String value) {
		this.titre = value;
	}

	public int getAnnee() {
		return this.annee;
	}

	public void setAnnee(int value) {
		this.annee = value;
	}

	public Object getDuree() {
		return this.duree;
	}

	public void setDuree(Object object) {
		this.duree = object;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String value) {
		this.type = value;
	}

	public boolean isReprise_titre() {
		return this.reprise_titre;
	}

	public void setReprise_titre(boolean value) {
		this.reprise_titre = value;
	}

	public String getAuteur() {
		return this.auteur;
	}

	public void setAuteur(String value) {
		this.auteur = value;
	}
}
