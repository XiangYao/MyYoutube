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
		ArrayList<String> result = new ArrayList<String>();

		try {
			List<String> videoList = AWSResource.getVideoList();
			for (int i=0; i<videoList.size(); i++) {
				String fileName = videoList.get(i);
				String subfix = fileName.substring(fileName.length() - 4, fileName.length());				
				if (subfix.equals(".mp4") || subfix.equals(".flv")) {
					result.add(fileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("video_list", result);
		request.getRequestDispatcher("listing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
