package frame_principale.onglet_planif;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objets.Programme;
import objets.Rencontre;

public class TableModelRencontre extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/* liste de rencontres */
	private final List<Rencontre> liste_rencontre = new ArrayList<Rencontre>();
	private final String[] entetes = { "Evénement", "Ville", "Lieu", "Date début", "Date fin", "Périodicité",
			"Nombre personnes attendues" };

	public TableModelRencontre() {
		super();
	}

	/* retourne le nombre de lignes du tableau */
	public int getRowCount() {
		return liste_rencontre.size();
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
			return liste_rencontre.get(rowIndex).getNom_renc();
		case 1:
			return liste_rencontre.get(rowIndex).getVille_renc();
		case 2:
			return liste_rencontre.get(rowIndex).getLieu_renc();
		case 3:
			return liste_rencontre.get(rowIndex).getDate_deb_renc();
		case 4:
			return liste_rencontre.get(rowIndex).getDate_fin_renc();
		case 5:
			return liste_rencontre.get(rowIndex).getPeriodicite_renc();
		case 6:
			return liste_rencontre.get(rowIndex).getNb_pers_attendues();
		default:
			return null;
		}
	}

	/* retourne une rencontre de la liste de groupe */
	public Rencontre getRencontreAt(int rowIndex) {
		return liste_rencontre.get(rowIndex);
	}

	/* retourne la liste de programme d'une rencontre selectionnée */
	public List<Programme> getListeProgAt(int rowIndex) {
		return liste_rencontre.get(rowIndex).getProgramme();
	}

	/*
	 * retourne les attribus d'un programme de la liste de programme d'une
	 * rencontre
	 */
	public void setValueProgAt(Programme prog1, int rowIndexRenc, int rowindexProg) {
		if (prog1 != null) {
			Rencontre rencontre1 = liste_rencontre.get(rowIndexRenc);
			rencontre1.getProgramme().get(rowindexProg).setGroupe_produit(prog1.getGroupe_produit());
			rencontre1.getProgramme().get(rowindexProg).setTitre_joue(prog1.getTitre_joue());
			rencontre1.getProgramme().get(rowindexProg).setHeure_debut(prog1.getHeure_debut());
			rencontre1.getProgramme().get(rowindexProg).setHeure_fin(prog1.getHeure_fin());
		}
	}

	/* ajoute un groupe a la liste de rencontre */
	public void addRencontre(Rencontre rencontre) {
		liste_rencontre.add(rencontre);
		fireTableRowsInserted(liste_rencontre.size() - 1, liste_rencontre.size() - 1);
	}

	/* supprime un groupe de la liste de rencontre */
	public void removeRencontre(int rowIndex) {
		liste_rencontre.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/* prend en compte les modifications de la rencontre */
	public void setValueAt(Rencontre obj, int rowIndex) {
		if (obj != null) {
			Rencontre rencontre = liste_rencontre.get(rowIndex);
			rencontre.setVille_renc(obj.getVille_renc());
			rencontre.setLieu_renc(obj.getLieu_renc());
			rencontre.setNom_renc(obj.getNom_renc());
			rencontre.setDate_deb_renc(obj.getDate_deb_renc());
			rencontre.setDate_fin_renc(obj.getDate_fin_renc());
			rencontre.setPeriodicite_renc(obj.getPeriodicite_renc());
			rencontre.setNb_pers_attendues(obj.getNb_pers_attendues());
		}
	}
}
