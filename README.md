# geb-spock-reports

geb-spock-reports is a library to integrate [Geb](http://gebish.org/) screenshots into [spock-reports](https://github.com/renatoathaydes/spock-reports).
  
## Usage

Add required dependencies to your `build.gradle`.

```groovy
// add a dependency to this library (todo: publish jar)

// required spock libraries
testCompile "org.spockframework:spock-core:1.1-groovy-2.4-rc-2"
testCompile ("com.athaydes:spock-reports:1.3.1") { transitive = false }

// required geb libraries
testCompile "org.gebish:geb-spock:1.1.1"

// you may also need selenium support
compile "org.seleniumhq.selenium:selenium-firefox-driver:2.52.0"
compile "org.seleniumhq.selenium:selenium-support:2.52.0"

// recommended for logging
testCompile 'org.slf4j:slf4j-api:1.7.13'
testCompile 'org.slf4j:slf4j-simple:1.7.13'
```

Create a `GebConfig.groovy` in `src/test/resources`.
See [Book of Geb](http://gebish.org/manual/current/#configuration) for further configuration.

```groovy
import com.aoe.gebspockreport.GebReportingListener

reportingListener = new GebReportingListener()
reportsDir = 'build/geb-spock-reports'
```

Create a properties file named `src/test/resources/META-INF/services/com.athaydes.spockframework.report.IReportCreator.properties`.
The following properties are required.

```properties
# output directory relative to working directory
com.athaydes.spockframework.report.outputDir=build/geb-spock-reports

# let's use the TemplateReportCreator to utilize our custom template
com.athaydes.spockframework.report.IReportCreator=com.athaydes.spockframework.report.template.TemplateReportCreator

# specific properties to the TemplateReportCreator
com.athaydes.spockframework.report.template.TemplateReportCreator.specTemplateFile=/templates/spec-template.html
com.athaydes.spockframework.report.template.TemplateReportCreator.reportFileExtension=html
com.athaydes.spockframework.report.template.TemplateReportCreator.summaryTemplateFile=/templates/summary-template.html
com.athaydes.spockframework.report.template.TemplateReportCreator.summaryFileName=index.html
```

See the [spock-reports documentation](https://github.com/renatoathaydes/spock-reports#customizing-the-reports) for further configuration.

Make sure that the `reportDir` in `GebConfig.groovy` matches the `outputDir` in the `.properties` file!
