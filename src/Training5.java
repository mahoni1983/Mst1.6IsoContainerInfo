import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;


public class Training5 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Training5 frame = new Training5();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Training5() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[33px][28px][89px]", "[416px][]"));
		
		textField = new JTextField();
		contentPane.add(textField, "cell 0 0,alignx center,aligny center");
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 1 0,alignx center,aligny center");
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, "cell 2 0,alignx left,aligny center");
		
		JButton btnNewButton_1 = new JButton("New button");
		contentPane.add(btnNewButton_1, "cell 0 1");
	}

}
