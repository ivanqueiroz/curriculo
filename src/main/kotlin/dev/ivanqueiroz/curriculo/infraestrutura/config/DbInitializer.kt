package dev.ivanqueiroz.curriculo.infraestrutura.config

import dev.ivanqueiroz.curriculo.dominio.conhecimento.Conhecimento
import dev.ivanqueiroz.curriculo.dominio.conhecimento.ConhecimentoRepository
import dev.ivanqueiroz.curriculo.dominio.conhecimento.TipoConhecimento
import dev.ivanqueiroz.curriculo.dominio.contato.Contato
import dev.ivanqueiroz.curriculo.dominio.contato.ContatoRepository
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato
import dev.ivanqueiroz.curriculo.dominio.curriculo.Curriculo
import dev.ivanqueiroz.curriculo.dominio.curriculo.CurriculoRepository
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import dev.ivanqueiroz.curriculo.dominio.historico.HistoricoRepository
import dev.ivanqueiroz.curriculo.dominio.historico.TipoHistorico
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(prefix = "banco", name = ["inicializar"], havingValue = "true")
class DbInitializer : CommandLineRunner {

    @Autowired
    lateinit var curriculoRepositorio: CurriculoRepository;

    @Autowired
    lateinit var historicoRepository: HistoricoRepository

    @Autowired
    lateinit var conhecimentoRepository: ConhecimentoRepository

    @Autowired
    lateinit var contatoRepository: ContatoRepository

    override fun run(vararg args: String?) {

        contatoRepository.deleteAll()
        conhecimentoRepository.deleteAll()
        historicoRepository.deleteAll()
        curriculoRepositorio.deleteAll()
        val curriculo = preencheCurriculo()
        curriculoRepositorio.save(curriculo)
    }

    private fun preencheCurriculo(): Curriculo {
        val curriculo = Curriculo(1L)
        curriculo.primeiroNome = "IVAN"
        curriculo.ultimoNome = "QUEIROZ"
        curriculo.profissao = "ARQUITETO DE SOFTWARE"
        curriculo.resumo = """Mais de 10 anos de experiência em desenvolvimento de software, desenhando, construindo, testando e
                |depurando. Profunda experiência na plataforma Java na construção de soluções web, desktop, backend e middleware.
                |Sempre busco aprender novas tecnologias, ferramentas e metodologias ligadas a construção de software com o
                |objetivo de melhorar minhas habilidades na construção de software. Acostumado a cuidar de sistemas em ambientes
                |críticos e de alto valor para a empresa, assim como legados. Ultimamente venho aplicando palestras e cursos, com isso
                |compartilhando e construindo conhecimento com a experiência.""".trimMargin()

        val historicos: MutableList<Historico> = mutableListOf()

        //EXPERIENCIA
        preencheHistorico(historicos)

        //CERTIFICAÇÕES
        preencheCertificacoes(historicos)

        preencheEducacao(historicos)

        //PALESTRAS
        preenchePalestras(historicos)

        curriculo.historicos = historicos

        //CONTATOS
        preencheContatos(curriculo)

        preencheConhecimentos(curriculo)

        return curriculo
    }

