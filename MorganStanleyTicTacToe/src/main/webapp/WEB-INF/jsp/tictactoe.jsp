<!DOCTYPE html>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" 
	import="javax.servlet.*, javax.servlet.http.*, com.morganstanley.business.manager.GameManager,	 
	java.util.ArrayList, java.util.List"	 
	errorPage=""%>
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tic Tac Toe Game</title>
<link th:href="@{/css/main.css}"  href="./css/main.css"  rel="stylesheet"></link>

<body>
<%
GameManager gm = (GameManager) request.getSession().getAttribute("gameManager");
char[][] tiles;

if(gm==null) {
	tiles = new char[3][3];	
	for (int r=0; r<3; r++)
		for (int c=0; c<3; c++)
			tiles[r][c] = ' ';
	
	request.getSession().setAttribute("tiles", tiles);
	request.getSession().setAttribute("newGame", true);
	request.getSession().setAttribute("bForTest", false);	
}
else {
	tiles = (char[][]) request.getSession().getAttribute("tiles");
	request.getSession().setAttribute("newGame", false);
}         
  
 %>
 <h1>Tic Tac Toe</h1>
 <h3>Click on Board to Reset</h3> 
 <%@ include file="message.jsp" %> 
<form action="./frontController?action=updateBoard" method="post" name="company_form" id="company_form">
<input name="manager" type="hidden" value="GameManager"/>
 <table class="gameBoard" border="1">
 <tbody> 
 <%
  for (int r=0; r<3; r++) {
    out.println("<tr>");
    for (int c=0; c<3; c++) {
      out.println("<td><input class=\"tic\" type=\"submit\" name=\"Position" + r + c + "\"" +
        " value=\"" + tiles[r][c] + "\"></td>");
    }
    out.println("</tr>");
  }
 %> 
 </tbody>
 </table>
</form>
</body>
</html>