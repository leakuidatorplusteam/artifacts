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


public class TestChromeSimulMulti {
    public static void main(String[] args) {
        int counter = 100;
        int users = 2;
        String path = "path_name_";
        String wp = "attack_page_";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("user-data-dir=C:\\Users\\[USER]\\AppData\\Local\\Google\\Chrome\\User Data");
        //options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //options.addArguments("load-extension=C:\\Users\\[USER]\\Downloads\\LeakuidatorPlus\\LeakuidatorPlus");
        //options.addArguments("--disable-web-security", "--user-data-dir", "--allow-running-insecure-content"); 
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
        String[] str = {"", "", "", "", "", "", "", ""};
        boolean flg = true;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(("Interrupted " + e + "while Sleeping"));
        }
        
        int u = 0;

        while(count > 0) {
            u = 0;
            while(u < users) {
                // navigate to attacker controlled webpage
                driver.get(wp + u + ".html");
                /*
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(("Interrupted " + e + "while Sleeping"));
                }
                */
                // run the attack
                //WebElement btnAttack = driver.findElement(By.id("btnAttack")); // attack button element
                //js.executeScript("arguments[0].click();", btnAttack); // executing JavaScript to click on the element

                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    System.out.println(("Interrupted " + e + "while Sleeping"));
                }

                // record the measurements
                WebElement txtResult = driver.findElement(By.id("results")); // attack results element
                String text = txtResult.getText();

                if(count == 1) {
                    str[u] += "[" + text + "]\n";
                }
                else {
                    str[u] += "[" + text + "],\n";
                }

                u++;
            }
            
            System.out.print(count + ", ");
            count--;
        }
        
        u = 0;
        while( u < users) {
            str[u] = "{\n" + "\"X\": [\n" + str[u] + "],\n" + "\"y\":\n[" + u;
            String val = "";
            for(int i = 1; i < counter; i++) {
                val += ","+u;
            }
            str[u] = str[u] + val + "]\n}";
            try (PrintWriter out = new PrintWriter(path + u + ".json")) {
                out.println(str[u]);
            } catch (java.io.FileNotFoundException e) {
                System.out.println(e);
            }
            u++;
        }

        driver.close();
        driver.quit();
    }
}
