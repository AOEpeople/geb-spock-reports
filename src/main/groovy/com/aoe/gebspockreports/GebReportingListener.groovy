/**
 * Copyright 2017 Tilman Ginzel, AOE GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aoe.gebspockreports

import com.aoe.gebspockreports.report.FeatureReport
import com.aoe.gebspockreports.report.GebArtifact
import com.aoe.gebspockreports.report.SpecReport
import geb.report.ReportState
import geb.report.Reporter
import geb.report.ReportingListener

/**
 * @author Tilman Ginzel
 */
class GebReportingListener implements ReportingListener {

    @Override
    void onReport(Reporter reporter, ReportState reportState, List<File> reportFiles) {
        try {
            def gebReport = GebReportUtils.readGebReport()

            // find or create spec report
            def specLabel = GebReportUtils.createSpecLabelFromPath(reportState.browser.getReportGroupDir().getPath())
            def specReport = gebReport.findSpecByLabel(specLabel)
            if (!specReport) {
                specReport = new SpecReport()
                specReport.label = specLabel
                gebReport.specs.add(specReport)
            }

            try {
                // get info about currently executed feature and report
                def (featureNum, reportNum, reportLabel) = reportState.label.split('-', 3) // does not split feature and report label
                int featureNumber = featureNum.toInteger()
                int reportNumber = reportNum.toInteger()

                // find or create feature report
                def featureReport = specReport.findFeatureByNumber(featureNumber)
                if (!featureReport) {
                    featureReport = new FeatureReport()
                    featureReport.number = featureNumber
                    featureReport.label = reportLabel
                    specReport.features.add(featureReport)
                }

                // find or create artifact
                def gebArtifact = featureReport.findArtifactByNumber(reportNumber)
                if (!gebArtifact) {
                    gebArtifact = new GebArtifact()
                    gebArtifact.number = reportNumber
                    gebArtifact.timestamp = new Date().time
                    gebArtifact.label = reportLabel
                    gebArtifact.pageObject = reportState.browser.page.getClass().getSimpleName()
                    gebArtifact.url = reportState.browser.getCurrentUrl()
                    featureReport.artifacts.add(gebArtifact)
                }

                // add report files
                gebArtifact.addFiles(reportFiles)
            } catch(e) {
                // in the case that some error occurred while splitting the report label,
                // this artifact will be added directly to the spec as an unassigned artifact

                def gebArtifact = specReport.findUnassignedArtifactByLabel(reportState.label)
                if (!gebArtifact) {
                    gebArtifact = new GebArtifact()
                    gebArtifact.timestamp = new Date().time
                    gebArtifact.label = reportState.label
                    gebArtifact.pageObject = reportState.browser.page.getClass().getSimpleName()
                    gebArtifact.url = reportState.browser.getCurrentUrl()
                    specReport.unassignedArtifacts.add(gebArtifact)
                }

                gebArtifact.addFiles(reportFiles)
            }

            // serialize geb report
            GebReportUtils.writeGebReport(gebReport)
        } catch(e) {
            println("Unexpected error while creating report for label '${reportState.label}: $e.")
        }
    }
}
