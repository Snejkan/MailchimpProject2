package StepDef;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class StepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("User is on the registration page")
    public void userIsOnTheRegistrationPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://login.mailchimp.com/signup/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @When("User enters valid username, email, and password")
    public void userEntersValidUsernameEmailAndPassword() {

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("snejkie@bluff.com");
        driver.findElement(By.id("new_username")).sendKeys("Stolpskott");
        driver.findElement(By.id("new_password")).sendKeys("YeeCmon666#");

    }

    @When("User enters username with more than 100 characters")
    public void userEntersUsernameWithMoreThan100Characters() {

        //driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("snejkie@bluff.com");
        driver.findElement(By.xpath("//*[@id=\"new_username\"]")).sendKeys("a".repeat(101));
        //driver.findElement(By.id("new_password")).sendKeys("YeeCmon666#");
    }

    @When("User enters an already taken username")
    public void userEntersAnAlreadyTakenUsername() {

        //driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("snejkie@bluff.com");
        driver.findElement(By.id("new_username")).sendKeys("Eric");
        //driver.findElement(By.id("new_password")).sendKeys("YeeCmon666#");
    }

    //@When("User enters valid username and password")
    //public void userEntersValidUsernameAndPassword() {

        //driver.findElement(By.id("new_username")).sendKeys("Snejkie");
        //driver.findElement(By.id("new_password")).sendKeys("YeeCmon666#");
    //}

    @And("User clicks the Sign Up button")
    public void userClicksTheSignUpButton() throws InterruptedException {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("create-account-enabled")));
        WebElement signup = driver.findElement(By.cssSelector("create-account-enabled"));
        signup.click();
    }

    @Then("User should be successfully registered")
    public void userShouldBeSuccesfullyRegistrered() {
        String pageTitle = driver.getTitle();
        assertEquals("Success Page Title", pageTitle);
    }

    @Then("User should see an error message for the username field")
    public void userShouldSeeAnErrorMessageForTheUsernameField() {
        String errorMessage = driver.findElement(By.cssSelector("#signup-form > fieldset > div:nth-child(2)")).getText();
        assertEquals("Enter a value less than 100 characters long", errorMessage);
    }

    @Then("User should see an error message indicating username is taken")
    public void userShouldSeeAnErrorMessageIndicatingUsernameIsTaken() {
        String errorMessage = driver.findElement(By.cssSelector("#signup-form > fieldset > div:nth-child(2) > div > span")).getText();
        assertEquals("Great minds think alike - someone already has this username. If it's you, log in.", errorMessage);
    }

    @Then("User should see an error message for the email address field")
    public void userShouldSeeAnErrorMessageForTheEmailAddressField() {
        String errorMessage = driver.findElement(By.className("invalid-error")).getText();
        assertEquals("An email address must contain a single @.", errorMessage);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @And("User enters valid email and password")
    public void userEntersValidEmailAndPassword() {



    }

    @When("User enters valid username and password")
    public void userEntersValidUsernameAndPassword() {

        driver.findElement(By.id("new_username")).sendKeys("Stolpskott");
        driver.findElement(By.id("new_password")).sendKeys("YeeCmon666#");
    }
}

