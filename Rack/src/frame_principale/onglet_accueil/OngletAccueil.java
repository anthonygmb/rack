package frame_principale.onglet_accueil;

import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import frame_principale.Main;
import methode.FocusTextField;

public class OngletAccueil extends JPanel {
	
	//test1
	//test2

	private static final long serialVersionUID = -4965499289693100757L;

	/* position des elements de l'aire de recherche */
	private static int POSITION_X1 = 50;
	private static int POSITION_Y = 90;

	/* position des elements de l'aire de resultat de recherche */
	static private int POSITION_X2 = 540;

	String[] tab_pays = { "Pays", "France", "Espagne", "Portugal", "Angleterre", "Italie", "etc..." };
	String[] tab_specialite = { "Spécialité du membre", "Choriste", "Soliste", "Musicien" };
	String[] tab_responsabilite = { "Responsabilité du membre", "Chauffeur", "Habilleur", "dirigeant", "trésorier",
			"etc..." };

	private JLabel titre_accueil, titre_rech, titre_result_rech;
	private JButton a_propos;
	// private JLabel image;
	private JTextField rech_nom_groupe, rech_nom_membre, rech_prenom_membre, rech_instrument, rech_nom_titre,
			rech_annee_titre, rech_duree_min, rech_duree_max, rech_auteur;
	private JComboBox<String> rech_pays, rech_specialite, rech_responsabilite;
	private JCheckBox rech_reprise;
	private JTextArea aire_result_rech;
	private JScrollPane scroll_result_rech;

	/* constructeur par defaut */
	public OngletAccueil() {
		this.setLayout(null);
		init_Conponents();
		// this.setBackground(COULEUR_FOND_DEFAULT);
	}

