package objets;

public class Programme {

	private Object heure_debut;
	private Object heure_fin;
	private Titre titre_joue = new Titre();
	private Groupe groupe_produit = new Groupe();

	public Programme() {
	}

	public Object getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(Object object) {
		this.heure_debut = object;
	}

	public Object getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(Object object) {
		this.heure_fin = object;
	}

	public Titre getTitre_joue() {
		return titre_joue;
	}

	public void setTitre_joue(Titre titre_joue) {
		this.titre_joue = titre_joue;
	}

	public Groupe getGroupe_produit() {
		return groupe_produit;
	}

	public void setGroupe_produit(Groupe groupe_produit) {
		this.groupe_produit = groupe_produit;
	}
}
