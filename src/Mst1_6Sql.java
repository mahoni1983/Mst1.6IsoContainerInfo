import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mst1_6Sql {
	// Название текущего ISO контейнера
	final String QUERY_SELECT_ISO_CONTAINER_NAME = "SELECT IDNo_InputCont FROM V_TST3_4;";

	// Содержание ISO контейнера:
	final String QUERY_SELECT_LOADED_T_CONTAINERS = "SELECT EndDateTime_MST1_6, Mass_MST1_6, DRSMean_MST1_6, DRSMax_MST1_6,  "
			+ "alphaAct_MST1_6, betaAct_MST1_6, gammaAct_MST1_6, Operator_MST1_6 "
			+ "FROM T_MST1_6 " + "WHERE InputContID_MST1_6 =  " // название
																// текущего ISO
																// контейнера
			+ "(SELECT IDNo_InputCont FROM V_TST3_4)";

	// Текущий не заполненный т-контейнер
	final String QUERY_SELECT_CURRENT_T_CONTAINER = "SELECT EndDateTime_MST1_6, Mass_MST1_6, DRSMean_MST1_6,DRSMax_MST1_6,  "
			+ "alphaAct_MST1_6, betaAct_MST1_6, gammaAct_MST1_6, Operator_MST1_6 "
			+ "FROM T_MST1_6 " + "WHERE InputContID_MST1_6 = 'BULK'";

	// Добавить новый контейнер, чтобы переименовать текущий Т-контейнер
	final String QUERY_INSERT_NEW_CONTAINER =
	"insert into T_InputCont(IDNo_InputCont) values('MST1.6_BULK_DELETED: ' + cast(CURRENT_TIMESTAMP AS NVARCHAR(19)))";
	
	
	// Переименовать (удалить) текущий т-контейнер. Не работает без такого же названия в таблице T_InputCont
	final String QUERY_RENAME_CURRENT_T_CONTAINER = "UPDATE T_MST1_6 " +
			"SET InputContID_MST1_6 = " +
			"(select IDNo_InputCont from T_InputCont where ID_InputCont = ?) " +
			"WHERE " +
			"InputContID_MST1_6 = 'BULK'";

	public String getIsoName() {
		System.out.println("Mst1_6Sql: getIsoName()");
		String isoName = null;
		try {
			// String query =
			// "insert into user(name, email, phone) values(?, ?, ?);";
			PreparedStatement ps = MsSqlConnection.getInstance()
					.getConnection()
					.prepareStatement(QUERY_SELECT_ISO_CONTAINER_NAME);
			// ps.setString(1, user.name);
			// ps.setString(2, user.email);
			// ps.setString(3, user.phone);

			ps.execute();
			ResultSet rs = ps.getResultSet();
			
			if (rs.next()) 
				isoName = rs.getString("IDNo_InputCont");
			;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Mst1_6Sql: getIsoName error");
		}

		return isoName;
	}

	public ArrayList<TContainer> getALLoadedTContainers() {
		System.out.println("Mst1_6Sql: getALLoadedTContainers()");
		ArrayList<TContainer> alLoadedTConainers = new ArrayList<TContainer>();
		try {
			// String query =
			// "insert into user(name, email, phone) values(?, ?, ?);";
			PreparedStatement ps = MsSqlConnection.getInstance()
					.getConnection()
					.prepareStatement(QUERY_SELECT_LOADED_T_CONTAINERS);
			// ps.setString(1, user.name);
			// ps.setString(2, user.email);
			// ps.setString(3, user.phone);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			TContainer tCont;
			if (rs.next())
			{
				tCont = new TContainer();
				tCont.EndDateTime_MST1_6 = rs.getString("EndDateTime_MST1_6");
				tCont.Mass_MST1_6 = rs.getString("Mass_MST1_6");
				tCont.DRSMean_MST1_6 = rs.getString("DRSMean_MST1_6");
				tCont.DRSMax_MST1_6 = rs.getString("DRSMax_MST1_6");
				tCont.alphaAct_MST1_6 = rs.getString("alphaAct_MST1_6");
				tCont.betaAct_MST1_6 = rs.getString("betaAct_MST1_6");
				tCont.gammaAct_MST1_6 = rs.getString("gammaAct_MST1_6");
				tCont.Operator_MST1_6 = rs.getString("Operator_MST1_6");
				alLoadedTConainers.add(tCont);
			}
							
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Mst1_6Sql: getALLoadedTContainers error");
		}

		return alLoadedTConainers;
	}
	
	

	public TContainer getCurrentTContainer() {
		System.out.println("Mst1_6Sql: getCurrentTContainer()");
		TContainer currentTContainer = new TContainer();
		return currentTContainer;
	}

	
	public int insertNewContainerToRemoveCurrentTContainer() {
		System.out.println("Mst1_6Sql: insertNewContainerToRemoveCurrentTContainer()");
		//ArrayList<TContainer> alLoadedTConainers = new ArrayList<TContainer>();
		int newContainerID = -1;
		try {
			// String query =
			// "insert into user(name, email, phone) values(?, ?, ?);";
			System.out.println("Query: " + QUERY_INSERT_NEW_CONTAINER);
			PreparedStatement ps = MsSqlConnection.getInstance()
					.getConnection()
					.prepareStatement(QUERY_INSERT_NEW_CONTAINER);
			// ps.setString(1, user.name);
			// ps.setString(2, user.email);
			// ps.setString(3, user.phone);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();;
			if (rs.next())
			{
				newContainerID = rs.getInt(1);
				System.out.println("New container has been added. New container's id: " + newContainerID);
			}
							
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Mst1_6Sql: insertNewContainerToRemoveCurrentTContainer error");
		}

		return newContainerID;
	}
	


	public boolean renameCurrentTContainer(int newContainerID) {
		System.out.println("Mst1_6Sql: renameCurrentTContainer()");
		//ArrayList<TContainer> alLoadedTConainers = new ArrayList<TContainer>();
		try {
			// String query =
			// "insert into user(name, email, phone) values(?, ?, ?);";
			System.out.println("Query: " + QUERY_RENAME_CURRENT_T_CONTAINER);
			PreparedStatement ps = MsSqlConnection.getInstance()
					.getConnection()
					.prepareStatement(QUERY_RENAME_CURRENT_T_CONTAINER);
			 ps.setInt(1, newContainerID);
			// ps.setString(2, user.email);
			// ps.setString(3, user.phone);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();;
			if (rs.next())
			{
				System.out.println("renameCurrentTContainer.rs.getInt(1): " +rs.getInt(1));
				System.out.println("New container has been added. New container's id: " + newContainerID);
				return true;
			}
							
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Mst1_6Sql: renameCurrentTContainer error");
		}

		return false;
	}
	
	
	
	
	public boolean renameCurrentTConatiner() {
		System.out.println("Mst1_6Sql: renameCurrentTConatiner()");
		return false;

	}
	/*
	 * try { String query =
	 * "insert into user(name, email, phone) values(?, ?, ?);";
	 * PreparedStatement ps =
	 * DBase.getInstance().getConnection().prepareStatement(query, 1);
	 * ps.setString(1, user.name); ps.setString(2, user.email); ps.setString(3,
	 * user.phone); ps.executeUpdate(); ResultSet rs = ps.getGeneratedKeys(); if
	 * (rs.next()) user.user_id = rs.getInt(1); return user.user_id; } catch
	 * (SQLException e) { System.out.println(e.getMessage()); return -1; }
	 */
}
