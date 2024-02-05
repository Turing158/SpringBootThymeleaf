package springDemo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

//testController.java
@Controller
public class testController {
    @RequestMapping(value = {"/","/test"})
    public String start(HttpSession session){
        session.setAttribute("page",0);
        return "test";
    }
    @RequestMapping("/test1")
    public String test1(HttpSession session){
        session.setAttribute("page",1);
        if(session.getAttribute("color")==null){
            session.setAttribute("color","Red");
        }
        return "test";
    }
    @RequestMapping("/changeIdRed")
    public String changeIdRed(HttpSession session){
        session.setAttribute("color","Red");
        return "redirect:/test1";
    }
    @RequestMapping("/changeIdBlue")
    public String changeIdBlue(HttpSession session){
        session.setAttribute("color","Blue");
        return "redirect:/test1";
    }
    @RequestMapping("/test2")
    public String test2(HttpSession session){
        session.setAttribute("page",2);
        if(session.getAttribute("test2Value")==null){
            session.setAttribute("test2Value",false);
        }
        return "test";
    }
    @RequestMapping("/changeTest2True")
    public String changeTest2True(HttpSession session){
        session.setAttribute("test2Value",true);
        return "redirect:/test2";
    }
    @RequestMapping("/changeTest2False")
    public String changeTest2False(HttpSession session){
        session.setAttribute("test2Value",false);
        return "redirect:/test2";
    }

    @RequestMapping("/test3")
    public String test3(HttpSession session){
        session.setAttribute("page",3);
        String text = "<span color=‘red’>This is a test</span><br/><span color=‘blue’>测试文字</span><br/><span color=‘green’>テストテキスト</span><br/><span color=‘yellow’>Тестовый текст</span>";
        session.setAttribute("test3text",text);
        return "test";
    }

    @RequestMapping("/test4")
    public String test4(HttpSession session){
        session.setAttribute("page",4);
        if(session.getAttribute("test4Value")==null){
            session.setAttribute("test4Value",new ArrayList<Integer>());
        }
        return "test";
    }
    @RequestMapping("/addTest4")
    public String changeTest3Add(HttpSession session){
        ArrayList<Integer> value = (ArrayList<Integer>)session.getAttribute("test4Value");
        value.add(value.size()+1);
        session.setAttribute("test4Value",value);
        return "redirect:/test4";
    }
    @RequestMapping("/delTest4")
    public String changeTest3Sub(HttpSession session){
        ArrayList<Integer> value = (ArrayList<Integer>)session.getAttribute("test4Value");
        if(!value.isEmpty()){
            value.remove(value.size()-1);
        }
        session.setAttribute("test4Value",value);
        return "redirect:/test4";
    }
    @RequestMapping("/test5")
    public String test5(HttpSession session){
        session.setAttribute("page",5);
//        if(session.getAttribute("test5Value")==null){
//            session.setAttribute("test5Value",null);
//        }
        return "test";
    }
    @RequestMapping("/submitTest5")
    public String changeTest5(HttpSession session, @RequestParam String value){
        session.setAttribute("test5Value",value);
        return "redirect:/test5";
    }
    @RequestMapping("/test6")
    public String test6(HttpSession session){
        session.setAttribute("page",6);
        return "test";
    }
    @RequestMapping("/submitTest6")
    public String changeTest6(HttpSession session, @RequestParam String color, @RequestParam String background_color){
        session.setAttribute("test6Color",color);
        session.setAttribute("test6BgColor",background_color);
        return "redirect:/test6";
    }
    @RequestMapping("/test7")
    public String test7(HttpSession session){
        session.setAttribute("page",7);
        return "test";
    }
    @RequestMapping("/submitTest7")
    public String changeTest7(HttpSession session, @RequestParam String value){
        int val = 0;
        if(value!=null && !value.isEmpty()){
            val = Integer.parseInt(value);
        }
        session.setAttribute("test7Value",val);
        return "redirect:/test7";
    }
    @RequestMapping("/test8")
    public String test8(HttpSession session){
        session.setAttribute("page",8);

        return "test";
    }
    @RequestMapping("/submitTest8")
    public String changeTest8(HttpSession session, @RequestParam String value){
        session.setAttribute("test8Value",value);
        return "redirect:/test8";
    }
}