    private fun preencheHistorico(historicos: MutableList<Historico>) {
        var historicoIndra = Historico("Arquiteto de Software")
        historicoIndra.instituicao = "INDRA"
        historicoIndra.anoInicio = "2011"
        historicoIndra.descricao = "Especificação, desenvolvimento e manutenção de sistemas em Java EE e C++ para a área de relacionamento com o cliente e jurídico da Telefonica."
        historicoIndra.tipoHistorico = TipoHistorico.EXPERIENCIA
        historicos.add(historicoIndra)

        var historicoCpmBraxis = Historico("Analista de Sistemas Pleno")
        historicoCpmBraxis.instituicao = "Capgemini"
        historicoCpmBraxis.anoInicio = "2010"
        historicoCpmBraxis.anoFim = "2011"
        historicoCpmBraxis.descricao = "Desenvolvimento de sistemas em Java Web e C++ Builder para a área de relacionamento com o cliente da Vivo."
        historicoCpmBraxis.tipoHistorico = TipoHistorico.EXPERIENCIA
        historicos.add(historicoCpmBraxis)

        var historicoDba = Historico("Analista de Sistemas Júnior")
        historicoDba.instituicao = "DBA Engenharia de Sistemas"
        historicoDba.anoInicio = "2008"
        historicoDba.anoFim = "2010"
        historicoDba.descricao = "Desenvolvimento de sistemas em Java Web e C++ Builder para a área de relacionamento com o cliente da Vivo."
        historicoDba.tipoHistorico = TipoHistorico.EXPERIENCIA
        historicos.add(historicoDba)

        var historicoDds = Historico("Analista de Telecomunicações")
        historicoDds.instituicao = "DDS Telecomunicações"
        historicoDds.anoInicio = "2006"
        historicoDds.anoFim = "2008"
        historicoDds.descricao = "Controle e monitoria da infra-estrutura da rede de voz do callcenter Salvador da Vivo. Desenvolvimento de sistema de monitoria de URA em PHP."
        historicoDds.tipoHistorico = TipoHistorico.EXPERIENCIA
        historicos.add(historicoDds)

        var historicoAtento = Historico("Analista de Microinformática")
        historicoAtento.instituicao = "Atendo do Brasil"
        historicoAtento.anoInicio = "2005"
        historicoAtento.anoFim = "2006"
        historicoAtento.descricao = "Monitoria e suporte da infra de telefonia, Help Desk e controle de patrimônio da filial Salvador."
        historicoAtento.tipoHistorico = TipoHistorico.EXPERIENCIA
        historicos.add(historicoAtento)

        var historicoCpm = Historico("Operador de Telecomunicações")
        historicoCpm.instituicao = "Capgemini"
        historicoCpm.anoInicio = "2004"
        historicoCpm.anoFim = "2004"
        historicoCpm.descricao = "Monitoria e suporte da infra de telefonia e Help Desk do cliente Contax."
        historicoCpm.tipoHistorico = TipoHistorico.EXPERIENCIA
        historicos.add(historicoCpm)

        var historicoHp = Historico("Analista Técnico")
        historicoHp.instituicao = "HP do Brasil"
        historicoHp.anoInicio = "2002"
        historicoHp.anoFim = "2004"
        historicoHp.descricao = "Help Desk dos clientes Oribitall, Credicard e Redecard."
        historicoHp.tipoHistorico = TipoHistorico.EXPERIENCIA
        historicos.add(historicoHp)
    }

    private fun preencheCertificacoes(historicos: MutableList<Historico>) {
        var certificacaoOcjp = Historico("OCJP - Oracle Certified Programmer for Java 5")
        certificacaoOcjp.tipoHistorico = TipoHistorico.CERTIFICACOES
        certificacaoOcjp.descricao = "Oracle"
        certificacaoOcjp.anoFim = "2009"
        historicos.add(certificacaoOcjp)

        var certificacaoScrum = Historico("Certified Scrum Master")
        certificacaoScrum.tipoHistorico = TipoHistorico.CERTIFICACOES
        certificacaoScrum.descricao = "Scrum Alliance"
        certificacaoScrum.anoFim = "2011"
        historicos.add(certificacaoScrum)

        var certificacaoMta = Historico("MTA: Database Administration Fundamentals")
        certificacaoMta.tipoHistorico = TipoHistorico.CERTIFICACOES
        certificacaoMta.descricao = "Microsoft"
        certificacaoMta.anoFim = "2011"
        historicos.add(certificacaoMta)
    }

