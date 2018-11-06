package com.aoe.gebspockreports.sample.specs

import spock.lang.Ignore
import spock.lang.Specification

class PassSkipTestSpec extends Specification {

    @Ignore
    def "skipping this test 1"() {
        expect:
        assert true
    }

    def "pass this test"() {
        expect:
        assert true
    }

    @Ignore
    def "skipping this test 2"() {
        expect:
        assert true
    }
}