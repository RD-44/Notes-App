<%@ page import="uk.ac.ucl.items.Item" %>
<%@ page import="uk.ac.ucl.items.ItemList" %>
<%@ page import="uk.ac.ucl.htmlfilter.Filter" %>
<%@ page import="java.util.ArrayList" %>
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
<jsp:include page="/header.jsp"/>
<h1><%=currList.getContents()%></h1>
<form method="POST" action="${pageContext.request.contextPath}/goback.html">
  <input class="listButton" type="submit" name="content" value="Go back">
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
  ArrayList<Item> items = currList.getItems();
  ArrayList<ItemList> lists = currList.getLists();
  int listIndex = 0, itemIndex = 0;
  // Loops are used to output the lists and items in the order they were created by comparing their id values
  while(listIndex < lists.size() && itemIndex < items.size()) {
    Item item = items.get(itemIndex);
    ItemList list = lists.get(listIndex);
    if (item.getId() < list.getId()) {
      String id = item.getId() + "";
      session.setAttribute(id, item);
  %>
      <tr>
        <td>
          <p><%=item.display()%></p>
        </td>
        <td>
          <form method="POST" action="${pageContext.request.contextPath}/deleteitem.html">
            <button class="deleteButton"  onsubmit="return confirm('Do you really want to delete this item?');" type="submit" name="item" value="<%=id%>">Delete</button>
          </form>
        </td>
        <td>
          <form method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/edititem.html">
            <input type="text" class = "inputText" name="userinput" value="<%=Filter.filter(item.getContents())%>" placeholder="<%=item.getEditText()%>"/>
            <button class="renameButton" type="submit" name="item" value="<%=id%>">Save Changes</button>
          </form>
        </td>
      </tr>
    <%itemIndex++;
    }else{
      String id = list.getId() + "";
      session.setAttribute(id, list);%>
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
            <input type="text" class = "inputText" name="userinput" value="<%=Filter.filter(list.getContents())%>" placeholder="<%=list.getEditText()%>"/>
            <button class="renameButton" type="submit" name="list" value="<%=id%>">Rename</button>
          </form>
        </td>
      </tr>
    <%listIndex++;
    }%>
<%}%>
  <%
  while (itemIndex < items.size()){
    Item item = items.get(itemIndex);
    String id = item.getId() + "";
    session.setAttribute(id, item);
%>
    <tr>
      <td>
        <p><%=item.display()%></p>
      </td>
      <td>
        <form method="POST" onsubmit="return confirm('Do you really want to delete this item?');" action="${pageContext.request.contextPath}/deleteitem.html">
          <button class="deleteButton"  type="submit" name="item" value="<%=id%>">Delete</button>
        </form>
      </td>
      <td>
        <form method="POST" onsubmit="return submitForm(this)" action="${pageContext.request.contextPath}/edititem.html">
          <input type="text" class = "inputText" name="userinput" value="<%=Filter.filter(item.getContents())%>" placeholder="<%=item.getEditText()%>"/>
          <button class="renameButton" type="submit" name="item" value="<%=id%>">Save Changes</button>
        </form>
      </td>
    </tr>
    <%itemIndex++;
  }
    while (listIndex < lists.size()){
      Item list = lists.get(listIndex);
      String id = list.getId() + "";
      session.setAttribute(id, list);%>
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
            <input type="text" class = "inputText" name="userinput" value="<%=Filter.filter(list.getContents())%>" placeholder="<%=list.getEditText()%>"/>
            <button class="renameButton" type="submit" name="list" value="<%=id%>">Rename</button>
          </form>
        </td>
      </tr>
<%listIndex++;
}
%>
</table>
<form class="inputForm" onsubmit="return submitForm(this)" method="POST" action="${pageContext.request.contextPath}/additem.html">
  <p>Once you enter something, you can choose to add text, a list, an image or a URL from it. Note that images can only be added from a url to the image.</p>
  <input type="text" class="inputText" name="userinput" placeholder="Enter contents here"/>
  <input type="submit" class="searchButton" name="add" value="Add text"/>
  <input type="submit" class="searchButton" name="add" value="Add list"/>
  <input type="submit" class="searchButton" name="add" value="Add url"/>
  <input type="submit" class="searchButton" name="add" value="Add image"/>
</form>
<script src="validation.js"></script>
</body>
</html>
