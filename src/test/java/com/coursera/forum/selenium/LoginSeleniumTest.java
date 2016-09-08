package com.coursera.forum.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginSeleniumTest {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        this.driver = new FirefoxDriver();
        this.baseUrl = "http://localhost:8080/";
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void deveCadastrarUsuarioERetornarParaTelaLogin() throws Exception {
        this.driver.get(this.baseUrl + "/forum/");
        this.driver.findElement(By.linkText("Cadastrar Usuário")).click();
        this.driver.findElement(By.id("txtLogin")).clear();
        this.driver.findElement(By.id("txtLogin")).sendKeys("admin");
        this.driver.findElement(By.id("txtSenha")).clear();
        this.driver.findElement(By.id("txtSenha")).sendKeys("admin");
        this.driver.findElement(By.id("txtNome")).clear();
        this.driver.findElement(By.id("txtNome")).sendKeys("Administrador");
        this.driver.findElement(By.id("txtEmail")).clear();
        this.driver.findElement(By.id("txtEmail")).sendKeys("admin@email.com");
        this.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        try {
            Assert.assertEquals(this.driver.findElement(By.id("mensagem")).getText(), "Usuário cadastrado com sucesso!");
        } catch (Error e) {
            this.verificationErrors.append(e.toString());
        }
    }

    @Test
    public void deveLogarNoSistema() throws Exception {
        this.driver.get(this.baseUrl + "/forum/?action=LogoutAction");
        this.driver.findElement(By.id("txtUsuario")).clear();
        this.driver.findElement(By.id("txtUsuario")).sendKeys("admin");
        this.driver.findElement(By.id("txtPassword")).clear();
        this.driver.findElement(By.id("txtPassword")).sendKeys("admin");
        this.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        Assert.assertTrue(this.isElementPresent(By.linkText("Logout")));
        Assert.assertTrue(this.isElementPresent(By.linkText("Criar Tópico")));
        Assert.assertTrue(this.isElementPresent(By.linkText("Exibir Ranking")));
    }

    @After
    public void tearDown() throws Exception {
        this.driver.quit();
        String verificationErrorString = this.verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}