package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Balloon;
import mk.finki.ukim.wp.lab.model.Manufacturer;
import mk.finki.ukim.wp.lab.service.BalloonService;
import mk.finki.ukim.wp.lab.service.ManufacturerService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model,
                                  @RequestParam (value = "balloonType", required = false) String balloonType) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Balloon> balloons;
        if (balloonType!= null && !balloonType.isEmpty()) {
            balloons = this.balloonService.searchByNameOrDescription(balloonType);
        }
        else {
            balloons = this.balloonService.listAll();
        }
        model.addAttribute("balloons",balloons);
        return "listBalloons";
    }

    @GetMapping("/add-form")
    public String addBalloonPage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers",manufacturers);
        return "add-balloon";
    }

    @GetMapping("/edit-form/{id}")
    public String editBalloonPage(@PathVariable Long id, Model model) {
        if(this.balloonService.findById(id).isPresent()) {
            Balloon balloon = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("balloon",balloon);
            return "add-balloon";
        }
        return "redirect:/balloons?error=Error";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer,
                              Model model,
                              @RequestParam(required = false) String error) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
            return "redirect:/balloons?error=" + error;
        }
        if(this.balloonService.save(id,name,description,manufacturer).isPresent()) {
            this.balloonService.save(id,name,description,manufacturer);
            return "redirect:/balloons";
        }
        return "redirect:/balloons?error=BAD";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.balloonService.delete(id);
        return "redirect:/balloons";
    }

    @PostMapping("/selectBalloon")
    public String selectBalloon(HttpServletRequest request,@RequestParam(value = "color",required = false) String balloonColor) {
        //String balloonColor = request.getParameter("color");
        if(balloonColor != null && !balloonColor.isEmpty()) {
            request.getSession().setAttribute("balloonColor",balloonColor);
            return "redirect:/selectBalloon";
        }
        return "redirect:/balloons?error=Error";
    }

}
