package dev.ivanqueiroz.curriculo.rest.contato

import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContatoConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/contatos")
class ContatoRestController {

    @Autowired
    lateinit var contatoService: ContatoService

    @GetMapping(value = ["/{id}"])
    fun contato(@PathVariable id: Long): HttpEntity<ContatoResource> {
        val contatoResource = contatoService.obterContatoPorId(id)
        return ResponseEntity<ContatoResource>(contatoResource, HttpStatus.OK)
    }

    @GetMapping
    fun contatoPorTipo(@RequestParam("tipo") tipo: TipoContato): HttpEntity<ContatoResource> {
        val contatoResource = contatoService.obterContatoPorTipo(tipo)
        return ResponseEntity<ContatoResource>(contatoResource, HttpStatus.OK)
    }

    @GetMapping("/lista")
    fun contatos(): ResponseEntity<List<ContatoResource>>{
        val todosContatos = contatoService.obterTodosContatos();
        return ResponseEntity(todosContatos, HttpStatus.OK)
    }

    @InitBinder
    fun initBinder(webdataBinder: WebDataBinder) {
        webdataBinder.registerCustomEditor(TipoContato::class.java, TipoContatoConverter())
    }

}
