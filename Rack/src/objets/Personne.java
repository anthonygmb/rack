package objets;

import java.util.Date;

public class Personne {
	
    public String nom_membre;
    public String prenom_membre;
    public String civi_membre;
    public Date date_naiss_membre;
    public String spe_membre;
    public String instru_membre;
    public String respon_membre;
    public String adresse_cor;
    public String tel_cor;
    public String fax_cor;
    public String mail_cor;

    public Personne(){
    }

    public String getNom_membre() {
        return this.nom_membre;
    }

    public void setNom_membre(String value) {
        this.nom_membre = value;
    }

    public String getPrenom_membre() {
        return this.prenom_membre;
    }

    public void setPrenom_membre(String value) {
        this.prenom_membre = value;
    }

    public String getCivi_membre() {
        return this.civi_membre;
    }

    public void setCivi_membre(String value) {
        this.civi_membre = value;
    }

    public Date getDate_naiss_membre() {
        return this.date_naiss_membre;
    }

    public void setDate_naiss_membre(Date value) {
        this.date_naiss_membre = value;
    }

    public String getSpe_membre() {
        return this.spe_membre;
    }

    public void setSpe_membre(String value) {
        this.spe_membre = value;
    }

    public String getInstru_membre() {
        return this.instru_membre;
    }

    public void setInstru_membre(String value) {
        this.instru_membre = value;
    }

    public String getRespon_membre() {
        return this.respon_membre;
    }

    public void setRespon_membre(String value) {
        this.respon_membre = value;
    }

    public String getAdresse_cor() {
        return this.adresse_cor;
    }

    public void setAdresse_cor(String value) {
        this.adresse_cor = value;
    }

    public String getTel_cor() {
        return this.tel_cor;
    }

    public void setTel_cor(String value) {
        this.tel_cor = value;
    }


    public String getFax_cor() {
        return this.fax_cor;
    }

    public void setFax_cor(String value) {
        this.fax_cor = value;
    }

    public String getMail_cor() {
        return this.mail_cor;
    }

    public void setMail_cor(String value) {
        this.mail_cor = value;
    }
}
