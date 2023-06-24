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

@WebServlet(name = "AddItemToInvoiceTable", urlPatterns = "/additemtoinvoicetableproccesing")
public class AddItemToInvoiceTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        Product itemUsingId = invoiceRepository.getItemUsingId(id);

        Gson gson = new Gson();
        String json = gson.toJson(itemUsingId);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
