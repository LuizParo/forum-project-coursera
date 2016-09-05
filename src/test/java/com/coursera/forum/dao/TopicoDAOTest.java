package com.coursera.forum.dao;

import java.sql.Connection;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.ITable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;

public class TopicoDAOTest {
    private static final String INICIO_XML_LOCATION = "/dbunit/inicio.xml";
    private static final String INSERT_XML_LOCATION = "/dbunit/topico-insert.xml";
    
    private IDatabaseTester jdt;
    private Connection connection;
    private TopicoDAO dao;
    
    @Before
    public void setUp() throws Exception {
        this.jdt = JDBCDatabaseTesterUtil.getJDBCDatabaseTester(INICIO_XML_LOCATION);
        this.connection = JDBCConnectionFactory.getConnection();
        this.dao = new TopicoDAOImpl(this.connection);
    }

    @Test
    public void deveInserirTopico() throws Exception {
        Topico topico = this.criaTopico("Título 4", "Conteúdo 4");
        topico.setId(4);
        this.dao.insereTopico(topico);
        
        ITable currentTable = this.jdt.getConnection().createDataSet().getTable("topico");
        ITable expectedTable = JDBCDatabaseTesterUtil.getDataset(INSERT_XML_LOCATION).getTable("topico");
        
        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"id_topico"});
    }
    
    @Test
    public void deveRecuperarTopicosDoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setLogin("maria");
        
        List<Topico> topicosDoUsuario = this.dao.recuperaTopicosDoUsuario(usuario);
        
        Assert.assertNotNull(topicosDoUsuario);
        Assert.assertFalse(topicosDoUsuario.isEmpty());
        Assert.assertEquals(2, topicosDoUsuario.size());
        
        for (Topico topico : topicosDoUsuario) {
            Assert.assertEquals("maria", topico.getUsuario().getLogin());
        }
    }

    private Topico criaTopico(String titulo, String conteudo) {
        Usuario usuario = new Usuario();
        usuario.setLogin("joao");
        
        Topico topico = new Topico();
        topico.setTitulo(titulo);
        topico.setConteudo(conteudo);
        topico.setUsuario(usuario);
        
        return topico;
    }
}