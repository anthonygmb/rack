package frame_fiche_groupe;

import static frame_principale.Main.COULEUR_FOND_DEFAULT;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import frame_fiche_groupe.onglet_event_futur.OngletEventFutur;
import frame_fiche_groupe.onglet_event_passe.OngletEventPasse;
import frame_fiche_groupe.onglet_infos.OngletInfosGroupe;
import frame_fiche_groupe.onglet_membre.OngletMembreGroupe;
import frame_fiche_groupe.onglet_titre.OngletTitre;
import frame_principale.FenetrePrincipale;

public class FicheGroupe extends JDialog {

	private static final long serialVersionUID = 1107210110037190150L;

	/* composants */
	public static JTabbedPane onglet_groupe;
	private OngletInfosGroupe onglet_infos_groupe;
	private static OngletMembreGroupe onglet_membre_groupe;
	private static OngletTitre onglet_titre_groupe;
	private static OngletEventFutur onglet_ev_fut_groupe;
	private static OngletEventPasse onglet_ev_pas_groupe;

	/* variables */
	final public static int largeur_fenetre = 600;

	/* constructeur par defaut */
	public FicheGroupe() {
		super(FenetrePrincipale.getFrame());
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(largeur_fenetre, 500);
		setResizable(false);
		setLocationRelativeTo(FenetrePrincipale.getFrame());
		setTitle("Nouveau groupe *");
		/* vide le tableau de titres lorsque l'on ferme la fenetre */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				for (int i = OngletEventFutur.modele.getRowCount(); i > 0; i--) {
					OngletEventFutur.modele.removeRencontre(i - 1);
				}
				for (int i = OngletTitre.modele.getRowCount(); i > 0; i--) {
					OngletTitre.modele.removeTitre(i - 1);
				}
				OngletInfosGroupe.groupe_exist = false;
			}
		});
		initComponents();
	}

	/* initialisation des composants */
	private void initComponents() {

		/* déclaration des onglets, du conteneur d'onglets */
		onglet_groupe = new JTabbedPane();
		onglet_infos_groupe = new OngletInfosGroupe(this);
		onglet_membre_groupe = new OngletMembreGroupe();
		onglet_titre_groupe = new OngletTitre();
		onglet_ev_fut_groupe = new OngletEventFutur();
		onglet_ev_pas_groupe = new OngletEventPasse();
		this.getContentPane();
		this.add(onglet_groupe);

		/* ajout des onglets au conteneur */
		onglet_groupe.addTab("Informations", onglet_infos_groupe);

		/* Couleur des onglets */
		this.getContentPane().setBackground(COULEUR_FOND_DEFAULT);
		onglet_infos_groupe.setBackground(COULEUR_FOND_DEFAULT);
		onglet_membre_groupe.setBackground(COULEUR_FOND_DEFAULT);
		onglet_titre_groupe.setBackground(COULEUR_FOND_DEFAULT);
		onglet_ev_fut_groupe.setBackground(COULEUR_FOND_DEFAULT);
		onglet_ev_pas_groupe.setBackground(COULEUR_FOND_DEFAULT);

		/* mise a jour des composants lorsqu'on change d'onglet */
		ChangeListener changeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!OngletInfosGroupe.tab_textfield[0].getText().equals(OngletInfosGroupe.tab_nom_label[0])) {
					OngletTitre.nom_titre.setText("Titres du groupe " + OngletInfosGroupe.tab_textfield[0].getText());
					OngletEventFutur.nom_event
							.setText("Evénements futurs de " + OngletInfosGroupe.tab_textfield[0].getText());
					OngletEventPasse.nom_event
							.setText("Evénements passés de " + OngletInfosGroupe.tab_textfield[0].getText());
					OngletTitre.champ_auteur_titre.setText(OngletInfosGroupe.tab_textfield[0].getText());
				}
			}
		};
		onglet_groupe.addChangeListener(changeListener);
	}

	/* methode d'ajout des onglets si le groupe est crée */
	public static void degeleOngletsGroupe() {
		onglet_groupe.addTab("Membres", onglet_membre_groupe);
		onglet_groupe.addTab("Titres", onglet_titre_groupe);
		onglet_groupe.addTab("Events Futurs", onglet_ev_fut_groupe);
		onglet_groupe.addTab("Event passés", onglet_ev_pas_groupe);
	}
}
