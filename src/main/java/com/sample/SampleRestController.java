package com.sample;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class SampleRestController {

    @RequestMapping(method=RequestMethod.GET)
    public String sampleGet() {
        return "sample";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public void samplePost(@RequestBody User user) {
        System.out.print(user);
    }
}
