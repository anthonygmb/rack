package frame_fiche_groupe.onglet_event_futur;

import static frame_principale.Main.COULEUR_TEXTE;
import static frame_principale.Main.HAUTEUR_ELEMENT;
import static frame_principale.Main.TITRE3;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import frame_fiche_groupe.FicheGroupe;
import frame_principale.Main;
import frame_principale.onglet_reper.OngletRepertoire;

public class OngletEventFutur extends JPanel {

	private static final long serialVersionUID = 2702395863125486163L;
	/* position des elements */
	private static int POSITION_X = 27;
	private static int POSITION_Y = 40;

	// String[] col_event_futur = { "Event", "Ville", "Lieu", "Début", "Fin" };
	// Object[] mess_event_futur = { "Rock en Seine", "Saint-Cloud", "?", "26
	// aout 2016", "28 aout 2016" };
	public static JLabel nom_event, trait, modif_event;
	// private DefaultTableModel liste_event_futur;
	private JTable table_event_futur;
	private JScrollPane scrollpane_event_futur;
	public static TableModelEventFutur modele = new TableModelEventFutur();
	private TableCellRenderer renderer = new DefaultTableCellRenderer();

	/* constructeur par defaut */
	public OngletEventFutur() {
		this.setLayout(null);
		init_Conponents();
	}

	/* initialisation des composants */
	private void init_Conponents() {

		/* titre du tableau */
		nom_event = new JLabel("Evénements futurs");
		nom_event.setFont(TITRE3);
		nom_event.setBounds(5, (POSITION_Y - 34), FicheGroupe.largeur_fenetre - 10, HAUTEUR_ELEMENT);
		nom_event.setHorizontalAlignment(SwingConstants.CENTER);
		nom_event.setForeground(COULEUR_TEXTE);
		this.add(nom_event);

		/* tableau des evenepents futurs du groupe */
		// liste_event_futur = new DefaultTableModel(col_event_futur, 0) {
		// private static final long serialVersionUID = 4103936026894095006L;
		//
		// @Override
		// public boolean isCellEditable(int row, int column) {
		// return false;
		// }
		// };
		table_event_futur = new JTable(modele);
		table_event_futur.setDefaultRenderer(Object.class, renderer);
		// table_event_futur.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		// table_event_futur.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		scrollpane_event_futur = new JScrollPane(table_event_futur);
		scrollpane_event_futur.setOpaque(false);
		scrollpane_event_futur.getViewport().setOpaque(false);
		this.add(scrollpane_event_futur);
		scrollpane_event_futur.setBounds((POSITION_X + 10), POSITION_Y, 520, 380);
		table_event_futur.setRowHeight(Main.HAUTEUR_ELEMENT);
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

	/* methode de modification d'evenement */
	public static void modifOngletEventFutur() {
		if (!OngletRepertoire.modele.getListeRencontreAt(OngletRepertoire.table_repertoire.getSelectedRow())
				.isEmpty()) {
			for (int i = 0; i < OngletRepertoire.modele
					.getListeRencontreAt(OngletRepertoire.table_repertoire.getSelectedRow()).size(); i++) {
				OngletEventFutur.modele.addRencontre(OngletRepertoire.modele
						.getListeRencontreAt(OngletRepertoire.table_repertoire.getSelectedRow()).get(i));
			}
		}
	}
}
