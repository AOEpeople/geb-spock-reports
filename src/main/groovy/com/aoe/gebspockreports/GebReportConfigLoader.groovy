/**
 * Copyright 2018 Tilman Ginzel, AOE GmbH
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

class GebReportConfigLoader {

    private static GebReportConfigLoader gebReportConfig

    private static final defaultConfigFilePath = "/GebSpockReportsDefaultConfig.groovy"
    private static final customConfigFilePath = "/GebSpockReportsConfig.groovy"

    Map config

    static GebReportConfigLoader getInstance() {
        if (!gebReportConfig) {
            gebReportConfig = new GebReportConfigLoader()
            gebReportConfig.load()
        }

        gebReportConfig
    }

    private load() {
        ConfigSlurper configSlurper = new ConfigSlurper()
        ConfigObject defaultConfig = configSlurper.parse(getClass().getResource(defaultConfigFilePath))

        try {
            ConfigObject customConfig = configSlurper.parse(getClass().getResource(customConfigFilePath))
            config = defaultConfig.merge(customConfig)
        } catch (Exception e) {
            // ignore exception and use default config (todo: log exception)
            config = defaultConfig
        }
    }
}
