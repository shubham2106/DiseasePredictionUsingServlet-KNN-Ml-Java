import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;
public class register extends HttpServlet
{
    
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException
 {
     int count=0;
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 String k[]=request.getParameterValues("symptom");
 String s[][]=new String[100][];
 String diseases[]=new String[100];
 try{ Class.forName("oracle.jdbc.driver.OracleDriver");
 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","shubham","shubham12");
 PreparedStatement ps=con.prepareStatement( "select * from dtable");
 int ip=ps.executeUpdate();
 ResultSet rs=ps.executeQuery();                
 ResultSetMetaData rsmd=rs.getMetaData();
   Set<String> ss=new HashSet<String>();
 while(rs.next())
 {
   diseases[count]=rs.getString(1);
  s[count++]=rs.getString(2).split(",");
   for(int j=0;j<s[count-1].length;j++)
     ss.add(s[count-1][j]);
 }
 int nu=ss.size();
int l=0;
 String symptoms[]=new String[nu];
 for(String sto : ss){symptoms[l++]=sto;}
 
 Map<String,Integer> mp=new LinkedHashMap<String,Integer>();
	
		for(int i=0;i<symptoms.length;i++)
			mp.put(symptoms[i],i);
		int st[][]=new int[count][symptoms.length];
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<s[i].length;j++)
				st[i][mp.get(s[i][j])]++;
		}
                int inp[]=new int[symptoms.length];
		for(int j=0;j<k.length;j++)
				inp[mp.get(k[j])]++;
                double result[]= euclid(st,inp,count,symptoms.length);
		Map<String,Double> tmp=new HashMap<String,Double>();
		for(int i=0;i<count;i++)
			tmp.put(diseases[i],result[i]);
		Map<String, Double> sorted = tmp
        .entrySet()
        .stream()
        .sorted(comparingByValue())
        .collect(
            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
		
		double x=4.2426406871192851464050661726291;
		
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
"<a href=\"index.html\">Home</a></li>\n" +
"  <li><a href=\"index.html\">Logout</a></li>\n" +
"</ul>\n" +
"</div>\n" +
"<img src=\"images/Disease.jpg\" alt=\"X\" width=\"995\" height=\"378\">\n" +
"</div>");
                
                
                out.println("*********************************************************************************************************************************<br>");
		out.println("<br><h2>Percentage probability of disease</h2><br>");
		for (Map.Entry<String,Double> entry : sorted.entrySet())  
                out.println("<br>"+entry.getKey() +" = " + ((Math.abs(entry.getValue()-x)/x)*100)+"%<br>");
                out.println("<br> <h3>The most probable disease is "+sorted.entrySet().iterator().next().getKey()+"</h3><br>");
                
                out.println("*********************************************************************************************************************************<br>");
                
out.println(
"<div class=\"footer\">\n" +
"<p class=\"copyright-text\">&copy;Designed For Java Da component</p>\n" +
"</div>\n" +
"</div>\n" +
"</div>\n" +
"</div>\n" +
"</body>\n" +
"</html>\n" +
"");          
 out.close();
 }
  catch (Exception e2)
 {
 System.out.println(e2);
 }
 }
 public double[] euclid(int st[][],int inp[],int count,int k)
	{
		double result[]=new double[k];
		for(int i=0;i<count;i++)
		{
			double temp=0.0;
			for(int j=0;j<k;j++)
				temp+=Math.pow(st[i][j]-inp[j],2);
			result[i]=Math.sqrt(temp);
		}
		return result;
	}
}
