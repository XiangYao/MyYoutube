package MyYoutube;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class rating
 */
@WebServlet("/rating")
public class rating extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("rating");
        
        String name = request.getParameter("name");
        int rating = Integer.parseInt(request.getParameter("rating"));
        out.println(name);
        out.print(rating);
        
    	Connection conn = null;
    	PreparedStatement preparedStatement = null;
	    String sql = "INSERT INTO rate"
				+ " (score, name) VALUES"
				+ " (?,?)";
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	    try {
	    	String id = "willfeather";
	        String pwd = "1989912y";
	        String url = "jdbc:mysql://myyoutubedb.c7hsbutjtwog.us-east-1.rds.amazonaws.com:3306/myyoutubedb";
	        conn = DriverManager.getConnection(url, id, pwd);
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, rating);
			preparedStatement.setString(2, name);			
	    	preparedStatement.executeUpdate();
	    	System.out.println("Success");    
	    } catch (SQLException ex) {
	    	System.out.println("SQLException: " + ex.getMessage());
	    	System.out.println("SQLState: " + ex.getSQLState());
	    	System.out.println("VendorError: " + ex.getErrorCode());
	    }
	    
	    request.getRequestDispatcher("listing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
