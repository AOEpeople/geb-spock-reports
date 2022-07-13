import com.aoe.gebspockreports.GebReportingListener
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

reportingListener = new GebReportingListener()
reportsDir = 'build/geb-spock-reports'

driver = {
    WebDriverManager.chromedriver().setup()
    ChromeOptions chromeOptions = new ChromeOptions()
    chromeOptions.setHeadless(true)

    Dimension dimension = new Dimension(1920, 1080)
    ChromeDriver chromeDriver = new ChromeDriver(chromeOptions)
    chromeDriver.manage().window().setSize(dimension)

    return chromeDriver
}
