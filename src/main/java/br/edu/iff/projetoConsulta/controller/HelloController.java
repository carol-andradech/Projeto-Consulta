package br.edu.iff.projetoConsulta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @GetMapping("/hello")
    //vou retornar um objeto da classe ModelAndView
    public ModelAndView hello(){
        //crio um objeto da classe ModelAndView
        ModelAndView mv = new ModelAndView("hello"); //nome do arquivo html a ser renderizado
        mv.addObject("nome", "Carolina");
        return mv; //o spring vai renderizar o arquivo templates.hello.html
    }

    @GetMapping("/hello-model")
    public String hello(Model model){
        model.addAttribute("nome", "Carol");
        return "hello"; //o spring vai renderizar o arquivo templates.hello.html
    }

    @GetMapping("/hello-servlet")
    public String hello(HttpServletRequest request){
        request.setAttribute("nome", "Annabeth");
        return "hello"; //o spring vai renderizar o arquivo templates.hello.html
    }
}