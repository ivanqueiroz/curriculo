package dev.ivanqueiroz.curriculo.rest.conhecimento

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/conhecimentos")
@Api(value = "Conhecimentos", description = "Conhecimentos profissionais de Ivan Queiroz")
class ConhecimentoRestController {

    @Autowired
    lateinit var conhecimentoService: ConhecimentoService

    @ApiOperation(value = "Obter um conhecimento pelo id")
    @GetMapping(value = ["/{id}"])
    fun conhecimento(@PathVariable id: Long): HttpEntity<ConhecimentoResource> {
        val contatoResource = conhecimentoService.obterConhecimentoPorId(id)
        return ResponseEntity(contatoResource, HttpStatus.OK)
    }

    @ApiOperation(value = "Obter todos os conhecimentos")
    @GetMapping("/lista")
    fun conhecimentos(): ResponseEntity<List<ConhecimentoResource>>{
        val todosConhecimentos = conhecimentoService.obterTodosConhecimentos();
        return ResponseEntity(todosConhecimentos,HttpStatus.OK)
    }

    @ApiOperation(value = "Obter todos os conhecimentos referentes a um assunto informado")
    @GetMapping
    fun conhecimentosPorAssunto(@RequestParam("assunto") assunto: String):ResponseEntity<List<ConhecimentoResource>>{
        val conhecimentosPeloAssunto = conhecimentoService.obterConhecimentoPeloAssunto(assunto)
        return ResponseEntity(conhecimentosPeloAssunto, HttpStatus.OK)
    }

}