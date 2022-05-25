package br.edu.iff.projetoConsulta.controller.view;
import br.edu.iff.projetoConsulta.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path ="/consultas")
public class ConsultaViewController {
    @Autowired
    private ConsultaService service;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("consultas", service.findAll());
        return "consultas";
    }

}
