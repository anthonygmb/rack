package objets;

import java.io.Serializable;

/* classe de creation d'utilisateurs */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String login;
	private String mod_de_passe;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMod_de_passe() {
		return mod_de_passe;
	}

	public void setMod_de_passe(String mod_de_passe) {
		this.mod_de_passe = mod_de_passe;
	}
}
