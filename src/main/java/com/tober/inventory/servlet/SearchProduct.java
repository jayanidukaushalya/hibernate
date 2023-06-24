package com.tober.inventory.servlet;

import com.google.gson.Gson;
import com.tober.inventory.entity.Product;
import com.tober.inventory.repository.InvoiceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "SearchProduct", urlPatterns = "/searchproductprocessing")
public class SearchProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String text = req.getParameter("text");

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        List<Product> searchItemList = invoiceRepository.getSearchItemList(text);

        // Iterator<Product> iterator = searchItemList.iterator();

        // while (iterator.hasNext()) {
            // Product product = iterator.next();
            // System.out.println(product.getInvoices().);
        // }

        Gson gson = new Gson();
        String json = gson.toJson(searchItemList);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
