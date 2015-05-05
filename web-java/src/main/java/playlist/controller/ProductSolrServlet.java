package playlist.controller;

import com.google.common.collect.Maps;
import playlist.model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * DataStax Academy Sample Application
 *
 * Copyright 2013 DataStax
 *
 */

public class ProductSolrServlet extends JinjaServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    Map<String, Object> context = Maps.newHashMap();

    String search_term = request.getParameter("s");
    String filter_by = request.getParameter("filter_by");

    List<ProductDAO> products = ProductDAO.getProductsSolrQuery(search_term,filter_by);

    context.put("products", products);

    String renderedTemplate = render("/search_list.jinja2", context);
    out.println(renderedTemplate);

  }
}