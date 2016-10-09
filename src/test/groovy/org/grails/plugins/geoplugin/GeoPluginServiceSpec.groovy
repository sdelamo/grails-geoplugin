package org.grails.plugins.geoplugin

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(GeoPluginService)
class GeoPluginServiceSpec extends Specification {

    @Unroll
    void "test fetchGeoPluginResponseByIpAddress should return status code: #expectedResponseCode for #ipaddress"(String ipaddress, Integer expectedResponseCode) {
        given:
        def service = new GeoPluginService()
        service.endpoint = 'http://www.geoplugin.net/json.gp'

        when:
        GeoPluginResponse rsp = service.fetchGeoPluginResponseByIpAddress(ipaddress)


        then:
        rsp.geopluginStatus == expectedResponseCode

        where:
        ipaddress       || expectedResponseCode
        '88.12.40.19'   || 200
        '127.0.0.1'     || 404
    }

    @Unroll
    void "test fetchGeoPluginResponseByIpAddress should return country #expectedCountryCode for #ipaddress"(String ipaddress, String expectedCountryCode) {
        given:
        def service = new GeoPluginService()
        service.endpoint = 'http://www.geoplugin.net/json.gp'

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
