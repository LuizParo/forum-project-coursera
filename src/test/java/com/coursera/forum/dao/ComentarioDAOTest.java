package com.coursera.forum.dao;

import java.sql.Connection;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.ITable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coursera.forum.model.Comentario;
import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;

public class ComentarioDAOTest {
    private static final String INICIO_XML_LOCATION = "/dbunit/inicio.xml";
    private static final String INSERT_XML_LOCATION = "/dbunit/comentario-insert.xml";
    
    private IDatabaseTester jdt;
    private Connection connection;
    private ComentarioDAO dao;
    
    @Before
    public void setUp() throws Exception {
        this.jdt = JDBCDatabaseTesterUtil.getJDBCDatabaseTester(INICIO_XML_LOCATION);
        this.connection = JDBCConnectionFactory.getConnection();
        this.dao = new ComentarioDAOImpl(this.connection);
    }

    @Test
    public void deveInserirComentario() throws Exception {
        Comentario comentario = this.criaComentario("Comentário 5");
        this.dao.inserir(comentario);
        
        ITable currentTable = this.jdt.getConnection().createDataSet().getTable("comentario");
        ITable expectedTable = JDBCDatabaseTesterUtil.getDataset(INSERT_XML_LOCATION).getTable("comentario");
        
        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"id_comentario"});
    }
    
    @Test
    public void deveRecuperarComentariosDoTopico() {
        List<Comentario> comentariosRecuperados = this.dao.recuperaComentariosPorTopico(1);
        Assert.assertNotNull(comentariosRecuperados);
        Assert.assertFalse(comentariosRecuperados.isEmpty());
        Assert.assertEquals(3, comentariosRecuperados.size());
        for (Comentario comentario : comentariosRecuperados) {
            Assert.assertEquals(new Integer(1), comentario.getTopico().getId());
        }
    }

    private Comentario criaComentario(String strComentario) {
        Usuario usuario = new Usuario();
        usuario.setLogin("maria");
        
        Topico topico = new Topico();
        topico.setId(1);
        topico.setTitulo("Título 1");
        topico.setConteudo("Conteúdo 1");
        topico.setUsuario(usuario);
        
        Comentario comentario = new Comentario();
        comentario.setComentario(strComentario);
        comentario.setUsuario(usuario);
        comentario.setTopico(topico);
        
        return comentario;
    }
}