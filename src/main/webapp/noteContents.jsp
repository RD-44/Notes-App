<%@ page import="java.util.List" %>
<%@ page import="Notes.Item" %>
<%@ page import="Notes.ItemList" %>

<%ItemList currList = (ItemList) request.getAttribute("list");%>
<%session.setAttribute("curr", currList); //  Stores current list as an attribute (needed for deletion, adding, going back)%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><%=currList.getContents()%></title>
  <link rel="stylesheet" type="text/css" href="styles.css"/>
</head>
<body>
<h1><%=currList.getContents()%></h1>
<form method="POST" action="${pageContext.request.contextPath}/goback.html">
  <input class="noteButton" type="submit" name="content" value="<%="Go back"%>">
</form>
<ul>
  <%
    List<Item> items = currList.getItems();
    List<ItemList> lists = currList.getLists();
    for (ItemList list : lists)
    {
        session.setAttribute(list.getContents(), list);
  %>
  <form method="POST" action="${pageContext.request.contextPath}/getnotecontent.html">
    <input class="noteButton" type="submit" name="content" value="<%=list.getContents()%>">
  </form>
  <form method="POST" action="${pageContext.request.contextPath}/deletelist.html">
    <button type="submit" name="list" value="<%=list.getContents()%>">Delete</button>
  </form>
  <%}%>
  <%
    for (Item item : items)
    {
      String content = item.getContents();
      session.setAttribute(content, item);
  %>
  <p><%=content%></p>
  <form method="POST" action="/deleteitem.html">
    <button type="submit" name="item" value="<%=content%>">Delete</button>
  </form>
  <%}%>
  <form class="searchForm" method="POST" action="/additem.html"> <!-- this indicates a request which gets mapped to a servlet
       could have multiple requests to same servlet -->
    <input type="text" class="searchText" name="notename" placeholder="Enter contents here"/>
    <input type="submit" class="searchButton" name="add" value="Add text"/>
    <input type="submit" class="searchButton" name="add" value="Add note"/>
    <input type="submit" class="searchButton" name="add" value="Add url"/>
    <input type="submit" class="searchButton" name="add" value="Add image"/>
  </form>
</ul>
</body>
</html>