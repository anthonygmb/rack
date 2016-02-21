package frame_fiche_groupe.onglet_infos;

import static frame_principale.Main.COULEUR_TEXTE;
import static frame_principale.Main.HAUTEUR_ELEMENT;
import static frame_principale.Main.LARGEUR_ELEMENT;
import static frame_principale.Main.TITRE3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frame_fiche_event.onglet_prog.OngletProgramme;
import frame_fiche_groupe.onglet_titre.OngletTitre;
import frame_principale.Main;
import frame_principale.onglet_planif.OngletPlanification;
import frame_principale.onglet_reper.OngletRepertoire;
import methode.FocusTextField;
import objets.Groupe;

public class OngletInfosGroupe extends JPanel {

	private static final long serialVersionUID = 7603690992874862037L;

	/* position des elements */
	private static int POSITION_X = 67;
	private static int POSITION_Y = 40;

	/* composants */
	private static JComboBox<String> pays;
	private static JButton bouton_valider, bouton_supprimer;
	private JButton bouton_annuler;
	private JLabel[] tab_label = new JLabel[4];
	public static JTextField[] tab_textfield = new JTextField[4];
	private JDialog FicheGroupe;

	/* variables */
	private String[] tab_pays = { "Pays", "Afghanistan", "Afrique du Sud", "Akrotiri", "Albanie", "Algérie",
			"Allemagne", "Andorre", "Angola", "Anguilla", "Antarctique", "Antigua-et-Barbuda", "Antilles néerlandaises",
			"Arabie saoudite", "Arctic Ocean", "Argentine", "Arménie", "Aruba", "Ashmore and Cartier Islands",
			"Atlantic Ocean", "Australie", "Autriche", "Azerbaïdjan", "Bahamas", "Bahreïn", "Bangladesh", "Barbade",
			"Belau", "Belgique", "Belize", "Bénin", "Bermudes", "Bhoutan", "Biélorussie", "Birmanie", "Bolivie",
			"Bosnie-Herzégovine", "Botswana", "Brésil", "Brunei", "Bulgarie", "Burkina Faso", "Burundi", "Cambodge",
			"Cameroun", "Canada", "Cap-Vert", "Chili", "Chine", "Chypre", "Clipperton Island", "Colombie", "Comores",
			"Congo", "Coral Sea Islands", "Corée du Nord", "Corée du Sud", "Costa Rica", "Côte d'Ivoire", "Croatie",
			"Cuba", "Danemark", "Dhekelia", "Djibouti", "Dominique", "Égypte", "Émirats arabes unis", "Équateur",
			"Érythrée", "Espagne", "Estonie", "États-Unis", "Éthiopie", "ex-République yougoslave de Macédoine",
			"Finlande", "France", "Gabon", "Gambie", "Gaza Strip", "Géorgie", "Ghana", "Gibraltar", "Grèce", "Grenade",
			"Groenland", "Guam", "Guatemala", "Guernsey", "Guinée", "Guinée équatoriale", "Guinée-Bissao", "Guyana",
			"Haïti", "Honduras", "Hong Kong", "Hongrie", "Ile Bouvet", "Ile Christmas", "Ile Norfolk", "Iles Cayman",
			"Iles Cook", "Iles des Cocos (Keeling)", "Iles Falkland", "Iles Féroé", "Iles Fidji",
			"Iles Géorgie du Sud et Sandwich du Sud", "Iles Heard et McDonald", "Iles Marshall", "Iles Pitcairn",
			"Iles Salomon", "Iles Svalbard et Jan Mayen", "Iles Turks-et-Caicos", "Iles Vierges américaines",
			"Iles Vierges britanniques", "Inde", "Indian Ocean", "Indonésie", "Iran", "Iraq", "Irlande", "Islande",
			"Israël", "Italie", "Jamaïque", "Jan Mayen", "Japon", "Jersey", "Jordanie", "Kazakhstan", "Kenya",
			"Kirghizistan", "Kiribati", "Koweït", "Laos", "Lesotho", "Lettonie", "Liban", "Liberia", "Libye",
			"Liechtenstein", "Lituanie", "Luxembourg", "Macao", "Madagascar", "Malaisie", "Malawi", "Maldives", "Mali",
			"Malte", "Man, Isle of", "Mariannes du Nord", "Maroc", "Maurice", "Mauritanie", "Mayotte", "Mexique",
			"Micronésie", "Moldavie", "Monaco", "Monde", "Mongolie", "Monténégro", "Montserrat", "Mozambique",
			"Namibie", "Nauru", "Navassa Island", "Népal", "Nicaragua", "Niger", "Nigeria", "Nioué", "Norvège",
			"Nouvelle-Calédonie", "Nouvelle-Zélande", "Oman", "Ouganda", "Ouzbékistan", "Pacific Ocean", "Pakistan",
			"Panama", "Papouasie-Nouvelle-Guinée", "Paracel Islands", "Paraguay", "Pays-Bas", "Pérou", "Philippines",
			"Pologne", "Polynésie française", "Porto Rico", "Portugal", "Qatar", "République centrafricaine",
			"République démocratique du Congo", "République dominicaine", "République tchèque", "Roumanie",
			"Royaume-Uni", "Russie", "Rwanda", "Sahara occidental", "Saint-Christophe-et-Niévès", "Sainte-Hélène",
			"Sainte-Lucie", "Saint-Marin", "Saint-Pierre-et-Miquelon", "Saint-Siège", "Saint-Vincent-et-les-Grenadines",
			"Salvador", "Samoa", "Samoa américaines", "Sao Tomé-et-Principe", "Sénégal", "Serbie", "Seychelles",
			"Sierra Leone", "Singapour", "Slovaquie", "Slovénie", "Somalie", "Soudan", "Southern Ocean",
			"Spratly Islands", "Sri Lanka", "Suède", "Suisse", "Suriname", "Swaziland", "Syrie", "Tadjikistan",
			"Taïwan", "Tanzanie", "Tchad", "Terres australes françaises", "Territoire britannique de l'Océan Indien",
			"Thaïlande", "Timor Oriental", "Togo", "Tokélaou", "Tonga", "Trinité-et-Tobago", "Tunisie", "Turkménistan",
			"Turquie", "Tuvalu", "Ukraine", "Union européenne", "Uruguay", "Vanuatu", "Venezuela", "Viêt Nam",
			"Wake Island", "Wallis-et-Futuna", "West Bank", "Yémen", "Zambie", "Zimbabwe" };
	public static boolean groupe_exist = false;
	public static boolean doublon_groupe = false;
	public static String[] tab_nom_label = { "Nom du groupe", "Caracteristique du groupe", "Pays du groupe",
			"Region du groupe" };

