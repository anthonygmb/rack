package frame_fiche_groupe.onglet_membre;

import static frame_fiche_event.FicheEvent.event_exist;
import static frame_principale.Main.COULEUR_FOND_GRISE;
import static frame_principale.Main.COULEUR_TEXTE;
import static frame_principale.Main.COULEUR_TEXTE_GRISE;
import static frame_principale.Main.HAUTEUR_ELEMENT;
import static frame_principale.Main.LARGEUR_1;
import static frame_principale.Main.TITRE3;

import java.awt.Color;
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

public class OngletMembreGroupe extends JPanel {

	private static final long serialVersionUID = -1678979606914135302L;
	/* position des elements */
	static private int POSITION_X = 37;
	static private int POSITION_Y = 40;

	String[] tab_membre = { "Ajout nouveau membre" };
	String[] tab_specialite = { "Spécialité du membre", "Choriste", "Soliste", "Musicien" };
	String[] tab_instrument = { "Instrument", "Pas d'instrument", "Guitare", "Basse", "Saxophone", "violon",
			"cornemuse", "etc..." };
	String[] tab_responsabilite = { "Responsabilité du membre", "Chauffeur", "Habilleur", "dirigeant", "trésorier",
			"etc..." };
	private JLabel civilite;
	private JTextField champ_nom_membre, champ_prenom_membre, champ_date_naiss, champ_adresse_corresp,
			champ_tel_corresp, champ_fax_corresp, champ_mail_corresp;
	private JCheckBox civi_madame, civi_monsieur, resp_corresp;
	private JComboBox<String> choix_membre, choix_specialite, choix_instrument, choix_respon;
	private JButton bouton_valider, bouton_annuler, bouton_supprimer;

	/* constructeur par defaut */
	public OngletMembreGroupe() {
		this.setLayout(null);
		init_Conponents();
	}

