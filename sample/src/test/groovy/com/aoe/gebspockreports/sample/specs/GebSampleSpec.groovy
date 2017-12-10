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

package com.aoe.gebspockreports.sample.specs

import geb.spock.GebReportingSpec

class GebSampleSpec extends GebReportingSpec {

    def "simple screenshot test"() {
        when:
        drive {
            go "http://gebish.org"
        }

        report "Geb start page"

        then:
        title.contains("Browser Automation")
    }

    def "browser.report with invalid label format"() {
        when:
        drive {
            go "http://gebish.org"
        }

        // this report gets ignored as the label format is invalid
        browser.report("invalid label")

        // use the report method provided by GebReportingSpec
        report "Geb start page"

        then:
        title.contains("Browser Automation")
    }

    def "screenshot test (expected failure)"() {
        when:
        drive {
            go "http://gebish.org"
        }

        report "Geb start page"

        then:
        title.contains("Invalid title")
    }
}
