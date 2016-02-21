package frame_fiche_event.onglet_prog;

import static frame_principale.Main.COULEUR_TEXTE;
import static frame_principale.Main.HAUTEUR_ELEMENT;
import static frame_principale.Main.LARGEUR_1;
import static frame_principale.Main.TITRE3;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DateFormatter;

import frame_principale.Main;
import frame_principale.onglet_planif.OngletPlanification;
import frame_principale.onglet_reper.OngletRepertoire;
import objets.Programme;

public class OngletProgramme extends JPanel {

	private static final long serialVersionUID = -771842682221447029L;
	/* position des elements */
	private static int POSITION_X = 27;
	private static int POSITION_Y = 40;

	/* composants */
	public static JLabel nom_event, trait, modif_prog, heure_deb, heure_fin;
	public static JTable table_prog;
	private JScrollPane scrollpane_table_prog;
	public static TableModelProgramme modele = new TableModelProgramme();
	private static JButton bouton_valider, bouton_supprimer;
	private JButton bouton_annuler;
	public static JComboBox<Object> cmbbox_groupe, cmbbox_titre;

	/* variables */
	public static String liste_vide = "vide";
	public static boolean prog_exist = false;
	private String[] tab_label = { "Groupe", "Titre", "Heure de début", "Heure de fin" };
	private static Calendar calendar1 = Calendar.getInstance();
	private static Calendar calendar2 = Calendar.getInstance();
	private static SpinnerDateModel model1 = new SpinnerDateModel();
	private static SpinnerDateModel model2 = new SpinnerDateModel();
	private static JSpinner spinner_heure_debut = new JSpinner(model1);
	private static JSpinner spinner_heure_fin = new JSpinner(model2);
	private JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spinner_heure_debut, "HH:mm");
	private JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner_heure_fin, "HH:mm");
	private DateFormatter formatter1 = (DateFormatter) editor1.getTextField().getFormatter();
	private DateFormatter formatter2 = (DateFormatter) editor2.getTextField().getFormatter();
	public static boolean conflict_prog = false;

	public OngletProgramme() {
		this.setLayout(null);
		initComponents();
	}

	/* initialisation des composants */
	private void initComponents() {

		/* titre du tableau */
		nom_event = new JLabel("Programme");
		nom_event.setFont(TITRE3);
		nom_event.setBounds(5, (POSITION_Y - 34), frame_fiche_event.FicheEvent.largeur_fenetre - 10, HAUTEUR_ELEMENT);
		nom_event.setHorizontalAlignment(SwingConstants.CENTER);
		nom_event.setForeground(COULEUR_TEXTE);
		this.add(nom_event);

		/* tableau des programme du groupe */
		table_prog = new JTable(modele);
		table_prog.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_prog.getColumnModel().getColumn(0).setPreferredWidth(170);
		table_prog.getColumnModel().getColumn(1).setPreferredWidth(164);
		table_prog.getColumnModel().getColumn(2).setPreferredWidth(90);
		table_prog.getColumnModel().getColumn(3).setPreferredWidth(90);
		table_prog.getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		table_prog.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		scrollpane_table_prog = new JScrollPane(table_prog);
		this.add(scrollpane_table_prog);
		scrollpane_table_prog.setBounds((POSITION_X + 10), POSITION_Y, 520, 180);
		scrollpane_table_prog.setOpaque(false);
		scrollpane_table_prog.getViewport().setOpaque(false);
		table_prog.setRowHeight(Main.HAUTEUR_ELEMENT);
		table_prog.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/* Compte le nombre de clics de la souris */
				if (e.getClickCount() == 1) {
					cmbbox_groupe.setSelectedItem(modele.getValueAt(table_prog.getSelectedRow(), 0));
					cmbbox_titre.setSelectedItem(modele.getValueAt(table_prog.getSelectedRow(), 1));
					spinner_heure_debut.setValue(modele.getValueAt(table_prog.getSelectedRow(), 2));
					spinner_heure_fin.setValue(modele.getValueAt(table_prog.getSelectedRow(), 3));
					bouton_valider.setText("Appliquer");
					bouton_valider.setToolTipText("Appliquer les modifications");
					prog_exist = true;
					bouton_supprimer.setEnabled(true);
				}
			}
		});

		/* trait de séparation */
		trait = new JLabel("__________________________________________________________");
		trait.setFont(TITRE3);
		trait.setBounds((POSITION_X + 10), (POSITION_Y + 180), 540, HAUTEUR_ELEMENT);
		trait.setForeground(COULEUR_TEXTE);
		this.add(trait);

		/* titre de l'espace des modifications */
		modif_prog = new JLabel("Ajouter des participants au Programme");
		modif_prog.setFont(TITRE3);
		modif_prog.setBounds((POSITION_X + 100), (POSITION_Y + 205), 540, HAUTEUR_ELEMENT);
		modif_prog.setForeground(COULEUR_TEXTE);
		this.add(modif_prog);

		/* combobox nom du groupe */
		cmbbox_groupe = new JComboBox<Object>();
		cmbbox_groupe.setBounds((POSITION_X + 10), (POSITION_Y + 240), LARGEUR_1, HAUTEUR_ELEMENT);
		this.add(cmbbox_groupe);
		if (OngletRepertoire.modele.getRowCount() == 0) {
			cmbbox_groupe.addItem(liste_vide);
		} else {
			for (int y = 0; y < OngletRepertoire.modele.getRowCount(); y++) {
				cmbbox_groupe.addItem(OngletRepertoire.modele.getValueAt(y, 0));
			}
		}
		cmbbox_groupe.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbbox_titre.removeAllItems();
				initialiseComboboxTitre();
			}
		});

		/* combobox nom du titre */
		cmbbox_titre = new JComboBox<Object>();
		cmbbox_titre.setBounds((POSITION_X + 277), (POSITION_Y + 240), LARGEUR_1, HAUTEUR_ELEMENT);
		initialiseComboboxTitre();
		this.add(cmbbox_titre);

		/* label heure de début */
		heure_deb = new JLabel(tab_label[2]);
		heure_deb.setFont(TITRE3);
		heure_deb.setBounds((POSITION_X + 10), (POSITION_Y + 275), 150, HAUTEUR_ELEMENT);
		heure_deb.setForeground(COULEUR_TEXTE);
		this.add(heure_deb);

		/* heure de début */
		calendar1.set(Calendar.HOUR_OF_DAY, 24);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		model1.setValue(calendar1.getTime());
		formatter1.setAllowsInvalid(false);
		formatter1.setOverwriteMode(true);
		spinner_heure_debut.setEditor(editor1);
		this.add(spinner_heure_debut);
		spinner_heure_debut.setBounds((POSITION_X + 10), (POSITION_Y + 305), 120, HAUTEUR_ELEMENT);
		spinner_heure_debut.setToolTipText(tab_label[2]);
		spinner_heure_debut.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!cmbbox_groupe.getSelectedItem().equals(liste_vide)
						&& !cmbbox_titre.getSelectedItem().equals(liste_vide)) {
					Date duree1 = (Date) spinner_heure_debut.getValue();
					Date duree2 = (Date) OngletRepertoire.modele.getListeTitreAt(cmbbox_groupe.getSelectedIndex())
							.get(cmbbox_titre.getSelectedIndex()).getDuree();
					long somme = duree1.getTime() + duree2.getTime();
					Date hour = new Date(3600 * 1000);
					Date long_to_Date = new Date(somme);
					Date sommeDate = new Date(long_to_Date.getTime() + hour.getTime());
					spinner_heure_fin.setValue(sommeDate);
				}
			}
		});

		/* label heure de fin */
		heure_fin = new JLabel(tab_label[3]);
		heure_fin.setFont(TITRE3);
		heure_fin.setBounds((POSITION_X + 278), (POSITION_Y + 275), 120, HAUTEUR_ELEMENT);
		heure_fin.setForeground(COULEUR_TEXTE);
		this.add(heure_fin);

		/* heure de fin */
		calendar2.set(Calendar.HOUR_OF_DAY, 24);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		model2.setValue(calendar2.getTime());
		formatter2.setAllowsInvalid(false);
		formatter2.setOverwriteMode(true);
		spinner_heure_fin.setEditor(editor2);
		this.add(spinner_heure_fin);
		spinner_heure_fin.setBounds((POSITION_X + 278), (POSITION_Y + 305), 120, HAUTEUR_ELEMENT);
		spinner_heure_fin.setToolTipText(tab_label[3]);

		/* bouton valider */
		bouton_valider = new JButton("Créer");
		bouton_valider.setToolTipText("Créer un programme");
		bouton_valider.setBounds(260, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_valider);
		bouton_valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/* la liste de groupes ne dois pas etre vide */
				if (cmbbox_groupe.getSelectedItem().equals(liste_vide)) {
					JOptionPane.showMessageDialog(null, "Vous n'avez aucun groupe à ajouter au programme", "Warning",
							JOptionPane.WARNING_MESSAGE);
					/* la liste de titres ne dois pas etre vide */
				} else if (cmbbox_titre.getSelectedItem().equals(liste_vide)) {
					JOptionPane.showMessageDialog(null, "Ce groupe doit comporter au moins un titre", "Warning",
							JOptionPane.WARNING_MESSAGE);
					/* heure debut doit etre différente de heure de fin */
				} else if (((Date) spinner_heure_fin.getValue()).equals((Date) spinner_heure_debut.getValue())) {
					JOptionPane.showMessageDialog(null,
							"Veuillez entrer une heure de début différente de l'heure de fin", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					/* recherche de conflicts de programmes */
					if (OngletProgramme.modele.getRowCount() != 0) {
						for (int i = 0; i < OngletProgramme.modele.getRowCount(); i++) {
							Date date1 = (Date) spinner_heure_debut.getValue();
							Date date2 = (Date) OngletProgramme.modele.getValueAt(i, 2);
							Date date3 = (Date) spinner_heure_fin.getValue();
							Date date4 = (Date) OngletProgramme.modele.getValueAt(i, 3);

							if (date1.after(date2) && date1.before(date4)
									|| date3.before(date4) && date3.after(date2)) {
								conflict_prog = true;
							}
						}
					}
					if (conflict_prog) {
						JOptionPane.showMessageDialog(null, "Ce créneau horaire est déjà pris", "Warning",
								JOptionPane.WARNING_MESSAGE);
						reinitialiseProg();
						conflict_prog = false;
					} else {
						/* création du programme */
						Programme prog1 = new Programme();
						prog1.setGroupe_produit(OngletRepertoire.modele.getGroupeAt(cmbbox_groupe.getSelectedIndex()));
						prog1.setTitre_joue(OngletRepertoire.modele.getListeTitreAt(cmbbox_groupe.getSelectedIndex())
								.get(cmbbox_titre.getSelectedIndex()));
						prog1.setHeure_debut(spinner_heure_debut.getValue());
						prog1.setHeure_fin(spinner_heure_fin.getValue());

						/* processus creation de programme */
						if (!prog_exist) {
							/*
							 * ajout du programme à la liste de programmes d'une
							 * rencontre
							 */
							OngletPlanification.modele.getListeProgAt(OngletPlanification.table_planif.getSelectedRow())
									.add(prog1);
							/* ajout du programme au tableau de programme */
							OngletProgramme.modele.addProgramme(OngletPlanification.modele
									.getListeProgAt(OngletPlanification.table_planif.getSelectedRow())
									.get(OngletPlanification.modele
											.getListeProgAt(OngletPlanification.table_planif.getSelectedRow()).size()
											- 1));
							/*
							 * ajout de la rencontre qui contient le groupe, au
							 * groupe
							 */
							modificationProg(1);

							/* processus modification de programme */
						} else {
							/*
							 * modification de la liste de programme d'une
							 * rencontre
							 */
							OngletPlanification.modele.setValueProgAt(prog1,
									OngletPlanification.table_planif.getSelectedRow(),
									OngletProgramme.table_prog.getSelectedRow());
							/* modification du tableau de programmes */
							OngletProgramme.modele.setValueAt(
									OngletPlanification.modele
											.getListeProgAt(OngletPlanification.table_planif.getSelectedRow())
											.get(OngletProgramme.table_prog.getSelectedRow()),
									OngletProgramme.table_prog.getSelectedRow());

							/*
							 * modification de l'evenement du tableau event
							 * futur
							 */
							modificationProg(2);
						}
						reinitialiseProg();
						conflict_prog = false;
					}
				}
			}
		});

		/* bouton annuler */
		bouton_annuler = new JButton("Annuler");
		bouton_annuler.setToolTipText("Annuler la création du programme");
		bouton_annuler.setBounds(370, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_annuler);
		bouton_annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reinitialiseProg();
			}
		});

		/* bouton supprimer */
		bouton_supprimer = new JButton("Supprimer");
		bouton_supprimer.setBounds(480, 400, 100, HAUTEUR_ELEMENT);
		bouton_supprimer.setToolTipText("Supprimer cet élément du programme");
		bouton_supprimer.setEnabled(false);
		this.add(bouton_supprimer);
		bouton_supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = OngletProgramme.table_prog.getSelectedRow();
				int exit = JOptionPane.showConfirmDialog(null, "Supprimer le programme ?", "Supprimer ?",
						JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.YES_OPTION) {
					modificationProg(3);
					
					OngletPlanification.modele.getListeProgAt(OngletPlanification.table_planif.getSelectedRow())
					.remove(row);
					
					int[] selection = OngletProgramme.table_prog.getSelectedRows();
					for (int i = selection.length - 1; i >= 0; i--) {
						OngletProgramme.modele.removeProgramme(selection[i]);
					}
					reinitialiseProg();
				}
			}
		});
	}

	/* formatage des colonnes de temps */
	TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = 1L;
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (value instanceof Date) {
				value = f.format(value);
			}
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	};

	/* methode d'initialisation du combobox titre */
	public void initialiseComboboxTitre() {
		if (cmbbox_groupe.getSelectedItem().equals(liste_vide)) {
			cmbbox_titre.addItem(liste_vide);
		} else if (OngletRepertoire.modele.getListeTitreAt(cmbbox_groupe.getSelectedIndex()).isEmpty()) {
			cmbbox_titre.addItem(liste_vide);
		} else {
			for (int i = 0; i < OngletRepertoire.modele.getListeTitreAt(cmbbox_groupe.getSelectedIndex()).size(); i++) {
				cmbbox_titre.addItem(
						OngletRepertoire.modele.getListeTitreAt(cmbbox_groupe.getSelectedIndex()).get(i).getTitre());
			}
		}
	}

	/* methode de reinitialisation des composants */
	public static void reinitialiseProg() {
		table_prog.repaint();
		cmbbox_groupe.setSelectedIndex(0);
		cmbbox_titre.setSelectedIndex(0);
		spinner_heure_debut.setValue(calendar1.getTime());
		spinner_heure_fin.setValue(calendar2.getTime());
		prog_exist = false;
		bouton_valider.setText("Créer");
		bouton_valider.setToolTipText("Créer un programme");
		bouton_supprimer.setEnabled(false);
	}

	/* methode de modification de programme */
	public static void modifOngletProgEvent() {
		for (int i = 0; i < OngletPlanification.modele.getListeProgAt(OngletPlanification.table_planif.getSelectedRow())
				.size(); i++) {
			OngletProgramme.modele.addProgramme(OngletPlanification.modele
					.getListeProgAt(OngletPlanification.table_planif.getSelectedRow()).get(i));
		}
	}

	/* methode de modification de programme */
	public static void modificationProg(int suppression) {
		if (!OngletPlanification.modele.getListeProgAt(OngletPlanification.table_planif.getSelectedRow()).isEmpty()) {
			for (int j = OngletPlanification.modele.getListeProgAt(OngletPlanification.table_planif.getSelectedRow())
					.size(); j > OngletPlanification.modele
							.getListeProgAt(OngletPlanification.table_planif.getSelectedRow()).size() - 1; j--) {
				for (int k = 0; k < OngletRepertoire.table_repertoire.getRowCount(); k++) {
					if (OngletPlanification.modele.getListeProgAt(OngletPlanification.table_planif.getSelectedRow())
							.get(j - 1).getGroupe_produit().getNom_groupe()
							.equals(OngletRepertoire.table_repertoire.getValueAt(k, 0))) {

						switch (suppression) {
						case 1:
							/* ajoute les programmes */
							OngletRepertoire.modele.getGroupeAt(k).getListe_rencontre().add(OngletPlanification.modele
									.getRencontreAt(OngletPlanification.table_planif.getSelectedRow()));
							break;
						case 2:
							/* met a jour les programmes */
							OngletRepertoire.modele.getGroupeAt(k).getListe_rencontre()
									.remove(OngletPlanification.modele
											.getRencontreAt(OngletPlanification.table_planif.getSelectedRow()));

							OngletRepertoire.modele.getGroupeAt(k).getListe_rencontre().add(OngletPlanification.modele
									.getRencontreAt(OngletPlanification.table_planif.getSelectedRow()));
							break;
						case 3:
							/* supprime les programmes */
							OngletRepertoire.modele.getGroupeAt(k).getListe_rencontre()
									.remove(OngletPlanification.modele
											.getRencontreAt(OngletPlanification.table_planif.getSelectedRow()));
							break;
						default:
							System.out.printf("Erreur\n");
							break;
						}
						System.out.printf(
								"liste rencontre ligne %d :\nliste programme ligne %d :\ncorrespond a liste groupe ligne %d\n\n",
								OngletPlanification.table_planif.getSelectedRow(), j - 1, k);
					}
				}
			}
		}
	}
}