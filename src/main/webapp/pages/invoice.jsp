<%--
  Created by IntelliJ IDEA.
  User: jayan
  Date: 6/23/2023
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Invoice</title>
</head>
<body>
    <input type="text" id="search-item">

    <ol id="item-list">
    </ol>

    <table border="1">
        <thead>
        <tr>
            <th>Index</th>
            <th>Product Name</th>
            <th>Price (Rs.)</th>
        </tr>
        </thead>
        <tbody id="invoice-table-body">

        </tbody>
    </table>

    <br><br>

    <button style="cursor: pointer" onclick="checkout();">Checkout</button>

    <script src="../assets/js/main.js"></script>
</body>
</html>
