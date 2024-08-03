package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.modelclass;
import com.example.demo.repository.mvcrepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class mvcmodelcontroller {
    @Autowired
    mvcrepository repo;
    @RequestMapping("/welcome")
    public String home() {
    	return "Welcome.html";
    }
    @GetMapping("/loadform")
    public String loadform() {
    	return "add";
    }
    @PostMapping("/insert")
    public String insertion(@ModelAttribute modelclass m,HttpSession session) {
    	repo.save(m);
    	session.setAttribute("msg","successfully inserted");
    	return "redirect:/loadform";
    }
    @DeleteMapping("/delete/{id}")
    public String deletebyid(@PathVariable(value = "id") int id,HttpSession session) {
    	repo.deleteById(id);
    	session.setAttribute("msg", "successfully deleted");
    	return "redirect:/";
    }
    @GetMapping("/getbyid/{id}")
    public String getbyid(@PathVariable(value = "id")int id,HttpSession session,Model mo) {
    Optional<modelclass> m = repo.findById(id);
     modelclass mc = m.get();
//     session.setAttribute(null, mc);
     mo.addAttribute("products",mc);
     return "edit";
    }
    @GetMapping("/getall")
    public String findall(Model m) {
    	List<modelclass> mc =(List<modelclass>) repo.findAll();//type casting
        m.addAttribute("allproducts",mc);
        return "home";
    }
    @PutMapping("/update")
    public String update(@ModelAttribute modelclass mc ,HttpSession session) {
    	repo.save(mc);
    	session.setAttribute("msg", "successfullyupdated");
    	return "redirect:/";
    	
    }
}
