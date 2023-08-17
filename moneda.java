package conversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.w3c.dom.Text;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JTextPane;
import java.awt.Window.Type;
import javax.swing.JTextArea;
import java.awt.Color;

public class moneda {

	private JFrame frmConvertidorDeMoneda;
	private JButton btn;
	private JComboBox cmb;
	private JLabel lbl;
	
	public enum Moneda{
		pesos_dolar,
		pesos_euro,
		pesos_libra,
		dolar_pesos,
		euro_pesos,
		libra_pesos,
	}
	
	public double dolar = 16.75;
	public double euro = 18.61;
	public double libra = 21.64;
	
	public double valorInput = 0.00;
	private JTextComponent txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					moneda window = new moneda();
					window.frmConvertidorDeMoneda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public moneda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConvertidorDeMoneda = new JFrame();
		frmConvertidorDeMoneda.setBackground(new Color(255, 255, 255));
		frmConvertidorDeMoneda.getContentPane().setBackground(new Color(0, 128, 192));
		frmConvertidorDeMoneda.setTitle("Convertidor de moneda");
		frmConvertidorDeMoneda.setBounds(100, 100, 450, 300);
		frmConvertidorDeMoneda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConvertidorDeMoneda.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txt.setBounds(92, 65, 96, 40);
		frmConvertidorDeMoneda.getContentPane().add(txt);
		((JTextField) txt).setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cmb.setBounds(58, 169, 130, 32);
		frmConvertidorDeMoneda.getContentPane().add(cmb);
		
		
		//evento boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn.setBounds(231, 169, 137, 32);
		frmConvertidorDeMoneda.getContentPane().add(btn);
		
		lbl = new JLabel("0.00");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl.setBounds(231, 64, 108, 40);
		frmConvertidorDeMoneda.getContentPane().add(lbl);
		
		JTextArea txtrCristianEscamilla = new JTextArea();
		txtrCristianEscamilla.setText("Cristian Escamilla");
		txtrCristianEscamilla.setBounds(264, 231, 162, 22);
		frmConvertidorDeMoneda.getContentPane().add(txtrCristianEscamilla);
	}
	
	public void Convertir() {
		if (Validar(txt.getText())) {
			Moneda moneda = (Moneda)cmb.getSelectedItem();	
			
			switch (moneda) {
			
			case pesos_dolar:
				PesosAMoneda(dolar);
				break;
			case pesos_euro:
				PesosAMoneda(euro);
				break;
			case pesos_libra:
				PesosAMoneda(libra);
				break;
			case dolar_pesos:
				PesosAMoneda(dolar);
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected values: " + moneda);	
		}

		
		}
	}
	
	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
		
	}
	
	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("Solamente numeros !!");
			return false;
		}
	}
}
