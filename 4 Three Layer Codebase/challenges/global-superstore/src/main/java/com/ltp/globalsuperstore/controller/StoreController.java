package com.ltp.globalsuperstore.controller;

import javax.validation.Valid;

import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StoreController {

    StoreService itemService = new StoreService();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        int index = itemService.getIndexFromId(id);
        model.addAttribute("item", itemService.getItemfromId(id));
        return "form";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
        if (item.getPrice() < item.getDiscount()) {
            result.rejectValue("price", "", "Price cannot be less than discount");
        }
        if (result.hasErrors()) return "form";

        String status = itemService.handleSubmit(item);
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", itemService.getItems());
        return "inventory";
    }






}