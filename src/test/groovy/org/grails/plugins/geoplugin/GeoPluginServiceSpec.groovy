package org.grails.plugins.geoplugin

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
@TestFor(GeoPluginService)
class GeoPluginServiceSpec extends Specification {

    void "test fetchGeoPluginResponseByIpAddress should return status code: #expectedResponseCode for #ipaddress"(String ipaddress, Integer expectedResponseCode) {
        when:
        GeoPluginResponse rsp = service.fetchGeoPluginResponseByIpAddress(ipaddress)

        then:
        rsp.geopluginStatus == expectedResponseCode

        where:
        ipaddress       || expectedResponseCode
        '88.12.40.19'   || 404
        '127.0.0.1'     || 404
    }

    void "test fetchGeoPluginResponseByIpAddress should return country #expectedCountryCode for #ipaddress"(String ipaddress, String expectedCountryCode) {
        when:
        GeoPluginResponse rsp = service.fetchGeoPluginResponseByIpAddress(ipaddress)

        then:
        rsp.geopluginCountryCode == expectedCountryCode

        where:
        ipaddress       || expectedCountryCode
        '88.12.40.19'   || 'ES'
        '127.0.0.1'     || ''
    }
}
