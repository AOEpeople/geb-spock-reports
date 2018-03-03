# Changelog

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