	/* initialisation des composants */
	private void init_Conponents() {

		/* titre bievenue dans rack */
		titre_accueil = new JLabel("Bienvenue dans Rack");
		titre_accueil.setFont(Main.TITRE1);
		titre_accueil.setBounds(350, 20, 400, 40);
		titre_accueil.setForeground(Main.COULEUR_TEXTE);
		this.add(titre_accueil);

		/* titre rechercher */
		titre_rech = new JLabel("Rechercher");
		titre_rech.setFont(Main.TITRE2);
		titre_rech.setBounds((POSITION_X1 + 138), POSITION_Y, 280, 40);
		titre_rech.setForeground(Main.COULEUR_TEXTE);
		this.add(titre_rech);

		/* textfield recherche par nom de groupe */
		rech_nom_groupe = new JTextField("Nom de groupe");
		rech_nom_groupe.addFocusListener(new FocusTextField(rech_nom_groupe));
		rech_nom_groupe.setBounds(POSITION_X1, (POSITION_Y + 62), 200, Main.HAUTEUR_ELEMENT);
		this.add(rech_nom_groupe);

		/* textfield recherche par nom de membre */
		rech_nom_membre = new JTextField("Nom de membre");
		rech_nom_membre.addFocusListener(new FocusTextField(rech_nom_membre));
		rech_nom_membre.setBounds(POSITION_X1, (POSITION_Y + 100), 200, Main.HAUTEUR_ELEMENT);
		this.add(rech_nom_membre);

		/* textfield recherche par prenom de membre */
		rech_prenom_membre = new JTextField("Prénom de membre");
		rech_prenom_membre.addFocusListener(new FocusTextField(rech_prenom_membre));
		rech_prenom_membre.setBounds((POSITION_X1 + 220), (POSITION_Y + 100), 200, Main.HAUTEUR_ELEMENT);
		this.add(rech_prenom_membre);

		/* textfield recherche par instrument */
		rech_instrument = new JTextField("Instrument");
		rech_instrument.addFocusListener(new FocusTextField(rech_instrument));
		rech_instrument.setBounds(POSITION_X1, (POSITION_Y + 214), 260, Main.HAUTEUR_ELEMENT);
		this.add(rech_instrument);

		/* textfield recherche par nom de titre */
		rech_nom_titre = new JTextField("Nom de titre");
		rech_nom_titre.addFocusListener(new FocusTextField(rech_nom_titre));
		rech_nom_titre.setBounds(POSITION_X1, (POSITION_Y + 252), 260, Main.HAUTEUR_ELEMENT);
		this.add(rech_nom_titre);

		/* textfield recherche par annee de titre */
		rech_annee_titre = new JTextField("Année de titre");
		rech_annee_titre.addFocusListener(new FocusTextField(rech_annee_titre));
		rech_annee_titre.setBounds((POSITION_X1 + 280), (POSITION_Y + 252), 140, Main.HAUTEUR_ELEMENT);
		this.add(rech_annee_titre);

		/* textfield recherche par duree min */
		rech_duree_min = new JTextField("Durée min.");
		rech_duree_min.addFocusListener(new FocusTextField(rech_duree_min));
		rech_duree_min.setBounds(POSITION_X1, (POSITION_Y + 290), 200, Main.HAUTEUR_ELEMENT);
		this.add(rech_duree_min);

		/* textfield recherche par duree max */
		rech_duree_max = new JTextField("Durée max.");
		rech_duree_max.addFocusListener(new FocusTextField(rech_duree_max));
		rech_duree_max.setBounds((POSITION_X1 + 220), (POSITION_Y + 290), 200, Main.HAUTEUR_ELEMENT);
		this.add(rech_duree_max);

		/* textfield recherche par auteur */
		rech_auteur = new JTextField("Auteur");
		rech_auteur.addFocusListener(new FocusTextField(rech_auteur));
		rech_auteur.setBounds(POSITION_X1, (POSITION_Y + 328), 420, Main.HAUTEUR_ELEMENT);
		this.add(rech_auteur);

		/* combo box recherche par pays */
		rech_pays = new JComboBox<String>();
		for (int i = 0; i < tab_pays.length; i++) {
			rech_pays.addItem(tab_pays[i]);
		}
		rech_pays.setBounds((POSITION_X1 + 220), (POSITION_Y + 62), 200, Main.HAUTEUR_ELEMENT);
		this.add(rech_pays);

		/* combo box recherche par specialite */
		rech_specialite = new JComboBox<String>();
		for (int i = 0; i < tab_specialite.length; i++) {
			rech_specialite.addItem(tab_specialite[i]);
		}
		rech_specialite.setBounds(POSITION_X1, (POSITION_Y + 138), 420, Main.HAUTEUR_ELEMENT);
		this.add(rech_specialite);

		/* combo box recherche par responsabilite */
		rech_responsabilite = new JComboBox<String>();
		for (int i = 0; i < tab_responsabilite.length; i++) {
			rech_responsabilite.addItem(tab_responsabilite[i]);
		}
		rech_responsabilite.setBounds(POSITION_X1, (POSITION_Y + 176), 420, Main.HAUTEUR_ELEMENT);
		this.add(rech_responsabilite);

		/* checkbox recherche par reprise */
		rech_reprise = new JCheckBox("Reprise");
		rech_reprise.setBounds((POSITION_X1 + 310), (POSITION_Y + 214), 140, Main.HAUTEUR_ELEMENT);
		rech_reprise.setOpaque(false);
		rech_reprise.setContentAreaFilled(false);
		rech_reprise.setBorderPainted(false);
		rech_reprise.setForeground(Main.COULEUR_TEXTE);
		this.add(rech_reprise);

		/* titre resultat de recherche */
		titre_result_rech = new JLabel("Resultat de recherche");
		titre_result_rech.setFont(Main.TITRE2);
		titre_result_rech.setBounds((POSITION_X2 + 110), POSITION_Y, 350, 40);
		titre_result_rech.setForeground(Main.COULEUR_TEXTE);
		this.add(titre_result_rech);

		/* aire resultat de recherche */
		aire_result_rech = new JTextArea();

		/* scrollpane resultat de recherche */
		scroll_result_rech = new JScrollPane(aire_result_rech);
		scroll_result_rech.setBounds(POSITION_X2, (POSITION_Y + 62), 500, 292);
		scroll_result_rech.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scroll_result_rech);

		/* bouton a propos */
		a_propos = new JButton("à propos...");
		a_propos.setBounds((POSITION_X2 + 320), (POSITION_Y + 390), 180, Main.HAUTEUR_ELEMENT);
		a_propos.setFont(new Font("Times new roman", Font.ITALIC + Font.BOLD, 18));
		a_propos.setOpaque(false);
		a_propos.setContentAreaFilled(false);
		a_propos.setBorderPainted(false);
		a_propos.setForeground(Main.COULEUR_TEXTE);
		this.add(a_propos);
		a_propos.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"(c) Copyright Rack contributors 2015\nAll rights reserved.\nVersion 1.0",
						"A propos de Rack...", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// image = new JLabel(new ImageIcon("src/img/gimp_fender3.jpeg"));
		// image.setBounds(0, 0, 1689, 798);
		// this.add(image);
	}

	/* methode d'objets graphiques */
	// public void paintComponent(Graphics g) {
	// /* image de fond */
	// try {
	// Image img = ImageIO.read(new File("src/img/gimp_fender2.jpeg"));
	// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
