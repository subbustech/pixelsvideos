import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class DownloadVideos {

	public static void main(String[] args) throws InterruptedException, IOException {


		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);		    

		System.setProperty("webdriver.chrome.driver", "server/chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();

		driver.get("https://www.pexels.com/videos/");
		chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );

		JavascriptExecutor js = (JavascriptExecutor) driver;
		for(int i=0; i<50; i++) {	
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(2000);
		}

		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(2000);

		int noOfVideos1 = driver.findElements(By.xpath("(//div[@class='photos']/div)[1]/div")).size();
		System.out.println("No. of Videos are "+noOfVideos1);

		int noOfVideos2 = driver.findElements(By.xpath("(//div[@class='photos']/div)[2]/div")).size();
		System.out.println("No. of Videos are "+noOfVideos2);

		int noOfVideos3 = driver.findElements(By.xpath("(//div[@class='photos']/div)[3]/div")).size();
		System.out.println("No. of Videos are "+noOfVideos3);

		UtilityClass uc = new UtilityClass();

		for(int i=0; i<noOfVideos1; i++) {
			String videoHref = driver.findElements(By.xpath("(//div[@class='photos']/div)[1]/div/article/a")).get(i*3).getAttribute("href");
			List<String> allLinks = uc.returnData("resources/videos.xlsx", "links");
			System.out.println(allLinks);
			if(!allLinks.contains(videoHref)) {
				uc.writeData("resources/videos.xlsx", "links", videoHref);
				System.out.println("Sheet updated");
				driver.findElements(By.xpath("(//div[@class='photos']/div)[1]/div")).get(i).click();
				WebElement downloadButton = driver.findElement(By.xpath("//span[text()='Free Download']"));
				Actions oAction=new Actions(driver);
				oAction.moveToElement(downloadButton);
				//Thread.sleep(1000);
				oAction.contextClick(downloadButton).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(1000);
				oAction.contextClick(downloadButton).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ESCAPE).build().perform();

				try {
					driver.findElement(By.xpath("(//button[contains(@class,'js-modal-close-button')])[3]")).click();
				}
				catch(Exception e) {

				}
				try {
					driver.findElement(By.xpath("(//button[contains(@class,'js-modal-close-button')])[2]")).click();
				}
				catch(Exception e) {

				}

				Thread.sleep(10000);
			}
		}

		for(int i=0; i<noOfVideos2; i++) {
			String videoHref = driver.findElements(By.xpath("(//div[@class='photos']/div)[2]/div/article/a")).get(i*3).getAttribute("href");
			List<String> allLinks = uc.returnData("resources/videos.xlsx", "links");
			System.out.println(allLinks);
			if(!allLinks.contains(videoHref)) {
				uc.writeData("resources/videos.xlsx", "links", videoHref);
				System.out.println("Sheet updated");
				driver.findElements(By.xpath("(//div[@class='photos']/div)[2]/div")).get(i).click();
				WebElement downloadButton = driver.findElement(By.xpath("//span[text()='Free Download']"));
				Actions oAction=new Actions(driver);
				oAction.moveToElement(downloadButton);
				//Thread.sleep(1000);
				oAction.contextClick(downloadButton).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(1000);
				oAction.contextClick(downloadButton).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ESCAPE).build().perform();

				try {
					driver.findElement(By.xpath("(//button[contains(@class,'js-modal-close-button')])[3]")).click();
				}
				catch(Exception e) {

				}
				try {
					driver.findElement(By.xpath("(//button[contains(@class,'js-modal-close-button')])[2]")).click();
				}
				catch(Exception e) {

				}

				Thread.sleep(10000);
			}
		}

		for(int i=0; i<noOfVideos3; i++) {
			String videoHref = driver.findElements(By.xpath("(//div[@class='photos']/div)[3]/div/article/a")).get(i*3).getAttribute("href");
			List<String> allLinks = uc.returnData("resources/videos.xlsx", "links");
			System.out.println(allLinks);
			if(!allLinks.contains(videoHref)) {
				uc.writeData("resources/videos.xlsx", "links", videoHref);
				System.out.println("Sheet updated");
				driver.findElements(By.xpath("(//div[@class='photos']/div)[3]/div")).get(i).click();
				WebElement downloadButton = driver.findElement(By.xpath("//span[text()='Free Download']"));
				Actions oAction=new Actions(driver);
				oAction.moveToElement(downloadButton);
				//Thread.sleep(1000);
				oAction.contextClick(downloadButton).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				Thread.sleep(1000);
				oAction.contextClick(downloadButton).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ESCAPE).build().perform();

				try {
					driver.findElement(By.xpath("(//button[contains(@class,'js-modal-close-button')])[3]")).click();
				}
				catch(Exception e) {

				}
				try {
					driver.findElement(By.xpath("(//button[contains(@class,'js-modal-close-button')])[2]")).click();
				}
				catch(Exception e) {

				}

				Thread.sleep(10000);
			}	
		}		
	}
}
