package com.example;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.db.data.UsersDao;
import com.example.domain.entity.Users;

import lombok.Data;
 
@Controller
public class HelloController {
	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
	@Autowired
	private UsersDao usersDao;
	
    @RequestMapping("/")
    public String index(Model model) {
        ArrayList<ViewData> list = new ArrayList<ViewData>();
        for (int i = 0; i < 5; i++) {
            ViewData data = new ViewData();
            StringBuffer buffer = new StringBuffer();
            buffer.append("メッセージだお x");
            buffer.append(i);
            data.setNo(i + 1);
            data.setMessage(buffer.toString());
            list.add(data);
        }
 
        model.addAttribute("msg", list);
        //DB Test
        Users users = new Users("miyake", 27);
        usersDao.insert(users);
        List<Users> usersList = usersDao.selectAll();
        usersList.forEach(u -> LOG.info(u.toString()));
        return "index";
    }
}
 
@Data
class ViewData {
    private int no;
    private String message;
}