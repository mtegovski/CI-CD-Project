package mk.finki.ukim.wp.lab.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloon-order",urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        String balloonColor = (String) context.getSession().getAttribute("balloonColor");
        context.setVariable("balloonColor",balloonColor);
        String balloonSize = (String) context.getSession().getAttribute("balloonSize");
        context.setVariable("balloonSize",balloonSize);
        this.springTemplateEngine.process("deliveryInfo.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        req.getSession().setAttribute("clientName",clientName);
        String clientAddress = req.getParameter("clientAddress");
        req.getSession().setAttribute("clientAddress",clientAddress);
        resp.sendRedirect("/ConfirmationInfo");
    }
}
