package com.gianmarco.merletti.progetto_ispw;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

public class CreateEventSeleniumTest {

	String username = "test";
	String password = "test";

	@Test
	public void testCreateEvent() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().setSize(new Dimension(1800, 800));
		driver.manage().window().setPosition(new Point(0, 0));
//		driver.get("http://localhost:13266/progetto_ISPW/logout.jsp");

		driver.get("http://localhost:13266/progetto_ISPW/login.jsp");
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[1]/div/div/input")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[2]/div/div/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[3]/div/button[1]")).click();

		assertEquals(username, SessionView.getUsername());

		driver.close();
	}

}
