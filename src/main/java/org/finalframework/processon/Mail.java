package org.finalframework.processon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author likly
 * @version 1.0
 * @date 2020/9/23 12:59:54
 * @since 1.0
 */
public interface Mail<T> {


    void open();

    void close();

    void parse();

    void setContentParser(ContentParser<T> parser);

    void setMailListener(MailListener<T> listener);

    interface ContentParser<T> {
        T doParse(WebDriver driver);
    }

    interface MailListener<T> {
        void onInit( String mail);

        void onReceive( WebDriver driver,  WebElement message);

        void onRead( T content);

    }


}
