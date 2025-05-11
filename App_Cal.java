// Classe contenant la logique de calcul
public class App_Cal {

    // Méthode de calcul en fonction de l'opérateur
    public double calculer(double num1, double num2, char operateur) {
        switch (operateur) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num2 != 0 ? num1 / num2 : 0;
            default: throw new IllegalArgumentException("Opérateur non valide");
        }
    }
}
