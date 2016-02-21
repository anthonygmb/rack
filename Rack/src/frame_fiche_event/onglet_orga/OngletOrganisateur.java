package frame_fiche_event.onglet_orga;

import static frame_fiche_event.FicheEvent.event_exist;
import static frame_principale.Main.COULEUR_TEXTE;
import static frame_principale.Main.HAUTEUR_ELEMENT;
import static frame_principale.Main.LARGEUR_ELEMENT;
import static frame_principale.Main.TITRE3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frame_principale.onglet_planif.OngletPlanification;
import methode.FocusTextField;

public class OngletOrganisateur extends JPanel {

	private static final long serialVersionUID = 6813969097186717601L;

	/* position des elements */
	static private int POSITION_X = 67;
	static private int POSITION_Y = 40;

	String[] tab_orga = { "Ajout nouvel organisateur" };
	private JLabel civilite;
	private JCheckBox civi_madame, civi_monsieur;
	private JComboBox<String> cmbbox_orga;
	private String[] tab_nom_textfield = { "Nom", "Prénom", "Nom de l'entreprise", "Adresse", "Téléphone", "Fax",
			"Mail" };
	private JTextField nom_orga, prenom_orga, entreprise_orga, adresse_orga, telephone_orga, fax_orga, mail_orga;
	private JButton bouton_valider, bouton_annuler, bouton_supprimer;

	/* constructeur par defaut */
	public OngletOrganisateur() {
		this.setLayout(null);
		initComponents();
	}

	/* initialisation des composants */
	private void initComponents() {

		cmbbox_orga = new JComboBox<String>();
		cmbbox_orga.setBounds(POSITION_X, 18, LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		for (int j = 0; j < tab_orga.length; j++) {
			cmbbox_orga.addItem(tab_orga[j]);
		}
		this.add(cmbbox_orga);

		/* civilite de l'organisateur */
		civilite = new JLabel("Civilité");
		civilite.setFont(TITRE3);
		civilite.setBounds((POSITION_X + 60), (POSITION_Y + 20), 120, HAUTEUR_ELEMENT);
		civilite.setForeground(COULEUR_TEXTE);
		this.add(civilite);

		/* checkBox civilite membre madame */
		civi_madame = new JCheckBox(" Madame");
		civi_madame.setBounds((POSITION_X + 230), (POSITION_Y + 20), 120, HAUTEUR_ELEMENT);
		civi_madame.setForeground(COULEUR_TEXTE);
		civi_madame.setOpaque(false);
		civi_madame.setContentAreaFilled(false);
		civi_madame.setBorderPainted(false);
		civi_madame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				civi_monsieur.setSelected(false);
			}
		});
		this.add(civi_madame);

		/* checkBox civilite membre monsieur */
		civi_monsieur = new JCheckBox(" Monsieur");
		civi_monsieur.setBounds((POSITION_X + 350), (POSITION_Y + 20), 120, HAUTEUR_ELEMENT);
		civi_monsieur.setForeground(COULEUR_TEXTE);
		civi_monsieur.setOpaque(false);
		civi_monsieur.setContentAreaFilled(false);
		civi_monsieur.setBorderPainted(false);
		civi_monsieur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				civi_madame.setSelected(false);
			}
		});
		this.add(civi_monsieur);

		/* champ saisie nom d'organisateur */
		nom_orga = new JTextField();
		nom_orga.setText(tab_nom_textfield[0]);
		nom_orga.setToolTipText(tab_nom_textfield[0]);
		nom_orga.setBounds(POSITION_X, (POSITION_Y + 60), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		this.add(nom_orga);
		nom_orga.addFocusListener(new FocusTextField(nom_orga));

		/* champ saisie prenom d'organisateur */
		prenom_orga = new JTextField();
		prenom_orga.setText(tab_nom_textfield[1]);
		prenom_orga.setToolTipText(tab_nom_textfield[1]);
		prenom_orga.setBounds(POSITION_X, (POSITION_Y + 100), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		this.add(prenom_orga);
		prenom_orga.addFocusListener(new FocusTextField(prenom_orga));

		/* champ saisie entreprise organisateur */
		entreprise_orga = new JTextField();
		entreprise_orga.setText(tab_nom_textfield[2]);
		entreprise_orga.setToolTipText(tab_nom_textfield[2]);
		entreprise_orga.setBounds(POSITION_X, (POSITION_Y + 140), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		this.add(entreprise_orga);
		entreprise_orga.addFocusListener(new FocusTextField(entreprise_orga));

		/* champ saisie adresse organisateur */
		adresse_orga = new JTextField();
		adresse_orga.setText(tab_nom_textfield[3]);
		adresse_orga.setToolTipText(tab_nom_textfield[3]);
		adresse_orga.setBounds(POSITION_X, (POSITION_Y + 180), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		this.add(adresse_orga);
		adresse_orga.addFocusListener(new FocusTextField(adresse_orga));

		/* champ saisie telephone organisateur */
		telephone_orga = new JTextField();
		telephone_orga.setText(tab_nom_textfield[4]);
		telephone_orga.setToolTipText(tab_nom_textfield[4]);
		telephone_orga.setBounds(POSITION_X, (POSITION_Y + 220), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		this.add(telephone_orga);
		telephone_orga.addFocusListener(new FocusTextField(telephone_orga));

		/* champ saisie fax organisateur */
		fax_orga = new JTextField();
		fax_orga.setText(tab_nom_textfield[5]);
		fax_orga.setToolTipText(tab_nom_textfield[5]);
		fax_orga.setBounds(POSITION_X, (POSITION_Y + 260), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		this.add(fax_orga);
		fax_orga.addFocusListener(new FocusTextField(fax_orga));

		/* champ saisie mail organisateur */
		mail_orga = new JTextField();
		mail_orga.setText(tab_nom_textfield[6]);
		mail_orga.setToolTipText(tab_nom_textfield[6]);
		mail_orga.setBounds(POSITION_X, (POSITION_Y + 300), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		this.add(mail_orga);
		mail_orga.addFocusListener(new FocusTextField(mail_orga));

		/* bouton valider */
		bouton_valider = new JButton("Valider");
		bouton_valider.setToolTipText("Valider la création de l'organisateur");
		bouton_valider.setBounds(370, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_valider);
		bouton_valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FicheEvent.dispose();
			}
		});

		/* bouton annuler */
		bouton_annuler = new JButton("Annuler");
		bouton_annuler.setToolTipText("Annuler la création de l'organisateur");
		bouton_annuler.setBounds(475, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_annuler);
		bouton_annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FicheEvent.dispose();
			}
		});

		/* bouton supprimer */
		bouton_supprimer = new JButton("Supprimer");
		bouton_supprimer.setBounds(265, 400, 100, HAUTEUR_ELEMENT);
		bouton_supprimer.setToolTipText("Supprimer cet organisateur");
		this.add(bouton_supprimer);
		bouton_supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selection = OngletPlanification.table_planif.getSelectedRows();
				for (int i = selection.length - 1; i >= 0; i--) {
					OngletPlanification.modele.removeRencontre(selection[i]);
				}
				// FicheEvent.dispose();
				event_exist = false;
			}
		});
	}
}
