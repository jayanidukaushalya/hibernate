package com.tober.inventory.servlet;

import com.tober.inventory.entity.Product;
import com.tober.inventory.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

@WebServlet(name = "AddProduct", urlPatterns = "/addproductprocessing")
public class AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Double price = Double.parseDouble(req.getParameter("price"));

        Integer status = 1;

        // resp.getWriter().write(name + " " + price);

        ProductRepository productRepository = new ProductRepository();
        productRepository.addProduct(name, price, status);

        List<Product> allProducts = productRepository.getAllProducts();

            Gson gson = new Gson();
            String jsonAllProducts = gson.toJson(allProducts);

            resp.setContentType("application/json");
            resp.getWriter().write(jsonAllProducts);
    }
}
