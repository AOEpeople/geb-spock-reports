import com.aoe.gebspockreports.GebReportingListener
import org.openqa.selenium.firefox.FirefoxDriver

reportingListener = new GebReportingListener()
reportsDir = 'build/geb-spock-reports'

driver = { new FirefoxDriver() }