    private fun preencheEducacao(historicos: MutableList<Historico>) {
        //EDUCAÇÃO
        var educacaoFib = Historico("Bacharelado em Sistemas de Informação")
        educacaoFib.anoFim = "2007"
        educacaoFib.descricao = "Completado em março de 2007 no Centro Universitário Estácio da Bahia"
        educacaoFib.tipoHistorico = TipoHistorico.EDUCACAO
        historicos.add(educacaoFib)

        var educacaoMbaTelecom = Historico("MBA Profissional em Análise de Sistemas e Telecomunicações")
        educacaoMbaTelecom.anoFim = "2011"
        educacaoMbaTelecom.descricao = "Graduação realizada na Escola Superior Aberta do Brasil (ESAB), completado em março de 2011."
        educacaoMbaTelecom.tipoHistorico = TipoHistorico.EDUCACAO
        historicos.add(educacaoMbaTelecom)

        var educacaoMbaProjetoSistemas = Historico("Especialização em Análise, Projeto e Gerência de Sistemas")
        educacaoMbaProjetoSistemas.anoFim = "2012"
        educacaoMbaProjetoSistemas.descricao = "Completado em junho de 2012 no Centro Universitário Estácio da Bahia"
        educacaoMbaProjetoSistemas.tipoHistorico = TipoHistorico.EDUCACAO
        historicos.add(educacaoMbaProjetoSistemas)

        var educacaoGerenciaProjetos = Historico("MBA em Gerência de Projetos")
        educacaoGerenciaProjetos.anoFim = "2019"
        educacaoGerenciaProjetos.descricao = "Em curso com previsão de finalização em maio 2019"
        educacaoGerenciaProjetos.tipoHistorico = TipoHistorico.EDUCACAO
        historicos.add(educacaoGerenciaProjetos)
    }

    private fun preencheConhecimentos(curriculo: Curriculo) {
        //CONHECIMENTOS
        var conhecimentoJava = Conhecimento("Java SE", 0.75f, TipoConhecimento.ESPECIFICO)
        var conhecimentoJavaEE = Conhecimento("Java EE", 0.60f, TipoConhecimento.ESPECIFICO)
        var conhecimentoSpring = Conhecimento("Spring", 0.50f, TipoConhecimento.ESPECIFICO)
        var conhecimtnoCplusPlus = Conhecimento("C++ Builder", 0.40f, TipoConhecimento.ESPECIFICO)
        var conhecimentoSql = Conhecimento("SQL", 0.60f, TipoConhecimento.ESPECIFICO)
        var conhemcimentoPhp = Conhecimento("PHP", 0.25f, TipoConhecimento.ESPECIFICO)
        var conhecimentoIngles = Conhecimento("Inglês", 0.8f, TipoConhecimento.LINGUAS)

        curriculo.conhecimentos = arrayListOf(conhecimentoJava, conhecimentoJavaEE, conhecimentoSpring, conhecimtnoCplusPlus, conhecimentoSql, conhemcimentoPhp, conhecimentoIngles)
    }

    private fun preencheContatos(curriculo: Curriculo) {
        var contatoTelefone = Contato("5571987731477", "Telefone", TipoContato.TELEFONE)
        var contatoEmail = Contato("ivanqueiroz@gmail.com", "E-Mail", TipoContato.EMAIL)
        var contatoTwitter = Contato("https://twitter.com/ivanqueiroz", "Twitter", TipoContato.TWITTER)
        var contatoFacebook = Contato("https://www.facebook.com/ivan.queiroz/", "Facebook", TipoContato.FACEBOOK)
        var contatoSkype = Contato("ifbcqueiroz", "Skype", TipoContato.SKYPE)
        var contatoLinkedin = Contato("https://www.linkedin.com/in/ivanqueiroz/", "Linkedin", TipoContato.LINKEDIN)

        curriculo.contatos = arrayListOf(contatoTelefone, contatoEmail, contatoTwitter, contatoFacebook, contatoSkype, contatoLinkedin)
    }

