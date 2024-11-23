import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmPolinomios extends JFrame {

    private JButton btnAgregar;
    private JButton btnCalcular;
    private JButton btnLimpiar;
    private JComboBox cmbOperacion;
    private JComboBox cmbPolinomio;
    private JLabel lblCoeficiente;
    private JLabel lblX;
    private JLabel lblExponente;
    private JLabel lblPolinomio1;
    private JLabel lblPolinomio2;
    private JLabel lblPolinomioR;
    private JLabel lblPolinomioRD;
    private JTextField txtCoeficiente;
    private JTextField txtExponente;

    public FrmPolinomios() {
        lblCoeficiente = new JLabel();
        txtCoeficiente = new JTextField();
        lblX = new JLabel();
        lblExponente = new JLabel();
        txtExponente = new JTextField();
        cmbPolinomio = new JComboBox();
        btnAgregar = new JButton();
        btnLimpiar = new JButton();
        lblPolinomio1 = new JLabel();
        lblPolinomio2 = new JLabel();
        cmbOperacion = new JComboBox();
        lblPolinomioR = new JLabel();
        lblPolinomioRD = new JLabel();
        btnCalcular = new JButton();

        setSize(600, 450);
        setTitle("Polinomios");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblCoeficiente.setText("Coeficiente:");
        lblCoeficiente.setBounds(10, 60, 80, 25);
        getContentPane().add(lblCoeficiente);

        txtCoeficiente.setBounds(80, 60, 100, 25);
        getContentPane().add(txtCoeficiente);

        lblX.setFont(new java.awt.Font("Times New Roman", 2, 48)); // NOI18N
        lblX.setText("x");
        lblX.setBounds(180, 40, 50, 40);
        getContentPane().add(lblX);

        lblExponente.setText("Exponente");
        lblExponente.setBounds(130, 20, 80, 25);
        getContentPane().add(lblExponente);

        txtExponente.setBounds(210, 20, 100, 25);
        getContentPane().add(txtExponente);

        cmbPolinomio.setModel(
                new DefaultComboBoxModel(new String[] { "Polinomio 1", "Polinomio 2" }));
        cmbPolinomio.setBounds(300, 50, 100, 25);
        getContentPane().add(cmbPolinomio);

        btnAgregar.setText("Agregar");
        btnAgregar.setBounds(410, 50, 80, 25);
        getContentPane().add(btnAgregar);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAgregarClick(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBounds(500, 50, 80, 25);
        getContentPane().add(btnLimpiar);

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLimpiarClick(evt);
            }
        });

        lblPolinomio1.setBackground(new java.awt.Color(0, 153, 204));
        lblPolinomio1.setOpaque(true);
        lblPolinomio1.setBounds(0, 90, 600, 50);
        getContentPane().add(lblPolinomio1);

        lblPolinomio2.setBackground(new java.awt.Color(0, 153, 204));
        lblPolinomio2.setOpaque(true);
        lblPolinomio2.setBounds(0, 150, 600, 50);
        getContentPane().add(lblPolinomio2);

        btnCalcular.setText("Calcular");
        btnCalcular.setBounds(10, 210, 100, 25);
        getContentPane().add(btnCalcular);

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCalcularClick(evt);
            }
        });

        cmbOperacion.setModel(
                new DefaultComboBoxModel(new String[] { "Suma", "Resta", "Multiplicacion", "Division", "Derivada" }));
        cmbOperacion.setBounds(120, 210, 100, 25);
        getContentPane().add(cmbOperacion);

        lblPolinomioR.setBackground(new java.awt.Color(255, 204, 153));
        lblPolinomioR.setOpaque(true);
        lblPolinomioR.setBounds(0, 250, 600, 50);
        getContentPane().add(lblPolinomioR);

        lblPolinomioR.setBackground(new java.awt.Color(255, 204, 153));
        lblPolinomioR.setOpaque(true);
        lblPolinomioR.setBounds(0, 250, 600, 50);
        getContentPane().add(lblPolinomioR);

        lblPolinomioRD.setBackground(new java.awt.Color(255, 153, 153));
        lblPolinomioRD.setOpaque(true);
        lblPolinomioRD.setBounds(0, 310, 600, 50);
        getContentPane().add(lblPolinomioRD);
    }

    Polinomio p1 = new Polinomio();
    Polinomio p2 = new Polinomio();

    private void btnAgregarClick(ActionEvent evt) {
        double coef = Double.parseDouble(txtCoeficiente.getText());
        int expo = Integer.parseInt(txtExponente.getText());

        switch (cmbPolinomio.getSelectedIndex()) {
            case 0:
                p1.agregar(new Nodo(coef, expo));
                p1.mostrar(lblPolinomio1);
                break;
            case 1:
                p2.agregar(new Nodo(coef, expo));
                p2.mostrar(lblPolinomio2);
                break;
        }
    }

    private void btnCalcularClick(ActionEvent evt) {
        Polinomio pR;
        switch (cmbOperacion.getSelectedIndex()) {
            case 0:
                // sumar
                pR = Polinomio.sumar(p1, p2);
                pR.mostrar(lblPolinomioR);
                break;
            case 1:
                // restar
                pR = Polinomio.restar(p1, p2);
                pR.mostrar(lblPolinomioR);
                break;
            case 2:
                // multiplicar
                pR = Polinomio.multiplicar(p1, p2);
                pR.mostrar(lblPolinomioR);
                break;
            case 3:
                // dividir
                pR = Polinomio.dividir(p1, p2);
                pR.mostrar(lblPolinomioR);
                break;

            case 4:
                // derivar
                switch (cmbPolinomio.getSelectedIndex()) {
                    case 0:
                        pR = p1.derivar();
                        pR.mostrar(lblPolinomioR);
                        break;
                    case 1:
                        pR = p2.derivar();
                        pR.mostrar(lblPolinomioR);
                        break;
                }
                break;

        }
    }

    private void btnLimpiarClick(ActionEvent evt) {
        if (cmbPolinomio.getSelectedIndex() == 0) {
            p1.limpiar();
            p1.mostrar(lblPolinomio1);
        } else {
            p2.limpiar();
            p2.mostrar(lblPolinomio2);
        }
    }

}
