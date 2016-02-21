package methode;

import javax.swing.*;

import static frame_principale.Main.COULEUR_FOND_TEXTFIELD;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FocusTextField extends FocusAdapter {

	/* methode definissant les regles lorsque l'on clique dans un JTextField */
    public FocusTextField(final JTextField t) {

        final String s = t.getText();
        t.addFocusListener(new FocusAdapter() {
            @Override
            /* fonction lorsque l'on clique dans le textfield */
            public void focusGained(FocusEvent e) {
//                System.out.printf("s = %s\n", s);
                /* si le texte actuel du textfield est égal a s (s est égal au
                 * label de textfield alors le textfield se vide */
                if (t.getText().equals(s)) {
                    t.setText("");
                    t.setBackground(COULEUR_FOND_TEXTFIELD);
                } else {
                    /* sinon le textfield prend la valeur du texte saisi, dans le cas ou on reviendrait 
                     * sur le textfield après avoir saisi une valeur différente de s */
                    t.getText();
                    t.setBackground(COULEUR_FOND_TEXTFIELD);
                }
            }

            /* fonction lorsque l'on sort du textfield */
            public void focusLost(FocusEvent e) {
                /* Si quelque chose à été saisi le textfield prend cette valeur quand on le quitte */
                if (!t.getText().equals("")) {
                    t.getText();
                    t.setBackground(Color.white);
                    /* Si le textfield est vide quand on le quitte alors il prend la valeur de départ, s */
                } else {
                    t.setText(s);
                    t.setBackground(Color.white);
                }
            }
        });
    }
}
