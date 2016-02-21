package frame_principale.onglet_admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import frame_principale.Main;
import methode.FocusTextField;
import objets.User;

public class OngletAdministration extends JPanel {

	private static final long serialVersionUID = -8646778674731617520L;

	/* position des elements */
	static private int POSITION_X = 300;
	static private int POSITION_Y = 80;

	private JTable table_user;
	private JScrollPane scrollpane_user;
	public static TableModelUser modele = new TableModelUser();
	private JLabel titre_gest_user;
	private JButton creer_user, modifier_user, supp_user;
	private JTextField login, mot_de_passe;
	private TableCellRenderer renderer = new DefaultTableCellRenderer();

	/* constructeur par defaut */
	public OngletAdministration() {
		this.setLayout(null);
		this.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		init_Conponents();
		this.setBackground(Main.COULEUR_FOND_DEFAULT);
	}

	/* initialisation des composants */
	private void init_Conponents() {

		/* titre administrateur */
		titre_gest_user = new JLabel("Utilisateurs");
		titre_gest_user.setFont(Main.TITRE2);
		titre_gest_user.setBounds((POSITION_X + 175), 20, 350, 50);
		titre_gest_user.setForeground(Main.COULEUR_TEXTE);
		this.add(titre_gest_user);

		/* tableau des utilisateurs */
		table_user = new JTable(modele);
		table_user.setDefaultRenderer(Object.class, renderer);
		table_user.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/* Compte le nombre de clics de la souris */
				if (e.getClickCount() == 1) {
					login.setText(modele.getValueAt(table_user.getSelectedRow(), 0).toString());
					mot_de_passe.setText(modele.getValueAt(table_user.getSelectedRow(), 1).toString());
				}
			}
		});

		/* déserialisation des users */
		modele.addListUser(DeserialiserUser.deserialise());

		/* permet de rendre le tableau transparent */
		// table_user.setOpaque(false);
		// ((DefaultTableCellRenderer)table_user.getDefaultRenderer(Object.class)).setOpaque(false);
		// table_user.setShowGrid(false);
		/* en-tete du tableau */
		// table_user.getTableHeader().setBackground(Color.gray);
		// table_user.getTableHeader().setForeground(Color.white);
		table_user.getTableHeader().setPreferredSize(new Dimension(600, Main.HAUTEUR_ELEMENT));
		/* corps du tableau */
		// ((DefaultTableCellRenderer)
		// table_user.getDefaultRenderer(Object.class)).setForeground(Color.white);
		// ((DefaultTableCellRenderer)
		// table_user.getDefaultRenderer(Object.class)).setBackground(Color.darkGray);
		table_user.setRowHeight(Main.HAUTEUR_ELEMENT);
		this.add(table_user);

		/* scrollpane tableau des utilisateurs */
		scrollpane_user = new JScrollPane(table_user);
		scrollpane_user.getViewport().setBackground(Color.black);
		scrollpane_user.setOpaque(false);
		scrollpane_user.getViewport().setOpaque(false);
		scrollpane_user.setBounds(POSITION_X, POSITION_Y, 500, 300);
		this.add(scrollpane_user);

		/* textfield login */
		login = new JTextField("login");
		login.addFocusListener(new FocusTextField(login));
		login.setBounds((POSITION_X + 40), (POSITION_Y + 310), 200, Main.HAUTEUR_ELEMENT);
		this.add(login);

		/* textfield mot de passe */
		mot_de_passe = new JTextField("mot de passe");
		mot_de_passe.addFocusListener(new FocusTextField(mot_de_passe));
		mot_de_passe.setBounds((POSITION_X + 260), (POSITION_Y + 310), 200, Main.HAUTEUR_ELEMENT);
		this.add(mot_de_passe);

		/* boutton créer utilisateur */
		creer_user = new JButton("Créer");
		creer_user.setBounds((POSITION_X + 70), (POSITION_Y + 385), 100, Main.HAUTEUR_ELEMENT);
		this.add(creer_user);
		creer_user.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User user1 = new User();
				/* teste si les champs login et mot de passe ont été remplis */
				if (!login.getText().equals("") && !(login.getText().equals("login"))
						&& !mot_de_passe.getText().equals("") && !(mot_de_passe.getText().equals("mot de passe"))) {
					boolean user_dupli = false;

					/* test si on utilise le login user */
					for (int i = 0; i < modele.getRowCount(); i++) {
						if (login.getText().equals(modele.getValueAt(i, 0))) {
							user_dupli = true;
						}
					}

					/* root ou un user existant ne doit pas etre utilisé */
					if ((login.getText().equals(Main.LOGIN_ADMIN)) || user_dupli == true) {
						JOptionPane.showMessageDialog(null, "Vous ne pouvez pas utiliser ce login", "Warning",
								JOptionPane.WARNING_MESSAGE);
						login.setBackground(Main.COULEUR_ERROR);

						/* le mot de passe doit faire 8 caracteres minimum */
					} else if (mot_de_passe.getText().length() < 8) {
						JOptionPane.showMessageDialog(null, "Veuillez entrer un mot de passe de 8 caractères minimum",
								"Warning", JOptionPane.WARNING_MESSAGE);
						mot_de_passe.setBackground(Main.COULEUR_ERROR);

					} else if ((mot_de_passe.getText().equals(login.getText()))) {
						JOptionPane.showMessageDialog(null, "Veuillez entrer un mot de passe différent du login",
								"Warning", JOptionPane.WARNING_MESSAGE);
						mot_de_passe.setBackground(Main.COULEUR_ERROR);
					} else {
						/* creation du user */
						user1.setLogin(login.getText());
						user1.setMod_de_passe(mot_de_passe.getText());
						modele.addUser(user1);
						table_user.repaint();
						login.setText("login");
						mot_de_passe.setText("mot de passe");
						login.setBackground(Color.white);
						mot_de_passe.setBackground(Color.white);
					}
				} else {
					login.setBackground(Main.COULEUR_ERROR);
					mot_de_passe.setBackground(Main.COULEUR_ERROR);
					login.setText("login");
					mot_de_passe.setText("mot de passe");
				}
			}
		});

		/* boutton modifier utilisateur */
		modifier_user = new JButton("Modifier");
		modifier_user.setBounds((POSITION_X + 195), (POSITION_Y + 385), 100, Main.HAUTEUR_ELEMENT);
		this.add(modifier_user);
		modifier_user.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User user1 = new User();
				user1.setLogin(login.getText());
				user1.setMod_de_passe(mot_de_passe.getText());
				modele.setValueAt(user1, table_user.getSelectedRow());
				table_user.repaint();
				login.setText("login");
				mot_de_passe.setText("mot de passe");
				login.setBackground(Color.white);
				mot_de_passe.setBackground(Color.white);
			}
		});

		/* boutton supprimer utilisateur */
		supp_user = new JButton("Supprimer");
		supp_user.setBounds((POSITION_X + 320), (POSITION_Y + 385), 110, Main.HAUTEUR_ELEMENT);
		this.add(supp_user);
		supp_user.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selection = table_user.getSelectedRows();
				for (int i = selection.length - 1; i >= 0; i--) {
					modele.removeUser(selection[i]);
				}
				table_user.repaint();
				login.setText("login");
				mot_de_passe.setText("mot de passe");
				login.setBackground(Color.white);
				mot_de_passe.setBackground(Color.white);
			}
		});
	}

	/* methode d'objets graphiques */
	// public void paintComponent(Graphics g) {
	// /* image de fond */
	// try {
	// Image img = ImageIO.read(new File("src/img/gimp_fender1.jpg"));
	// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
