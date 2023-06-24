package com.tober.inventory.servlet;

import com.google.gson.Gson;
import com.tober.inventory.repository.InvoiceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Checkout", urlPatterns = "/checkout")
public class Checkout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        ids.replace("[", "").replace("]", "").replaceAll("\\s+", "");
        String[] splitIDs = ids.split(",");

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        invoiceRepository.insertCheckoutData(splitIDs);

        resp.getWriter().write("3131231");
    }
}
