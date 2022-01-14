package mk.finki.ukim.wp.lab.web.servlet;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Order;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "confirm-servlet",urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        String balloonColor = (String) context.getSession().getAttribute("balloonColor");
        context.setVariable("balloonColor",balloonColor);
        String balloonSize = (String) context.getSession().getAttribute("balloonSize");
        context.setVariable("balloonSize",balloonSize);
        String clientName = (String) context.getSession().getAttribute("clientName");
        context.setVariable("clientName",clientName);
        String clientAddress = (String) context.getSession().getAttribute("clientAddress");
        context.setVariable("clientAddress",clientAddress);
        context.setVariable("ipAddress",req.getRemoteAddr());
        String [] browserParts = req.getHeader("User-Agent").split("\\s+");
        context.setVariable("browser",browserParts[8]);
        this.springTemplateEngine.process("confirmationInfo.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloonColor = (String) req.getSession().getAttribute("balloonColor");
        String balloonSize = (String) req.getSession().getAttribute("balloonSize");
        LocalDateTime orderDate = LocalDateTime.parse(req.getParameter("orderDate"));
        req.getSession().setAttribute("orderDate",orderDate);
        Order order = new Order(balloonColor,balloonSize);
        DataHolder.orders.add(order);
        req.getSession().setAttribute("orders",DataHolder.orders);
        resp.sendRedirect("/orders");
    }
}
