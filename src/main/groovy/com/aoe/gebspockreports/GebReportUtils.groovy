/**
 * Copyright 2017-2019 Tilman Ginzel, AOE GmbH
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

import com.aoe.gebspockreports.report.GebReport
import com.google.gson.GsonBuilder
import geb.ConfigurationLoader

import java.util.regex.Matcher

/**
 * Utility class to access relevant geb report info more easily.
 */
class GebReportUtils {

    static final GEB_REPORT_JSON_FILE = "geb-report-info.json"
    static final GEB_CONF = new ConfigurationLoader().getConf()
    static final REPORT_DIR = GEB_CONF.reportsDir

    static gson = new GsonBuilder().setPrettyPrinting().create()

    static {
        if (!REPORT_DIR.exists()) {
            REPORT_DIR.mkdirs()
        }

        // write css and js files to report dir
        ['base.css', 'spec.css', 'summary.css', 'list.min.js', 'chartist.min.css', 'chartist.min.js' ].each { file ->
            writeResourceFile("/templates/$file", file)
        }

        // write custom css file if it was configured to report dir
        def customCssFile = GebReportConfigLoader.instance.config.customCssFile
        if (customCssFile != null) {
            writeResourceFile("/$customCssFile", "custom.css")
        }
    }

    /**
     * Write the content of a file to a new file.
     *
     * @param src - source file path
     * @param dest - destination file path
     */
    private static void writeResourceFile(String src, String dest) {
        try {
            def dst = new File(REPORT_DIR, dest)
            dst.write(GebReportUtils.class.getResource(src).text)
        } catch (Exception e) {
            // todo: proper logging
            println("Could not write resource file from $src to $dest. ${e.getMessage()}")
            throw e
        }
    }

    /**
     * Reads the geb report from a json file and converts it into a GebReport object.
     *
     * @return GebReport
     */
    static GebReport readGebReport() {
        def gebReportFile = new File(GEB_CONF.reportsDir.path, GEB_REPORT_JSON_FILE)
        gebReportFile.exists() ? gson.fromJson(new FileReader(gebReportFile), GebReport.class) : new GebReport()
    }

    /**
     * Writes a GebReport object to a json file.
     *
     * @param gebReport
     */
    static void writeGebReport(GebReport gebReport) {
        def gebReportFile = new File(GEB_CONF.reportsDir.path, GEB_REPORT_JSON_FILE)
        gebReportFile.write(gson.toJson(gebReport))
    }

    /**
     * Converts "build/reportDir/com/example/MyGebSpec"
     * to "com.example.MyGebSpec".
     *
     * This is necessary because the spock-report template creator has only
     * access to the specs class name, which we'll use as an identifier.
     *
     * @param specPath
     * @return specPath converted to class name
     */
    static String createSpecLabelFromPath(String specPath) {
        String label = specPath
        label = removeReportDirFromPath(label)
        label.replaceAll(Matcher.quoteReplacement(File.separator), ".")
    }

    /**
     * We need relative paths most of the time without the reportDir.
     *
     * @param path
     * @return path with subtracted reportDir
     */
    static String removeReportDirFromPath(String path) {
        if (path.startsWith(REPORT_DIR.path)) {
            path = path.substring(REPORT_DIR.path.size())
        }

        if (path.startsWith(File.separator)) {
            path = path.substring(1)
        }

        path
    }

    /**
     * Format milliseconds as string.
     * If the duration is longer than 60 seconds, format as minutes, otherwise as seconds.
     *
     * @param millis
     * @return milliseconds as formatted string
     */
    static String formatMillis(long millis) {
        if (millis > 60000) {
            float minutes = (float) ((millis / 1000.0) / 60f)
            String.format("%.3f min", minutes)
        } else {
            float seconds = (float) (millis / 1000.0f)
            String.format("%.3f sec", seconds)
        }
    }

    /**
     * Write the full stacktrace to the given writer.
     *
     * @param t
     */
    static void writeStacktrace(Throwable t, Writer writer) {
        t.printStackTrace(new PrintWriter(writer))
    }
}
