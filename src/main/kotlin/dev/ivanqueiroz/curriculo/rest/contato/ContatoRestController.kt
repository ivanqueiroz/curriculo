package dev.ivanqueiroz.curriculo.rest.contato

import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContatoConverter
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/contatos")
@Api(value = "Contatos", description = "Meios de contato")
class ContatoRestController {

    @Autowired
    lateinit var contatoRestService: ContatoRestService

    @ApiOperation(value = "Obter um contato pelo id")
    @GetMapping(value = ["/{id}"])
    fun contato(@PathVariable id: Long): HttpEntity<ContatoResource> {
        val contatoResource = contatoRestService.obterContatoPorId(id)
        return ResponseEntity<ContatoResource>(contatoResource, HttpStatus.OK)
    }

    @ApiOperation(value = "Obter um contato por Tipo")
    @GetMapping
    fun contatoPorTipo(@RequestParam("tipo") tipo: TipoContato): HttpEntity<ContatoResource> {
        val contatoResource = contatoRestService.obterContatoPorTipo(tipo)
        return ResponseEntity<ContatoResource>(contatoResource, HttpStatus.OK)
    }

    @ApiOperation(value = "Listar todos os contatos")
    @GetMapping("/lista")
    fun contatos(): ResponseEntity<List<ContatoResource>>{
        val todosContatos = contatoRestService.obterTodosContatos();
        return ResponseEntity(todosContatos, HttpStatus.OK)
    }

    @InitBinder
    fun initBinder(webdataBinder: WebDataBinder) {
        webdataBinder.registerCustomEditor(TipoContato::class.java, TipoContatoConverter())
    }

}
