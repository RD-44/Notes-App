<%@ page import="notes.ItemList" %>
<%@ page import="htmlfilter.Filter" %>
<%@ page import="htmlfilter.Filter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>notes App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>Your notes: </h1>
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
      session.setAttribute(list.getContents(), list);
      String content = Filter.parse(list.getContents());
  %>
  <tr>
    <td>
      <form method="POST" action="${pageContext.request.contextPath}/getlistcontent.html">
        <input class="noteButton" type="submit" name="content" value="<%=content%>">
      </form>
    </td>
    <td>
      <form method="POST" onsubmit="return confirm('Do you really want to delete this item?');" action="${pageContext.request.contextPath}/deletelist.html">
        <button class="deleteButton" type="submit" name="list" value="<%=content%>">Delete</button>
      </form>
    </td>
    <td>
      <form method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/renamelist.html">
        <input type="text" name="userinput" value="<%=content%>" placeholder="Enter new list name here:"/>
        <button class="renameButton" type="submit" name="list" value="<%=content%>">Rename</button>
      </form>
    </td>
  </tr>
  <% }%>
</table>
<form class="inputForm" method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/addnote.html"> <!-- Only adding a list is allowed in the main list-->
  <input type="text" class="inputText" name="userinput" placeholder="Enter note name here"/>
  <input type="submit" class="searchButton" value="CREATE!"/>
</form>
<jsp:include page="/footer.jsp"/>
<script src="validation.js"></script>
</body>
</html>