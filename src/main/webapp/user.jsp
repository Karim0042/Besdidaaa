<%@ page import="com.company.dao.inter.UserDaoInter" %>
<%@ page import="com.company.main.Context" %>
<%@ page import="com.company.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 15.12.2022
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Assets/css/users.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
</head>
<body>
<%
   User user =(User) session.getAttribute("loggedInUser");
%>
<%="Welcome "+user.getName()%>
<%
    UserDaoInter userDao = Context.instanceUserDao();
    User u =(User)request.getAttribute("user");
    List<User> userList = userDao.getAll(u.getName(),u.getSurname(),null);
%>
<div class = "container mycontainer" >
    <div class="col-4">
        <form action="userdetail" method="post">

            <div class="col 4">
                <div class="form-group ">
                    <label for="name">name: </label>
                    <input type="text" class="form-control" name="name" value="<%=u.getName()==null? "" :u.getName()%>" id="whatIamTyping"/>
                    <span></span>
                </div>
                <div class="form-group">
                    <label for="surname">surname:</label>
                    <input type="text" class="form-control" name="surname" value="<%=u.getSurname()==null? "" : u.getSurname()%>"/>
                </div>
                <input type = "submit" class="btn btn-primary" name="action" value="search"/>
            </div>
        </form>
    </div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>name</th>
                <th>surname</th>
                <th>nationality</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                for (User z : userList) {
            %>
            <tr>
                <td><%=z.getName()%>
                </td>
                <td><%=z.getSurname()%>
                </td>
                <td><%=z.getNationality().getName() == null ? "N/A" : u.getNationality().getName()%>
                </td>
                <td width="5px">
                    <form action="userdetail" name="POST">
                        <input type="hidden" name="id" value="<%=z.getId()%>"/>
                    <button type="submit" class="btn btn-danger " name="action" value="delete" >
                        <i class="fas fa-trash-alt"></i>
                    </button>
                    </form>
                </td>
                <td>
                    <form action="userdetail" method="get">
                        <input type="hidden" name="id" value="<%=z.getId()%>"/>
                        <input type="hidden" name="action" value="update"/>
                    <button type="submit" class="btn btn-secondary " value="update" >
                        <i class=" fas fa-pen-square"></i>
                    </button>
                        </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
