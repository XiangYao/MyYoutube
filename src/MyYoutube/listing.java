package MyYoutube;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/listing")
public class listing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> nameList = new ArrayList<String>();
		List<String> dateList = new ArrayList<String>();
		try {
			 nameList = AWSResource.getVideoList();
			 dateList  = AWSResource.getVideoTimeStampList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("video_list", nameList);
		request.setAttribute("date_list", dateList);
		request.getRequestDispatcher("listing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
