/*---------------------------------------------------------------------
 * Programme:                   Rack
 * Fichier:                     Main.java
 * Paquage:                     packFramePrincipale
 * Classe:                      Main
 * Auteur:                      anthony
 * Date de creation:            05/12/2015
 * Mise à jour:                 11/12/15
 *-------------------------------------------------------------------*/

/**
 * @author anthony
 * @version 1.0
 */

package frame_principale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	/* administrateur */
	public static final String LOGIN_ADMIN = "root";
	public static final String MDP_ADMIN = "admin";
	/* utilisateur 1 */
//	public static final String LOGIN_USER1 = "user1";
//	public static final String MDP_USER1 = "azerty1";
//	/* utilisateur 2 */
//	public static final String LOGIN_USER2 = "user2";
//	public static final String MDP_USER2 = "azerty2";

	public static final int HAUTEUR_ELEMENT = 26;
	public static final int LARGEUR_ELEMENT = 460;
	public static final int LARGEUR_1 = 255;
	public static final Font TITRE1 = new Font("Arial", 0, 36);
	public static final Font TITRE2 = new Font("Arial", 0, 26);
	public static final Font TITRE3 = new Font("Arial", 0, 18);
	public static final Color COULEUR_TEXTE = new Color(52, 171, 251);
	public static final Color COULEUR_ERROR = new Color(255, 97, 93);
	public static final Dimension DIM = new Dimension(160, HAUTEUR_ELEMENT);
	public static final Dimension DIM2 = new Dimension(115, HAUTEUR_ELEMENT);
	public static final Color COULEUR_FOND_DEFAULT = new Color(61, 62, 57);
	public static final Color COULEUR_FOND_USER = new Color(53, 73, 255);
	public static final Color COULEUR_FOND_ROOT = new Color(255, 47, 54);
	public static final Color COULEUR_FOND_TEXTFIELD = new Color(115, 194, 251);
	public static final Color COULEUR_FOND_GRISE = new Color(157, 157, 157);
	public static final Color COULEUR_TEXTE_GRISE = new Color(226, 226, 226);

	public static void main(String[] args) {

		try {
			// setLookAndFeel(getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		/* appel de la Frame principale */
		new FenetrePrincipale();
	}
}
