package org.finalframework.processon;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://www.linshiyouxiang.net/">临时邮箱</a>
 *
 * @author likly
 * @version 1.0
 * @date 2020/9/23 00:24:05
 * @since 1.0
 */
@Slf4j
public class TempMail<T> implements Mail<T> {
    private static final String HOME = "https://www.linshiyouxiang.net/";
    private static final List<String> MAIL_SUFFIXS = Arrays.asList(
            "linshiyouxiang.net",
            "iffygame.com",
            "maileven.com",
            "smuggroup.com",
            "chapedia.net",
//            "besttempmail.com",
//            "bestlistbase.com",
            "meantinc.com",
            "powerencry.com",
            "worldzipcodes.net",
            "chapedia.org",
            "chasefreedomactivate.com",
            "wellsfargocomcardholders.com"
    );

    private final WebDriver driver;

    private ContentParser<T> parser;

    private MailListener<T> listener;

    public TempMail() {
        this.driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 1000));
        driver.manage().window().setSize(new Dimension(1920, 1200));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public TempMail(WebDriver driver) {
        this.driver = driver;
    }

    private void doInit() throws InterruptedException {
        logger.info("try to find email...");
        String address = null;

        while (address == null || address.trim().isEmpty()) {
            sleep(1000);
            logger.info("try to find email...");
            address = driver.findElement(By.id("active-mail")).getAttribute("value");
        }

        if (listener != null) {
            logger.info("found mail:{}", address);
            address = address.substring(0, address.indexOf("@"));
            address = address + "@" + MAIL_SUFFIXS.get(new Random().nextInt(MAIL_SUFFIXS.size()));
            listener.onInit(address);
        }

        doReceive();
    }

    private void doReceive() throws InterruptedException {
        WebElement message = tryReceive();

        while (message == null) {
            Thread.sleep(1000);
            message = tryReceive();
        }

        if (listener != null) {
            listener.onReceive(driver, message);
        }
    }


    private WebElement tryReceive() {
        try {
//            driver.navigate().refresh();
            logger.info("try to receive message");
            WebElement messages = driver.findElement(By.id("message-list"));
            return messages.findElement(By.className("link"));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void close() {
        this.driver.close();
    }

    @Override
    public void skipAd() {
        try {
            driver.navigate().to(HOME);
            this.doReceive();
        }catch (Exception e){

        }
    }

    @Override
    public void parse() {
        T content = parser.doParse(driver);
        if (listener != null) {
            listener.onRead(content);
        }
    }

    @Override
    public void setContentParser(ContentParser<T> parser) {
        this.parser = parser;
    }

    @Override
    public void setMailListener(MailListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void open() {
        driver.get(HOME);
        try {
            this.doInit();
        } catch (Exception e) {
            logger.error("init error:", e);
        }
    }


    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
