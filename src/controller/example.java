package controller;
import controller.finesentries;
import controller.Booksearch;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/log")
public class example extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public example() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    } 
		    catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			 
		    	e.printStackTrace();
		    		}
		
	try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
			Statement stmt = null;
			stmt = conn.createStatement();
		    stmt.execute("use library;");
		    String field;
	
		    field=request.getParameter("reference");
		if(field.equals("booksearch"))
		{	
	
		    
		    
			String txt;
			txt=request.getParameter("search");
      		ResultSet rs = stmt.executeQuery("SELECT Isbn,Title,name,availability FROM Booksearch  where isbn='"+txt+"' or title like '%"+txt+"%' or name like'%"+txt+"';");
    		ArrayList<Booksearch> Books=new ArrayList<Booksearch>();
    		 
    		 while (rs.next()) {
					
					Booksearch Book=new Booksearch();
					Book.setIsbn(rs.getString("Isbn"));
					Book.setTitle(rs.getString("Title"));
					Book.setAuthor(rs.getString("name"));
					Book.setavailability(rs.getString("availability"));
					Books.add(Book);
					
					} 

				
				rs.close();
				conn.close();
				request.setAttribute("Books",Books);
				
				RequestDispatcher view=request.getRequestDispatcher("booksdisplay.jsp");
				view.forward(request,response);

		} // book search if close
	
	else if(field.equals("borrower"))
	{
		String card_id=request.getParameter("card_id");
		String ssn=request.getParameter("ssn");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String phone=request.getParameter("phone");
		
		try{
		String query="insert into borrower values('%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		query = String.format(query, card_id,ssn,firstname,lastname,email,address,city,state,phone);
		
		stmt.executeUpdate(query);
			}
	catch(Exception e)
			{
		request.setAttribute("Error","Cannot be inserted User already exists");
		
			}
	finally{
		RequestDispatcher view=request.getRequestDispatcher("borrower.jsp");
		view.forward(request,response);
			}
	} //borrower close 
	
	else if(field.equals("checkout"))
	{   
		String card_id=request.getParameter("card_id");
		String isbn=request.getParameter("isbn");
		String avail="yes";
		String avail1="no";
		int x=1;
		int y=0;
		int z=0;
		ResultSet rs1 = stmt.executeQuery("select availability from booksearch where Isbn='"+isbn+"' and availability='"+avail+"';");
		   while(rs1.next())
		   {
			   y++;
		   }
		   ResultSet rs3 = stmt.executeQuery("select paid from finesupdate where card_id='"+card_id+"' and paid='"+avail1+"';"); 
		   while(rs3.next())
		   {
			   z++;
		   }
		   
		  if(y>0 && (z==0))
		  {	  
			  
			  ResultSet rs = stmt.executeQuery("SELECT card_id from book_loans where card_id='"+card_id+"';");
			  while(rs.next())
			  {
				  x++;
			  }
		
			  if(x<=3)//go to this block if boorrower doesnot have 3 books on his name
			  {
				  try
				  {
					  stmt.executeUpdate("insert into book_loans(card_id,isbn,date_out,due_date) values('"+card_id+"','"+isbn+"',current_date(),current_date()+interval 14 day)");
					  stmt.executeUpdate("update booksearch set availability='"+avail1+"' where Isbn='"+isbn+"'");
				  }
				  catch(Exception e)
				  {
					  request.setAttribute("Error1","Check the book Isbn and the borrower id");
				  }
				  finally
				  {
					  RequestDispatcher view=request.getRequestDispatcher("checkout.jsp");
					  view.forward(request,response);
				 
				  }
			  }//if close
			  else
			  {   
				  request.setAttribute("Error2","Borrower cannot have more than 3 books or he has some fine");
				  RequestDispatcher view=request.getRequestDispatcher("checkout.jsp");
				  view.forward(request,response);
			 
			  }
		  }//close block book not checked out
		  else if(z>0)
		  {
			  request.setAttribute("Error3","Borrower has fines cannot checkout a book");
				RequestDispatcher view=request.getRequestDispatcher("checkout.jsp");
				view.forward(request,response);
		  }
			  else
		  {
			  	request.setAttribute("Error3","Book already checked out");
				RequestDispatcher view=request.getRequestDispatcher("checkout.jsp");
				view.forward(request,response);
			  
		  }
		 
	}//checkout close
	
	else if(field.equals("checkin"))//check in start
	{
			String card_id=request.getParameter("card_id");
			String isbn=request.getParameter("isbn");
			String loan_id="xyz";
			String avail="yes";
		
			int x=0;
			double y=0;
		
			String query ="update book_loans set date_in=current_date() where isbn='"+isbn+"' and card_id='"+card_id+"' "; 
			stmt.executeUpdate(query);
		
			stmt.executeUpdate("update booksearch set availability='"+avail+"' where Isbn='"+isbn+"' ");
			ResultSet rs=stmt.executeQuery("select datediff(date_in,due_date) as diff,loan_id from book_loans where isbn='"+isbn+"' and card_id='"+card_id+"'");
			while(rs.next())
			{	
				loan_id=rs.getString("loan_id");
				x=rs.getInt("diff");
			}
		 
			if(x>0)
			{ 		
				y=x*0.5;
				query = "insert into fines(loan_id,fine_amt) values('"+loan_id+"','"+y+"')";
				stmt.executeUpdate(query);
			}
			RequestDispatcher view=request.getRequestDispatcher("index.jsp");
			view.forward(request,response);
			
	}//check in close
	
	
	else if(field.equals("finescheck"))//fines for a particular borrower
	{
		int x=0;
				String card_id=request.getParameter("card_id");
				ResultSet rs=stmt.executeQuery("select card_id,fine_amt,paid from book_loans,fines where book_loans.loan_id=fines.loan_id and card_id='"+card_id+"'");
				ArrayList<finesentries> fines=new ArrayList<finesentries>();
		 
				while (rs.next()) 
				{
				x++;
					finesentries fine=new finesentries();
		
					fine.setcard_id(rs.getString("card_id"));
					fine.setfine_amt(rs.getString("fine_amt"));
					fine.setpaid(rs.getString("paid"));
					fines.add(fine);
				}
	if(x>0)
			{
				request.setAttribute("fines",fines);
				RequestDispatcher view=request.getRequestDispatcher("finesdisplay.jsp");
				view.forward(request,response);
			}
	else
	{
		
		RequestDispatcher view=request.getRequestDispatcher("nofines.jsp");
		view.forward(request,response);
	}
		 
	}
	else if(field.equals("updatefine"))
		{    
				String card_id=request.getParameter("card_id");
				String param = String.valueOf(request.getParameter("paid"));
				
				if (param.equals("yes"))
				{
					stmt.executeUpdate("update finesupdate set paid='"+param+"'where card_id='"+card_id+"' ");
				}
				else if(param.equals("no"))
				{
					stmt.executeUpdate("update finesupdate set paid='"+param+"'where card_id='"+card_id+"' ");
				}
		
				RequestDispatcher view=request.getRequestDispatcher("finesupdated.jsp");
				view.forward(request,response);
		}
	
	} //try close	
    catch (SQLException e) {
	
			e.printStackTrace();
		}
		
	
	
	}//get method close

	
	

}//class example close
