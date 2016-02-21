package frame_principale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import frame_principale.onglet_accueil.OngletAccueil;
import frame_principale.onglet_admin.OngletAdministration;
import frame_principale.onglet_admin.SerialiserUser;
import frame_principale.onglet_planif.OngletPlanification;
import frame_principale.onglet_reper.OngletRepertoire;
import methode.FocusTextField;

public class FenetrePrincipale extends JFrame {

	public static JFrame frame;
	private static final long serialVersionUID = 5231046381328612841L;
	boolean bool_connect = false;

	/* bandeau de fenetre */
	private JPanel bandeau;
	private JTextField login;
	private JPasswordField mot_de_passe;
	private String smot_de_passe;
	private JButton connection;
	private JButton deconnection;
	private GridBagConstraints c;

	/* corps de fenetre avec conteneur d'onglets et onglets */
	private JTabbedPane jTabPanOnglets;
	private OngletAccueil jPanAccueil;
	private OngletRepertoire jPanRepertoire;
	private OngletPlanification jPanPlanification;
	private OngletAdministration jPanAdministration;

	/* getter de la fenêtre pour définir la modalité */
	public static JFrame getFrame() {
		return frame;
	}

	/* constructeur par defaut */
	public FenetrePrincipale() {
		frame = this;
		setSize(1100, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Rack v1.0");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon("src/img/rolling-stones.gif").getImage());
		init_Conponents();
		setVisible(true); // toujours le mettre après le initComponent
		/* message de confirmation pour quitter */
		this.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("static-access")
			public void windowClosing(WindowEvent e) {
				int exit = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir quitter?", "Quitter?",
						JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.YES_OPTION) {
					SerialiserUser.serialise(OngletAdministration.modele.liste_user);
					System.exit(0);
				}
			}
		});
	}

	/* initialisation des composants */
	private void init_Conponents() {
		/* bandeau contenant login, mot de passe et bouton de connection */
		bandeau = new JPanel();

		/* textfield de login */
		login = new JTextField("login");
		login.addFocusListener(new FocusTextField(login));
		login.setPreferredSize(Main.DIM);
		login.setMinimumSize(Main.DIM);

		/* textfield de mot de passe */
		mot_de_passe = new JPasswordField("mot de passe");
		mot_de_passe.addFocusListener(new FocusTextField(mot_de_passe));
		mot_de_passe.setPreferredSize(Main.DIM);
		mot_de_passe.setMinimumSize(Main.DIM);

		/* bouton de connection */
		connection = new JButton("Connection");
		/*
		 * lorsque le bouton de connection est presse, le texte saisie dans les
		 * textfields de login et mot de passe sont recupere et stoque dans les
		 * variables correspondantes
		 */
		connection.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				actionConnection();
			}
		});
		connection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionConnection();
			}
		});
		connection.setPreferredSize(Main.DIM2);
		connection.setMinimumSize(Main.DIM2);

		/* bouton de deconnection */
		deconnection = new JButton("Deconnection");
		/*
		 * lorsque le bouton de deconnection est presse, l'onglet admin est
		 * ferme
		 */
		deconnection.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				actionDeconnection();
			}
		});
		deconnection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionDeconnection();
			}
		});
		deconnection.setPreferredSize(Main.DIM);
		deconnection.setMinimumSize(Main.DIM);

		/* contrainte du GrigBagLayout */
		bandeau.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(1, 5, 1, 5);
		c.gridx = c.gridy = 0;

		/* placement du TextField login */
		c.gridx = 2;
		c.weightx = 1.;
		c.anchor = GridBagConstraints.BASELINE_TRAILING;
		c.insets = new Insets(0, 3, 0, 3);
		bandeau.add(login, c);

		/* placement du TextField mot de passe */
		c.gridx = 3;
		c.weightx = 0.;
		c.insets = new Insets(0, 3, 0, 3);
		bandeau.add(mot_de_passe, c);

		/* placement du Bouton connection */
		c.gridx = 4;
		c.weightx = 0.;
		c.insets = new Insets(0, 3, 0, 3);
		bandeau.add(connection, c);

		/* placement du Bouton deconnection */
		c.gridx = 5;
		c.weightx = 0.;
		c.insets = new Insets(0, 3, 0, 10);
		bandeau.add(deconnection, c);

		/* initialisation des conteneurs et des onglets */
		jTabPanOnglets = new JTabbedPane();
		jPanAccueil = new OngletAccueil();
		jPanRepertoire = new OngletRepertoire();
		jPanPlanification = new OngletPlanification();
		jPanAdministration = new OngletAdministration();
		this.getContentPane();
		this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(Main.COULEUR_FOND_DEFAULT);
		bandeau.setBackground(Main.COULEUR_FOND_DEFAULT);
		this.add(jTabPanOnglets);
		jTabPanOnglets.addTab("Accueil", jPanAccueil);
		jTabPanOnglets.addTab("Répertoire", jPanRepertoire);
		jTabPanOnglets.addTab("Planification", jPanPlanification);
		this.add(bandeau, BorderLayout.NORTH);
	}

	public void actionConnection() {
		smot_de_passe = new String(mot_de_passe.getPassword());
		// System.out.printf("%s\n", slogin);
		// System.out.printf("%s\n", smot_de_passe);

		/* affichage de l'onglet administration sous condition admin */
		if (login.getText().equals(Main.LOGIN_ADMIN) && smot_de_passe.equals(Main.MDP_ADMIN)) {
			jTabPanOnglets.addTab("Administration", jPanAdministration);
			login.setBackground(Color.white);
			mot_de_passe.setBackground(Color.white);
			// bandeau.setBackground(COULEUR_FOND_ROOT);
			// getContentPane().setBackground(COULEUR_FOND_ROOT);
			bool_connect = true;
		} else {
			login.setBackground(Main.COULEUR_ERROR);
			mot_de_passe.setBackground(Main.COULEUR_ERROR);
			bool_connect = false;
		}
	}

	public void actionDeconnection() {
		if (bool_connect) {
			/* fermeture de l'onglet Administration */
			jTabPanOnglets.removeTabAt(3);
			login.setBackground(Color.white);
			login.setText("login");
			mot_de_passe.setBackground(Color.white);
			mot_de_passe.setText("mot de passe");
			// bandeau.setBackground(COULEUR_FOND_DEFAULT);
			// getContentPane().setBackground(COULEUR_FOND_DEFAULT);
		} else {
			System.out.printf("Erreur\n");
		}
		bool_connect = false;
	}
}
