package test;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Collections;
import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;
//import java.util.concurrent.TimeUnit;


public class TestTorSimul {
    public static void main(String[] args) {
        int counter = 100;

        FirefoxOptions options = new FirefoxOptions();
        //options.setExperimentalOption("useAutomationExtension", false);
        //options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-web-security", "--user-data-dir", "--allow-running-insecure-content"); 

        File pathToBinary = new File("C:\\Users\\[USER]\\Desktop\\Tor Browser\\Browser\\firefox.exe");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        //options.addArguments("start-maximized");
        //options.addArguments("path=C:\\Users\\[USER]\\Desktop\\Tor Browser\\Browser\\firefox.exe");
        FirefoxProfile profile = new FirefoxProfile(new File("C:\\Users\\[USER]\\Desktop\\Tor Browser\\Browser\\TorBrowser\\Data\\Browser\\profile.default"));
        
        profile.setPreference("xpinstall.signatures.required", false);

        //profile.addExtension(new File("C:\\Users\\[USER]\\Documents\\GitHub\\LeakuidatorPlus\\LeakuidatorPlus.xpi"));
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.socks", "127.0.0.1");
        profile.setPreference("extensions.torlauncher.start_tor", false);
        profile.setPreference("extensions.torbutton.block_disk", false);
        profile.setPreference("extensions.torbutton.custom.socks_host", "127.0.0.1");
        profile.setPreference("extensions.torbutton.custom.socks_port", 9150);
        profile.setPreference("extensions.torbutton.inserted_button", true);
        profile.setPreference("extensions.torbutton.launch_warning", false);
        profile.setPreference("privacy.spoof_english", 2);
        profile.setPreference("extensions.torbutton.loglevel", 2);
        profile.setPreference("extensions.torbutton.logmethod", 0);
        profile.setPreference("extensions.torbutton.settings_method", "custom");
        profile.setPreference("extensions.torbutton.use_privoxy", false);
        profile.setPreference("extensions.torlauncher.control_port", 9251); // 9251
        profile.setPreference("extensions.torlauncher.loglevel", 2);
        profile.setPreference("extensions.torlauncher.logmethod", 0);
        profile.setPreference("extensions.torlauncher.prompt_at_startup", false);
        profile.setPreference("browser.startup.page", "0");
        profile.setPreference("browser.startup.homepage", "about:newtab");
        profile.setPreference("extensions.torlauncher.prompt_at_startup", 0);
        profile.setPreference("webdriver.load.strategy", "normal");
        profile.setPreference("app.update.enabled", false);
        profile.setPreference("extensions.torbutton.versioncheck_enabled", false);
        profile.setPreference("extensions.torbutton.prompted_language", true);
        profile.setPreference("network.proxy.socks_port", 9150);
        profile.setPreference("extensions.torbutton.socks_port", 9150);
        
        // enforce new tab instead of new window
        //profile.setPreference("browser.link.open_newwindow", 1);
        //profile.setPreference("browser.link.open_newwindow.restriction", 1);
        options.setProfile(profile);
        options.setBinary(ffBinary);
        WebDriver driver = new FirefoxDriver(options);

        // maximize the window
        driver.manage().window().maximize();

        // navigate to login page of the resource-sharing service

        driver.get("https://www.google.com/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(("Interrupted " + e + "while Sleeping"));
        }
        /*
        driver.get("https://www.gmail.com/");

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            System.out.println(("Interrupted " + e + "while Sleeping"));
        }
        */

        driver.get("https://www.google.com/");

        // login
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            System.out.println(("Interrupted " + e + "while Sleeping"));
        }
/*
        driver.get("attack_1.html?run=1");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(("Interrupted " + e + "while Sleeping"));
            }
        
        driver.get("attack_2.html?run=1");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(("Interrupted " + e + "while Sleeping"));
        }
  */      
        String firstTabHandle = driver.getWindowHandle();

        // Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;

        int count = counter;
        String str1 = "";
        String str2 = "";
        boolean flg = true;
        
        while(count > 0) {
            
            System.out.print("\n\n\n\n\nCount:" + count + "\n\n\n\n"/*"[" + text + "],"*/);

            // target

            flg = true;

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
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                System.out.println(("Interrupted " + e + "while Sleeping"));
            }

            for(String winHandle : driver.getWindowHandles()){
                if (!winHandle.equals(firstTabHandle))
                {
                    driver.switchTo().window(winHandle);
                }
            }

            // record the measurements
            String text1 = "";
            try {
                WebElement txtResult = driver.findElement(By.id("results"));
                text1 = txtResult.getText();
            } catch (Exception e) {
                System.out.println(("Exception " + e));
            }
            
            if(/*flg &&*/ text1.length() > 10) {
                flg = true;
            } else {
                flg = false;
            }
            

            ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(1));
            driver.close();
            driver.switchTo().window(tabs1.get(0));

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
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                System.out.println(("Interrupted " + e + "while Sleeping"));
            }

            for(String winHandle : driver.getWindowHandles()){
                if (!winHandle.equals(firstTabHandle))
                {
                    driver.switchTo().window(winHandle);
                }
            }

            // record the measurements
            String text2 = "";
            try {
                WebElement txtResult2 = driver.findElement(By.id("results"));
                text2 = txtResult2.getText();
            } catch (Exception e) {
                System.out.println(("Exception " + e));
            }
            
            if(flg == true) {
                if(text2.length() > 10) {
                    flg = true;
                } else {
                    flg = false;
                }
            }
            
            ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));
            driver.close();
            driver.switchTo().window(tabs2.get(0));
            
            if(flg == true) {
                if(count == 1) {
                    str1 += "[" + text1 + "]\n";
                    str2 += "[" + text2 + "]\n";
                }
                else {
                    str1 += "[" + text1 + "],\n";
                    str2 += "[" + text2 + "],\n";
                }
                count--;
            }
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
