package frame_principale.onglet_planif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import frame_fiche_event.FicheEvent;
import frame_fiche_event.onglet_infos.OngletInfosEvent;
import frame_fiche_event.onglet_prog.OngletProgramme;
import frame_principale.Main;

public class OngletPlanification extends JPanel {

	private static final long serialVersionUID = -7498384458601922617L;

	/* composants */
	public static JTable table_planif;
	public static JScrollPane scrollpane_planif;
	public static TableModelRencontre modele = new TableModelRencontre();

	/* constructeur par defaut */
	public OngletPlanification() {
		this.setLayout(new BorderLayout());
		init_Conponents();
		this.setBackground(Main.COULEUR_FOND_DEFAULT);
	}

	/* initialisation des composants */
	private void init_Conponents() {

		/* tableau de planification */
		table_planif = new JTable(modele);
		table_planif.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		table_planif.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		table_planif.setRowHeight(Main.HAUTEUR_ELEMENT);
		// table_planif.setAutoCreateRowSorter(true);
		table_planif.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/* Compte le nombre de clics de la souris */
				if (e.getClickCount() == 2) {
					/* ouvre la fiche de l'évenement */
					FicheEvent fiche_event_exist = new FicheEvent();
					fiche_event_exist.setTitle(OngletPlanification.modele
							.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 0).toString() + " "
							+ OngletPlanification.modele
									.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 1).toString());
					FicheEvent.degeleOngletsEvent();
					OngletInfosEvent.modifOngletInfoEvent();
					OngletProgramme.modifOngletProgEvent();
					/*
					 * met les labels de titre à jour avec le nom de l'événement
					 */
					if (!OngletInfosEvent.nom_event.getText().equals(OngletInfosEvent.tab_nom_textfield[0])) {
						OngletProgramme.nom_event.setText("Programme " + OngletInfosEvent.nom_event.getText());
					}
					fiche_event_exist.setVisible(true);
				}
			}
		});

		/* scrollpane du tableau */
		scrollpane_planif = new JScrollPane(table_planif);
		scrollpane_planif.getViewport().setBackground(Color.black);
		scrollpane_planif.setOpaque(false);
		scrollpane_planif.getViewport().setOpaque(false);
		scrollpane_planif.setToolTipText("Double-clic pour créer un nouveau groupe");
		scrollpane_planif.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/* Compte le nombre de clics de la souris */
				if (e.getClickCount() == 2) {
					/* ouvre une fenetre vierge */
					new FicheEvent().setVisible(true);
				}
			}
		});

		/* liste du tableau */
		this.add(scrollpane_planif);
	}

	/* formatage des colonnes de dates */
	TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = 1L;
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (value instanceof Date) {
				value = f.format(value);
			}
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	};
}
