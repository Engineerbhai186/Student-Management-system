

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowList")
public class ShowList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String path="jdbc:mysql://localhost:3306/ak47";
			String username="root";
			String password="bihar";
			Connection con=DriverManager.getConnection(path,username,password);

			PreparedStatement ps=con.prepareStatement("select * from student");
			
			ResultSet rs = ps.executeQuery();
			out.println("<table border=1 style='width:1000px; font-size:20px;'>");
			out.println("<tr>");
			out.println("<th>Sno.</th>");
			out.println("<th>Name</th>");
			out.println("<th>Enrollement no.</th>");
			out.println("<th>Email</th>");
			out.println("<th>Password</th>");
			out.println("<th>Edit</th>");
			out.println("<th>Delete</th>");
			out.println("</tr>");
			
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>" +rs.getInt(1)+ "</td>");
				out.println("<td>" +rs.getString(2)+ "</td>");
				out.println("<td>" +rs.getString(3)+ "</td>");
				out.println("<td>" +rs.getString(4)+ "</td>");
				out.println("<td>" +rs.getString(5)+ "</td>");
				out.println("<td><a href='Edit?sno="+rs.getInt(1)+"'>Edit</a></td>");
				out.println("<td><a href='Delete?sno="+rs.getInt(1)+"'>Delete</a></td>");
				
				out.println("</tr>");
				
			}
			
			out.println("</table>");
			
			out.println(" <a href='RegisterPage.html' style='position:relative;top:10px; border:2px solid red; color:red; margin-top:20px; font-size:30px;'>Home</a>");
			
		}
		catch(Exception e) {
			out.println("<h2>" +e.getMessage()+ "</h2>");
			e.printStackTrace();
		}
		out.close();
		
	}

}
