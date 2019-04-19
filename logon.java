import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class logon extends HttpServlet
{
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException
 {
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 String n=request.getParameter("name");  
 String p=request.getParameter("pass");
//out.println(n+" "+p); 
 try{ Class.forName("oracle.jdbc.driver.OracleDriver");
 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","shubham","shubham12");
 PreparedStatement ps=con.prepareStatement( "select * from userdetails where username=? and password=?");
 ps.setString(1,n); 
ps.setString(2,p);
 ResultSet rs=ps.executeQuery();                
 ResultSetMetaData rsmd=rs.getMetaData();
 while(rs.next())
 {
    //out.println(rs.getString(2));
   if(rs.getString(2)!=null)
   response.sendRedirect("display");
   //out.println(rs);
 }
    out.println("Wrong details");
    out.println("<a href=\"login.html\">Go to login page</a>");
 }
 catch (Exception e2)
 {
 System.out.println(e2);
 }
 }
}
 