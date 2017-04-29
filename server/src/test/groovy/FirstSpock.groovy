import spock.lang.Specification

/**
 * Created by gorg on 29.04.17.
 */

class FirstSpock extends Specification {

    def "this is first test"() {

    given:
        Object o = new Object()

    expect:
        o!=null
}

}