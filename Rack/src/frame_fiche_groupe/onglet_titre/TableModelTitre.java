package frame_fiche_groupe.onglet_titre;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objets.Titre;

public class TableModelTitre extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/* liste de titre */
	private final List<Titre> liste_titre = new ArrayList<Titre>();

	private final String[] entetes = { "Titre", "Année", "Durée" };

	public TableModelTitre() {
		super();
	}

	/* retourne le nombre de lignes du tableau */
	public int getRowCount() {
		return liste_titre.size();
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
			return liste_titre.get(rowIndex).getTitre();
		case 1:
			return liste_titre.get(rowIndex).getAnnee();
		case 2:
			return liste_titre.get(rowIndex).getDuree();
		case 3:
			return liste_titre.get(rowIndex).getType();
		case 4:
			return liste_titre.get(rowIndex).isReprise_titre();
		case 5:
			return liste_titre.get(rowIndex).getAuteur();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	/* ajoute un groupe a la liste de titre */
	public void addTitre(Titre titre) {
		liste_titre.add(titre);
		fireTableRowsInserted(liste_titre.size() - 1, liste_titre.size() - 1);
	}

	/* supprime un groupe de la liste de titre */
	public void removeTitre(int rowIndex) {
		liste_titre.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/* indique quelles sont les cellules éditables */
	// public boolean isCellEditable(int rowIndex, int columnIndex) {
	// return true; //Toutes les cellules éditables
	// }

	/* prend en compte les modifications du titre */
	public void setValueAt(Titre obj, int rowIndex) {
		if (obj != null) {
			Titre titre = liste_titre.get(rowIndex);
			titre.setTitre(obj.getTitre());
			titre.setAnnee(obj.getAnnee());
			titre.setDuree(obj.getDuree());
			titre.setType(obj.getType());
			titre.setReprise_titre(obj.isReprise_titre());
			titre.setAuteur(obj.getAuteur());
		}
	}
}
