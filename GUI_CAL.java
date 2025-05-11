import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Classe de l'interface graphique
public class GUI_CAL extends JFrame implements ActionListener {

    private JTextField ecran; // Champ d'affichage
    private double num1 = 0, num2 = 0; // Nombres
    private char operateur; // Opérateur
    private boolean enAttenteDeNouvelleEntree = false; // Pour gérer les entrées successives
    private final App_Cal app; // Instance de la classe de logique métier

    // Constructeur de l'interface
    public GUI_CAL() {
        app = new App_Cal(); // Instanciation de la classe de calcul

        setTitle("Calculatrice_PM_LAWO_MOGUE");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ecran = new JTextField();
        ecran.setEditable(false);
        ecran.setFont(new Font("Arial", Font.BOLD, 24));
        add(ecran, BorderLayout.CENTER);

        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(4, 4, 10, 10));

        String[] boutons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String texte : boutons) {
            JButton bouton = new JButton(texte);
            bouton.setFont(new Font("Arial", Font.BOLD, 20));
            bouton.addActionListener(this);
            panneau.add(bouton);
        }

        add(panneau, BorderLayout.CENTER);
    }

    // Affiche la fenêtre
    public void afficher() {
        setVisible(true);
    }

    // Gestion des événements (clics)
    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();

        if (commande.matches("[0-9]")) {
            if (enAttenteDeNouvelleEntree) {
                ecran.setText(commande);
                enAttenteDeNouvelleEntree = false;
            } else {
                ecran.setText(ecran.getText() + commande);
            }

        } else if (commande.equals("C")) {
            ecran.setText("");
            num1 = num2 = 0;
            operateur = '\0';
            enAttenteDeNouvelleEntree = false;

        } else if (commande.equals("=")) {
            try {
                num2 = Double.parseDouble(ecran.getText());
                double resultat = app.calculer(num1, num2, operateur);
                ecran.setText(String.valueOf(resultat));
                num1 = resultat;
                enAttenteDeNouvelleEntree = true;
            } catch (Exception ex) {
                ecran.setText("Erreur");
            }

        } else {
            try {
                num1 = Double.parseDouble(ecran.getText());
                operateur = commande.charAt(0);
                enAttenteDeNouvelleEntree = true;
            } catch (Exception ex) {
                ecran.setText("Erreur");
            }
        }
    }
}
