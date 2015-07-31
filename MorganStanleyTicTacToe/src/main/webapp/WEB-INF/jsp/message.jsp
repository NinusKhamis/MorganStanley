<%@ page contentType="text/html; charset=iso-8859-1" language="java"
import="com.morganstanley.business.helper.MessageHelper"
errorPage=""%>

<%
MessageHelper msg = (MessageHelper) request.getSession().getAttribute("msgHelper");

if(msg != null) {
%>
<div id="message">
<%= msg.getMessage() %>
</div>
<%
}
%>