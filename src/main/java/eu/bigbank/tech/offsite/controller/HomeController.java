package eu.bigbank.tech.offsite.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.debugger.NoSuchSymbolException;

@RestController
public class HomeController {
    @RequestMapping(value="/", produces = "text/plain")
    public String index() {
        return "Hello! \n" + System.currentTimeMillis();
    }
}
