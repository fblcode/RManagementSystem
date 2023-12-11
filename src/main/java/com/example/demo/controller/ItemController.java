package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public String viewItemHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Item> items = itemService.getAllItems(keyword);
        model.addAttribute("listItems", items);
        model.addAttribute("keyword", keyword);
        return findPaginatedItem(1, "itemName", "asc", keyword, model);
    }

    @GetMapping("/showNewItemForm")
    public String showNewItemForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "new_item";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item) {
    	itemService.saveItem(item);
        return "redirect:/item";
    }

    @GetMapping("/showFormForUpdateItem/{itemID}")
    public String showFormForItemUpdate(@PathVariable(value = "itemID") Integer itemID, Model model) {

        Item item = itemService.getItemById(itemID);

        model.addAttribute("item", item);
        return "update_item";
    }

    @GetMapping("/deleteItem/{itemID}")
    public String deleteItem(@PathVariable(value = "itemID") Integer itemID) {

        this.itemService.deleteItemById(itemID);
        return "redirect:/item";
    }


    @GetMapping("/item/page/{pageNo}")
    public String findPaginatedItem(
        @PathVariable(value = "pageNo") Integer pageNo,
        @RequestParam(value = "sortField", required = false, defaultValue = "itemName") String sortField,
        @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
        @RequestParam(value = "keyword", required = false) String keyword,
        Model model) {

        int pageSize = 8;

        Page<Item> page = itemService.findPaginatedItem(pageNo, pageSize, sortField, sortDir, keyword);
        List<Item> listItems = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listItems", listItems);
        return "item";
    }
}