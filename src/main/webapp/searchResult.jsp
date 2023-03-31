<%@ page import="java.util.ArrayList" %>
<%@ page import="uk.ac.ucl.items.ItemList" %>
<%@ page import="uk.ac.ucl.items.Item" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles.css"/>
  <title>Search Results</title>
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
        for (HashMap.Entry<ItemList, ArrayList<Item>> entry : foundLists.entrySet()) { // Iterate through HashMap of search results
          ItemList list = entry.getKey();
          String id = Objects.hashCode(list)+"";// Uses hashes to avoid mixing up items from 2 different lists with the same id
          session.setAttribute(id, list);
          ArrayList<Item> items = entry.getValue();
      %>
      <ul class="searchItems">
      <% if(items.size() > 0){ %>
      <h2>Items found in <%=list.display()%>:</h2>
      <%
        for(Item foundItem : items){ // Displays matching items in each list.
      %>
        <li><%=foundItem.display()%></li>
      <%}%>
      </ul>
      <h2>Click below to go to <%=list.display()%>:</h2>
      <%}%>
      <!--If just the list name itself matches, then the below alone will be outputted. -->
      <form method="POST" action="${pageContext.request.contextPath}/getlistcontent.html">
        <button class="listButton" type="submit" name="content" value="<%=id%>"><%=list.display()%></button>
      </form>
      <hr>
     <% }
    } else
    {%>
      <p>Nothing found</p>
  <%}%>
  </ul>
</div>
</body>
</html>