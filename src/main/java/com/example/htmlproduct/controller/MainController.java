package com.example.htmlproduct.controller;

import com.example.htmlproduct.models.CartData;
import com.example.htmlproduct.models.Post;
//import com.example.htmlproduct.repo.PostRepository;
import com.example.htmlproduct.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    private Post post;
    @Autowired
    private final PostRepository postRepository;

    public MainController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
//    private PostRepository postRepository;

    @GetMapping("/")
    public String main(Model model) {
        return "main-page";
    }

    @GetMapping("/purchase")
    public String purchase(Model model) {
        BigDecimal money = new BigDecimal(post.getMoney());
        BigDecimal crypto = new BigDecimal(post.getCrypto());
        model.addAttribute("money", money);
        model.addAttribute("crypto", crypto);
        return "purchase";
    }


    @PostMapping("/purchase")
    public String postpurchase(@RequestParam String money, @RequestParam String crypto) {
        try {
            if (Float.parseFloat(money) < 5000) {
                return "redirect:/";
            } else {
                post = new Post(Float.parseFloat(money), Float.parseFloat(crypto));
                return "redirect:/purchase";
            }
        } catch (NumberFormatException e) {
            return "redirect:/";
        }
    }



    @GetMapping("/purchase/credit-cart")
    public String creditcart(Model model) {
        System.out.println("in get");
        List<String> month_array = new ArrayList<String>();
        month_array.add("01");
        month_array.add("02");
        month_array.add("03");
        month_array.add("04");
        month_array.add("05");
        month_array.add("06");
        month_array.add("07");
        month_array.add("08");
        month_array.add("09");
        month_array.add("10");
        month_array.add("11");
        month_array.add("12");
        model.addAttribute("month_array", month_array);
        List<String> year_array = new ArrayList<String>();
        year_array.add("2023");
        year_array.add("2024");
        year_array.add("2025");
        year_array.add("2026");
        year_array.add("2027");
        year_array.add("2028");
        year_array.add("2029");
        year_array.add("2030");
        year_array.add("2031");
        year_array.add("2032");
        year_array.add("2033");
        year_array.add("2034");
        model.addAttribute("year_array", year_array);
        return "credit-cart";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {

    }

    @PostMapping("/purchase/credit-cart")
    public String blogPostAdd(@RequestParam String number, @RequestParam String holders, @RequestParam String month, @RequestParam String year, @RequestParam String cvv, Model model){

        if (number.length() == 19 && holders.length() != 0 && month.length() != 0 && year.length() != 0 && cvv.length() != 0) {
            CartData cartData = new CartData(number, holders, month, year, cvv, post.getMoney());
            postRepository.save(cartData);
            return "redirect:/";
        } else {
            return "redirect:/purchase/credit-cart";
        }
    }
}
