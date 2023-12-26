<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 26/12/2023
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Product</title>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Avatar</th>
                <th>Other Images</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listProducts}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td><img src="${product.avatarImage}" alt="${product.name}" height="50" width="50"/></td>
                    <td>
                        <c:forEach items="${product.listImages}" var="image">
                            <img src="${image.imageLink}" alt="${product.name}" height="50" width="50"/>
                        </c:forEach>
                    </td>
                    <td>${product.status?"Active":"Inactive"}</td>
                    <td></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="<%=request.getContextPath()%>/views/newProduct.jsp">Create New Product</a>
</body>
</html>
