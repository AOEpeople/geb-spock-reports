package com.aoe.gebspockreports.sample.specs

import spock.lang.Ignore
import spock.lang.Specification

class SkipTestSpec extends Specification {

    @Ignore
    def "skipping this test"() {
        expect:
        assert true
    }
}