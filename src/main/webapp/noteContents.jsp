<%@ page import="java.util.List" %>
<%@ page import="notes.Item" %>
<%@ page import="notes.ItemList" %>
<%@ page import="htmlfilter.Filter" %>
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
  <input class="noteButton" type="submit" name="content" value="Go back">
</form>
<table>
  <tr>
    <td>
      <h2>Item</h2>
    </td>
    <td>
      <h2>Delete Item</h2>
    </td>
    <td>
      <h2>Edit Item</h2>
    </td>
  </tr>
  <%
  List<Item> items = currList.getItems();
  List<ItemList> lists = currList.getLists();
  int listIndex = 0, itemIndex = 0;
  while(listIndex < lists.size() && itemIndex < items.size()) {
    Item item = items.get(itemIndex);
    ItemList list = lists.get(listIndex);
    if (item.getId() < list.getId()) {
      session.setAttribute(item.getContents(), item);
      String content = Filter.parse(item.getContents());
  %>
      <tr>
        <td>
          <p><%=item.display()%></p>
        </td>
        <td>
          <form method="POST" action="${pageContext.request.contextPath}/deleteitem.html">
            <button class="deleteButton"  onclick="alert('Yo')" type="submit" name="item" value="<%=content%>">Delete</button>
          </form>
        </td>
        <td>
          <form method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/edititem.html">
            <input type="text" class = "inputText" name="userinput" value="<%=content%>" placeholder="<%=item.getEditText()%>"/>
            <button class="renameButton" type="submit" name="item" value="<%=content%>">Save Changes</button>
          </form>
        </td>
      </tr>
    <%itemIndex++;
    }else{
      session.setAttribute(list.getContents(), list);
      String content = Filter.parse(list.getContents());%>
      <tr>
        <td>
          <form method="POST" action="${pageContext.request.contextPath}/getlistcontent.html">
            <input class="noteButton" type="submit" name="content" value="<%=content%>">
          </form>
        </td>
        <td>
          <form method="POST" action="${pageContext.request.contextPath}/deletelist.html">
            <button class="deleteButton" type="submit" name="list" value="<%=content%>">Delete</button>
          </form>
        </td>
        <td>
          <form method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/renamelist.html">
            <input type="text" class = "inputText" name="userinput" value="<%=content%>" placeholder="<%=list.getEditText()%>"/>
            <button class="renameButton" type="submit" name="list" value="<%=content%>">Rename</button>
          </form>
        </td>
      </tr>
    <%listIndex++;
    }%>
<%}%>
  <%
  while (itemIndex < items.size()){
    Item item = items.get(itemIndex);
    session.setAttribute(item.getContents(), item);
    String content = Filter.parse(item.getContents());
%>
    <tr>
      <td>
        <p><%=item.display()%></p>
      </td>
      <td>
        <form method="POST" action="${pageContext.request.contextPath}/deleteitem.html">
          <button class="deleteButton"  type="submit" name="item" value="<%=content%>">Delete</button>
        </form>
      </td>
      <td>
        <form method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/edititem.html">
          <input type="text" class = "inputText" name="userinput" value="<%=content%>" placeholder="<%=item.getEditText()%>"/>
          <button class="renameButton" type="submit" name="item" value="<%=content%>">Save Changes</button>
        </form>
      </td>
    </tr>
    <%itemIndex++;
  }
    while (listIndex < lists.size()){
      Item list = lists.get(listIndex);
      session.setAttribute(list.getContents(), list);
      String content = Filter.parse(list.getContents());%>
      <tr>
        <td>
          <form method="POST" action="${pageContext.request.contextPath}/getlistcontent.html">
            <input class="noteButton" type="submit" name="content" value="<%=content%>">
          </form>
        </td>
        <td>
          <form method="POST" action="${pageContext.request.contextPath}/deletelist.html">
            <button class="deleteButton" type="submit" name="list" value="<%=content%>">Delete</button>
          </form>
        </td>
        <td>
          <form method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/renamelist.html">
            <input type="text" class = "inputText" name="userinput" value="<%=content%>" placeholder="<%=list.getEditText()%>"/>
            <button class="renameButton" type="submit" name="list" value="<%=content%>">Rename</button>
          </form>
        </td>
      </tr>
<%listIndex++;
}
%>
</table>
<form class="inputForm" onsubmit="return submitForm(this)" method="POST" action="${pageContext.request.contextPath}/additem.html"> <!-- this indicates a request which gets mapped to a servlet
     could have multiple requests to same servlet -->
  <input type="text" class="inputText" name="userinput" placeholder="Enter contents here"/>
  <input type="submit" class="searchButton" name="add" value="Add text"/>
  <input type="submit" class="searchButton" name="add" value="Add note"/>
  <input type="submit" class="searchButton" name="add" value="Add url"/>
  <input type="submit" class="searchButton" name="add" value="Add image"/>
</form>
<script src="validation.js"></script>
</body>
</html>
