package MyYoutube;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.s3.model.S3ObjectSummary;

@WebServlet(name = "upload", urlPatterns = {"/upload"})
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("key");
        java.util.Date date= new java.util.Date();
        
        out.println("name: " + name);
        
    	Connection conn = null;
    	PreparedStatement preparedStatement = null;
	    String sql = "INSERT INTO video"
				+ " (uploadtime, name) VALUES"
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
			preparedStatement.setTimestamp(1, new Timestamp(date.getTime()));
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
	}

}
