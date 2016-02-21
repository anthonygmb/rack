package methode;

import static frame_principale.Main.COULEUR_FOND_TEXTFIELD;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;

public class FocusOptionField extends FocusAdapter {

	/* methode definissant les regles lorsque l'on clique dans un JFormatedTextField
	 * on indique le type de textfield que l'on souhaite formater,
	 * 1 = textfield integer
	 * 2 = textfield duree */
	public FocusOptionField(final JFormattedTextField t, int typeTextField) {

		final String s = t.getText();
		t.addFocusListener(new FocusAdapter() {
			@Override
			/* fonction lorsque l'on clique dans le textfield */
			public void focusGained(FocusEvent e) {
				// System.out.printf("s = %s\n", s);
				/*
				 * si le texte actuel du textfield est égal a s (s est égal au
				 * label de textfield alors le textfield se vide
				 */
				if (t.getText().equals(s)) {
					t.setText("");
					t.setBackground(COULEUR_FOND_TEXTFIELD);
				} else {
					/*
					 * sinon le textfield prend la valeur du texte saisi, dans
					 * le cas ou on reviendrait sur le textfield après avoir
					 * saisi une valeur différente de s
					 */
					t.getText();
					t.setBackground(COULEUR_FOND_TEXTFIELD);
				}
			}

			/* fonction lorsque l'on sort du textfield */
			public void focusLost(FocusEvent e) {
				/*
				 * Si quelque chose à été saisi le textfield prend cette valeur
				 * quand on le quitte
				 */
				if (!t.getText().equals("")) {
					t.getText();
					t.setBackground(Color.white);
					/*
					 * Si le textfield est vide quand on le quitte alors il
					 * prend la valeur de départ, s
					 */
				} else {
					switch (typeTextField) {
					case 1:
						t.setText("0");
						break;
					case 2:
						t.setText("00:00");
						break;
					default:
						break;
					}
					t.setBackground(Color.white);
				}
			}
		});
	}
}
