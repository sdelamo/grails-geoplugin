Grails GEOPLUGIN
================

[![Build Status](https://travis-ci.org/sdelamo/grails-geoplugin.svg?branch=master)](https://travis-ci.org/sdelamo/grails-geoplugin)
[![Download](https://api.bintray.com/packages/sdelamo/plugins/grails-geoplugin/images/download.svg)](https://bintray.com/sdelamo/plugins/grails-geoplugin/_latestVersion)


# Introduction

Grails 3 plugin which exposes a Grails service which queries the geoplugin api (http://www.geoplugin.com) given an ip address


# Installation
Declare the plugin dependency in the build.gradle file, as shown here:

```groovy
repositories {
    ...
    maven { url "http://dl.bintray.com/sdelamo/plugins" }
}
dependencies {
    ...
    compile 'org.grails.plugins:grails-geo-plugin:0.5'
}
```

# Usage

The plugin provides the following Grails artefacts:

* **GeoPluginService**

```groovy
Map<String, GeoPluginResponse> m = geoPluginService.findGeoPluginResponseForIpAddresses(['88.12.40.19'])
assert m['88.12.40.19'].geopluginCountryCode == 'ES'

```
# Bugs

To report any bug, please use the project [Issues](http://github.com/sdelamo/grails-geoplugin/issues) section on GitHub.

Feedback and pull requests are welcome!
