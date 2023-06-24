<%@ page import="com.tober.inventory.repository.ProductRepository" %>
<%@ page import="com.tober.inventory.entity.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jayan
  Date: 6/22/2023
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Products</title>
</head>
<body>
<h1>Add Products</h1>

<input type="text" id="name" placeholder="Product Name">
<input type="number" id="price" placeholder="Price">

<button onclick="addProduct();">Add Item</button>

<br><br>

<table border="1">
    <thead>
    <tr>
        <th>Index</th>
        <th>Product Name</th>
        <th>Price (Rs.)</th>
    </tr>
    </thead>
    <tbody id="product-table-body">
    <%
        ProductRepository productRepository = new ProductRepository();
        List<Product> allProducts = productRepository.getAllProducts();

        int index = 0;

        for (Product product : allProducts) {
            ++index;
    %>
    <tr>
        <td><%=index%></td>
        <td><%=product.getName()%></td>
        <td><%=product.getPrice()%></td>
    </tr>
    <%
        }
    %>

    </tbody>
</table>

<script src="../assets/js/main.js"></script>
</body>
</html>
