package frame_fiche_groupe.onglet_titre;

import static frame_principale.Main.COULEUR_FOND_GRISE;
import static frame_principale.Main.COULEUR_TEXTE;
import static frame_principale.Main.COULEUR_TEXTE_GRISE;
import static frame_principale.Main.HAUTEUR_ELEMENT;
import static frame_principale.Main.TITRE3;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DateFormatter;

import com.toedter.calendar.JYearChooser;

import frame_fiche_groupe.FicheGroupe;
import frame_fiche_groupe.onglet_infos.OngletInfosGroupe;
import frame_principale.Main;
import frame_principale.onglet_reper.OngletRepertoire;
import methode.FocusTextField;
import objets.Titre;

public class OngletTitre extends JPanel {

	private static final long serialVersionUID = 378974579372774853L;
	/* position des elements */
	private static int POSITION_X = 27;
	private static int POSITION_Y = 40;

	/* composants */
	public static JTable table_titre;
	private JScrollPane scrollpane_titre;
	public static TableModelTitre modele = new TableModelTitre();
	public static JLabel nom_titre, trait, modif_titre;
	public static JTextField champ_nom_titre, champ_type_titre, champ_auteur_titre;
	private JCheckBox chkbox_reprise_titre;
	private JButton bouton_valider, bouton_annuler, bouton_supprimer;
	private static JYearChooser champ_annee_titre = new JYearChooser();
	public static String[] tab_nom_textfield = { "Titre", "Type", "Entrez l'auteur original du titre", " Reprise" };
	private static Calendar calendar = Calendar.getInstance();
	private static SpinnerDateModel model = new SpinnerDateModel();
	private static JSpinner spinner_duree = new JSpinner(model);
	private JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner_duree, "mm:ss");
	private DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();

	/* variables */
	public static boolean titre_exist = false;

	/* constructeur par defaut */
	public OngletTitre() {
		this.setLayout(null);
		init_Conponents();
	}

	/* initialisation des composants */
	private void init_Conponents() {

		/* titre du tableau */
		nom_titre = new JLabel("Titres du groupe");
		nom_titre.setFont(TITRE3);
		nom_titre.setBounds(5, (POSITION_Y - 34), FicheGroupe.largeur_fenetre - 10, HAUTEUR_ELEMENT);
		nom_titre.setHorizontalAlignment(SwingConstants.CENTER);
		nom_titre.setForeground(COULEUR_TEXTE);
		this.add(nom_titre);

		/* tableau des titres du groupe */
		table_titre = new JTable(modele);
		table_titre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_titre.getColumnModel().getColumn(0).setPreferredWidth(354);
		table_titre.getColumnModel().getColumn(1).setPreferredWidth(80);
		table_titre.getColumnModel().getColumn(2).setPreferredWidth(80);
		table_titre.getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		table_titre.getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		scrollpane_titre = new JScrollPane(table_titre);
		this.add(scrollpane_titre);
		scrollpane_titre.getViewport().setBackground(Color.black);
		scrollpane_titre.setOpaque(false);
		scrollpane_titre.getViewport().setOpaque(false);
		scrollpane_titre.setBounds((POSITION_X + 10), POSITION_Y, 520, 150);
		table_titre.setRowHeight(HAUTEUR_ELEMENT);
		table_titre.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/* Compte le nombre de clics de la souris */
				if (e.getClickCount() == 1) {

					try {
						champ_nom_titre.setText(modele.getValueAt(table_titre.getSelectedRow(), 0).toString());
					} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
						e1.printStackTrace();
					}
					champ_annee_titre.setYear((int) modele.getValueAt(table_titre.getSelectedRow(), 1));
					spinner_duree.setValue(modele.getValueAt(table_titre.getSelectedRow(), 2));
					champ_type_titre.setText(modele.getValueAt(table_titre.getSelectedRow(), 3).toString());
					chkbox_reprise_titre
							.setSelected((boolean) modele.getValueAt(table_titre.getSelectedRow(), 4) ? true : false);
					if (chkbox_reprise_titre.isSelected()) {
						degriseElement();
						champ_auteur_titre.setText(modele.getValueAt(table_titre.getSelectedRow(), 5).toString());
					} else {
						griseElement();
					}
					bouton_valider.setText("Appliquer");
					bouton_valider.setToolTipText("Appliquer les modifications");
					titre_exist = true;
					bouton_supprimer.setEnabled(true);
				}
			}
		});

		/* trait de séparation */
		trait = new JLabel("__________________________________________________________");
		trait.setFont(TITRE3);
		trait.setBounds((POSITION_X + 10), (POSITION_Y + 160), 540, HAUTEUR_ELEMENT);
		trait.setForeground(COULEUR_TEXTE);
		this.add(trait);

		/* titre ajouter ou modifier un titre du groupe */
		modif_titre = new JLabel("Ajouter ou modifier un titre");
		modif_titre.setFont(TITRE3);
		modif_titre.setBounds((POSITION_X + 150), (POSITION_Y + 185), 520, HAUTEUR_ELEMENT);
		modif_titre.setForeground(COULEUR_TEXTE);
		this.add(modif_titre);

		/* nom du titre */
		champ_nom_titre = new JTextField(tab_nom_textfield[0]);
		champ_nom_titre.addFocusListener(new FocusTextField(champ_nom_titre));
		champ_nom_titre.setBounds((POSITION_X + 10), (POSITION_Y + 220), 520, HAUTEUR_ELEMENT);
		champ_nom_titre.setToolTipText("Nom du titre");
		this.add(champ_nom_titre);

		/* annee du titre */
		champ_annee_titre.setBounds((POSITION_X + 10), (POSITION_Y + 260), 140, HAUTEUR_ELEMENT);
		champ_annee_titre.setHorizontalAlignment(SwingConstants.LEFT);
		champ_annee_titre.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				/* l'année du titre ne dépasse pas l'année actuelle */
				if (champ_annee_titre.getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
					champ_annee_titre.setYear(2016);	
				}
			}
		});
		this.add(champ_annee_titre);

		/* type du titre */
		champ_type_titre = new JTextField(tab_nom_textfield[1]);
		champ_type_titre.addFocusListener(new FocusTextField(champ_type_titre));
		champ_type_titre.setBounds((POSITION_X + 158), (POSITION_Y + 260), 225, HAUTEUR_ELEMENT);
		champ_type_titre.setToolTipText("Type du titre");
		this.add(champ_type_titre);

		/* duree du titre */
		calendar.set(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		model.setValue(calendar.getTime());
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		spinner_duree.setEditor(editor);
		spinner_duree.setBounds((POSITION_X + 391), (POSITION_Y + 260), 140, HAUTEUR_ELEMENT);
		spinner_duree.setToolTipText("Durée du titre au format mm:ss");
		this.add(spinner_duree);

		/*
		 * auteur du titre, apparait uniquement si la checkbox reprise du titre
		 * est coché
		 */
		champ_auteur_titre = new JTextField(tab_nom_textfield[2]);
		champ_auteur_titre.addFocusListener(new FocusTextField(champ_auteur_titre));
		champ_auteur_titre.setBounds((POSITION_X + 158), (POSITION_Y + 300), 373, HAUTEUR_ELEMENT);
		griseElement();
		champ_auteur_titre.setToolTipText("Auteur du titre");
		this.add(champ_auteur_titre);

		/* checkbox reprise du titre, fait apparaitre le textfield auteur */
		chkbox_reprise_titre = new JCheckBox(tab_nom_textfield[3]);
		chkbox_reprise_titre.setBounds((POSITION_X + 15), (POSITION_Y + 300), 120, HAUTEUR_ELEMENT);
		chkbox_reprise_titre.setForeground(COULEUR_TEXTE);
		chkbox_reprise_titre.setOpaque(false);
		chkbox_reprise_titre.setContentAreaFilled(false);
		chkbox_reprise_titre.setBorderPainted(false);
		chkbox_reprise_titre.setSelected(false);
		/*
		 * evenement pour griser le textfield auteur si la checkbox est decochee
		 */
		chkbox_reprise_titre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (chkbox_reprise_titre.isSelected() == true) {
					degriseElement();
				} else {
					griseElement();
				}
			}
		});
		this.add(chkbox_reprise_titre);

		/* bouton ajouter */
		bouton_valider = new JButton("Créer");
		bouton_valider.setToolTipText("Créer un titre");
		bouton_valider.setBounds(260, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_valider);
		bouton_valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* nom du titre obligatoire */
				if (champ_nom_titre.getText().equals("") || champ_nom_titre.getText().equals(tab_nom_textfield[0])) {
					champ_nom_titre.setBackground(Main.COULEUR_ERROR);
				} else {
					/* création du titre */
					Titre titre1 = new Titre();
					titre1.setTitre(champ_nom_titre.getText());
					titre1.setType(champ_type_titre.getText());
					titre1.setAnnee(champ_annee_titre.getYear());
					titre1.setDuree(spinner_duree.getValue());
					titre1.setReprise_titre(chkbox_reprise_titre.isSelected());
					titre1.setAuteur(champ_auteur_titre.getText());

					/* processus si c'est une creation de titre */
					if (!titre_exist) {
						/* ajout du titre à la liste de titres du groupe */
						OngletRepertoire.modele.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow())
								.add(titre1);
						/* ajout du titre au tableau de titres */
						OngletTitre.modele.addTitre(OngletRepertoire.modele
								.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow())
								.get(OngletRepertoire.modele
										.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow()).size()
										- 1));
						testListeTitre();
						/* processus si c'est une modification de titre */
					} else {
						/* modification de la liste de titres du groupe */
						OngletRepertoire.modele.setValueTitreAt(titre1,
								OngletRepertoire.table_repertoire.getSelectedRow(),
								OngletTitre.table_titre.getSelectedRow());
						/* modification du tableau de titres */
						OngletTitre.modele.setValueAt(
								OngletRepertoire.modele
										.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow())
										.get(OngletTitre.table_titre.getSelectedRow()),
								OngletTitre.table_titre.getSelectedRow());
						testListeTitre();
					}
					reinitialiseTitre();
				}
			}
		});

		/* bouton annuler */
		bouton_annuler = new JButton("Annuler");
		bouton_annuler.setToolTipText("Annuler la création du titre");
		bouton_annuler.setBounds(370, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_annuler);
		bouton_annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reinitialiseTitre();
			}
		});

		/* bouton supprimer */
		bouton_supprimer = new JButton("Supprimer");
		bouton_supprimer.setBounds(480, 400, 100, HAUTEUR_ELEMENT);
		bouton_supprimer.setToolTipText("Supprimer ce titre");
		bouton_supprimer.setEnabled(false);
		this.add(bouton_supprimer);
		bouton_supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = OngletTitre.table_titre.getSelectedRow();
				int exit = JOptionPane.showConfirmDialog(null, "Supprimer le titre ?", "Supprimer ?",
						JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.YES_OPTION) {
					int[] selection = OngletTitre.table_titre.getSelectedRows();
					for (int i = selection.length - 1; i >= 0; i--) {
						OngletTitre.modele.removeTitre(selection[i]);
					}
					OngletRepertoire.modele.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow())
							.remove(row);
					testListeTitre();
					reinitialiseTitre();
				}
			}
		});
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

	/* methode pour desactiver le champ auteur */
	private void griseElement() {
		champ_auteur_titre.setText(OngletInfosGroupe.tab_textfield[0].getText());
		champ_auteur_titre.setBackground(COULEUR_FOND_GRISE);
		champ_auteur_titre.setForeground(COULEUR_TEXTE_GRISE);
		champ_auteur_titre.setEnabled(false);
	}

	/* methode pour activer le champ auteur */
	private void degriseElement() {
		champ_auteur_titre.setText(tab_nom_textfield[2]);
		champ_auteur_titre.setBackground(Color.white);
		champ_auteur_titre.setForeground(Color.black);
		champ_auteur_titre.setEnabled(true);
	}

	/* methode de reinitialisation des composants */
	private void reinitialiseTitre() {
		table_titre.repaint();
		champ_nom_titre.setText(tab_nom_textfield[0]);
		champ_nom_titre.setBackground(Color.white);
		champ_type_titre.setText(tab_nom_textfield[1]);
		champ_annee_titre.setYear(2016);
		spinner_duree.setValue(calendar.getTime());
		chkbox_reprise_titre.setSelected(false);
		griseElement();
		titre_exist = false;
		bouton_valider.setText("Créer");
		bouton_valider.setToolTipText("Créer un titre");
		bouton_supprimer.setEnabled(false);
	}

	/* methode de modification de titre */
	public static void modifOngletTitreGroupe() {
		for (int i = 0; i < OngletRepertoire.modele.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow())
				.size(); i++) {
			OngletTitre.modele.addTitre(
					OngletRepertoire.modele.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow()).get(i));
		}
	}

	/* test de liste de titres */
	private void testListeTitre() {
		for (int i = 0; i < OngletRepertoire.modele.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow())
				.size(); i++) {
			System.out.printf("%d. titre %s\n", i, OngletRepertoire.modele
					.getListeTitreAt(OngletRepertoire.table_repertoire.getSelectedRow()).get(i).titre);
		}
		System.out.printf("\n");
	}
}
