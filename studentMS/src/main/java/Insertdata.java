

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Insertdata")
public class Insertdata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String name=request.getParameter("username");
		String enroll=request.getParameter("enroll");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String path="jdbc:mysql://localhost:3306/ak47";
			String username="root";
			String password="bihar";
			Connection con=DriverManager.getConnection(path,username,password);
			
			String query="insert into student(name,enrollementNo,email,password) values(?,?,?,?)";
			
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,name);
			ps.setString(2,enroll);
			ps.setString(3,email);
			ps.setString(4,pass);
			
			int count=ps.executeUpdate();
			if(count==1) {
				out.println("<div style='width:250px;height:100px; border:1px solid black; text-align:center; position:absolute;top:40%;left:40%;backgrounnd-color:green;'>");
				out.println(" <h2 style='color:red;'>Registered SuccessFully</h2>");
				out.println(" <a href=\"RegisterPage.html\" style=\"border:1px solid; position:relative; left:-30px\">Home</a>");
				out.println("<a href=\"ShowList\" style=\"border:1px solid; position:relative; left:10px\">Show Student List</a>");
			    out.println("</div>");
			}
			else {
				out.println("not registered");
			}
			ps.close();
		}
		catch(Exception e) {
			out.println("<h2>" +e.getMessage()+ "</h2>");
			e.printStackTrace();
		}
		
	}

}
