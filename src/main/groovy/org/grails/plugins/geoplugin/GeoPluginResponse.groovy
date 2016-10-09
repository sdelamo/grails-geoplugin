package org.grails.plugins.geoplugin

import groovy.transform.CompileStatic

@CompileStatic
class GeoPluginResponse {
    String geopluginRequest
    Integer geopluginStatus
    String geopluginCredit
    String geopluginCity
    String geopluginRegion
    String geopluginAreaCode
    String geopluginDmaCode
    String geopluginCountryCode
    String geopluginCountryName
    String geopluginContinentCode
    String geopluginLatitude
    String geopluginLongitude
    String geopluginRegionCode
    String geopluginRegionName
    String geopluginCurrencyCode
    String geopluginCurrencySymbol
    String geopluginCurrencySymbolUTF8
    BigDecimal geopluginCurrencyConverter
}