	/* constructeur par defaut */
	public OngletInfosGroupe(JDialog FicheGroupe) {
		this.FicheGroupe = FicheGroupe;
		this.setLayout(null);
		init_Conponents();
	}

	/* initialisation des composants */
	private void init_Conponents() {

		/* declaration des composants */
		for (int i = 0; i < tab_label.length; i++) {
			tab_label[i] = new JLabel();
			tab_label[i].setText(tab_nom_label[i]);
			tab_label[i].setFont(TITRE3);
			tab_label[i].setForeground(COULEUR_TEXTE);
			tab_label[i].setBounds(POSITION_X, (POSITION_Y - 20) + i * 80, LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
			this.add(tab_label[i]);
			if (i == 2) {
				/* combo box pays du groupe */
				pays = new JComboBox<String>();
				pays.setBounds(POSITION_X, (POSITION_Y + 200) - 20, LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
				for (int y = 0; y < tab_pays.length; y++) {
					pays.addItem(tab_pays[y]);
				}
				this.add(pays);
			} else {
				tab_textfield[i] = new JTextField();
				tab_textfield[i].setText(tab_nom_label[i]);
				tab_textfield[i].setBounds(POSITION_X, ((POSITION_Y * 2) - 20 + i * 80), LARGEUR_ELEMENT,
						HAUTEUR_ELEMENT);
				this.add(tab_textfield[i]);
				tab_textfield[i].addFocusListener(new FocusTextField(tab_textfield[i]));
			}
		}

		/* bouton valider */
		bouton_valider = new JButton("Valider");
		bouton_valider.setToolTipText("Valider la création du groupe");
		bouton_valider.setBounds(260, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_valider);
		bouton_valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* nom de groupe obligatoire */
				if (!tab_textfield[0].getText().equals("") && (tab_textfield[0].getText().equals(tab_nom_label[0]))) {
					tab_textfield[0].setBackground(Main.COULEUR_FOND_ROOT);
				} else {
					/* recherche de doublons de groupes */
					if (OngletRepertoire.modele.getRowCount() != 0) {
						for (int i = 0; i < OngletRepertoire.modele.getRowCount(); i++) {
							if (tab_textfield[0].getText()
									.equals(OngletRepertoire.modele.getValueAt(i, 0).toString())) {
								doublon_groupe = true;
							}
						}
					}

					/* teste si un doublon de groupe est trouvé */
					if (doublon_groupe && !groupe_exist) {
						JOptionPane.showMessageDialog(null, "Ce groupe est déjà existant", "Warning",
								JOptionPane.WARNING_MESSAGE);
						tab_textfield[0].setBackground(Main.COULEUR_ERROR);
						doublon_groupe = false;
					} else {
						Groupe groupe1 = new Groupe();
						groupe1.setNom_groupe(tab_textfield[0].getText());
						groupe1.setCarac_groupe((tab_textfield[1].getText().equals(tab_nom_label[1])) ? ""
								: tab_textfield[1].getText());
						groupe1.setPays((pays.getSelectedItem().toString().equals(tab_pays[0])) ? ""
								: pays.getSelectedItem().toString());
						groupe1.setRegion_groupe((tab_textfield[3].getText().equals(tab_nom_label[3])) ? ""
								: tab_textfield[3].getText());

						/* teste si c'est une modification de groupe */
						if (!groupe_exist) {
							OngletRepertoire.modele.addGroupe(groupe1);
							OngletRepertoire.table_repertoire.repaint();
							FicheGroupe.dispose();
						} else {
							OngletRepertoire.modele.setValueAt(groupe1,
									OngletRepertoire.table_repertoire.getSelectedRow());
							OngletRepertoire.table_repertoire.repaint();
							FicheGroupe.dispose();
						}
						doublon_groupe = false;
						groupe_exist = false;
					}
				}
				for (int i = OngletTitre.modele.getRowCount(); i > 0; i--) {
					OngletTitre.modele.removeTitre(i - 1);
				}
			}
		});

