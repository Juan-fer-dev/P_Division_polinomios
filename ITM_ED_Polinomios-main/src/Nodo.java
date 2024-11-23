public class Nodo {

    private double Coeficiente;
    private int Exponente;

    Nodo siguiente;

    public Nodo(double coeficiente, int exponente) {
        Coeficiente = coeficiente;
        Exponente = exponente;
    }

    public double getCoeficiente() {
        return Coeficiente;
    }

    public void setCoeficiente(double coeficiente) {
        Coeficiente = coeficiente;
    }

    public int getExponente() {
        return Exponente;
    }

    public void setExponente(int exponente) {
        Exponente = exponente;
    }

}
