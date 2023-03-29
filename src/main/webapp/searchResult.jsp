<%@ page import="java.util.ArrayList" %>
<%@ page import="notes.ItemList" %>
<%@ page import="notes.Item" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>notes App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h1>Search Results</h1>
  <%
    HashMap<ItemList, ArrayList<Item>> foundLists = (HashMap<ItemList, ArrayList<Item>>) request.getAttribute("result");
    if (foundLists.size() != 0) { %>
    <ul>
      <%
        for (HashMap.Entry<ItemList, ArrayList<Item>> foundList : foundLists.entrySet()) { // Iterate through HashMap pairs
          String ListName = foundList.getKey().getContents(); // Gets Note name to set it as an attribute
          session.setAttribute(ListName, foundList.getKey());
      %>
      <ul class="searchItems">
      <% if(foundList.getValue().size() > 0){ %>
      <h2>Items found in <%=ListName%>:</h2>
      <%
        for(Item foundItem : foundList.getValue()){ // Displays matching items in each list.
      %>
        <li><%=foundItem.getContents()%></li>
      <%}%>
      </ul>
      <h2>Click below to go to <%=ListName%>:</h2>
      <%}%>
      <form method="POST" action="${pageContext.request.contextPath}/getlistcontent.html">
        <input class="noteButton" type="submit" name="content" value="<%=ListName%>">
      </form>
      <hr>
     <% }
    } else
    {%>
      <p>Nothing found</p>
  <%}%>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>