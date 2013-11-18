package MyYoutube;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
		String deleteFilename = request.getParameter("filename");
		out.println(deleteFilename);
		try {
			AWSResource.deleteObjectS3(deleteFilename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	Connection conn = null;
	    String sql = "DELETE FROM video WHERE name = '" + deleteFilename + "'";
	    String sql2 = "DELETE FROM rate WHERE name = '" + deleteFilename + "'";
	    Statement statement = null;
	    
	    try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	    	String id = "willfeather";
	        String pwd = "1989912y";
	        String url = "jdbc:mysql://myyoutubedb.c7hsbutjtwog.us-east-1.rds.amazonaws.com:3306/myyoutubedb";
	    	conn = DriverManager.getConnection(url, id, pwd);
	    	statement = conn.createStatement();
	    	statement.execute(sql);
	    	statement.execute(sql2);
	    	out.println("Success");
	    } catch (Exception e) {
			e.printStackTrace();
	    }
	    			
		request.getRequestDispatcher("listing.jsp").forward(request, response);
	}

}
