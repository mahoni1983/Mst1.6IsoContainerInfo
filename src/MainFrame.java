import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class MainFrame {
	final String PROGRAM_NAME = "MST1.6 ISO container info v0.93";
	final String NOT_INITIALISED_STRING = "-";
	final String NOT_APPLICABLE_STRING = "N/A";
	final String ABOUT_PROGRAM_MESSAGE = PROGRAM_NAME + "\n" 
			+ "The program is to work with T-Containers Monitoring Station (MST1.6) of Ignalina Nuclear Power Plant. \n";
//			+ "It gets and shows short information about current nuclide vector and measuring nuclides. \n"
//			+ "The program gets paths for MST files from \"Nuclide vector information of MST programs.ini\" in the same folder.\n"
//			+ "Lines for paths must start with:\n"
//			+ "LSC.ini location: \n"
//			+ "MST_iniparam.mdb location: \n"
//			+ "LSC_.mdb location: \n"
//			+ "Example:\n"
//			+ "LSC.ini location: c:\\Program Files\\LSC\\LSC.ini\n"
//			+ "Case not sensitive. All other lines are ignored. The order is not important."
//			+ "\n"
//			+ "The program reads the files to get information about nuclide vector and measuring nuclides then shows in appropriate fields.\n"
//			+ "The files are read on the original paths again upon refresh button.\n"
//			+ "\n"
//			+ "Jevgenij Kariagin \n"
//			+ "2017";
	final String STRING_CONFIRM_TO_REMOVE_CURRENT_T_CONTAINER = "Remove current T-container?";
	JFrame frame;
	private Mst1_6IsoContainerInfoProgram mst1_6IsoContainerInfoProgram;

	private JPanel mainPanel;
	TitledBorder tbTxtIsoName;
	public JTextField txtIsoName;
	JTableNonEditable jtableIsoNotInit;
	public String[] columnNamesIsoCont;
	public String[][] arrayIsoContInfo;
	private JScrollPane scrollPaneIsoContInfo;
	private String[] columnNamesLoadedTCont;
	private String[][] arrayLoadedTCont;
	JTableNonEditable jtableLoadedTContNotInit;
	private JScrollPane scrollPaneLoadedTCont;
	private String[] columnNamesCurrentTCont;
	private String[][] arrayCurrentTCont;
	JTableNonEditable jtableCurrentTContNotInit;
	private JScrollPane scrollPaneCurrentTCont;
	private JButton btnRemoveCurrentTCont;
	private JTextField txtLastReresh;
	private JPanel panelForButtons;
	private JButton btnRefresh;
	private JButton btnAbout;
	float totalMass;
	float totalDensity;
	float totalAlphaActivity;
	float totalBetaActivity;
	float totalGammaActivity;

	
	public MainFrame(Mst1_6IsoContainerInfoProgram mst1_6IsoContainerInfoProgram)
	{
		this.mst1_6IsoContainerInfoProgram = mst1_6IsoContainerInfoProgram;
	}
	
	public void showFrame()
	{
		System.out.println("Building main window.");
		frame = new JFrame(PROGRAM_NAME);
		frame.setBounds(100, 100, 457, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		txtIsoName = new JTextField(NOT_INITIALISED_STRING);
	//	txtIsoName.setColumns(12);
		txtIsoName.setHorizontalAlignment(SwingConstants.CENTER);
		tbTxtIsoName= new TitledBorder("Current ISO container");
		tbTxtIsoName.setTitleJustification(TitledBorder.CENTER);
		txtIsoName.setBorder(tbTxtIsoName);
		txtIsoName.setEditable(false);
		mainPanel.add(txtIsoName);
		
		
		
		columnNamesIsoCont = new String [] { "Registered", "Total mass, kg", "Density, g/cm3", "Alpha activiy", "Beta activiy", "Gamma activiy" };
		arrayIsoContInfo = new String [1][columnNamesIsoCont.length];
//		System.out.println(columnNamesIsoCont.length);
		int i = 0;
		while (i < columnNamesIsoCont.length)
		{
			arrayIsoContInfo[0][i++] = NOT_INITIALISED_STRING;
		} 
		jtableIsoNotInit = new JTableNonEditable(arrayIsoContInfo, columnNamesIsoCont);
		scrollPaneIsoContInfo= new JScrollPane(jtableIsoNotInit);
		scrollPaneIsoContInfo.setViewportView(jtableIsoNotInit);
		mainPanel.add(scrollPaneIsoContInfo);
	//	scrollPaneIsoContInfo.setVisible(false);
		jtableIsoNotInit.setVisible(false);
		
		JSeparator jseparatorIso = new JSeparator();
		mainPanel.add(jseparatorIso);
		
		columnNamesLoadedTCont = new String [] { 
				"Number", "Loaded", "Mass, kg", "DR mean, uSv/h", "Alpha activity, Bq/kg", "Beta activity, Bq/kg",
				"Gamma activity, Bq/kg", "Density, g/cm3", "Operator" };
		arrayLoadedTCont = new String [1][columnNamesLoadedTCont.length];
//		System.out.println(columnNamesLoadedTCont.length);
		i = 0;
		while (i < columnNamesLoadedTCont.length)
			arrayLoadedTCont[0][i++] = NOT_INITIALISED_STRING;
		jtableLoadedTContNotInit = new JTableNonEditable(arrayLoadedTCont, columnNamesLoadedTCont);
		jtableLoadedTContNotInit.setVisible(false);
		
		scrollPaneLoadedTCont= new JScrollPane(jtableLoadedTContNotInit);
		scrollPaneLoadedTCont.setViewportView(jtableLoadedTContNotInit);
		TitledBorder tbLoadedTCont = new TitledBorder("Loaded T-containers");
		tbLoadedTCont.setTitleJustification(TitledBorder.CENTER);
		scrollPaneLoadedTCont.setBorder(tbLoadedTCont);
		mainPanel.add(scrollPaneLoadedTCont);
		
		JSeparator jseparatorLoadedTCont = new JSeparator();
		mainPanel.add(jseparatorLoadedTCont);
		
		columnNamesCurrentTCont = new String [] { "Date, time", "Mass, kg", "Density, g/cm3",
				"Alpha activity, Bq/kg", "Beta activity, Bq/kg",
				"Gamma activity, Bq/kg", "Operator" };
		arrayCurrentTCont = new String [1][columnNamesCurrentTCont.length];
		i = 0;
		while (i < columnNamesCurrentTCont.length)
			arrayCurrentTCont[0][i++] = NOT_INITIALISED_STRING; 
		jtableCurrentTContNotInit = new JTableNonEditable(arrayCurrentTCont, columnNamesCurrentTCont);
		jtableCurrentTContNotInit.setVisible(false);
		scrollPaneCurrentTCont= new JScrollPane(jtableCurrentTContNotInit);
		scrollPaneCurrentTCont.setViewportView(jtableCurrentTContNotInit);
		
		TitledBorder tbCurrentTCont = new TitledBorder("Current T-containers");
		tbCurrentTCont.setTitleJustification(TitledBorder.CENTER);
		
		scrollPaneCurrentTCont.setBorder(tbCurrentTCont);
		mainPanel.add(scrollPaneCurrentTCont);	
		
		
		btnRemoveCurrentTCont = new JButton("Remove current T-container");
		btnRemoveCurrentTCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Remove current T-container button clicked");
				//showMessage("Nuclide vector information for MST programs refresh", "Refreshing data");
				int intBtnRemoveCurrentTContResult = JOptionPane.YES_NO_OPTION;
				int RemoveCurrentTContResult = JOptionPane.showConfirmDialog 
						(null, STRING_CONFIRM_TO_REMOVE_CURRENT_T_CONTAINER,
								"Removing current T-container",intBtnRemoveCurrentTContResult);
				if(RemoveCurrentTContResult == JOptionPane.YES_OPTION){
				  // Saving code here
					System.out.println("Confirmed to remove current T-Container");
					mst1_6IsoContainerInfoProgram.removeCurrentTContainer();
				}
				
			}
		});
		btnRemoveCurrentTCont.setEnabled(true);	// to switch it off put false
		mainPanel.add(btnRemoveCurrentTCont);
		
		txtLastReresh = new JTextField(NOT_INITIALISED_STRING);
	//	txtIsoName.setColumns(12);
		txtLastReresh.setHorizontalAlignment(SwingConstants.CENTER);
		
		TitledBorder tbLastRefresh = new TitledBorder("Last refreshed");
		tbLastRefresh.setTitleJustification(TitledBorder.CENTER);
		
		txtLastReresh.setBorder(tbLastRefresh);
		txtLastReresh.setEditable(false);
		mainPanel.add(txtLastReresh);
		
		
		panelForButtons = new JPanel();
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//showMessage("Nuclide vector information for MST programs refresh", "Refreshing data");
				mst1_6IsoContainerInfoProgram.refreshData();
			}
		});
		panelForButtons.add(btnRefresh);
		
		btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//showMessage("Nuclide vector information for MST programs refresh", "Refreshing data");
				System.out.println("MainFrame: About button clicked");
				showMessage("About program", ABOUT_PROGRAM_MESSAGE);
			}
		});
		panelForButtons.add(btnAbout);
		mainPanel.add(panelForButtons);
		
		frame.setVisible(true);
		System.out.println("Main window is built");
		
