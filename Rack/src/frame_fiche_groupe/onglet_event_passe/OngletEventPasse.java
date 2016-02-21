package frame_fiche_groupe.onglet_event_passe;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import frame_fiche_groupe.FicheGroupe;
import frame_principale.Main;

//import java.awt.LayoutManager;

public class OngletEventPasse extends JPanel {

	private static final long serialVersionUID = 8447748484192552380L;
	/* position des elements */
	private static int POSITION_X = 27;
	private static int POSITION_Y = 40;
	String[] col_event_passe = { "Event", "Ville", "Lieu", "Début", "Fin" };
	Object[] mess_event_passe = { "Rock en Seine", "Saint-Cloud", "?", "26 aout 2016", "28 aout 2016" };
	public static JLabel nom_event;
	private DefaultTableModel liste_event_passe;
	private JTable table_event_passe;
	private JScrollPane scrollpane_event_passe;
	private TableCellRenderer renderer = new DefaultTableCellRenderer();

	/* constructeur par defaut */
	public OngletEventPasse() {
		this.setLayout(null);
		init_Conponents();
	}

	/* initialisation des composants */
	private void init_Conponents() {

		/* titre du tableau */
		nom_event = new JLabel("Evenements passés");
		nom_event.setFont(TITRE3);
		nom_event.setForeground(COULEUR_TEXTE);
		nom_event.setBounds(5, (POSITION_Y - 34), FicheGroupe.largeur_fenetre - 10, HAUTEUR_ELEMENT);
		nom_event.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(nom_event);

		/* tableau des evenepents passes du groupe */
		liste_event_passe = new DefaultTableModel(col_event_passe, 0) {
			private static final long serialVersionUID = 4103936026894095006L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_event_passe = new JTable(liste_event_passe);
		table_event_passe.setDefaultRenderer(Object.class, renderer);
		table_event_passe.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		table_event_passe.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		scrollpane_event_passe = new JScrollPane(table_event_passe);
		this.add(scrollpane_event_passe);
		scrollpane_event_passe.setBounds((POSITION_X + 10), POSITION_Y, 520, 380);
		scrollpane_event_passe.setOpaque(false);
		scrollpane_event_passe.getViewport().setOpaque(false);
		table_event_passe.setRowHeight(Main.HAUTEUR_ELEMENT);
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
