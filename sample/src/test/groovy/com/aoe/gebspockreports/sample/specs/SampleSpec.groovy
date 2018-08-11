/**
 * Copyright 2017-2018 Tilman Ginzel, AOE GmbH
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

import spock.lang.Ignore
import spock.lang.Issue
import spock.lang.See
import spock.lang.Specification

/**
 * @author Tilman Ginzel
 */
class SampleSpec extends Specification {

    @Issue("123654")
    def "maximum of two numbers"() {
        given:
        def x = 17

        expect: "42 is bigger than 17"
        Math.max(x, 42) == 42
    }

    @Ignore("Not fully implemented yet")
    def "ignore me"() {
        expect:
        Math.max(1, 1) == 1
    }

    def "maximum of two numbers (expected failure)"() {
        expect: "17 is bigger than 42"
        Math.max(17, 42) == 17
    }

    @See("Interesting link")
    def "data table (expected failure)"() {
        expect:
        Math.min(a, b) == c

        where:
        a | b || c
        1 | 2 || 1
        2 | 1 || 2
        3 | 1 || 1
        1 | 4 || 4
    }

    def "long exception message"() {
        expect:
        throw new IllegalStateException("this is a really long exception message which would break the layout without scrolling.")
    }

    def "this is going to be a really bloated feature method with a long label and block description to check correct word wrap behavior"() {
        expect: "this is a really long block description which should result in a proper word wrap. the code below on the other hand is scrollable and should not result in a word wrap."
        def x = 0 + 1 + 1 + 2 + 3 + 5 + 8 + 13 + 21 + 34 + 55 + 89 + 144 + 233 + 377 + 610 + 987 + 1597
        def y = 4180
        x == y
    }

    def "huge data table"() {
        expect:
        [param1, param2, param3, param4, param5, param6, param7, param8, param9, param10].max() == c

        where:
        param1 | param2 | param3 | param4 | param5 | param6 | param7 | param8 | param9 | param10 || c
        1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10      || 10
        1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10      || 10
        1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10      || 10
        1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10      || 10
        1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10      || 10
        1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10      || 10
        1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10      || 10
        1      | 2      | 3      | 4      | 5      | 6      | 7      | 8      | 9      | 10      || 10
    }
}
