package it.de.fraunhofer.fkie.caad.bamboo.chucknorris;

import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.sal.api.UrlMode;
import de.fraunhofer.fkie.caad.bamboo.chucknorris.api.MyPluginComponent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

@RunWith(AtlassianPluginsTestRunner.class)
public class BuildResultSeleniumTest {
    private final ApplicationProperties applicationProperties;
    private final MyPluginComponent pluginComponent;
    private static WebDriver driver;

    private String baseUrl;

    public BuildResultSeleniumTest(ApplicationProperties applicationProperties,
                                   MyPluginComponent pluginComponent){
        this.applicationProperties = applicationProperties;
        this.pluginComponent = pluginComponent;
        this.baseUrl = applicationProperties.getBaseUrl(UrlMode.ABSOLUTE);
    }

    @BeforeClass
    public static void initWebDriver(){
        if(!(driver == null)){
            return;
        }
        driver = new FirefoxDriver();
    }

    private void authenticate(){
        driver.findElement(By.id("loginForm_os_username")).sendKeys("admin");
        driver.findElement(By.id("loginForm_os_password")).sendKeys("admin");
        driver.findElement(By.id("loginForm_os_password")).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeWebDriver(){
        driver.close();
    }

    @Test
    public void testImagePresentForRunningBuild(){
        driver.get(baseUrl + "/chain/viewChain.action?planKey=LRP-LRP");
        if(driver.getCurrentUrl().contains("userlogin!doDefault.action")) {
            authenticate();
        }
        driver.findElement(By.cssSelector(".aui-buttons > .aui-button:nth-child(1)")).click();
        driver.findElement(By.id("manualBuild_LRP-LRP")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement image = driver.findElement(By.id("chucknorris_img"));
        assertThat(image.getAttribute("src"), containsString("bad_ass.jpg"));
    }

    @Test
    public void testImagePresentForSucceededBuild(){
        driver.get(baseUrl + "/browse/SUCS-SUCS/latest");
        if(driver.getCurrentUrl().contains("userlogin!doDefault.action")) {
            authenticate();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement image = driver.findElement(By.id("chucknorris_img"));
        assertThat(image.getAttribute("src"), containsString("thumb_up.jpg"));
    }

    @Test
    public void testImagePresentForFailedBuild(){
        driver.get(baseUrl + "/browse/FAIL-FAIL/latest");
        if(driver.getCurrentUrl().contains("userlogin!doDefault.action")) {
            authenticate();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement image = driver.findElement(By.id("chucknorris_img"));
        assertThat(image.getAttribute("src"), containsString("alert.jpg"));
    }
}