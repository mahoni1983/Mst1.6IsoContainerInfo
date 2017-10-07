import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;


public class Training2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Training2 frame = new Training2();
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
	public Training2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Current ISO-container", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNorth.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(20);
		
		JSeparator separator = new JSeparator();
		panel_3.add(separator);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"5000", "2000", "1000", "1.3"},
				{null, null, null, null},
			},
			new String[] {
				"Total mass", "Total alpha activity", "Total beta activity", "Total density"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		panelNorth.add(table);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(20, 20));
		
		JLabel lblNewLabel = new JLabel("Loaded T-containers");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelCenter.add(lblNewLabel, BorderLayout.NORTH);
		
		table_1 = new JTable();
		table_1.setBorder(UIManager.getBorder("TitledBorder.border"));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "500", null, null},
				{"2", "568", null, null},
				{"3", null, null, null},
			},
			new String[] {
				"Number", "Mass", "Alpha activity", "Beta activity"
			}
		));
		panelCenter.add(table_1, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSouth.getLayout();
		flowLayout.setVgap(10);
		contentPane.add(panelSouth, BorderLayout.SOUTH);
	}

}
