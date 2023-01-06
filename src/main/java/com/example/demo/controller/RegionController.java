package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.RegionDAO;
import com.example.demo.model.DbConnection;
import com.example.demo.model.Region;

//request mapping region buat auto tambah tulisan reigon di url localhost:8088/region untuk setiap method di regioncontroller, jadi kalo getmapping kosong langsung kebaca /region, yg "form" kebaca region/form
// tulis /region ato region aja sama 
@Controller
@RequestMapping("region")
public class RegionController {
    private RegionDAO rdao = new RegionDAO(DbConnection.getConnection());
    
    @GetMapping
    public String index(Model model){
        //method ini biar pas kita request ke localhost:8088/region code dibawah dilakuin, trs return nampilin view di "region/index" 
        // Object region = rdao.getAll();
        model.addAttribute("regions", rdao.getAll());
        
        return "region/index";
    }

    // CREATE
    // GET render page
    // /region/form
    // @GetMapping({"form"}) @GetMapping("form") ato pake value sama
    @GetMapping(value = {"form","form/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model) {
        Region region = new Region();
        if (id!=null) {
            region = rdao.getById(id);
            model.addAttribute("region", region);
        }else{
            model.addAttribute("region", region);
        }

        return "region/form";
    }
    //method path dikasi path variable, 
    // POST ngirim data ke server buat diinsert(pas save form)
    @PostMapping("save")
    public String save(Region region){
        //Boolean result = rdao.insert(region);
        Boolean result;
        if (region.getId() != null) {
            result = rdao.update(region);
        }else {
            result = rdao.insert(region);
        }

        // if (region.getId() == null){
        //     result = rdao.insert(region);
        // }else {
        //     result = rdao.update(region);
        // }
        
        if(result){
            return "redirect:/region";
        } else{
            return "region/form";
        }

    }

    // UPDATE
    // GET
    // @GetMapping("form/{id}")
    // public String update(@PathVariable(required = false) Integer id, Model model){
    //     Region region = new Region();
    //     region = rdao.getById(id);
    //     model.addAttribute("region", region);

    //     return "region/form";
    // }

    //POST
    //POST nya pake punya form krn sama htmlnya
    

    // DELETE
    // POST
    @PostMapping(value= {"delete/{id}"})
    public String delete(@PathVariable(required = false) Integer id) {
        
        rdao.delete(id);

        return "redirect:/region";
    }


}
