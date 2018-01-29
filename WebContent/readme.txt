package controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class log
 */
@WebServlet("/log")
public class example extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public example() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("hello");
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("hello");
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "vasantha");
			Statement stmt = null;
			stmt = conn.createStatement();
		    stmt.execute("use library;");
	String field;
	
	field=request.getParameter("reference");
	if(field.equals("booksearch"))
	{	
	
		    
		    
		String txt;
		int linect=0;
		String Isbn13;
		String Booktitle;
		String authorname;
  
      txt=request.getParameter("search");
     
    		 ResultSet rs = stmt.executeQuery("SELECT Isbn,Title,name FROM Booksearch  where isbn='"+txt+"' or title like '%"+txt+"%' or name like'%"+txt+"';");
    		 while (rs.next()) {
					
					linect++;
					// Populate field variables

					Isbn13 = rs.getString("Isbn");
					Booktitle = rs.getString("Title");
					authorname= rs.getString("name");
					System.out.print(linect + ")\t");
					System.out.print(Isbn13 + "\t");
					System.out.print(Booktitle + "\t");
					System.out.print(authorname + "\t");
					System.out.println();
					
				} // End while(rs.next())

				// Always close the recordset and connection.
				rs.close();
				conn.close();

	} // book search if close
	
	else if(field.equals("borrower"))
	{
		System.out.println("lol");
	
	
	
	
	}
	
	
	
		} //try close	
     catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}//getclose

	
	

}
