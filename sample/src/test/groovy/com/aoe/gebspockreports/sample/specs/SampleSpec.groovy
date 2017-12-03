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

import spock.lang.Specification

/**
 * @author Tilman Ginzel
 */
class SampleSpec extends Specification {

    def "maximum of two numbers"() {
        expect: "42 is bigger than 17"
        Math.max(17, 42) == 42
    }

    def "maximum of two numbers (expected failure)"() {
        expect: "17 is bigger than 42"
        Math.max(17, 42) == 17
    }
}
