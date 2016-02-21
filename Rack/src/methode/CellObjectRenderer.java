package methode;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CellObjectRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = -3962742480076681410L;

	/* constructeur par defaut */
	public CellObjectRenderer() {
		super();
		setOpaque(true); // A NE PAS OUBLIER sinon ca marche pas
	}

	/**
	 * méthode personnalisant les couleurs de la cellule.
	 *
	 * @param table
	 * @param value
	 * @param isSelected
	 * @param hasFocus
	 * @param row
	 * @param column
	 * @return le component a afficher
	 */

	/* personnalisation des cellules du tableau */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText((String) value);
		if ((row % 2) != 0) { // ligne impaire
			setBackground(new Color(174, 213, 214));
		} else { // ligne paire
			setBackground(Color.white);
		}

		if (isSelected) { // ligne selectionnée
			setBackground(new Color(115, 194, 251));
			setForeground(new Color(15, 5, 107));
			// isCellEditable(row,column);
		} else { // si pas selectionnée
			setForeground(new Color(96, 96, 96));
		}
		return this;
	}
}
