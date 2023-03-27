<%@ page import="Notes.ItemList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Notes App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h1>Your notes: </h1>
  <ul>
    <%
      ItemList mainList = (ItemList) request.getAttribute("list");

      session.setAttribute("curr", mainList); //  Used for going back

      for (ItemList note : mainList.getLists()) // mainList is purely a list of lists. We display each one here.
      {
        session.setAttribute(note.getContents(), note);
    %>
    <form method="POST" action="${pageContext.request.contextPath}/getnotecontent.html">
      <input class="noteButton" type="submit" name="content" value="<%=note.getContents()%>">
    </form>
    <form method="POST" action="${pageContext.request.contextPath}/deletelist.html">
      <button type="submit" name="list" value="<%=note.getContents()%>">Delete</button>
    </form>
    <% }%>
    <form class="searchForm" method="POST" action="${pageContext.request.contextPath}/addnote.html"> <!-- Only adding a list is allowed in the main list-->
      <input type="text" class="searchText" name="notename" placeholder="Enter note name here"/>
      <input type="submit" class="searchButton" value="CREATE!"/>
    </form>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>