package bw.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wind on 12/6/2016 AD.
 *
 */
@Controller
public class LoginController {

    @RequestMapping("/test")
    public ModelAndView test() {
        return new ModelAndView("test");
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
