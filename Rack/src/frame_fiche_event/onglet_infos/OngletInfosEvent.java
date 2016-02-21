package frame_fiche_event.onglet_infos;

import static frame_fiche_event.FicheEvent.event_exist;
import static frame_principale.Main.HAUTEUR_ELEMENT;
import static frame_principale.Main.LARGEUR_ELEMENT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;

import frame_principale.Main;
import frame_principale.onglet_planif.OngletPlanification;
import methode.FocusOptionField;
import methode.FocusTextField;
import objets.Rencontre;

public class OngletInfosEvent extends JPanel {

	private static final long serialVersionUID = -2319697223425454070L;

	/* position des elements */
	static private int POSITION_X = 67;
	static private int POSITION_Y = 40;

	/* composants */
	public static JTextField nom_event, ville_event, lieu_event;
	private static NumberFormat format = NumberFormat.getInstance();
	private static NumberFormatter formatter = new NumberFormatter(format);
	private static JFormattedTextField nb_pers = new JFormattedTextField(formatter);
	private JLabel date_deb, date_fi;
	private static JDateChooser date_debut = new JDateChooser();
	private static JDateChooser date_fin = new JDateChooser();
	private static JComboBox<String> cmbbox_perio;
	private static JButton bouton_valider, bouton_supprimer;
	private JButton bouton_annuler;
	private JDialog FicheEvent;

	/* variables */
	String[] tab_perio = { "Périodicité", "Annuelle", "Semestrielle", "Trimestrielle", "Bimensuelle", "Mensuelle",
			"Hebdomadaire", "Journalière" };
	public static String[] tab_nom_textfield = { "Nom de l'evenement", "Ville", "Lieu",
			"Nombre de personnes attendues" };
	public static boolean doublon_event = false;

	/* constructeur par defaut */
	public OngletInfosEvent(JDialog FicheEvent) {
		this.FicheEvent = FicheEvent;
		this.setLayout(null);
		initComponents();
	}

