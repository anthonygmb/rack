package frame_principale.onglet_admin;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import objets.User;

public class DeserialiserUser {

	/* methonde de deserialisation de la liste de users */
	@SuppressWarnings("unchecked")
	public static List<User> deserialise() {

		List<User> liste_user = new ArrayList<User>();

		try {
			FileInputStream fileIn = new FileInputStream("src/frame_principale/onglet_admin/user/user.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			boolean todo = true; // boucle de lecture des objets
			do {
				try {
					liste_user = (List<User>) in.readObject();
					System.out.println("Objet importé: " + liste_user);
				} catch (EOFException eof) {
					todo = false;
				}
			} while (todo);
			in.close();
			System.out.println("Fin de déserialisation");
		} catch (Exception e) {
			System.out.println(e);
		}
		return liste_user;
	}
}
