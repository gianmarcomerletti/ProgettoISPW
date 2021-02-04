package com.gianmarco.merletti.progetto_ispw;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.scene.web.WebEngine;

public class sendRequestTestSelenium {

	String username = "user";
	String password = "user";

	@Test
	public void testSendRequest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().setSize(new Dimension(1280, 720));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.get("http://localhost:13266/progetto_ISPW/logout.jsp");

		driver.get("http://localhost:13266/progetto_ISPW/login.jsp");
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[1]/div/div/input")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[2]/div/div/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[3]/div/button[1]")).click();

		driver.findElement(By.xpath("//*[@id=\"mapid\"]/div[1]/div[4]/img[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"sendRequestModalBtnId\"]")).click();

		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"sendRequestFormId\"]/div[1]/div/div/div/textarea")).click();
		driver.findElement(By.xpath("//*[@id=\"sendRequestFormId\"]/div[1]/div/div/div/textarea")).sendKeys("Incontriamoci 10 miunti prima se è possibile.");
		driver.findElement(By.xpath("//*[@id=\"sendRequestFormId\"]/div[2]/div/div/button")).click();

		driver.findElement(By.xpath("//*[@id=\"mapid\"]/div[1]/div[4]/img[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"sendRequestModalBtnId\"]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"sendRequestFormId\"]/div[2]/div/div/button")).click();

		 try {
	         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     } catch (UnhandledAlertException e) {
	         Alert alert = driver.switchTo().alert();
	         String alertText = alert.getText().trim();
	         assertEquals("You are already a participant of the event or your request is in pending status!", alertText);
	         alert.dismiss();
	     }

		//driver.close();
	}

}