	/* initialisation des composants */
	private void init_Conponents() {

		/* comboBox choix du membre */
		choix_membre = new JComboBox<String>();
		choix_membre.setBounds(POSITION_X, (POSITION_Y - 20), 525, HAUTEUR_ELEMENT);
		for (int i = 0; i < tab_membre.length; i++) {
			choix_membre.addItem(tab_membre[i]);
		}
		this.add(choix_membre);

		/* label civilite */
		civilite = new JLabel("civilite");
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

		/* textfield nom de membre */
		champ_nom_membre = new JTextField("Nom");
		champ_nom_membre.addFocusListener(new FocusTextField(champ_nom_membre));
		champ_nom_membre.setBounds(POSITION_X, (POSITION_Y + 60), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(champ_nom_membre);

		/* comboBox choix de specialite */
		choix_specialite = new JComboBox<String>();
		for (int i = 0; i < tab_specialite.length; i++) {
			choix_specialite.addItem(tab_specialite[i]);
		}
		choix_specialite.setBounds((POSITION_X + 270), (POSITION_Y + 60), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(choix_specialite);

		/* textfield prenom de membre */
		champ_prenom_membre = new JTextField("Prénom");
		champ_prenom_membre.addFocusListener(new FocusTextField(champ_prenom_membre));
		champ_prenom_membre.setBounds(POSITION_X, (POSITION_Y + 100), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(champ_prenom_membre);

		/* comboBox choix d'instrument */
		choix_instrument = new JComboBox<String>();
		for (int i = 0; i < tab_instrument.length; i++) {
			choix_instrument.addItem(tab_instrument[i]);
		}
		choix_instrument.setBounds((POSITION_X + 270), (POSITION_Y + 100), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(choix_instrument);

		/* textfield date naissance de membre */
		champ_date_naiss = new JTextField("Date de naissance");
		champ_date_naiss.addFocusListener(new FocusTextField(champ_date_naiss));
		champ_date_naiss.setBounds(POSITION_X, (POSITION_Y + 140), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(champ_date_naiss);

		/* comboBox choix de responsabilite */
		choix_respon = new JComboBox<String>();
		for (int i = 0; i < tab_responsabilite.length; i++) {
			choix_respon.addItem(tab_responsabilite[i]);
		}
		choix_respon.setBounds((POSITION_X + 270), (POSITION_Y + 140), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(choix_respon);

		/* checkBox correspondant membre */
		resp_corresp = new JCheckBox(" Correspondant");
		resp_corresp.setBounds((POSITION_X + 230), (POSITION_Y + 180), 140, HAUTEUR_ELEMENT);
		resp_corresp.setForeground(COULEUR_TEXTE);
		resp_corresp.setOpaque(false);
		resp_corresp.setContentAreaFilled(false);
		resp_corresp.setBorderPainted(false);
		resp_corresp.setToolTipText("Cochez cette case si le membre est le correspondant du groupe");
		resp_corresp.setSelected(true);
		this.add(resp_corresp);

		/*
		 * evenement pour griser les elements de correspondant si la checkbox
		 * est decochee
		 */
		resp_corresp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (resp_corresp.isSelected() == true) {
					champ_adresse_corresp.setBackground(Color.white);
					champ_adresse_corresp.setForeground(Color.black);
					champ_adresse_corresp.setEnabled(true);
					champ_tel_corresp.setBackground(Color.white);
					champ_tel_corresp.setForeground(Color.black);
					champ_tel_corresp.setEnabled(true);
					champ_fax_corresp.setBackground(Color.white);
					champ_fax_corresp.setForeground(Color.black);
					champ_fax_corresp.setEnabled(true);
					champ_mail_corresp.setBackground(Color.white);
					champ_mail_corresp.setForeground(Color.black);
					champ_mail_corresp.setEnabled(true);
				} else {
					champ_adresse_corresp.setText("Adresse correspondant");
					champ_adresse_corresp.setBackground(COULEUR_FOND_GRISE);
					champ_adresse_corresp.setForeground(COULEUR_TEXTE_GRISE);
					champ_adresse_corresp.setEnabled(false);
					champ_tel_corresp.setText("Tel correspondant");
					champ_tel_corresp.setBackground(COULEUR_FOND_GRISE);
					champ_tel_corresp.setForeground(COULEUR_TEXTE_GRISE);
					champ_tel_corresp.setEnabled(false);
					champ_fax_corresp.setText("Fax correspondant");
					champ_fax_corresp.setBackground(COULEUR_FOND_GRISE);
					champ_fax_corresp.setForeground(COULEUR_TEXTE_GRISE);
					champ_fax_corresp.setEnabled(false);
					champ_mail_corresp.setText("Mail correspondant");
					champ_mail_corresp.setBackground(COULEUR_FOND_GRISE);
					champ_mail_corresp.setForeground(COULEUR_TEXTE_GRISE);
					champ_mail_corresp.setEnabled(false);
				}
			}
		});

		/* textfield adresse correspondant */
		champ_adresse_corresp = new JTextField("Adresse correspondant");
		champ_adresse_corresp.addFocusListener(new FocusTextField(champ_adresse_corresp));
		champ_adresse_corresp.setBounds(POSITION_X, (POSITION_Y + 220), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(champ_adresse_corresp);

		/* textfield tel correspondant */
		champ_tel_corresp = new JTextField("Tel correspondant");
		champ_tel_corresp.addFocusListener(new FocusTextField(champ_tel_corresp));
		champ_tel_corresp.setBounds((POSITION_X + 270), (POSITION_Y + 220), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(champ_tel_corresp);

		/* textfield fax correspondant */
		champ_fax_corresp = new JTextField("Fax correspondant");
		champ_fax_corresp.addFocusListener(new FocusTextField(champ_fax_corresp));
		champ_fax_corresp.setBounds(POSITION_X, (POSITION_Y + 260), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(champ_fax_corresp);

		/* textfield mail correspondant */
		champ_mail_corresp = new JTextField("Mail correspondant");
		champ_mail_corresp.addFocusListener(new FocusTextField(champ_mail_corresp));
		champ_mail_corresp.setBounds((POSITION_X + 270), (POSITION_Y + 260), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(champ_mail_corresp);

		/* bouton valider */
		bouton_valider = new JButton("Valider");
		bouton_valider.setToolTipText("Valider la création du membre");
		bouton_valider.setBounds(15, 400, 90, HAUTEUR_ELEMENT);
		this.add(bouton_valider);
		bouton_valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				FrameFicheGroupe.dispose();
			}
		});

		/* bouton annuler */
		bouton_annuler = new JButton("Annuler");
		bouton_annuler.setToolTipText("Annuler la création du membre");
		bouton_annuler.setBounds(110, 400, 90, HAUTEUR_ELEMENT);
		this.add(bouton_annuler);
		bouton_annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				FrameFicheGroupe.dispose();
			}
		});

		/* bouton supprimer */
		bouton_supprimer = new JButton("Supprimer");
		bouton_supprimer.setBounds(480, 400, 100, HAUTEUR_ELEMENT);
		bouton_supprimer.setToolTipText("Supprimer ce membre");
		this.add(bouton_supprimer);
		bouton_supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selection = OngletPlanification.table_planif.getSelectedRows();
				for (int i = selection.length - 1; i >= 0; i--) {
					OngletPlanification.modele.removeRencontre(selection[i]);
				}
//				FrameFicheGroupe.dispose();
				event_exist = false;
			}
		});
	}
}