    private fun preenchePalestras(historicos: MutableList<Historico>) {
        var palestraNoSqlBa032018 = Historico("Spring Data Neo4J com Spring Boot")
        palestraNoSqlBa032018.anoFim = "2018"
        palestraNoSqlBa032018.descricao = "NoSql – BA: Conferência Baiana de NoSql 2018"
        palestraNoSqlBa032018.linkReferencia = "http://www.nosqlba.org/2017/index.html"
        palestraNoSqlBa032018.instituicao = "UCSAL"
        palestraNoSqlBa032018.tipoHistorico = TipoHistorico.PALESTRAS_APLICADAS
        historicos.add(palestraNoSqlBa032018)

        var palestraNoSqlBa032017 = Historico("Neo4J – Banco Orientado a Grafos")
        palestraNoSqlBa032017.anoFim = "2017"
        palestraNoSqlBa032017.descricao = "NoSql – BA: Conferência Baiana de NoSql 2017"
        palestraNoSqlBa032017.linkReferencia = "http://www.nosqlba.org/2017/index.html"
        palestraNoSqlBa032017.instituicao = "UCSAL"
        palestraNoSqlBa032017.tipoHistorico = TipoHistorico.PALESTRAS_APLICADAS
        historicos.add(palestraNoSqlBa032017)

        var palestraUfba122016 = Historico("Spring Data JPA")
        palestraUfba122016.anoFim = "2016"
        palestraUfba122016.descricao = "Tour na UFBA Javabahia"
        palestraUfba122016.linkReferencia = "http://javabahia.blogspot.com.br/2016/12/ultima-atividade-java-bahia-2016.htm"
        palestraUfba122016.instituicao = "UFBA"
        palestraUfba122016.tipoHistorico = TipoHistorico.PALESTRAS_APLICADAS
        historicos.add(palestraUfba122016)

        var palestraUnime112016 = Historico("Spring Data JPA")
        palestraUnime112016.anoFim = "2016"
        palestraUnime112016.descricao = "II Semana de Ciências, Tecnologia e Sustentabilidade da Unime"
        palestraUnime112016.linkReferencia = "http://javabahia.blogspot.com/2016/11/ii-semana-de-ciencias-tecnologiae.html"
        palestraUnime112016.instituicao = "Unime"
        palestraUnime112016.tipoHistorico = TipoHistorico.PALESTRAS_APLICADAS
        historicos.add(palestraUnime112016)

        var palestraCairu102016 = Historico("Spring Data JPA")
        palestraCairu102016.anoFim = "2016"
        palestraCairu102016.descricao = "III Simpósio de Tecnologia da Informação da Faculdade Visconde de Cairu"
        palestraCairu102016.linkReferencia = "http://javabahia.blogspot.com/2016/10/iii-simposio-de-tecnologia-da.html"
        palestraCairu102016.instituicao = "Faculdade Visconde de Cairu"
        palestraCairu102016.tipoHistorico = TipoHistorico.PALESTRAS_APLICADAS
        historicos.add(palestraCairu102016)

        var palestraFtc102016 = Historico("Spring Data JPA")
        palestraFtc102016.anoFim = "2016"
        palestraFtc102016.descricao = "III Encontro Javabahia nas Universidades 2016"
        palestraFtc102016.linkReferencia = "http://javabahia.blogspot.com/2016/10/iii-encontro-javabahia-nas.html"
        palestraFtc102016.instituicao = "Ruy Barbosa"
        palestraFtc102016.tipoHistorico = TipoHistorico.PALESTRAS_APLICADAS
        historicos.add(palestraFtc102016)
    }

    private fun preencheTreinamentos(historicos: MutableList<Historico>) {
        var treinamentoSpringBoot042017 = Historico("Minicurso de Spring Boot")
        treinamentoSpringBoot042017.anoFim = "2017"
        treinamentoSpringBoot042017.descricao = "Minicurso de Spring Boot I Community Day da Faculdade Visconde de Cairú"
        treinamentoSpringBoot042017.linkReferencia = "https://www.cairu.br/portal/eventos/icommunity_day_TI/"
        treinamentoSpringBoot042017.instituicao = "Faculdade Visconde de Cairu"
        treinamentoSpringBoot042017.tipoHistorico = TipoHistorico.TREINAMENTOS_APLICADOS
        historicos.add(treinamentoSpringBoot042017)

        var treinamentoSpringBoot102018 = Historico("Desenvolvendo Rapidamente Aplicações Web com Spring Boot")
        treinamentoSpringBoot102018.anoFim = "2018"
        treinamentoSpringBoot102018.descricao = "V Simpósio de Tecnologia da Informação da Faculdade Visconde de Cairú"
        treinamentoSpringBoot102018.linkReferencia = "https://www.cairu.br/portal/noticias/index.php?id=169"
        treinamentoSpringBoot102018.instituicao = "Faculdade Visconde de Cairu"
        treinamentoSpringBoot102018.tipoHistorico = TipoHistorico.TREINAMENTOS_APLICADOS
        historicos.add(treinamentoSpringBoot102018)
    }
}