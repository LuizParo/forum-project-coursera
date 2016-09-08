package com.coursera.forum.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coursera.forum.model.Usuario;

public class UsuarioDAOTest {
    private static final String INICIO_XML_LOCATION = "/dbunit/inicio.xml";
    private static final String VERIFICA_XML_LOCATION = "/dbunit/usuario-insert.xml";
    private static final String VERIFICA_UPDATE_XML_LOCATION = "/dbunit/usuario-update.xml";
    
    private Connection connection;
    private UsuarioDAO dao;
    private IDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        this.jdt = JDBCDatabaseTesterUtil.getJDBCDatabaseTester(INICIO_XML_LOCATION);
        this.connection = JDBCConnectionFactory.getConnection();
        this.dao = new UsuarioDAOImpl(this.connection);
    }
    
    @After
    public void tearDown() throws SQLException {
        this.connection.close();
    }

    @Test
    public void deveInserirNovoUsuario() throws SQLException, Exception {
        Usuario usuario = this.criaUsuario("user", "User", 0);
        this.dao.inserir(usuario);
        
        ITable currentTable = this.getCurrentTable();
        ITable expectedTable = this.getModifiedTable(VERIFICA_XML_LOCATION);
        
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    @Test
    public void deveRecuperarUsuario() {
        Usuario usuario = this.criaUsuario("user", "User", 0);
        this.dao.inserir(usuario);
        
        Usuario usuarioRecuperado = this.dao.recuperar(usuario.getLogin(), usuario.getSenha());

        Assert.assertNotNull(usuarioRecuperado);
        Assert.assertEquals(usuario.getLogin(), usuario.getLogin());
        Assert.assertEquals(usuario.getSenha(), usuario.getSenha());
        Assert.assertEquals(usuario.getNome(), usuario.getNome());
        Assert.assertEquals(usuario.getEmail(), usuario.getEmail());
        Assert.assertEquals(usuario.getPontos(), usuario.getPontos());
    }
    
    @Test
    public void deveAtualizarPontosDoUsuario() throws SQLException, Exception {
        Usuario usuario = this.criaUsuario("user", "User", 0);
        this.dao.inserir(usuario);
        this.dao.adicionarPontos("user", 10);
        
        ITable currentTable = this.getCurrentTable();
        ITable expectedTable = this.getModifiedTable(VERIFICA_UPDATE_XML_LOCATION);
        
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    @Test
    public void deveRetornarRankingDeUsuarios() {
        Usuario usuario = this.criaUsuario("user", "User", 0);
        this.dao.inserir(usuario);
        
        List<Usuario> ranking = this.dao.ranking();
        
        Assert.assertNotNull(ranking);
        Assert.assertFalse(ranking.isEmpty());
        Assert.assertEquals(3, ranking.size());
    }
    
    private Usuario criaUsuario(String login, String nome, int pontos) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha("pass");
        usuario.setNome(nome);
        usuario.setEmail(login + "@email.com");
        usuario.setPontos(pontos);
        return usuario;
    }

    private ITable getCurrentTable() throws SQLException, Exception, DataSetException {
        IDataSet currentDataset = this.jdt.getConnection().createDataSet();
        return currentDataset.getTable("usuario");
    }
    
    private ITable getModifiedTable(String xmlDataSetLocation) throws DataSetException {
        IDataSet expectedDataset = JDBCDatabaseTesterUtil.getDataset(xmlDataSetLocation);
        return expectedDataset.getTable("usuario");
    }
}