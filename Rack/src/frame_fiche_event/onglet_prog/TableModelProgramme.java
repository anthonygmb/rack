package frame_fiche_event.onglet_prog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objets.Programme;

public class TableModelProgramme extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/* liste de programmes */
	private final List<Programme> liste_prog = new ArrayList<Programme>();
	private final String[] entetes = { "Groupe", "Titre", "Début", "Fin" };

	public TableModelProgramme() {
		super();
	}

	/* retourne le nombre de lignes du tableau */
	public int getRowCount() {
		return liste_prog.size();
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
			return liste_prog.get(rowIndex).getGroupe_produit().getNom_groupe();
		case 1:
			return liste_prog.get(rowIndex).getTitre_joue().getTitre();
		case 2:
			return liste_prog.get(rowIndex).getHeure_debut();
		case 3:
			return liste_prog.get(rowIndex).getHeure_fin();
		default:
			return null;
		}
	}

	/* ajoute un programme a la liste de programme */
	public void addProgramme(Programme prog1) {
		liste_prog.add(prog1);
		fireTableRowsInserted(liste_prog.size() - 1, liste_prog.size() - 1);
	}

	/* supprime un programme de la liste de programme */
	public void removeProgramme(int rowIndex) {
		liste_prog.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/* prend en compte les modifications du programme */
	public void setValueAt(Programme obj, int rowIndex) {
		if (obj != null) {
			Programme prog1 = liste_prog.get(rowIndex);
			prog1.setHeure_debut(obj.getHeure_debut());
			prog1.setHeure_fin(obj.getHeure_fin());
			prog1.setGroupe_produit(obj.getGroupe_produit());
			prog1.setTitre_joue(obj.getTitre_joue());
		}
	}
}
