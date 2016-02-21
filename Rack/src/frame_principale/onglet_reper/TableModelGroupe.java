package frame_principale.onglet_reper;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objets.Groupe;
import objets.Personne;
import objets.Rencontre;
import objets.Titre;

public class TableModelGroupe extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/* liste de groupes */
	private final List<Groupe> liste_groupe = new ArrayList<Groupe>();
	private final String[] entetes = { "Nom du groupe", "Caracteristique du groupe", "Pays du groupe",
			"Region du groupe" };

	public TableModelGroupe() {
		super();
	}

	/* retourne le nombre de lignes du tableau */
	public int getRowCount() {
		return liste_groupe.size();
	}

	/* retourne le nombre de colonnes du tableau */
	public int getColumnCount() {
		return entetes.length;
	}

	/* retourne l'en tete de la colonnes spécifiée */
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	/* retourne la valeur du tableau à la colonne et la ligne spécifiées */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return liste_groupe.get(rowIndex).getNom_groupe();
		case 1:
			return liste_groupe.get(rowIndex).getCarac_groupe();
		case 2:
			return liste_groupe.get(rowIndex).getPays();
		case 3:
			return liste_groupe.get(rowIndex).getRegion_groupe();
		default:
			return null;
		}
	}

	/* retourne un groupe de la liste de groupe */
	public Groupe getGroupeAt(int rowIndex) {
		return liste_groupe.get(rowIndex);
	}

	/* retourne la liste de titres du groupe selectionné */
	public List<Titre> getListeTitreAt(int rowIndex) {
		return liste_groupe.get(rowIndex).getTitre();
	}

	/* retourne les attribus d'un titre de la liste de titre d'un groupe */
	public void setValueTitreAt(Titre titre1, int rowIndexGroupe, int rowindexTitre) {
		if (titre1 != null) {
			Groupe groupe = liste_groupe.get(rowIndexGroupe);
			groupe.getTitre().get(rowindexTitre).setTitre(titre1.getTitre());
			groupe.getTitre().get(rowindexTitre).setAnnee(titre1.getAnnee());
			groupe.getTitre().get(rowindexTitre).setDuree(titre1.getDuree());
			groupe.getTitre().get(rowindexTitre).setType(titre1.getType());
			groupe.getTitre().get(rowindexTitre).setReprise_titre(titre1.isReprise_titre());
			groupe.getTitre().get(rowindexTitre).setAuteur(titre1.getAuteur());
		}
	}

	/* retourne la liste de personnes du groupe selectionné */
	public List<Personne> getListePersonneAt(int rowIndex) {
		return liste_groupe.get(rowIndex).getPersonne();
	}

	/*
	 * retourne les attribus d'une personne de la liste de personnes d'un groupe
	 */
	public void setValuepersonneAt(Personne personne1, int rowIndexGroupe, int rowindexTitre) {
		if (personne1 != null) {
			Groupe groupe = liste_groupe.get(rowIndexGroupe);
			groupe.getPersonne().get(rowindexTitre).setNom_membre(personne1.getNom_membre());
			groupe.getPersonne().get(rowindexTitre).setPrenom_membre(personne1.getPrenom_membre());
			groupe.getPersonne().get(rowindexTitre).setCivi_membre(personne1.getCivi_membre());
			groupe.getPersonne().get(rowindexTitre).setDate_naiss_membre(personne1.getDate_naiss_membre());
			groupe.getPersonne().get(rowindexTitre).setSpe_membre(personne1.getSpe_membre());
			groupe.getPersonne().get(rowindexTitre).setInstru_membre(personne1.getInstru_membre());
			groupe.getPersonne().get(rowindexTitre).setRespon_membre(personne1.getRespon_membre());
			groupe.getPersonne().get(rowindexTitre).setAdresse_cor(personne1.getAdresse_cor());
			groupe.getPersonne().get(rowindexTitre).setTel_cor(personne1.getTel_cor());
			groupe.getPersonne().get(rowindexTitre).setFax_cor(personne1.getFax_cor());
			groupe.getPersonne().get(rowindexTitre).setMail_cor(personne1.getMail_cor());
		}
	}
	
	/* retourne la liste de rencontre du groupe selectionné */
	public List<Rencontre> getListeRencontreAt(int rowIndex) {
		return liste_groupe.get(rowIndex).getListe_rencontre();
	}

	/* ajoute un groupe a la liste de groupe */
	public void addGroupe(Groupe groupe) {
		liste_groupe.add(groupe);
		fireTableRowsInserted(liste_groupe.size() - 1, liste_groupe.size() - 1);
	}

	/* supprime un groupe de la liste de groupe */
	public void removeGroupe(int rowIndex) {
		liste_groupe.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/* prend en compte les modifications du groupe */
	public void setValueAt(Groupe obj, int rowIndex) {
		if (obj != null) {
			Groupe groupe = liste_groupe.get(rowIndex);
			groupe.setNom_groupe(obj.getNom_groupe());
			groupe.setCarac_groupe(obj.getCarac_groupe());
			groupe.setPays(obj.getPays());
			groupe.setRegion_groupe(obj.getRegion_groupe());
		}
	}
}