	/* initialisation des composants */
	private void initComponents() {

		/* nom de l'evenement */
		nom_event = new JTextField();
		nom_event.setText(tab_nom_textfield[0]);
		nom_event.setBounds(POSITION_X, (POSITION_Y - 20), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		nom_event.setToolTipText(tab_nom_textfield[0]);
		this.add(nom_event);
		nom_event.addFocusListener(new FocusTextField(nom_event));

		/* ville de l'evenement */
		ville_event = new JTextField();
		ville_event.setText(tab_nom_textfield[1]);
		ville_event.setBounds(POSITION_X, (POSITION_Y + 20), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		ville_event.setToolTipText(tab_nom_textfield[1]);
		this.add(ville_event);
		ville_event.addFocusListener(new FocusTextField(ville_event));

		/* lieu de l'evenement */
		lieu_event = new JTextField();
		lieu_event.setText(tab_nom_textfield[2]);
		lieu_event.setBounds(POSITION_X, (POSITION_Y + 60), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		lieu_event.setToolTipText(tab_nom_textfield[2]);
		this.add(lieu_event);
		lieu_event.addFocusListener(new FocusTextField(lieu_event));

		/* textfield nombre de personnes attendues formatte en Integer */
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setCommitsOnValidEdit(true);
		nb_pers.setText(tab_nom_textfield[3]);
		nb_pers.setBounds(POSITION_X, (POSITION_Y + 100), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		nb_pers.setToolTipText(tab_nom_textfield[3]);
		this.add(nb_pers);
		nb_pers.addFocusListener(new FocusOptionField(nb_pers, 1));

		/* titre date de debut */
		date_deb = new JLabel("Date de debut");
		date_deb.setFont(Main.TITRE3);
		date_deb.setBounds(POSITION_X, (POSITION_Y + 135), 400, HAUTEUR_ELEMENT);
		date_deb.setForeground(Main.COULEUR_TEXTE);
		this.add(date_deb);

		/* choix de date de debut */
		this.add(date_debut);
		date_debut.setBounds(POSITION_X, (POSITION_Y + 165), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		date_debut.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				/* la date de début ne peut pas etre anti-datée */
				if (date_debut.getDate().before(Calendar.getInstance().getTime())) {
					date_debut.setDate(Calendar.getInstance().getTime());
				}
			}
		});

		/* titre date de fin */
		date_fi = new JLabel("Date de fin");
		date_fi.setFont(Main.TITRE3);
		date_fi.setBounds(POSITION_X, (POSITION_Y + 210), 400, HAUTEUR_ELEMENT);
		date_fi.setForeground(Main.COULEUR_TEXTE);
		this.add(date_fi);

		/* choix de date de fin */
		this.add(date_fin);
		date_fin.setBounds(POSITION_X, (POSITION_Y + 240), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		date_fin.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				/* la date de fin ne peut pas etre anti-datée */
				if (date_debut.getDate().before(Calendar.getInstance().getTime())) {
					date_debut.setDate(Calendar.getInstance().getTime());
				} 
			}
		});

		/* combobox periodicite */
		cmbbox_perio = new JComboBox<String>();
		cmbbox_perio.setBounds(POSITION_X, (POSITION_Y + 280), LARGEUR_ELEMENT, HAUTEUR_ELEMENT);
		for (int y = 0; y < tab_perio.length; y++) {
			cmbbox_perio.addItem(tab_perio[y]);
		}
		this.add(cmbbox_perio);

		/* bouton valider */
		bouton_valider = new JButton("Valider");
		bouton_valider.setToolTipText("Valider la création de l'événement");
		bouton_valider.setBounds(260, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_valider);
		bouton_valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Rencontre rencontre1 = new Rencontre();
				/* nom de rencontre obligatoire */
				if (!nom_event.getText().equals("") && (nom_event.getText().equals(tab_nom_textfield[0]))) {
					nom_event.setBackground(Main.COULEUR_FOND_ROOT);
				} else {
					/* date debut doit etre anterieur a date de fin */
					if (date_fin.getDate().before(date_debut.getDate())) {
						JOptionPane.showMessageDialog(null,
								"Veuillez entrer une date de debut antérieure à la date de fin", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} else {
						rencontre1.setNom_renc(nom_event.getText());
						rencontre1.setVille_renc(
								(ville_event.getText().equals(tab_nom_textfield[1])) ? "" : ville_event.getText());
						rencontre1.setLieu_renc(
								(lieu_event.getText().equals(tab_nom_textfield[2])) ? "" : lieu_event.getText());
						rencontre1.setNb_pers_attendues(
								(nb_pers.getText().equals(tab_nom_textfield[3])) ? "0" : nb_pers.getText());
						rencontre1.setDate_deb_renc(date_debut.getDate());
						rencontre1.setDate_fin_renc(date_fin.getDate());
						rencontre1.setPeriodicite_renc((cmbbox_perio.getSelectedItem().toString().equals(tab_perio[0]))
								? "" : cmbbox_perio.getSelectedItem().toString());

						/* teste si c'est une modification d'événement */
						if (!event_exist) {

							/* recherche de doublons d'événements */
							for (int i = 0; i < OngletPlanification.modele.getRowCount(); i++) {
								if (OngletPlanification.modele.getRowCount() != 0 && nom_event.getText()
										.equals(OngletPlanification.modele.getValueAt(i, 0).toString())) {
									doublon_event = true;
								}
							}

							/* teste si un doublon d'événement est trouvé */
							if (doublon_event) {
								JOptionPane.showMessageDialog(null, "Cet événement est déjà existant", "Warning",
										JOptionPane.WARNING_MESSAGE);
								nom_event.setBackground(Main.COULEUR_ERROR);
								doublon_event = false;
							} else {
								OngletPlanification.modele.addRencontre(rencontre1);
								OngletPlanification.table_planif.repaint();
								FicheEvent.dispose();
								event_exist = false;
								doublon_event = false;
							}
						} else {
							OngletPlanification.modele.setValueAt(rencontre1,
									OngletPlanification.table_planif.getSelectedRow());
							OngletPlanification.table_planif.repaint();
							FicheEvent.dispose();
							event_exist = false;
						}
					}
				}
			}
		});

		/* bouton annuler */
		bouton_annuler = new JButton("Annuler");
		bouton_annuler.setToolTipText("Annuler la création de l'événement");
		bouton_annuler.setBounds(370, 400, 100, HAUTEUR_ELEMENT);
		this.add(bouton_annuler);
		bouton_annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FicheEvent.dispose();
			}
		});

		/* bouton supprimer */
		bouton_supprimer = new JButton("Supprimer");
		bouton_supprimer.setEnabled(false);
		bouton_supprimer.setBounds(480, 400, 100, HAUTEUR_ELEMENT);
		bouton_supprimer.setToolTipText("Supprimer cet événement");
		this.add(bouton_supprimer);
		bouton_supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int exit = JOptionPane.showConfirmDialog(null, "Supprimer l'événement ?", "Supprimer ?",
						JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.YES_OPTION) {
					int[] selection = OngletPlanification.table_planif.getSelectedRows();
					for (int i = selection.length - 1; i >= 0; i--) {
						OngletPlanification.modele.removeRencontre(selection[i]);
					}
					FicheEvent.dispose();
					frame_fiche_event.FicheEvent.event_exist = false;
				}
			}
		});
	}

	/* methode de modification de l'evenement */
	public static void modifOngletInfoEvent() {
		nom_event.setText(
				OngletPlanification.modele.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 0).toString());

		if (OngletPlanification.modele.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 1).toString()
				.equals("")) {
			ville_event.setText(tab_nom_textfield[1]);
		} else {
			ville_event.setText(OngletPlanification.modele
					.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 1).toString());
		}

		if (OngletPlanification.modele.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 2).toString()
				.equals("")) {
			lieu_event.setText(tab_nom_textfield[2]);
		} else {
			lieu_event.setText(OngletPlanification.modele
					.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 2).toString());
		}

		nb_pers.setText(
				OngletPlanification.modele.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 6).toString());
		date_debut.setDate(
				(Date) OngletPlanification.modele.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 3));
		date_fin.setDate(
				(Date) OngletPlanification.modele.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 4));
		cmbbox_perio.setSelectedItem(
				OngletPlanification.modele.getValueAt(OngletPlanification.table_planif.getSelectedRow(), 5).toString());
		event_exist = true;
		bouton_supprimer.setEnabled((event_exist) ? true : false);
		bouton_valider.setText("Appliquer");
		bouton_valider.setToolTipText("Appliquer les modifications");
	}
}
