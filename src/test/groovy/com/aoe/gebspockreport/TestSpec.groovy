package com.aoe.gebspockreport

import spock.lang.Specification

/**
 * @author Tilman Ginzel
 */
class TestSpec extends Specification {

    def "maximum of two numbers"() {
        expect:
        Math.max(17, 42) == 42
    }

}
