package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Order;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.service.AuthService;
import mk.finki.ukim.wp.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final AuthService userService;

    public OrderController(OrderService orderService, AuthService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String getOrdersPage(Model model, HttpServletRequest request) {
        List<Order> orders;
        String text = request.getParameter("filter-order");
        if(text==null || text.isEmpty()) {
            orders = this.orderService.findAll();
        }
        else {
            orders = this.orderService.searchByOrderAttribute(text);
        }
        model.addAttribute("orders",orders);
        return "userOrders";
    }

    @PostMapping
    public String placeOrder(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();

        String balloonColor = (String) session.getAttribute("balloonColor");
        String size = (String) session.getAttribute("balloonSize");
        String username = req.getParameter("clientName");
        LocalDateTime orderDate = LocalDateTime.parse(req.getParameter("orderDate"));
        session.setAttribute("orderDate",orderDate);
        model.addAttribute("orderDate",orderDate);
        session.setAttribute("clientName",username);
        if (this.userService.findByUsername(username).isPresent()) {
            User user = this.userService.findByUsername(username).get();
            username = user.getUsername();
        }
        else {
            this.userService.save(new User(username));
        }
            this.orderService.placeOrder(balloonColor, size, username,orderDate);
        return "redirect:/ConfirmationInfo";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/balloons";
    }
}
