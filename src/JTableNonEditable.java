import javax.swing.JTable;


public class JTableNonEditable extends JTable{

	public JTableNonEditable(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }
	
	@Override
	public boolean isCellEditable(int a, int b)
	{
		return false;
		
	}
}
