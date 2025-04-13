package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PLP {
    private WebDriver driver;
    private int itemsAdded = 0;

    private By sortDropdown = By.className("product_sort_container");
    private By addToCartJacket = By.id("add-to-cart-sauce-labs-fleece-jacket");
    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By addToCartBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By addToCartTshirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private By addToCartOnesie = By.id("add-to-cart-sauce-labs-onesie");
    private By cartIcon = By.className("shopping_cart_link");
    private By hamburger = By.id("react-burger-menu-btn");
    private By ResetAppState = By.id("reset_sidebar_link");
    private By about = By.id("about_sidebar_link");
    private By logout = By.id("logout_sidebar_link");

    public PLP(WebDriver driver) {
        this.driver = driver;
    }

    public int getItemsAdded() {
        return itemsAdded;
    }

    public void selectSortOption(String optionText) {
        WebElement dropdown = driver.findElement(sortDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(optionText);
    }

    public void addJacketToCart() {
        driver.findElement(addToCartJacket).click();
        itemsAdded++;
    }

    public void addBackpackToCart() {
        driver.findElement(addToCartBackpack).click();
        itemsAdded++;
    }

    public void addBikeLightToCart() {
        driver.findElement(addToCartBikeLight).click();
        itemsAdded++;

    }

    public void addTshirtToCart() {
        driver.findElement(addToCartTshirt).click();
        itemsAdded++;

    }

    public void addOnesieToCart() {
        driver.findElement(addToCartOnesie).click();
        itemsAdded++;
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public void clickIcon() {
        driver.findElement(hamburger).click();
    }

    public void ResetState() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // wait for up to 10 seconds
        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(ResetAppState)); // wait for element to be clickable
        resetButton.click();
    }

    public void aboutPage() {
        driver.findElement(about).click();
    }

    public void Logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // wait for up to 10 seconds
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(logout)); // wait for element to be clickable
        logoutButton.click();
    }


}
