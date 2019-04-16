# Changelog

## v0.2.3

* Fix mapping iusse when using data-driven tables with `@Unroll` annotation (#18)

## v0.2.2

* Show full stacktrace with nested exceptions in report 

## v0.2.1

Many thanks to @trinidad8516 for providing the following changes:
* Add support to show projectName and projectVersion on summary page
  * See [configuration](https://github.com/AOEpeople/geb-spock-reports#configure-spock-reports) on how to use them
* Highlight skipped specifications on summary page and add quick filter

## v0.2.0

This update contains some major template design changes.
In addition, new features from spock-reports have been added.

* **Breaking change**: Make sure to update spock-reports to 1.6.0 or later
* Major design improvements
* Add support for `reportInfo` and `reportHeader` from spock-reports (#10)
* Summary page improvements:
  * Add pagination to specification table. This must be enabled via a configuration. 
  * Add filter and search functionality to specification table
  * Show pie chart to give a quick summary on passed/failed specifications
* Minor improvements regarding long specification titles and word-wrap

Many thanks to @durgeshshisode1988 for his valuable feedback and ideas! :) 

## v0.1.5

* Screenshots created outside of feature methods are now shown in a separate area in the html report (#6)
* Fix layout issues where stacktraces and other long lines would break the layout (#7)

## v0.1.4

* Check setting to show source code next to block text (thanks to @tenwit)
  * `com.athaydes.spockframework.report.showCodeBlocks=true` must be set
* Add toggle button to show stacktrace
* Ignore Geb reports with invalid label format
 
## v0.1.3

* Escape file separator for Windows environment (thanks to @lubosek)

## v0.1.2

* Use utf8 charset in html templates

## v0.1.1

* Fix wrong package name in `spec-template.html`

## v0.1.0

* Initial release