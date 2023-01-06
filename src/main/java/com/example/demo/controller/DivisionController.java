package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.DivisionDAO;
import com.example.demo.model.DbConnection;
import com.example.demo.model.Division;

@Controller
@RequestMapping("division")
public class DivisionController {
    private DivisionDAO ddao = new DivisionDAO(DbConnection.getConnection());

    @GetMapping
    public String index(Model model){
        model.addAttribute("divisions", ddao.getAll());
        return "division/index";
    }

    // CREATE
    // GET
    @GetMapping(value = {"form","form/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model) {
        Division division = new Division();
        if (id!=null) {
            division = ddao.getById(id);
            model.addAttribute("division", division);
        }else{
            model.addAttribute("division", division);
        }

        return "division/form";
    }

    // POST 
    @PostMapping("save")
    public String save(Division division){
        Boolean result;
        if (division.getId() != null) {
            result = ddao.update(division);
        }else {
            result = ddao.insert(division);
        }
        if(result){
            return "redirect:/division";
        } else{
            return "division/form";
        }

    }

    // DELETE
    // POST
    @PostMapping(value= {"delete/{id}"})
    public String delete(@PathVariable(required = false) Integer id) {
        
        ddao.delete(id);

        return "redirect:/division";
    }
    

}
