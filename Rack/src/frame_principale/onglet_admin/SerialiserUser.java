package frame_principale.onglet_admin;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import objets.User;

public class SerialiserUser {

	/* methode de serialisation de users */
	public static void serialise(List<User> liste_user) {
		try {
			FileOutputStream fileout = new FileOutputStream("src/frame_principale/onglet_admin/user/user.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(liste_user);
			System.out.println("Objet exporté: " + liste_user);
			out.close();
			System.out.println("Fin de sérialisation");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
