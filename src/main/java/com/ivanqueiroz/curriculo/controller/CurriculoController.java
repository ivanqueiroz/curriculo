package com.ivanqueiroz.curriculo.controller;

import com.ivanqueiroz.curriculo.model.Curriculo;
import com.ivanqueiroz.curriculo.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 */
@Controller
public class CurriculoController {
    
    @Autowired
    private CurriculoRepository curriculos;
    
    @RequestMapping("/")
    public String page(Model model) {
        Curriculo curriculo = curriculos.findOne(1l);
        model.addAttribute("curriculo", curriculo);
        return "index";
    }
    
}
