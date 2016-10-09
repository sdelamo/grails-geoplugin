package org.grails.plugins.geoplugin

import groovy.json.JsonSlurper

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic
class GeoPluginService implements GrailsConfigurationAware {

    String endpoint
    int maxRequestsPerMinute

    String buildGeoPluginUrlWithIpAddress(String ipAddress) {
        "${endpoint}?ip=$ipAddress"
    }

    Map<String, GeoPluginResponse> findGeoPluginResponseForIpAddresses(List<String> ipAddresses) {

        Map<String, GeoPluginResponse> m = [:]
        for (int i = 0; i < ipAddresses.size(); i++) {
            if ((i + 1) % maxRequestsPerMinute == 0) {
                // Pause for two minutes, be gentle to geoPlugin API
                sleep(60_000)
                sleep(60_000)
            }
            String ipAddress = ipAddresses[i]
            def geoResponse = fetchGeoPluginResponseByIpAddress(ipAddress)
            m[ipAddress] = geoResponse
        }
        m
    }

    GeoPluginResponse fetchGeoPluginResponseByIpAddress(String ipAddress) {
        String jsonText = new URL(buildGeoPluginUrlWithIpAddress(ipAddress)).text
        JsonSlurper jsonSlurper = new JsonSlurper()
        Object result = jsonSlurper.parseText(jsonText)
        instantiateGeoPluginResponseWithObject(result)
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    GeoPluginResponse instantiateGeoPluginResponseWithObject(Object result) {
        GeoPluginResponse pluginResponse = new GeoPluginResponse()

        pluginResponse.with {
            geopluginRequest = result["geoplugin_request"]
            geopluginStatus = result["geoplugin_status"]
            geopluginCredit = result["geoplugin_credit"]
            geopluginCity = result["geoplugin_city"]
            geopluginRegion = result["geoplugin_region"]
            geopluginAreaCode = result["geoplugin_areaCode"]
            geopluginDmaCode = result["geoplugin_dmaCode"]
            geopluginCountryCode = result["geoplugin_countryCode"]
            geopluginCountryName = result["geoplugin_countryName"]
            geopluginContinentCode = result["geoplugin_continentCode"]
            geopluginLatitude = result["geoplugin_latitude"]
            geopluginLongitude = result["geoplugin_longitude"]
            geopluginRegionCode = result["geoplugin_regionCode"]
            geopluginRegionName = result["geoplugin_regionName"]
            geopluginCurrencyCode = result["geoplugin_currencyCode"]
            geopluginCurrencySymbol = result["geoplugin_currencySymbol"]
            geopluginCurrencySymbolUTF8 = result["geoplugin_currencySymbol_UTF8"]
            geopluginCurrencyConverter = result["geoplugin_currencyConverter"]
        }
        pluginResponse
    }

    @Override
    void setConfiguration(Config co) {

        endpoint = co.getProperty('geoplugin.endpoint', String, 'http://www.geoplugin.net/json.gp')
        maxRequestsPerMinute = co.getProperty('geoplugin.maxRequestsPerMinute', Integer, 120)

    }
}
