<%@ page import="uk.ac.ucl.items.ItemList" %>
<%@ page import="uk.ac.ucl.htmlfilter.Filter" %>
<%@ page import="uk.ac.ucl.htmlfilter.Filter" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles.css"/>
  <title>Main Lists</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>Main Lists</h1>
<table>
  <tr>
    <td>
      <h2>List</h2>
    </td>
    <td>
      <h2>Delete List</h2>
    </td>
    <td>
      <h2>Rename List</h2>
    </td>
  </tr>
  <%
    ItemList mainList = (ItemList) request.getAttribute("list");
    session.setAttribute("curr", mainList); //  Used for going back

    for (ItemList list : mainList.getLists()) // mainList is purely a list of lists. We display each one here.
    {
      String id = list.getId() + "";
      session.setAttribute(id, list);
  %>
  <tr>
    <td>
      <form method="POST" action="${pageContext.request.contextPath}/getlistcontent.html">
        <button class="listButton" type="submit" name="content" value="<%=id%>"><%=list.display()%></button>
      </form>
    </td>
    <td>
      <form method="POST" onsubmit="return confirm('Do you really want to delete this item?');" action="${pageContext.request.contextPath}/deletelist.html">
        <button class="deleteButton" type="submit" name="list" value="<%=id%>">Delete</button>
      </form>
    </td>
    <td>
      <form method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/editlist.html">
        <input type="text" name="userinput" class = "inputText" value="<%=Filter.filter(list.getContents())%>" placeholder="Enter new list name here:"/>
        <button class="renameButton" type="submit" name="list" value="<%=id%>">Rename</button>
      </form>
    </td>
  </tr>
  <%}%>
</table>
<form class="inputForm" method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/addlist.html"> <!-- Only adding a list is allowed in the main list-->
  <input type="text" class="inputText" name="userinput" placeholder="Enter list name here"/>
  <input type="submit" class="searchButton" value="Add list"/>
</form>
<script src="validation.js"></script>
</body>
</html>