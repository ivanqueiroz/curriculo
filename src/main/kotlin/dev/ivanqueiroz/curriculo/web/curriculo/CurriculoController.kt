package dev.ivanqueiroz.curriculo.web.curriculo

import dev.ivanqueiroz.curriculo.web.util.ContatoCssView
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@Hidden
class CurriculoController {

    @Autowired
    lateinit var curriculoService: CurriculoService

    @RequestMapping("/")
    fun index(model: Model): String {
        val curriculo = curriculoService.obterCurriculoPortugues()
        model.addAttribute("curriculo", curriculo)

        val formacoes = curriculoService.obterHistoricoFormacao()
        model.addAttribute("formacoes", formacoes)

        val certificacoes = curriculoService.obterHistoricoCertificacao()
        model.addAttribute("certificacoes", certificacoes)

        val experiencias = curriculoService.obterHistoricoExperiencia()
        model.addAttribute("experiencias", experiencias)

        val contatos = curriculoService.obterContatos().map { c -> ContatoCssView(c) }
        model.addAttribute("contatos", contatos)

        val conhecimentos = curriculoService.obterConhecimentosEspecificos()
        model.addAttribute("conhecimentos", conhecimentos)

        val linguas = curriculoService.obterConhecimentosLinguas()
        model.addAttribute("linguas", linguas)

        val palestras = curriculoService.obterHistoricoPalestras()
        model.addAttribute("palestras", palestras)

        return "index"
    }

}