/*		i=0;
		txtLastReresh.setText("Update check");
		while (i < columnNamesCurrentTCont.length)
			arrayCurrentTCont[0][i++] = "Update check"; */

		
	}
	
	
	public void showMessage(String title, String messageText)
	{
		System.out.println("MainFrame: showMessage: " + title + " " + messageText);
		if (title == "")
			JOptionPane.showMessageDialog(frame, messageText);
		else
			JOptionPane.showMessageDialog(frame, messageText, title, JOptionPane.PLAIN_MESSAGE);
	}
	
	

	public void setALLoadedTConainers(ArrayList<TContainer> alLoadedTConainers) {
		System.out.println("MainFrame: setALLoadedTConainers");
		String [] [] arrayLoadedTContTemporary = new String[alLoadedTConainers.size()][columnNamesLoadedTCont.length];
		int i =0;
		float totalMass=0;
		float totalAlphaActivity =0;
		float totalBetaActivity =0;
		float totalGammaActivity =0;
		for (TContainer tCont : alLoadedTConainers)
		{
			// LoadedTContainers columns:
			//	"Number", "Loaded", "Mass, kg", "DR mean, uSv/h", "Alpha activity, Bq/kg", "Beta activity, Bq/kg",
			//  "Gamma activity, Bq/kg", "Density, g/cm3", "Operator"
			arrayLoadedTContTemporary[i][0]=Integer.toString(i); 
			arrayLoadedTContTemporary[i][1]=tCont.EndDateTime_MST1_6;
			arrayLoadedTContTemporary[i][2]=tCont.Mass_MST1_6;
			arrayLoadedTContTemporary[i][3]=tCont.DRSMean_MST1_6;
			arrayLoadedTContTemporary[i][4]=tCont.alphaAct_MST1_6;
			arrayLoadedTContTemporary[i][5]=tCont.betaAct_MST1_6;
			arrayLoadedTContTemporary[i][6]=tCont.gammaAct_MST1_6;
			arrayLoadedTContTemporary[i][7]=Float.toString( Float.parseFloat(tCont.Mass_MST1_6)/940);
			arrayLoadedTContTemporary[i][8]=tCont.Operator_MST1_6;
			i++;
			totalMass =+ (Float.parseFloat(tCont.Mass_MST1_6));
			totalAlphaActivity =+ Float.parseFloat(tCont.alphaAct_MST1_6);
			totalBetaActivity =+ Float.parseFloat(tCont.betaAct_MST1_6);
			totalGammaActivity =+ Float.parseFloat(tCont.gammaAct_MST1_6);
		}
		this.totalMass = totalMass;
		this.totalDensity = totalMass / (i * 940);
		this.totalAlphaActivity = totalAlphaActivity;
		this.totalBetaActivity = totalBetaActivity;
		this.totalGammaActivity = totalGammaActivity;
		arrayLoadedTCont = arrayLoadedTContTemporary;
		
		arrayIsoContInfo[0][1] = Float.toString(totalMass);
		arrayIsoContInfo[0][2] = Float.toString(totalDensity);
		arrayIsoContInfo[0][3] = Float.toString(totalAlphaActivity);
		arrayIsoContInfo[0][4] = Float.toString(totalBetaActivity);
		arrayIsoContInfo[0][5] = Float.toString(totalGammaActivity);
		// ISO container columns:
		// "Registered", "Total mass, kg", "Density, g/cm3", "Alpha activiy", "Beta activiy", "Gamma activiy"
	}

	public void setFieldsInvisible() {
		jtableIsoNotInit.setVisible(false);
		jtableLoadedTContNotInit.setVisible(false);
		jtableCurrentTContNotInit.setVisible(false);
		btnRemoveCurrentTCont.setEnabled(false);
	}

	public void setCurrentTContainer(TContainer currentTContainer) {
		//ColumnNames: { "Date, time", "Mass, kg", "Density, g/cm3",
		//"Alpha activity, Bq/kg", "Beta activity, Bq/kg",
		//"Gamma activity, Bq/kg", "Operator"  }
		//arrayCurrentTCont = new String[1][arrayCurrentTCont.length]{};
		arrayCurrentTCont = new String[][]
				{{currentTContainer.EndDateTime_MST1_6, 
					currentTContainer.Mass_MST1_6,
					Float.toString(Float.parseFloat((currentTContainer.Mass_MST1_6))/940),	// density
					currentTContainer.alphaAct_MST1_6, 
					currentTContainer.betaAct_MST1_6, 
					currentTContainer.gammaAct_MST1_6,
					currentTContainer.Operator_MST1_6
				}};
		jtableCurrentTContNotInit = new JTableNonEditable(arrayCurrentTCont, columnNamesCurrentTCont);
		jtableCurrentTContNotInit.setVisible(true);
		btnRemoveCurrentTCont.setVisible(true);
	}
	
}
