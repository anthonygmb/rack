package frame_fiche_event;

import static frame_principale.Main.COULEUR_FOND_DEFAULT;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import frame_fiche_event.onglet_infos.OngletInfosEvent;
import frame_fiche_event.onglet_orga.OngletOrganisateur;
import frame_fiche_event.onglet_prog.OngletProgramme;
import frame_principale.FenetrePrincipale;

public class FicheEvent extends JDialog {

	private static final long serialVersionUID = 6488286816072897137L;

	/* composants */
	private static JTabbedPane onglet_event;
	private OngletInfosEvent onglet_info_event;
	private static OngletOrganisateur onglet_orga_event;
	private static OngletProgramme onglet_prog_event;

	/* variables */
	public static boolean event_exist = false;
	final public static int largeur_fenetre = 600;

	/* constructeur par defaut */
	public FicheEvent() {
		super(FenetrePrincipale.getFrame());
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(largeur_fenetre, 500);
		setResizable(false);
		setLocationRelativeTo(FenetrePrincipale.getFrame());
		setTitle("Nouvel événement *");
		/*
		 * vide le combobox de groupes et le tableau de programmes lorsque l'on
		 * ferme la fenetre
		 */
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				for (int i = OngletProgramme.modele.getRowCount(); i > 0; i--) {
					OngletProgramme.modele.removeProgramme(i - 1);
				}
				event_exist = false;
			}
		});
		initComponents();
	}

	/* initialisation des composants */
	private void initComponents() {

		/* déclaration des onglets, du conteneur d'onglets */
		onglet_event = new JTabbedPane();
		onglet_info_event = new OngletInfosEvent(this);
		onglet_orga_event = new OngletOrganisateur();
		onglet_prog_event = new OngletProgramme();
		this.getContentPane();
		this.add(onglet_event);

		/* ajout des onglets au conteneur */
		onglet_event.addTab("Informations", onglet_info_event);

		/* Couleur des onglets */
		this.getContentPane().setBackground(COULEUR_FOND_DEFAULT);
		onglet_orga_event.setBackground(COULEUR_FOND_DEFAULT);
		onglet_info_event.setBackground(COULEUR_FOND_DEFAULT);
		onglet_prog_event.setBackground(COULEUR_FOND_DEFAULT);
	}

	/* methode d'ajout des onglets si le groupe est créer */
	public static void degeleOngletsEvent() {
		onglet_event.addTab("Organisateur", onglet_orga_event);
		onglet_event.addTab("Programme", onglet_prog_event);
	}
}
