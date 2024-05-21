package com.itacademy;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class TestOnSelenid {

    @Test
    public void addOneProduct() {
        Configuration.browser = "chrome";
        open("https://react-shopping-cart-67954.firebaseapp.com/");
        $(By.xpath("//button[@class='sc-124al1g-0 jCsgpZ']")).click();
        Configuration.timeout = 30000;
        String oneProduct = $(By.xpath("//p[@class='sc-124al1g-4 eeXMBo'][text()='Cropped Stay Groovy off white']")).getText();
        String oneProductInBasket = $(By.xpath("//p[@class='sc-11uohgb-2 elbkhN'][text()='Cropped Stay Groovy off white']")).getText();
        System.out.println(oneProduct.equals(oneProductInBasket));

    }

    @Test
    public void allProduct() throws InterruptedException {
        Configuration.browser = "chrome";
        open("https://react-shopping-cart-67954.firebaseapp.com/");
        ElementsCollection list = $$(By.xpath("//button[@class='sc-124al1g-0 jCsgpZ']"));
        for (SelenideElement items : list) {
            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click();", items.toWebElement());
            Duration.ofSeconds(120000);
        }
        ElementsCollection list2 = $$(By.xpath("//p[@class='sc-124al1g-4 eeXMBo']"));
        for (SelenideElement selEl : list2) {
            System.out.println(selEl.getText());
        }
        ElementsCollection list3 = $$(By.xpath("//p[@class='sc-11uohgb-2 elbkhN']"));
        for (SelenideElement selid : list3) {
            System.out.println(selid.getText());
        }
        System.out.println(list2.texts().equals(list3.texts()));
    }

    @Test
    public void addOneProductOnSize() throws InterruptedException {
        Configuration.browser = "chrome";
        open("https://react-shopping-cart-67954.firebaseapp.com/");
        ElementsCollection list2 = $$(By.xpath("//p[@class='sc-124al1g-4 eeXMBo']")).shouldHave(size(16));
        for (SelenideElement selid : list2) {
            System.out.println(selid.getText());
        }
        $(By.xpath("//span[@class='checkmark'][text()='XS']")).click();
        ElementsCollection list3 = $$(By.xpath("//p[@class='sc-124al1g-4 eeXMBo']")).shouldHave(size(1));
        for (SelenideElement sld : list3) {
        }
        System.out.println(list2.equals(list3));
    }

    @Test
    public void stringProduct() throws InterruptedException {
        Configuration.browser = "chrome";
        open("https://react-shopping-cart-67954.firebaseapp.com/");
        String allProduct = $x("//p[text()='16'][text()=' Product(s) found']").getText();
        String[] strings = allProduct.split("");
        int num = Integer.parseInt(strings[0]);
        int num2 = Integer.parseInt(strings[1]);
        int numbs = num * 10 + num2;
        System.out.println(numbs);
        $(By.xpath("//span[@class='checkmark'][text()='XS']")).click();
        ElementsCollection list3 = $$(By.xpath("//p[@class='sc-124al1g-4 eeXMBo']")).shouldHave(size(1));
        String oneProduct = $x("//p[text()='1'][text()=' Product(s) found']").getText();
        String[] string = oneProduct.split("");
        int num1 = Integer.parseInt(string[0]);
        System.out.println(num1);
        System.out.println(allProduct.equals(oneProduct));
    }
}

