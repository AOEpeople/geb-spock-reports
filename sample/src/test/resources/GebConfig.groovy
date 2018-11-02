import com.aoe.gebspockreports.GebReportingListener
import org.openqa.selenium.chrome.ChromeDriver

reportingListener = new GebReportingListener()
reportsDir = 'build/geb-spock-reports'

driver = { new ChromeDriver() }