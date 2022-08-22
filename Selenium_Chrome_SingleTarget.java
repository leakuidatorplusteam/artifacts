package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Collections;
import java.util.Set;
import java.io.PrintWriter;


public class TestChromeSimul {
    public static void main(String[] args) {
        int counter = 100;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("user-data-dir=C:\\Users\\[USER]\\AppData\\Local\\Google\\Chrome\\User Data");
        //options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //options.addArguments("load-extension=C:\\Users\\[USER]\\Downloads\\LeakuidatorPlus\\LeakuidatorPlus");
        
        WebDriver driver = new ChromeDriver(options);
        
        // maximize the window
        driver.manage().window().maximize();

        // navigate to login page of the resource-sharing service
        driver.get("https://google.com");

        String noiseHandle = driver.getWindowHandle();

        // Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        String attackHandle = driver.getWindowHandle();

        int count = counter;
        String str1 = "";
        String str2 = "";
        boolean flg = true;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(("Interrupted " + e + "while Sleeping"));
        }
        
        while(count > 0) {
            
            // target

            // navigate to attacker controlled webpage
            driver.get("attack_page_1.html");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(("Interrupted " + e + "while Sleeping"));
            }
            // run the attack
            WebElement btnAttack = driver.findElement(By.id("btnAttack")); // attack button element
            js.executeScript("arguments[0].click();", btnAttack); // executing JavaScript to click on the element
            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(("Interrupted " + e + "while Sleeping"));
            }
            // record the measurements
            WebElement txtResult = driver.findElement(By.id("results")); // attack results element
            String text = txtResult.getText();
            
            System.out.print(count + ", "/*"[" + text + "],"*/);
            if(count == 1) {
                str1 += "[" + text + "]\n";
            }
            else {
                str1 += "[" + text + "],\n";
            }

            // non target

            // navigate to attacker controlled webpage
            driver.get("attack_page_2.html");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(("Interrupted " + e + "while Sleeping"));
            }
            // run the attack
            WebElement btnAttack2 = driver.findElement(By.id("btnAttack")); // attack button element
            js.executeScript("arguments[0].click();", btnAttack2); // executing JavaScript to click on the element
            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(("Interrupted " + e + "while Sleeping"));
            }
            // record the measurements
            WebElement txtResult2 = driver.findElement(By.id("results")); // attack results element
            String text2 = txtResult2.getText();
            
            System.out.print(count + ", "/*"[" + text + "],"*/);
            if(count == 1) {
                str2 += "[" + text2 + "]\n";
            }
            else {
                str2 += "[" + text2 + "],\n";
            }

            // refresh the attack page
            //driver.navigate().refresh();
            count--;
        }

        //System.out.println(str);

        str1 = "{\n" + "\"X\": [\n" + str1 + "],\n" + "\"y\":\n[1";
        String val1 = "";
        for(int i = 1; i < counter; i++) {
            val1 += ",1";
        }
        str1 = str1 + val1 + "]\n}"; 

        try (PrintWriter out = new PrintWriter("target.json")) {
            out.println(str1);
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e);
        }

        str2 = "{\n" + "\"X\": [\n" + str2 + "],\n" + "\"y\":\n[0";
        String val2 = "";
        for(int i = 1; i < counter; i++) {
            val2 += ",0";
        }
        str2 = str2 + val2 + "]\n}"; 

        try (PrintWriter out = new PrintWriter("nontarget.json")) {
            out.println(str2);
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e);
        }

        // close the tab or window
        driver.close();
        //driver.switchTo().window(tabHandle);

        // quit the browser at the end of the session
        driver.quit();
    }
}