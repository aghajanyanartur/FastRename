package art.pro.FastRename.controllers;

import art.pro.FastRename.service.RenameUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    public RenameUtil renameUtil;
    public MainController() {
        this.renameUtil = new RenameUtil();
    }

    @GetMapping("/fast-rename")
    public String homepage() {
        return "homepage";
    }

    @PostMapping("/rename")
    public String rename(
            @RequestParam("directory-path") String directoryPath,
            @RequestParam("additional-name-pattern") String pattern,
            @RequestParam("starting-number") int startNumber,
            @RequestParam("increment-by") int incrementBy,
            @RequestParam("digits-number") int digitsNumber,
            @RequestParam(name = "decrement", defaultValue = "false") boolean decrement,
            @RequestParam(name = "name-pattern-trailing", defaultValue = "false") boolean patternTrailing,
            @RequestParam(name = "sorted", defaultValue = "false") boolean sorted) {
        renameUtil.renameToPattern(directoryPath, pattern, startNumber, incrementBy,
                digitsNumber, !decrement, !patternTrailing, sorted);
        return "homepage";
    }
}
