

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

@WebServlet("/Edit")
public class Edit extends HttpServlet {
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
			
			String query="select name,enrollementNo,email,password from student where sno=?";
			int sno= Integer.parseInt(request.getParameter("sno"));
			
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setInt(1, sno);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			
			out.println("<div ><h2 class=\"reg\">Edit Here</h2></div>");
			out.println("<div style='position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%); border: 1px solid; border-radius: 10px;width:"
					+ " 400px; height: 500px;background-color: aquamarine;text-align:center;'>");
			out.println("<form action='Update?sno='"+sno+"' method='get'>");
			out.println("<h1 style=' position: relative;top: -20px;'>Edit Record</h1>");
			out.println("Name: <input type=\"text\" name='username' value='"+rs.getString(1)+"' placeholder=\" enter username\" style='border-radius:5px;"
					+ " width:180px;height:30px;' required><br><br>");
			out.println("Enrollement no: <input type=\"text\" name='enroll' value='"+rs.getString(2)+"' placeholder=\"enter Enrollement no.\" style='width:200px;"
					+ " height:30px;border-radius:5px;margin-top:10px;' required><br><br>");
			out.println("Email: <input type=\"email\" name='email' value='"+rs.getString(3)+"' placeholder=\"enter email\" style='width:200px;height:30px;"
					+ "border-radius:5px;' required><br><br>");
			out.println("Password: <input type=\"password\" name='password' value='"+rs.getString(4)+"' placeholder=\"enter password\" style='width:200px;"
					+ " height:30px;border-radius:5px;' required><br><br>");
			out.println("<input type=\"submit\" value='SUBMIT' style='width:220px;height:30px;background-color:aqua;font-size:bolder;border-radius:5px;' ><br>");
			out.println("<input type=\"reset\" value=\"cancle\" style='margin-top:25px;width:220px;height:30px;'>");
			out.println("</form>");
			
			out.println(" <a href='RegisterPage.html' style='position:relative;top:10px; border:2px solid red; color:red; margin-top:20px; font-size:30px;"
					+ "border-radius:5px;'>Home</a>");
			out.println("<a href='ShowList' style='border:1px solid black; position:relative; top:30px; display:block; background-color:gray; color:black;"
					+ " font-size:30px;'>Show Student List</a>");
			
		}
		catch(Exception e) {
			out.println("<h2>" +e.getMessage()+ "</h2>");
			e.printStackTrace();
		}
		out.close();
	}

}
