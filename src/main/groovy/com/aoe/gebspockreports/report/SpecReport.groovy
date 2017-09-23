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
package com.aoe.gebspockreports.report

class SpecReport {

    String label
    List<FeatureReport> features

    SpecReport() {
        features = new ArrayList<>()
    }

    FeatureReport findFeatureByNumber(int number) {
        features.find { feature -> feature.number == number}
    }

    /**
     * Used while creating the spock-report template.
     *
     * @param number - feature number
     * @param featureName - the name provided by spock-reports during template creation
     * @return FeatureReport
     */
    FeatureReport findFeatureByNumberAndName(int number, String featureName) {
        features.find { feature ->
            feature.number == number && feature.label.startsWith(featureName)
        }
    }
}
