package com.xadmin.itemmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.itemmanagement.bean.Item;

public class ItemDao {
	private String jdbcUrl = "jdbc:mysql://localhost:3306/kottulabs?useSSL=false";
	private String userName = "root";
	private String password = "";
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_ITEMS_SQL = "INSERT INTO items" + " ( name, price, type) VALUES " + " (?,?,?);";
	private static final String SELECT_ITEM_BY_ID= "select itemCode,name,price,type from items where itemCode=?";
	private static final String SELECT_ALL_ITEMS = "select * from items";
	private static final String DELETE_ITEMS_SQL = "delete from items where itemCode = ?;";
	private static final String UPDATE_ITEMS_SQL = "update kottulabs.items set name = ?,price = ?,type = ? where itemCode = ?;";
	
	public ItemDao() {
		
	}
	
	
	private Connection getConnection () {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,userName,password);
//			if (connection != null) {
//                System.out.println("Successfully connected to MySQL database test");
//            }
			
		}catch (SQLException e) {
			System.out.println("Unable to connected MySQL database test 1");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("Unable to connected MySQL database test 2");
			e.printStackTrace();
		}
		return connection;
	}
	
//	Add Items
	public void insertItem(Item item) throws SQLException {
		System.out.println(INSERT_ITEMS_SQL);
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMS_SQL)){
			
			preparedStatement.setString(1, item.getName());
			preparedStatement.setInt(2, item.getPrice());
			preparedStatement.setString(3, item.getType());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			printSQLException(e);
		}
	}


	
// Select Item using item code	
	public Item selectItem(int itemCode) {
		Item item = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID);) {
			
			preparedStatement.setInt(1, itemCode);
			System.out.println(preparedStatement);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				String name = result.getString("name");
				int price = result.getInt("price");
				String type = result.getString("type");
				item = new Item(itemCode, name, price, type);				
			}
			
		}catch (SQLException e) {
			printSQLException(e);
		}
		return item;
	}
	
	
//	Select all Item
	public List<Item> selectAllItems(){
		List<Item> items = new ArrayList<>();
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);) {
			
			System.out.println(preparedStatement);
			ResultSet result = preparedStatement.executeQuery();	
			while(result.next()) {
				int itemCode = result.getInt("itemCode");
				String name = result.getString("name");
				int price = result.getInt("price");
				String type = result.getString("type");
				items.add(new Item(itemCode, name, price, type));
			}
			
		}catch (SQLException e) {
			printSQLException(e);
		}
		return items;
	}
	
	
//	Update Item
	public boolean updateItem(Item item) throws SQLException {
		boolean itemUpdated = false;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_ITEMS_SQL);) {
			System.out.println("update Item: "+statement);
			statement.setString(1, item.getName());
			statement.setInt(2, item.getPrice());
			statement.setString(3, item.getType());
			statement.setInt(4, item.getItemCode());
			itemUpdated = statement.executeUpdate() > 0;
		}catch (SQLException e) {
			printSQLException(e);
		}
		return itemUpdated;
	}
	
	
//	Delete Item
	public boolean deleteItem(int itemCode) throws SQLException {
		boolean itemDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_ITEMS_SQL);) {
			
			statement.setInt(1, itemCode);
			itemDeleted = statement.executeUpdate() > 0;
		}
		return itemDeleted;
	}
	
	
	
	
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: "+ ((SQLException)e).getSQLState());
				System.err.println("Error Code : " + ((SQLException)e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
}
