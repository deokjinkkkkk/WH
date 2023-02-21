/**
 * 
 * 
 * 
 * 
 * 크롤링 ...................................
 * selenium 버전............................
 * 
 * 
 * 
 * 
 */

package co.admin.wh.hotel.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrawlingSelenium {
    private WebDriver driver;

    private static final String url = "https://hotels.naver.com/list?placeFileName=place%3ASeoul&adultCnt=2&checkIn=2023-02-22&checkOut=2023-02-23&includeTax=false&sortField=popularityKR&sortDirection=descending&pageIndex=1";
    public void process() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        //크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)

        driver = new ChromeDriver();
        //브라우저 선택

        try {
            getDataList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();	//탭 닫기
        driver.quit();	//브라우저 닫기
    }


    /**
     * data가져오기
     */
    private List<String> getDataList() throws InterruptedException {
        List<String> list = new ArrayList<>();

        driver.get(url);    //브라우저에서 url로 이동한다.
        Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.

        List<WebElement> elements = driver.findElements(By.cssSelector("ul.SearchList_SearchList__CtPf8"));
        for (WebElement element : elements) {
            System.out.println("----------------------------");
            System.out.println(element);	//⭐
        }
        
        return list;
    }

}