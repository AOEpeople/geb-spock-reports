package com.aoe.gebspockreport

import geb.report.ReportState
import geb.report.Reporter
import geb.report.ReportingListener
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

/**
 * @author Tilman Ginzel
 */
class GebReportingListener implements ReportingListener {

    @Override
    void onReport(Reporter reporter, ReportState reportState, List<File> reportFiles) {
        def gebReportFile = new File(reportState.browser.config.reportsDir.path, 'geb-report-info.json')
        def reportsDir = reportState.browser.config.reportsDir.path
        def allReports = gebReportFile.exists() ? new JsonSlurper().parseText(gebReportFile.text) : [specs: []]

        def specLabel = reportState.browser.getReportGroupDir().getName()
        def (featureNumber, reportNumber, featureLabel) = reportState.label.split('-', 3) // does not split featureLabel and reportLabel
        featureNumber = featureNumber.toInteger()
        reportNumber = reportNumber.toInteger()

        // add spec to reports if it does not exist yet
        if (!(specLabel in allReports.specs.label)) {
            allReports.specs << [
                label: specLabel,
                features: []
            ]
        }

        // add feature to spec if it does not exist yet
        def spec = allReports.specs.find { spec -> spec.label == specLabel }
        if (!(featureNumber in spec.features.number)) {
            spec.features << [
                label: featureLabel,
                number: featureNumber,
                reports: []
            ]
        }

        // add report to feature if it does not exist yet
        def feature = spec.features.find { feature -> feature.number == featureNumber }
        if (!(reportNumber in feature.reports.number)) {
            feature.reports << [
                label: featureLabel,
                time: new Date().time,
                number: reportNumber,
                page: reportState.browser.page.getClass().getSimpleName(),
                url: reportState.browser.driver.currentUrl,
                files: []
            ]
        }

        // add file to report
        def report = feature.reports.find { report -> report.number == reportNumber }
        def files = reportFiles.collect { it.path - reportsDir }
        report.files += files // todo: check if file already exists is list

        gebReportFile.write(JsonOutput.prettyPrint(JsonOutput.toJson(allReports)))
    }
}
