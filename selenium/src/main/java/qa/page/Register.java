package qa.page;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import qa.util.TestUtil;
import selenium.TestBase;

public class Register extends TestBase {
	
	@FindBy(xpath = "//li[@id='accountBtn']")
	WebElement loginButton;

	@FindBy(xpath = "/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/nav[1]/ul[1]")
	WebElement actButton;
	
	@FindBy(xpath = "//input[@id='login-email']")
	WebElement emailText;
	
	@FindBy(xpath = "//input[@id='login-password-input']")
	WebElement passWord;
	
	
	@FindBy(xpath = "//body/div[@id='container']/div[@id='login-register']/div[3]/div[1]/form[1]/button[1]/span[1]")
	WebElement actionButton;
	

	
	public Register() {
		PageFactory.initElements(driver, this);
	}
	 
	
	public WebElement getloginButton() {
		return loginButton;
	}
	
	public WebElement getActButton() {
		return actButton;
	}
	
	public WebElement getEmailText() {
		return emailText;
	}
	
	public WebElement getpasswordText() {
		return passWord;
	}
	
	public WebElement actionButton() {
		return actionButton;
	}
	
	
		
	@Test
	public void LoginAction() throws InterruptedException{
		String email = "ozgeilerisaral@gmail.com";
		String password = "1909Ab**";
		 
		emailText.sendKeys("ozgeilerisaral@gmail.com");
		passWord.sendKeys("1909Ab**");
	 
		actionButton.click();
		 System.out.println("Email & Password alanları doldurulup login olundu.");
		
		Thread.sleep(3000);
		String crntUrl = driver.getCurrentUrl();
		TestUtil.imgAll(crntUrl);
		
		System.out.println("Sayfanın gelip gelmediği 200 koduyla kontrol edildi.");
		
		List<WebElement> liElements = driver.findElements(By.xpath("//body/div[@id='header']/div[@id='browsing-gw-navigation']/div[@id='navigation']/div[@id='navigation-wrapper']/nav[1]/ul[1]"));

        System.out.println(liElements.size());
    	int k = 1;
    	System.out.println("Kategoriler tıklanarak kontrol ediliyor");
        for(int i=1; i <= 10; i++)
        {
        	 if (i == 10) {
        	    
        	    System.out.println("Rastgele kategori ve butik seçiliyor. Rastgele bir ürün detayına gidilip sepete ekleniyor.");

             	int randomNumber=TestUtil.randInt(1, 9);
             	int randomBtk=TestUtil.randInt(1, 18);
             	int randomProduct=TestUtil.randInt(1, 24);

                 WebElement linkClick = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/nav[1]/ul[1]/li["+randomNumber+"]"));
                 linkClick.click();
                 String crntUrltwo = driver.getCurrentUrl();
         		 TestUtil.imgAll(crntUrltwo);
         		Thread.sleep(3000);
         		
         		WebElement btkClick = driver.findElement(By.xpath("//*[@id=\"browsing-gw-homepage\"]/div/div/div[1]/div[1]/article["+randomBtk+"]"));
                 btkClick.click();
                 String crntUrltr = driver.getCurrentUrl();
         		 TestUtil.imgAll(crntUrltr);
                 driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);

                 WebElement productClick = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div["+randomProduct+"]"));
                 productClick.click();
         		 String crntUrlfour = driver.getCurrentUrl();
         		 TestUtil.imgAll(crntUrlfour);
                 WebElement addChart = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/button[1]/div[1]"));
                 addChart.click();
                 String crntUrlf = driver.getCurrentUrl();
         		 TestUtil.imgAll(crntUrlf);
 
         		Thread.sleep(3000);
 
         	 
             } else {
            	 WebElement linkElement = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/nav[1]/ul[1]/li["+i+"]"));
                 System.out.println(linkElement.getText()); 
                 if (i == k) {
                 	k++;
                     linkElement.click();
                     String crntUrlone = driver.getCurrentUrl();
             		 TestUtil.imgAll(crntUrlone);
                     for (int j = 1; j <= 18; j++) {
                          String links = driver.findElement(By.xpath("//*[@id=\"browsing-gw-homepage\"]/div/div/div[1]/div[1]/article["+j+"]/a/span/img")).getAttribute("src");

                         TestUtil.imgAll(links);
                         
                     }
                 }
             }
            
        }
  
	}
	
}



