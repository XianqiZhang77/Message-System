package com.example.demo.controller;

import com.example.demo.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    /*
	@PostMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false) String name,
						@RequestBody(required = false) String body) {
		return String.format("Hello %s %s!", name, body);
	}
	 */

    @GetMapping(path="/hello/{name}/{age}")
    public String hello(@PathVariable("name") String name, @PathVariable("age") String age) {

        String msg = String.format("%s is %s years old", name, age);
        return msg;
    }

    @PostMapping(path="/person")
    public Person person(@RequestBody Person person) throws Exception {

        if (person.getAge() < 18) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "age less than 18");
        }
        return person;
    }

    @GetMapping(path="/allPerson")
    public List<Person> allPerson(@RequestParam(value = "per_page", defaultValue = "20") int perPage,
                                  @RequestParam(value = "page", defaultValue = "1") int page) {
        List<Person> lp = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            lp.add(new Person(i, "male", null));
        }
        List<Person> res = new ArrayList<>();
        int start = (page - 1) * perPage;
        if (start > 50) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        for (int i = start; i < start + perPage && i < lp.size(); i++) {
            res.add(lp.get(i));
        }
        return res;
    }
}
