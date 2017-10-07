import java.util.ArrayList;

public class Mst1_6IsoContainerInfoProgram {

	public static void main(String[] args) {
		Mst1_6IsoContainerInfoProgram mst1_6IsoContainerInfoProgram = new Mst1_6IsoContainerInfoProgram();
		mst1_6IsoContainerInfoProgram.start();

	}

	String isoName;
	ArrayList<TContainer> alLoadedTConainers;
	TContainer currentTContainer;
	MainFrame mainFrame;
	Mst1_6Sql mst1_6Sql;

	private void start() {
		mainFrame = new MainFrame(this);
		mainFrame.showFrame();
	}

	public void refreshData() {
		// TODO Auto-generated method stub
		System.out.println("Mst1_6IsoContainerInfoProgram: refresh data");
		mainFrame.setFieldsInvisible();
		mst1_6Sql = new Mst1_6Sql();
		if (MsSqlConnection.getInstance().getConnection() != null) {
			isoName = null;
		//	mainFrame.txtIsoName
		//			.setText("New name before connecting to a server");
			// isoName = "";
			try {
				isoName = mst1_6Sql.getIsoName();
				if (isoName != null) {	// ISO container is registered
					mainFrame.txtIsoName.setText(isoName);
					mainFrame.jtableIsoNotInit.setVisible(true);
					mainFrame.jtableLoadedTContNotInit.setVisible(true);
					
					alLoadedTConainers = null;
					try {
						alLoadedTConainers = mst1_6Sql.getALLoadedTContainers();
						mainFrame.setALLoadedTConainers(alLoadedTConainers);
					} catch (Exception e) {
						// e.printStackTrace();
						System.out.println(e.getMessage());
					}

					currentTContainer = new TContainer();
					currentTContainer = mst1_6Sql.getCurrentTContainer();
					if (currentTContainer == null)
					{
						
							mainFrame.setCurrentTContainer(currentTContainer);
					}
				
				} else				// ISO container is not registered
				{
					mainFrame.txtIsoName.setText("ISO container not registered");
					alLoadedTConainers = null;
				//	alLoadedTConainers.add(new TContainer(mainFrame.NOT_APPLICABLE_STRING));
				// ƒќбавить установку значений не применимо во все строки mainFrame (добавив новую или новые методы)
					
					
					
					
					
					
				}
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
				mainFrame.txtIsoName
						.setText("Error getting ISO name from server");
			}

		} else
		{
			System.out.println("Connection to a server failed");
		mainFrame.showMessage("Error", "Can't connect to the SQL-server.");	
		}

		System.out.println("Refresh data finished");
	}

	public void removeCurrentTContainer() {
		System.out.println("removeCurrentTContainer()");
		int newContainerID = mst1_6Sql.insertNewContainerToRemoveCurrentTContainer();
		if (newContainerID != -1)
		{
			if (mst1_6Sql.renameCurrentTContainer(newContainerID))
			{
				mainFrame.showMessage("Current T-container removed.", 
						"Current T-container has been removed.");
				refreshData();
			}
		}
		System.out.println("removeCurrentTConainer() finished");
	}

}
