/**
 * 
 */
package Chapter01.Client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author HomeUser
 *
 */
public class CalculatorClient {
	CalculatorAgent calcAgent;
	JTextField operand1 = new JTextField(4);
	JTextField operator = new JTextField(2);
	JTextField operand2 = new JTextField(4);
	JButton equal = new JButton("=");
	JTextField result = new JTextField(6);
	JButton clear = new JButton("Clear");
	
	public CalculatorClient() {
		try {
			calcAgent = new CalculatorAgent("localhost", 8888);
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		JFrame mainFrame = new JFrame("CalculatorClient");
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				calcAgent.close();
				System.exit(0);
			}
		});
		
		equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compute();
			}
		});
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		
		Container contentPane = mainFrame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		contentPane.add(Box.createVerticalGlue());
		contentPane.add(createInputForm());
		contentPane.add(createToolBar());
		contentPane.add(Box.createVerticalGlue());
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	private void compute() {
		double a = Double.parseDouble(operand1.getText()); 
		double b = Double.parseDouble(operand2.getText());
		double r = 0;
		
		try {
			r = calcAgent.compute(operator.getText(), a, b);
			result.setText(Double.toString(r));
			
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void clearForm() {
		this.operand1.setText("");
		this.operand2.setText("");
		this.result.setText("");
	}
	
	
	private Box createInputForm() {
		Box box = Box.createHorizontalBox();
		box.setMaximumSize(new Dimension(300, 30));
		box.setAlignmentY(Box.CENTER_ALIGNMENT);
		box.add(operand1);
		box.add(operator);
		box.add(operand2);
		box.add(equal);
		box.add(result);
		return box;
	}
	
	private Box createToolBar() {
		Box box = Box.createHorizontalBox();
		box.add(clear);
		return box;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CalculatorClient();
	}

}
