<%@ page import="com.tober.inventory.repository.ReportsRepository" %>
<%@ page import="com.tober.inventory.entity.Invoices" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.tober.inventory.entity.Product" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: jayan
  Date: 6/24/2023
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout Report</title>
</head>
<body>
<%
    ReportsRepository reportsRepository = new ReportsRepository();
    List<Invoices> invoices = reportsRepository.loadCheckoutReport();

    Iterator<Invoices> iterator = invoices.iterator();

    while (iterator.hasNext()) {
        Invoices invoice = iterator.next();

%>

<h3>Checkout ID: <span id="checkout-id"><%=invoice.getId()%></span></h3>
<span>Date: <span id="date"><%=invoice.getDate()%></span></span>&nbsp;&nbsp;
<span>Qty: <span id="qty"></span></span><br>
<span>Items:
        <ol>
            <%

                Set<Product> products = invoice.getProducts();
                Iterator<Product> iterator2 = products.iterator();

                double total = 0.0;

                while (iterator2.hasNext()) {
                    Product product = iterator2.next();
                    total += product.getPrice();
            %>

<li><%=product.getName()%></li>
            <%

                }

            %>
        </ol>
    </span>
<span>Total: <span id="total"><%=total%></span></span>&nbsp;&nbsp;

<%

    }
%>

</body>
</html>
