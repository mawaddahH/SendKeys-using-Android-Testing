package ass3W11D3.SendKeysusingAndroidTesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class TestAss3W11D3 {
	private AndroidDriver driver;

	@BeforeSuite
	public void setUp() throws MalformedURLException, InterruptedException {

		// Setting up desire caps using DesireCapabilities class
		// Create an object for Desired Capabilities
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		// Set capabilities
		desiredCapabilities.setCapability("appium:app", "C:\\Users\\lo0ol\\eclipse-workspace\\SendKeysusingAndroidTesting\\Collect-data-Application.apk");
		desiredCapabilities.setCapability("appium:deviceName", "23b9cb400c1c7ece");
		desiredCapabilities.setCapability("appium:platformName", "Android");
		desiredCapabilities.setCapability("appium:platformVersion", "10");
		desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
		desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

		// Java package of the Android app you want to run
		desiredCapabilities.setCapability("appium:appPackage", "com.example.mahhid_collect_data");

		// Activity name for the Android activity you want to launch from your package
		desiredCapabilities.setCapability("appium:appActivity", "com.example.mahhid_collect_data.SplashScreen");

		// Initialize the driver object with the URL to Appium Server and
		// passing the capabilities
		URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@BeforeTest
	public void location() throws InterruptedException {
		// Allow using location
		if (driver.findElement(
				AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).isDisplayed()) {
			WebElement allowElement = driver.findElement(
					AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
			allowElement.click();
			System.out.println("Click on Button");
		}
		Thread.sleep(5000);
	}

	@Test
	public void sendKeyTest() throws InterruptedException {
		// Enter Text
		WebElement element = driver
				.findElement(AppiumBy.id("com.example.mahhid_collect_data:id/edit_text_road_file_name"));
		element.sendKeys("Mawaddah");
		System.out.println("Send Key");
		Thread.sleep(5000);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}