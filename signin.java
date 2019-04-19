import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class signin extends HttpServlet
{
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException
 {
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 String n=request.getParameter("name");  
 String u=request.getParameter("uname");
 String p=request.getParameter("pass");
 String e=request.getParameter("email");

 try{ Class.forName("oracle.jdbc.driver.OracleDriver");
 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","shubham","shubham12");
 PreparedStatement ps=con.prepareStatement( "insert into userdetails values(?,?)");
 //ps.setString(1,n); 
 ps.setString(1,u);
 //ps.setString(3,e);
 ps.setString(2,p);
 
 int i=ps.executeUpdate();
 if(i>0)
out.print("User added successfully ...");
 out.println("<a href=\"login.html\">Go to login page</a>");
 }
 catch (Exception e2)
 {
 System.out.println(e2);
 }
 }
}
 