package frame_principale.onglet_reper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import frame_fiche_groupe.FicheGroupe;
import frame_fiche_groupe.onglet_event_futur.OngletEventFutur;
import frame_fiche_groupe.onglet_infos.OngletInfosGroupe;
import frame_fiche_groupe.onglet_titre.OngletTitre;
import frame_principale.Main;

public class OngletRepertoire extends JPanel {

	private static final long serialVersionUID = 5620819187280768593L;
	public static boolean existant =false;

	/* tableau de repertoire */
	public static JTable table_repertoire;
	public static JScrollPane scrollpane_repertoire;
	public static TableModelGroupe modele = new TableModelGroupe();
	private TableCellRenderer renderer = new DefaultTableCellRenderer();

	/* constructeur par defaut */
	public OngletRepertoire() {
		this.setLayout(new BorderLayout());
		init_Conponents();
		this.setBackground(Main.COULEUR_FOND_DEFAULT);
	}

	/* initialisation des composants */
	private void init_Conponents() {

		table_repertoire = new JTable(modele);
		table_repertoire.setDefaultRenderer(Object.class, renderer);
		table_repertoire.setRowHeight(Main.HAUTEUR_ELEMENT);
		// table_repertoire.setAutoCreateRowSorter(true);
		table_repertoire.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/* Compte le nombre de clics de la souris */
				if (e.getClickCount() == 2) {
					/* ouvre la fiche du groupe */
					FicheGroupe fiche_groupe_exist = new FicheGroupe();
					fiche_groupe_exist.setTitle(OngletRepertoire.modele
							.getValueAt(OngletRepertoire.table_repertoire.getSelectedRow(), 0).toString());
					/* ajoute les onglets manquants */
					FicheGroupe.degeleOngletsGroupe();
					OngletInfosGroupe.modifOngletInfoGroupe();
					OngletTitre.modifOngletTitreGroupe();
					OngletEventFutur.modifOngletEventFutur();
					fiche_groupe_exist.setVisible(true);
				}
			}
		});

		/* scrollpane du tableau */
		scrollpane_repertoire = new JScrollPane(table_repertoire);
		scrollpane_repertoire.getViewport().setBackground(Color.black);
		scrollpane_repertoire.setOpaque(false);
		scrollpane_repertoire.getViewport().setOpaque(false);
		scrollpane_repertoire.setToolTipText("Double-clic pour créer un nouveau groupe");
		scrollpane_repertoire.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/* Compte le nombre de clics de la souris */
				if (e.getClickCount() == 2) {
					/* ouvre une fenetre vierge */
					new FicheGroupe().setVisible(true);
				}
			}
		});
		this.add(scrollpane_repertoire);
	}
}
