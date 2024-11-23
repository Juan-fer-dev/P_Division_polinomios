import java.awt.Font;

import javax.swing.JLabel;

public class Polinomio {
    private Nodo cabeza;

    public Polinomio() {
        cabeza = null;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void agregar(Nodo n) {
        if (n != null) {
            if (cabeza == null) {
                cabeza = n;
            } else {
                Nodo apuntador = cabeza;
                Nodo predecesor = null;
                int encontrado = 0;
                while (apuntador != null && encontrado == 0) {
                    if (n.getExponente() == apuntador.getExponente()) {
                        encontrado = 1;
                    } else if (n.getExponente() < apuntador.getExponente()) {
                        encontrado = 2;
                    } else {
                        predecesor = apuntador;
                        apuntador = apuntador.siguiente;
                    }
                }
                if (encontrado == 1) {
                    double coeficiente = n.getCoeficiente() + apuntador.getCoeficiente();
                    if (coeficiente == 0) {
                        // quitar nodo
                        if (predecesor == null) {
                            cabeza = apuntador.siguiente;
                        } else {
                            predecesor.siguiente = apuntador.siguiente;
                        }
                    } else {
                        apuntador.setCoeficiente(coeficiente);
                    }
                } else {
                    insertar(n, predecesor);
                }
            }
        }
    }

    public void insertar(Nodo n, Nodo predecesor) {
        if (n != null) {
            if (predecesor == null) {
                n.siguiente = cabeza;
                cabeza = n;
            } else {
                n.siguiente = predecesor.siguiente;
                predecesor.siguiente = n;
            }
        }
    }

    public void limpiar() {
        cabeza = null;
    }

    public String[] getTextos() {
        String[] lineas = new String[2];
        String espacio = " ";
        Nodo apuntador = cabeza;
        lineas[0] = "";
        lineas[1] = "";
        while (apuntador != null) {
            String texto = String.valueOf(apuntador.getCoeficiente()) + " X";
            if (apuntador.getCoeficiente() >= 0) {
                texto = "+" + texto;
            }
            lineas[0] += String.format("%0" + texto.length() + "d", 0).replace("0", espacio);
            lineas[1] += texto;

            texto = String.valueOf(apuntador.getExponente());
            lineas[0] += texto;
            lineas[1] += String.format("%0" + texto.length() + "d", 0).replace("0", espacio);

            apuntador = apuntador.siguiente;
        }

        return lineas;
    }

    public void mostrar(JLabel lbl) {
        String[] lineas = getTextos();
        String espacio = "&nbsp;";
        lineas[0] = lineas[0].replace(" ", espacio);
        lineas[1] = lineas[1].replace(" ", espacio);
        lbl.setFont(new Font("Courier New", Font.PLAIN, 12));
        lbl.setText("<html>" + lineas[0] + "<br>" + lineas[1] + "</html>");
    }

    public Polinomio derivar() {
        Polinomio pR = new Polinomio();
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            if (apuntador.getExponente() != 0) {
                Nodo n = new Nodo(apuntador.getCoeficiente() * apuntador.getExponente(), apuntador.getExponente() - 1);
                pR.agregar(n);
            }
            apuntador = apuntador.siguiente;
        }

        return pR;
    }

    // ********** Metodos estaticos **********

    public static Polinomio sumar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();

        Nodo apuntador1 = p1.getCabeza();
        Nodo apuntador2 = p2.getCabeza();

        while (apuntador1 != null || apuntador2 != null) {
            Nodo n = null;

            if (apuntador1 != null && apuntador2 != null && apuntador1.getExponente() == apuntador2.getExponente()) {
                if (apuntador1.getCoeficiente() + apuntador2.getCoeficiente() != 0) {
                    n = new Nodo(apuntador1.getCoeficiente() + apuntador2.getCoeficiente(), apuntador1.getExponente());
                }
                apuntador1 = apuntador1.siguiente;
                apuntador2 = apuntador2.siguiente;
            } else if (apuntador2 == null
                    || (apuntador1 != null && apuntador1.getExponente() < apuntador2.getExponente())) {
                n = new Nodo(apuntador1.getCoeficiente(), apuntador1.getExponente());
                apuntador1 = apuntador1.siguiente;
            } else {
                n = new Nodo(apuntador2.getCoeficiente(), apuntador2.getExponente());
                apuntador2 = apuntador2.siguiente;
            }

            if (n != null) {
                pR.agregar(n);
            }
        }

        return pR;
    }

    public static Polinomio restar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();

        Nodo apuntador1 = p1.getCabeza();
        Nodo apuntador2 = p2.getCabeza();

        while (apuntador1 != null || apuntador2 != null) {
            Nodo n = null;

            if (apuntador1 != null && apuntador2 != null && apuntador1.getExponente() == apuntador2.getExponente()) {
                if (apuntador1.getCoeficiente() - apuntador2.getCoeficiente() != 0) {
                    n = new Nodo(apuntador1.getCoeficiente() - apuntador2.getCoeficiente(), apuntador1.getExponente());
                }
                apuntador1 = apuntador1.siguiente;
                apuntador2 = apuntador2.siguiente;
            } else if (apuntador2 == null
                    || (apuntador1 != null && apuntador1.getExponente() < apuntador2.getExponente())) {
                n = new Nodo(apuntador1.getCoeficiente(), apuntador1.getExponente());
                apuntador1 = apuntador1.siguiente;
            } else {
                n = new Nodo(-apuntador2.getCoeficiente(), apuntador2.getExponente());
                apuntador2 = apuntador2.siguiente;
            }

            if (n != null) {
                pR.agregar(n);
            }
        }

        return pR;
    }

    public static Polinomio multiplicar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();

        Nodo apuntador1 = p1.getCabeza();
        while (apuntador1 != null) {

            Nodo apuntador2 = p2.getCabeza();
            while (apuntador2 != null) {
                Nodo n = new Nodo(apuntador1.getCoeficiente() * apuntador2.getCoeficiente(),
                        apuntador1.getExponente() + apuntador2.getExponente());
                pR.agregar(n);
                apuntador2 = apuntador2.siguiente;
            }
            apuntador1 = apuntador1.siguiente;
        }

        return pR;
    }

    public static Polinomio dividir(Polinomio p1, Polinomio p2) {
        Polinomio cociente = new Polinomio();
        Polinomio residuo = new Polinomio();
        residuo = p1; // El dividendo inicial es p1

        while (residuo.getCabeza() != null && residuo.getCabeza().getExponente() >= p2.getCabeza().getExponente()) {
            // Dividir el primer término del dividendo por el primer término del divisor
            double coeficienteCociente = residuo.getCabeza().getCoeficiente() / p2.getCabeza().getCoeficiente();
            int exponenteCociente = residuo.getCabeza().getExponente() - p2.getCabeza().getExponente();

            Nodo terminoCociente = new Nodo(coeficienteCociente, exponenteCociente);
            cociente.agregar(terminoCociente);

            // Crear un polinomio con el término cociente multiplicado por el divisor
            Polinomio terminosMultiplicados = new Polinomio();
            Nodo temp = p2.getCabeza();
            while (temp != null) {
                Nodo nuevoTermino = new Nodo(temp.getCoeficiente() * coeficienteCociente,
                        temp.getExponente() + exponenteCociente);
                terminosMultiplicados.agregar(nuevoTermino);
                temp = temp.siguiente;
            }

            // Restar los términos multiplicados del residuo
            residuo = Polinomio.restar(residuo, terminosMultiplicados);
        }

        return cociente; // Retorna el cociente de la división
    }

}
