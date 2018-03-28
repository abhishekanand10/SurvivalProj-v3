package com.sapient.pm.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;

import com.sapient.pm.model.StockOrder;
import com.sapient.pm.repository.StockTable;
import com.sun.org.apache.xpath.internal.operations.And;

/**
 * Servlet implementation class PageControl
 */
@WebServlet(urlPatterns = { "/trigger3" }) 
public class servletfromlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	StockTable implementation;
	List<StockOrder> stockList = new ArrayList<>();

	public servletfromlogin() throws SQLException {
		super();
		implementation = new StockTable();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String oone=(String)request.getParameter("user");
		String ttwo=(String)request.getParameter("password");
		//System.out.println(oone);

		
		if(oone.equals("pmanager") && ttwo.equals("abc123")  ) {
			
			
			RequestDispatcher rd = request.getRequestDispatcher("load-portfolio");
			rd.forward(request,response);
			
	
		
	
		}
		
		
		if(oone.equals("trader") && ttwo.equals("xyz123") ) {
			
			List<StockOrder> stockList = new ArrayList<StockOrder>();
			try {
				ResultSet result = implementation.load(4);
				while (result.next()) {
					StockOrder st1 = new StockOrder();

					st1.setTid(result.getInt(2));
					st1.setSymbol(result.getString(3));
					st1.setQuantity(result.getInt(4));
					st1.setPrice(result.getDouble(5));
					st1.setStopLoss(result.getDouble(6));
					st1.setTargetPrice(result.getDouble(7));
					stockList.add(st1);

				}
//				request.setAttribute("stockList", stockList);

				request.getRequestDispatcher("TraderLandingPage.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			
		}
		else 
		{
			
		}
			
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
