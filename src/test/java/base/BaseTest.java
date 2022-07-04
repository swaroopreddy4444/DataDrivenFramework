package base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;

public class BaseTest {

	public static WebDriver driver;
	public static Logger log = Logger.getLogger(BaseTest.class.getName());
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\Excel\\loginData.xlsx");
	public static WebDriverWait wait;

	
	public static void click(String key) {

		if (key.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(key))).click();
		} else if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(key))).click();
		} else if (key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(key))).click();
		} else if (key.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(key))).click();
		} else if (key.endsWith("_CLASS")) {
			driver.findElement(By.className(OR.getProperty(key))).click();
		}else if (key.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(OR.getProperty(key))).click();
		}else if (key.endsWith("_PLINKTEXT")) {
			driver.findElement(By.partialLinkText(OR.getProperty(key))).click();
		}
		log.info("clicking on Element " + key);
	}
	public static void keypress(String key) {

		Actions action = new Actions(driver);
		if (key.equalsIgnoreCase("ENTER")) {
			action.sendKeys(Keys.ENTER);
		} else if (key.equalsIgnoreCase("TAB")) {
			action.sendKeys(Keys.TAB);
		} else if (key.equalsIgnoreCase("ESCAPE")) {
			action.sendKeys(Keys.ESCAPE);
		} else if (key.equalsIgnoreCase("PgUP")) {
			action.sendKeys(Keys.PAGE_UP);
		} else if (key.equalsIgnoreCase("PgDown")) {
			action.sendKeys(Keys.PAGE_DOWN);
		} 
		log.info("clicking on Element " + key);
	}

	public static boolean isElementPresent(String key) {

		try {
			if (key.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(key)));
			} else if (key.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(key)));
			} else if (key.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(key)));
			} else if (key.endsWith("_NAME")) {
				driver.findElement(By.name(OR.getProperty(key)));
			} else if (key.endsWith("_CLASS")) {
				driver.findElement(By.className(OR.getProperty(key)));
			}else if (key.endsWith("_LINKTEXT")) {
				driver.findElement(By.linkText(OR.getProperty(key)));
			}else if (key.endsWith("_PLINKTEXT")) {
				driver.findElement(By.partialLinkText(OR.getProperty(key)));
			}
			log.info("found Element : " + key);
			return true;
		} catch (Throwable t) {
			log.error("Error while finding an Element " + key + "\nError log:\n" + t.getMessage());
			return false;
		}
	}

	public static void verifyTitle(String key) {

		try {
			String Expected_Title = OR.getProperty(key);
			Assert.assertEquals(Expected_Title,driver.getTitle(), " Titles Mismatched ");
			log.info("Titles Matched");
		} catch (Throwable t) {
			log.error("Error while verifying Titles " + key + "\nError log:\t" + t.getMessage());
			throw t;
		}
	}

	public static void type(String key, String value) {

		if (key.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
		} else if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);
		} else if (key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
		} else if (key.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(key))).sendKeys(value);
		} else if (key.endsWith("_CLASS")) {
			driver.findElement(By.className(OR.getProperty(key))).sendKeys(value);
		}else if (key.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(OR.getProperty(key))).sendKeys(value);
		}else if (key.endsWith("_PLINKTEXT")) {
			driver.findElement(By.partialLinkText(OR.getProperty(key))).sendKeys(value);
		}
		log.info("Typing in Element " + key + ":" + value);
	}

	@BeforeSuite
	public void setUp() {
		if (driver == null) {
			PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");
			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR Properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.info("Config Properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@BeforeMethod
	public void launchBrowser() {
		if (Config.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Chrome Launched !!!");
		} else if (Config.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			log.info("Edge Launched !!!");
		}

		driver.get(Config.getProperty("urlsite"));
		log.info("Navigated to URL site : " + Config.getProperty("urlsite"));
//		DbManager.setMysqlDbConnection();
//		log.info("MySQL DB connected");
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(Config.getProperty("implicit.wait"))));

		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Config.getProperty("explicit.wait"))));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("Test completed\n----------------------------------------------------------------------------");
	}
}
