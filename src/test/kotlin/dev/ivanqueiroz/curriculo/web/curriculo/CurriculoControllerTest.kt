package dev.ivanqueiroz.curriculo.web.curriculo

import dev.ivanqueiroz.curriculo.CurriculoApplicationTests
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class CurriculoControllerTest : CurriculoApplicationTests() {

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var curriculoController: CurriculoController

    @BeforeEach
    fun setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(curriculoController).build()
    }

    @Test
    @Throws(Exception::class)
    fun testGetIndexCurriculoController() {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk)
    }


}
