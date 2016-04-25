package org.grails.plugins.geoplugin

import groovy.json.JsonSlurper

class GeoPluginService {


    static String buildGeoPluginUrlWithIpAddress(String ipAddress) {
        "http://www.geoplugin.net/json.gp?ip=$ipAddress"
    }


    GeoPluginResponse fetchGeoPluginResponseByIpAddress(String ipAddress) {

        def jsonText = new URL(buildGeoPluginUrlWithIpAddress(ipAddress)).text
        JsonSlurper jsonSlurper = new JsonSlurper()

        Object result = jsonSlurper.parseText(jsonText)
        GeoPluginResponse pluginResponse = new GeoPluginResponse()

        pluginResponse.geopluginRequest = result["geoplugin_request"]
        pluginResponse.geopluginStatus = result["geoplugin_status"]
        pluginResponse.geopluginCredit = result["geoplugin_credit"]
        pluginResponse.geopluginCity = result["geoplugin_city"]
        pluginResponse.geopluginRegion = result["geoplugin_region"]
        pluginResponse.geopluginAreaCode = result["geoplugin_areaCode"]
        pluginResponse.geopluginDmaCode = result["geoplugin_dmaCode"]
        pluginResponse.geopluginCountryCode = result["geoplugin_countryCode"]
        pluginResponse.geopluginCountryName = result["geoplugin_countryName"]
        pluginResponse.geopluginContinentCode = result["geoplugin_continentCode"]
        pluginResponse.geopluginLatitude = result["geoplugin_latitude"]
        pluginResponse.geopluginLongitude = result["geoplugin_longitude"]
        pluginResponse.geopluginRegionCode = result["geoplugin_regionCode"]
        pluginResponse.geopluginRegionName = result["geoplugin_regionName"]
        pluginResponse.geopluginCurrencyCode = result["geoplugin_currencyCode"]
        pluginResponse.geopluginCurrencySymbol = result["geoplugin_currencySymbol"]
        pluginResponse.geopluginCurrencySymbolUTF8 = result["geoplugin_currencySymbol_UTF8"]
        pluginResponse.geopluginCurrencyConverter = result["geoplugin_currencyConverter"]
        pluginResponse
    }
}
