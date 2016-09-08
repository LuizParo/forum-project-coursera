package com.coursera.forum.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TopicoSeleniumTest {
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
    public void deveCriarUmNovoTopico() throws Exception {
        this.driver.get(this.baseUrl + "/forum/?action=LogoutAction");
        this.driver.findElement(By.id("txtUsuario")).clear();
        this.driver.findElement(By.id("txtUsuario")).sendKeys("admin");
        this.driver.findElement(By.id("txtPassword")).clear();
        this.driver.findElement(By.id("txtPassword")).sendKeys("admin");
        this.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        this.driver.findElement(By.linkText("Criar Tópico")).click();
        this.driver.findElement(By.id("txtTitulo")).clear();
        this.driver.findElement(By.id("txtTitulo")).sendKeys("Tópico do Administrador");
        this.driver.findElement(By.id("txtConteudo")).clear();
        this.driver.findElement(By.id("txtConteudo")).sendKeys("Descrição do administrador");
        this.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

        Assert.assertEquals("Tópico do Administrador", this.driver.findElement(By.tagName("table")).getText());
    }

    @Test
    public void deveInserirComentarioEmTopico() throws Exception {
        this.driver.get(this.baseUrl + "/forum/?action=LogoutAction");
        this.driver.findElement(By.id("txtUsuario")).clear();
        this.driver.findElement(By.id("txtUsuario")).sendKeys("admin");
        this.driver.findElement(By.id("txtPassword")).clear();
        this.driver.findElement(By.id("txtPassword")).sendKeys("admin");
        this.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        this.driver.findElement(By.linkText("Criar Tópico")).click();
        this.driver.findElement(By.id("txtTitulo")).clear();
        this.driver.findElement(By.id("txtTitulo")).sendKeys("Tópico do Administrador 2");
        this.driver.findElement(By.id("txtConteudo")).clear();
        this.driver.findElement(By.id("txtConteudo")).sendKeys("Descrição do administrador 2");
        this.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        this.driver.findElement(By.linkText("Exibir")).click();
        this.driver.findElement(By.name("txtComentario")).clear();
        this.driver.findElement(By.name("txtComentario")).sendKeys("Comentário do Administrador");
        this.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        
        Assert.assertEquals(this.driver.findElement(By.cssSelector("div > div")).getText(), "Administrador\n Comentário do Administrador");
    }

    @After
    public void tearDown() throws Exception {
        this.driver.quit();
        String verificationErrorString = this.verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }
}