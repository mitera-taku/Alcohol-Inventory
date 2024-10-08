package com.example.demo.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // エラーページを表示するために "error.html" を返す
        return "error";  // この "error" は src/main/resources/templates/error.html を指す
    }

    // Spring Boot 2.3以降では以下のメソッドをオーバーライドしなくてもよい
    public String getErrorPath() {
        return "/error";
    }
}
