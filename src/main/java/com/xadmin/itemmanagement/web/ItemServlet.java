package com.xadmin.itemmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.itemmanagement.bean.Item;
import com.xadmin.itemmanagement.dao.ItemDao;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ItemDao itemDao;   

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		itemDao = new ItemDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			case "/new" : 
				showNewForm(request, response);
				break;
			case "/insert":
				try {
					insertItem(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
				break;
			case "/delete" :
				try {
					deleteItem(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/edit" :
				try {
					showEditForm(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "/update" :
				try {
					updateItem(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			
			default:
				try {
					listItem(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		RequestDispatcher dispacher = request.getRequestDispatcher("item-form.jsp");
		dispacher.forward(request, response);
	}

//	Insert Item
	private void insertItem (HttpServletRequest request, HttpServletResponse response ) throws SQLException, IOException {
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String type = request.getParameter("type");
		Item newItem = new Item(name, price, type);
		System.out.println(newItem);
		itemDao.insertItem(newItem);
		response.sendRedirect("list");
	}
	
//	Delete Item
	private void deleteItem (HttpServletRequest request, HttpServletResponse response ) throws SQLException, IOException {
		int itemCode = Integer.parseInt(request.getParameter("itemCode"));
		try {
			itemDao.deleteItem(itemCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}
	
//	Edit Item
	private void showEditForm (HttpServletRequest request, HttpServletResponse response ) throws SQLException, IOException {
		int itemCode = Integer.parseInt(request.getParameter("itemCode"));
		Item existItem;
		try {
			existItem = itemDao.selectItem(itemCode);
			RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
			request.setAttribute("item", existItem);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	Update item
	private void updateItem (HttpServletRequest request, HttpServletResponse response ) throws SQLException, IOException {
		int itemCode = Integer.parseInt(request.getParameter("itemCode"));
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String type = request.getParameter("type");
		
		Item item = new Item(itemCode, name, price, type);
		itemDao.updateItem(item);
		response.sendRedirect("list");
	}
	
//	Default 
	private void listItem (HttpServletRequest request, HttpServletResponse response ) throws SQLException, IOException {
		try {
			List<Item> listItem = itemDao.selectAllItems();
			request.setAttribute("listItem", listItem);
			RequestDispatcher dispatcher = request.getRequestDispatcher("item-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}























