import com.aoe.gebspockreports.GebReportingListener
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver

reportingListener = new GebReportingListener()
reportsDir = 'build/geb-spock-reports'

ChromeDriverManager.instance.setup()
driver = { new ChromeDriver() }