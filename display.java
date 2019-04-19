import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;
public class display extends HttpServlet
{
    
 public void doGet(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException
 {
      response.setContentType("text/html");
 PrintWriter out = response.getWriter();
     out.println("<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"  <meta charset=\"UTF-8\">\n" +
"  <title>Disease Diagnosis</title>\n" +
"  <link href=\"main.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
"</head>\n" +
"<body>\n" +
"<div class=\"main\">\n" +
"<div class=\"page\">\n" +
"<div class=\"page-in\">\n" +
"<div class=\"header\">\n" +
"<div class=\"topmenu\">\n" +
"<ul>\n" +
"  <li>\n" +
"<a href=\"welcome.html\">Home</a></li>\n" +
"  <li><a href=\"index.html\">Logout</a></li>\n" +
"</ul>\n" +
"</div>\n" +
"<img src=\"images/Disease.jpg\" alt=\"X\" width=\"995\" height=\"378\">\n" +
"</div>\n" +
"<form action=\"register\" name=\"Form\" method=\"POST\">\n" +
"            <h1>Check your symptoms</h1>\n" +
"            <fieldset class=\"row1\">\n" +
"               ");
     int count=0;
      String s[][]=new String[100][];

 try{ Class.forName("oracle.jdbc.driver.OracleDriver");
 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","shubham","shubham12");
 PreparedStatement ps=con.prepareStatement( "select * from dtable");
 int i=ps.executeUpdate();
 ResultSet rs=ps.executeQuery();                
 ResultSetMetaData rsmd=rs.getMetaData();
 Set<String> ss=new HashSet<String>();
 while(rs.next())
 {
  s[count++]=rs.getString(2).split(",");
  for(int j=0;j<s[count-1].length;j++)
     ss.add(s[count-1][j]);
 }
 
 for(String sto : ss){
     out.println("<input type='checkbox' name='symptom' value='"+sto+"'>"+sto+"<br>");
 }
 out.println("</fieldset>\n" +
"            <div><button type=\"submit\" class=\"button\">Submit &raquo;</button></div>\n" +
"			<div><button type=\"reset\" class=\"button\">Clear &raquo;</button></div>\n" +
"        </form>\n" +
"\n" +
"<div class=\"footer\">\n" +
"<p class=\"copyright-text\">&copy;Designed for Java Da component</p>\n" +
"</div>\n" +
"</div>\n" +
"</div>\n" +
"</div>\n" +
"</body>\n" +
"</html>\n" +
"");
}

 catch (Exception e2)
 {
 System.out.println(e2);
 }        
 out.close();
 }
}