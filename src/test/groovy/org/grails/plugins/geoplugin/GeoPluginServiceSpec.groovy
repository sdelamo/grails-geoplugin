package org.grails.plugins.geoplugin

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
@TestFor(GeoPluginService)
class GeoPluginServiceSpec extends Specification {

    void "test fetchGeoPluginResponseByIpAddress should return status code: #expectedResponseCode an #expectedCountryCode for #ipaddress"(String ipaddress, String expectedCountryCode, Integer expectedResponseCode) {
        when:
        GeoPluginResponse rsp = service.fetchGeoPluginResponseByIpAddress(ipaddress)

        then:
        rsp.geopluginStatus == expectedResponseCode
        rsp.geopluginCountryCode == expectedCountryCode

        where:
        ipaddress       || expectedCountryCode | expectedResponseCode
        '88.12.40.19'   || 'ES'                | 200
        'rabbit'        || null                | 404
        '88124019'      || null                | 404
        '127.0.0.1'     || ''                  | 404
    }
}
