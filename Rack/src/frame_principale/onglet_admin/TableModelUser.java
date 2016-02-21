package frame_principale.onglet_admin;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objets.User;

/* modele de table pour le tableau d'utilisateurs de l'onglet administration */
public class TableModelUser extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/* liste de groupes */
	public static List<User> liste_user = new ArrayList<User>();

	private final String[] entetes = { "Login", "Mot de passe" };

	public TableModelUser() {
		super();
	}

	/* retourne le nombre de lignes du tableau */
	public int getRowCount() {
		return liste_user.size();
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
			return liste_user.get(rowIndex).getLogin();
		case 1:
			return liste_user.get(rowIndex).getMod_de_passe();
		default:
			return null;
		}
	}

	/* ajoute un user a la liste de user */
	public void addUser(User user) {
		liste_user.add(user);
		fireTableRowsInserted(liste_user.size() - 1, liste_user.size() - 1);
	}

	/* supprime un user de la liste de user */
	public void removeUser(int rowIndex) {
		liste_user.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/* ajoute une liste d'user à la liste de user */
	public void addListUser(List<User> liste_user) {
		// liste_user.add(liste_user);
		TableModelUser.liste_user = liste_user;
		fireTableRowsInserted(liste_user.size() - 1, liste_user.size() - 1);
	}

	/* indique quelles sont les cellules éditables */
	// public boolean isCellEditable(int rowIndex, int columnIndex) {
	// return true; //Toutes les cellules éditables
	// }

	/* prend en compte les modifications du user */
	public void setValueAt(User obj, int rowIndex) {
		if (obj != null) {
			User user = liste_user.get(rowIndex);
			user.setLogin(obj.getLogin());
			user.setMod_de_passe(obj.getMod_de_passe());
		}
	}
}
