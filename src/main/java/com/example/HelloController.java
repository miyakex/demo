package com.example;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;
 
@Controller
public class HelloController {
 
    @RequestMapping("/")
    public String index(Model model) {
        ArrayList<ViewData> list = new ArrayList<ViewData>();
        for (int i = 0; i < 5; i++) {
            ViewData data = new ViewData();
            StringBuffer buffer = new StringBuffer();
            buffer.append("メッセージだお");
            buffer.append(i);
            data.setNo(i + 1);
            data.setMessage(buffer.toString());
            list.add(data);
        }
 
        model.addAttribute("msg", list);
        return "index";
    }
}
 
@Data
class ViewData {
    private int no;
    private String message;
}