		/* bouton annuler */
		bouton_annuler = new JButton(
				"Annuler"/* new ImageIcon("src/img/annuler.png") */);
		bouton_annuler.setToolTipText("Annuler la création du groupe");
		// bouton_annuler.setOpaque(false);
		// bouton_annuler.setContentAreaFilled(false);
		// bouton_annuler.setBorderPainted(false);
		// bouton_annuler.setBounds(30, 375, 60, 60);
		bouton_annuler.setBounds(370, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_annuler);
		bouton_annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = OngletTitre.modele.getRowCount(); i > 0; i--) {
					OngletTitre.modele.removeTitre(i - 1);
				}
				FicheGroupe.dispose();
				groupe_exist = false;
			}
		});

		/* bouton supprimer */
		bouton_supprimer = new JButton("Supprimer");
		bouton_supprimer.setEnabled(false);
		bouton_supprimer.setBounds(480, 400, 100, HAUTEUR_ELEMENT);
		bouton_supprimer.setToolTipText("Supprimer le groupe");
		this.add(bouton_supprimer);
		bouton_supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int exit = JOptionPane.showConfirmDialog(null, "Supprimer le groupe ?", "Supprimer ?",
						JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.YES_OPTION) {
					System.out.printf("SUPPRESSION:\n");

					/* suppression des programmes */
					if (OngletPlanification.table_planif.getRowCount() > 0) {

						/* suppression des programmes du tableau */
						if (OngletProgramme.table_prog.getRowCount() > 0) {
							for (int i = OngletProgramme.table_prog.getRowCount(); i > 0; i--) {
								if (OngletProgramme.table_prog.getValueAt(i, 0).toString()
										.equals(OngletRepertoire.modele
												.getGroupeAt(OngletRepertoire.table_repertoire.getSelectedRow())
												.getNom_groupe())) {
									OngletProgramme.modele.removeProgramme(i);
									System.out.printf("programme du tableau ligne %d supprimé\n", i);
								}
							}
						}
						System.out.printf("\n");

						/* suppression des programmes du groupe */
						if (OngletPlanification.table_planif.getRowCount() > 0) {
							for (int i = 0; i < OngletPlanification.table_planif.getRowCount(); i++) {
								if (OngletPlanification.modele.getListeProgAt(i).size() > 0) {
									for (int j = OngletPlanification.modele.getListeProgAt(i).size(); j > 0; j--) {
										if (OngletPlanification.modele.getListeProgAt(i).get(j - 1).getGroupe_produit()
												.getNom_groupe()
												.equals(OngletRepertoire.modele
														.getGroupeAt(OngletRepertoire.table_repertoire.getSelectedRow())
														.getNom_groupe())) {
											OngletPlanification.modele.getListeProgAt(i).remove(j - 1);
											System.out.printf("programme de la rencontre %d ligne %d supprimé\n", i,
													j - 1);
										}
									}
								}
							}
						}
						OngletProgramme.reinitialiseProg();
						System.out.printf("\n");
					}

					/* suppression des titres du groupe */
					if (OngletTitre.modele.getRowCount() > 0) {
						for (int i = OngletTitre.modele.getRowCount(); i > 0; i--) {
							OngletTitre.modele.removeTitre(i - 1);
							OngletRepertoire.modele.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow())
									.remove(i - 1);
							System.out.printf("titre %d groupe supprimé\n", i);
						}
					}

					/* test de liste de titres */
					System.out.printf("titres du groupe %s supprimés\n", OngletRepertoire.modele
							.getValueAt(OngletRepertoire.table_repertoire.getSelectedRow(), 0).toString());
					for (int i = 0; i < OngletRepertoire.modele
							.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow()).size(); i++) {
						System.out.printf("%d. titre %s\n", i,
								OngletRepertoire.modele
										.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow())
										.get(i).titre);
					}
					System.out.printf("\n");

					/* suppression du groupe */
					int[] selection = OngletRepertoire.table_repertoire.getSelectedRows();
					for (int i = selection.length - 1; i >= 0; i--) {
						OngletRepertoire.modele.removeGroupe(selection[i]);
					}
					System.out.printf("groupe supprimé\n");
					FicheGroupe.dispose();
					groupe_exist = false;
				}
			}
		});
	}

	/* methode de modification de groupe */
	public static void modifOngletInfoGroupe() {
		tab_textfield[0].setText(
				OngletRepertoire.modele.getValueAt(OngletRepertoire.table_repertoire.getSelectedRow(), 0).toString());

		if (OngletRepertoire.modele.getValueAt(OngletRepertoire.table_repertoire.getSelectedRow(), 1).toString()
				.equals("")) {
			tab_textfield[1].setText(tab_nom_label[1]);
		} else {
			tab_textfield[1].setText(OngletRepertoire.modele
					.getValueAt(OngletRepertoire.table_repertoire.getSelectedRow(), 1).toString());
		}

		pays.setSelectedItem(
				OngletRepertoire.modele.getValueAt(OngletRepertoire.table_repertoire.getSelectedRow(), 2).toString());

		if (OngletRepertoire.modele.getValueAt(OngletRepertoire.table_repertoire.getSelectedRow(), 3).toString()
				.equals("")) {
			tab_textfield[3].setText(tab_nom_label[3]);
		} else {
			tab_textfield[3].setText(OngletRepertoire.modele
					.getValueAt(OngletRepertoire.table_repertoire.getSelectedRow(), 3).toString());
		}

		groupe_exist = true;
		bouton_supprimer.setEnabled((groupe_exist) ? true : false);
		bouton_valider.setText("Appliquer");
		bouton_valider.setToolTipText("Appliquer les modifications");
	}
}
