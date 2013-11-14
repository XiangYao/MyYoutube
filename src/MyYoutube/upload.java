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

import com.amazonaws.services.s3.model.S3ObjectSummary;

@WebServlet(name = "upload", urlPatterns = {"/upload"})
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ArrayList<String> result = new ArrayList<String>();

		try {
			List<S3ObjectSummary> videoList = AWSResource.getVideoList();
			out.println(videoList.size());
			for (int i=0; i<videoList.size(); i++) {
				String fileName = videoList.get(i).getKey();
				String subfix = fileName.substring(fileName.length() - 4, fileName.length());				
				if (subfix.equals(".mp4") || subfix.equals(".flv")) {
					result.add(fileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("video_list", result);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
