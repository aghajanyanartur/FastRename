package art.pro.FastRename.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/fast-rename")
    public String homepage() {
        return "homepage";
    }
}
