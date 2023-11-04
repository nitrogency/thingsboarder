# Thingsboarder

An app made for gathering basic Android telemetry data and sending it to your Thingsboard dashboard/gateway using HTTP.

# Usage

**1. Clone the repository**

Clone the repository with your preferred method.

**2. Open the `strings.xml` file**

It can be found in `/diagnosis/app/src/main/res/values/`. Open the file either using your text editor, or by opening the whole project in Android Studio (or preferred IDE). You're looking for these two lines:
![image](https://github.com/nitrogency/thingsboarder/assets/129758495/a2e4a32b-6aa1-4741-bb81-a90e40d3e857)

Replace the FIRST (`url_address`) with the telemetry URL that Thingsboard generated for your device. You can find it here:
![image(2)](https://github.com/nitrogency/thingsboarder/assets/129758495/54bb5e52-d39b-43bf-89eb-6d51184a9e45)

Replace the SECOND (`public_address`) with a URL that you'd like to receive the device's IP address from. For example, Amazon AWS offers this here: http://checkip.amazonaws.com/. You can also use something like ipify.

**3. Launch the application**

You can either do this in Android Studio (through a virtual device or USB debugging) or on a physical device by building the `.apk` file. Here's how to build the `.apk` file using Android Studio:

Click on the square in the left corner of the editor, and select "Build Variants":
![image](https://github.com/nitrogency/thingsboarder/assets/129758495/2e0af24d-052f-44ea-8bd4-4428bf111f50)

Click on the "debug" part of the row to open a dropdown menu. Select "release":
![image](https://github.com/nitrogency/thingsboarder/assets/129758495/b792e6c1-32b0-430f-a9d4-3636808d87d9)

Once clicked, Gradle will automatically build the project. Once done, build the `.apk`, following any prompts Android Studio might show (about signing the `.apk`, for example.):
![image](https://github.com/nitrogency/thingsboarder/assets/129758495/d5c8a557-1f5f-422a-a0aa-6a74a0b0ff57)

When this is done, Android Studio will show you where the .apk was saved. All left to do now is move the file to your device!

**4. Layout example (optional)**

You can set up your dashboard however you like, but if you want to save some time and want to see everything this app sends, you can use this example: 

```json
{
  "title": "Phone Dashboard",
  "image": null,
  "mobileHide": false,
  "mobileOrder": null,
  "configuration": {
    "description": "",
    "widgets": {
      "2e724852-1545-c353-9f70-45e71dcfa42d": {
        "typeFullFqn": "system.battery_level",
        "type": "latest",
        "sizeX": 2.5,
        "sizeY": 2.5,
        "config": {
          "datasources": [
            {
              "type": "device",
              "name": "",
              "deviceId": "257d3bf0-7882-11ee-8b0f-f3667b03f9fc",
              "dataKeys": [
                {
                  "name": "batteryCap",
                  "type": "timeseries",
                  "label": "batteryCap",
                  "color": "#2196f3",
                  "settings": {},
                  "_hash": 0.6700649521675981
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              }
            }
          ],
          "timewindow": {
            "displayValue": "",
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 1,
              "interval": 1000,
              "timewindowMs": 60000,
              "quickInterval": "CURRENT_DAY"
            },
            "history": {
              "historyType": 0,
              "interval": 1000,
              "timewindowMs": 60000,
              "fixedTimewindow": {
                "startTimeMs": 1698749890464,
                "endTimeMs": 1698836290464
              },
              "quickInterval": "CURRENT_DAY"
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": true,
          "backgroundColor": "rgba(0, 0, 0, 0)",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "0px",
          "settings": {
            "background": {
              "type": "color",
              "color": "#fff",
              "overlay": {
                "enabled": false,
                "color": "rgba(255,255,255,0.72)",
                "blur": 3
              }
            },
            "layout": "vertical_solid",
            "showValue": true,
            "autoScaleValueSize": true,
            "valueFont": {
              "family": "Roboto",
              "size": 20,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500",
              "lineHeight": "24px"
            },
            "valueColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.87)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "batteryLevelColor": {
              "color": "rgba(92, 223, 144, 1)",
              "type": "range",
              "rangeList": [
                {
                  "from": 0,
                  "to": 25,
                  "color": "rgba(227, 71, 71, 1)"
                },
                {
                  "from": 25,
                  "to": 50,
                  "color": "rgba(246, 206, 67, 1)"
                },
                {
                  "from": 50,
                  "to": 100,
                  "color": "rgba(92, 223, 144, 1)"
                }
              ],
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "batteryShapeColor": {
              "color": "rgba(92, 223, 144, 0.32)",
              "type": "range",
              "rangeList": [
                {
                  "from": 0,
                  "to": 25,
                  "color": "rgba(227, 71, 71, 0.32)"
                },
                {
                  "from": 25,
                  "to": 50,
                  "color": "rgba(246, 206, 67, 0.32)"
                },
                {
                  "from": 50,
                  "to": 100,
                  "color": "rgba(92, 223, 144, 0.32)"
                }
              ],
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            }
          },
          "title": "Battery level",
          "dropShadow": true,
          "enableFullscreen": false,
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "units": "%",
          "decimals": 0,
          "useDashboardTimewindow": true,
          "showLegend": false,
          "widgetStyle": {},
          "actions": {},
          "configMode": "basic",
          "displayTimewindow": true,
          "margin": "0px",
          "borderRadius": "0px",
          "widgetCss": "",
          "pageSize": 1024,
          "noDataDisplayMessage": "",
          "showTitleIcon": false,
          "titleTooltip": "",
          "titleFont": {
            "size": 16,
            "sizeUnit": "px",
            "family": "Roboto",
            "weight": "500",
            "style": "normal",
            "lineHeight": "24px"
          },
          "titleIcon": "",
          "iconColor": "rgba(0, 0, 0, 0.87)",
          "iconSize": "14px",
          "timewindowStyle": {
            "showIcon": true,
            "iconSize": "14px",
            "icon": "query_builder",
            "iconPosition": "left",
            "font": {
              "size": 12,
              "sizeUnit": "px",
              "family": null,
              "weight": null,
              "style": null,
              "lineHeight": "1"
            },
            "color": null
          },
          "titleColor": "rgba(0, 0, 0, 0.87)"
        },
        "row": 0,
        "col": 0,
        "id": "2e724852-1545-c353-9f70-45e71dcfa42d"
      },
      "2c88820b-8fca-83f7-2f0a-e103881904e0": {
        "typeFullFqn": "system.cards.value_card",
        "type": "latest",
        "sizeX": 3,
        "sizeY": 3,
        "config": {
          "datasources": [
            {
              "type": "device",
              "name": "",
              "deviceId": "257d3bf0-7882-11ee-8b0f-f3667b03f9fc",
              "dataKeys": [
                {
                  "name": "batteryStatus",
                  "type": "timeseries",
                  "label": "Charging:",
                  "color": "#2196f3",
                  "settings": {},
                  "_hash": 0.9498731309839267
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              }
            }
          ],
          "timewindow": {
            "displayValue": "",
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 1,
              "interval": 1000,
              "timewindowMs": 60000,
              "quickInterval": "CURRENT_DAY"
            },
            "history": {
              "historyType": 0,
              "interval": 1000,
              "timewindowMs": 60000,
              "fixedTimewindow": {
                "startTimeMs": 1698750107538,
                "endTimeMs": 1698836507538
              },
              "quickInterval": "CURRENT_DAY"
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": false,
          "backgroundColor": "rgba(0, 0, 0, 0)",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "0px",
          "settings": {
            "labelPosition": "top",
            "layout": "square",
            "showLabel": true,
            "labelFont": {
              "family": "Roboto",
              "size": 16,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "labelColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.87)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "showIcon": true,
            "iconSize": 40,
            "iconSizeUnit": "px",
            "icon": "electrical_services",
            "iconColor": {
              "type": "constant",
              "color": "#5469FF",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "valueFont": {
              "family": "Roboto",
              "size": 52,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "valueColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.87)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "showDate": false,
            "dateFormat": {
              "format": null,
              "lastUpdateAgo": true,
              "custom": false
            },
            "dateFont": {
              "family": "Roboto",
              "size": 12,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "dateColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.38)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "background": {
              "type": "color",
              "color": "#fff",
              "overlay": {
                "enabled": false,
                "color": "rgba(255,255,255,0.72)",
                "blur": 3
              }
            }
          },
          "title": "Value card",
          "dropShadow": true,
          "enableFullscreen": false,
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "units": "",
          "decimals": 0,
          "useDashboardTimewindow": true,
          "showLegend": false,
          "widgetStyle": {},
          "actions": {},
          "configMode": "basic",
          "displayTimewindow": true,
          "margin": "0px",
          "borderRadius": "0px",
          "widgetCss": "",
          "pageSize": 1024,
          "noDataDisplayMessage": "",
          "showTitleIcon": false,
          "titleTooltip": "",
          "titleFont": {
            "size": 12,
            "sizeUnit": "px",
            "family": null,
            "weight": null,
            "style": null,
            "lineHeight": "1.6"
          },
          "titleIcon": "",
          "iconColor": "rgba(0, 0, 0, 0.87)",
          "iconSize": "14px",
          "timewindowStyle": {
            "showIcon": true,
            "iconSize": "14px",
            "icon": "query_builder",
            "iconPosition": "left",
            "font": {
              "size": 12,
              "sizeUnit": "px",
              "family": null,
              "weight": null,
              "style": null,
              "lineHeight": "1"
            },
            "color": null
          }
        },
        "row": 0,
        "col": 0,
        "id": "2c88820b-8fca-83f7-2f0a-e103881904e0"
      },
      "ad7fd1ef-7c90-5466-361b-e3e5175b4a6e": {
        "typeFullFqn": "system.cards.attributes_card",
        "type": "latest",
        "sizeX": 7.5,
        "sizeY": 3,
        "config": {
          "datasources": [
            {
              "type": "entity",
              "name": "",
              "entityAliasId": "372c912f-243c-fb83-07ef-88bc0b059356",
              "dataKeys": [
                {
                  "name": "manufacturer",
                  "type": "timeseries",
                  "label": "Manufacturer",
                  "color": "#2196f3",
                  "settings": {},
                  "_hash": 0.7986597053720489,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                },
                {
                  "name": "model",
                  "type": "timeseries",
                  "label": "Model",
                  "color": "#4caf50",
                  "settings": {},
                  "_hash": 0.7722106739343023,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                },
                {
                  "name": "user",
                  "type": "timeseries",
                  "label": "User",
                  "color": "#f44336",
                  "settings": {},
                  "_hash": 0.290740686460097,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                },
                {
                  "name": "brand",
                  "type": "timeseries",
                  "label": "Brand",
                  "color": "#607d8b",
                  "settings": {},
                  "_hash": 0.755498468047098,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                },
                {
                  "name": "host",
                  "type": "timeseries",
                  "label": "Host",
                  "color": "#9c27b0",
                  "settings": {},
                  "_hash": 0.9417380419973791,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                },
                {
                  "name": "version",
                  "type": "timeseries",
                  "label": "Android version",
                  "color": "#ffc107",
                  "settings": {},
                  "_hash": 0.4021789390272068,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                },
                {
                  "name": "api",
                  "type": "timeseries",
                  "label": "API",
                  "color": "#8bc34a",
                  "settings": {},
                  "_hash": 0.5055101882097636,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                },
                {
                  "name": "timeSinceBoot",
                  "type": "timeseries",
                  "label": "Time since last boot",
                  "color": "#3f51b5",
                  "settings": {},
                  "_hash": 0.77284212247775,
                  "aggregationType": "NONE",
                  "units": "minutes",
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              }
            }
          ],
          "timewindow": {
            "displayValue": "",
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 1,
              "interval": 1000,
              "timewindowMs": 60000,
              "quickInterval": "CURRENT_DAY"
            },
            "history": {
              "historyType": 0,
              "interval": 1000,
              "timewindowMs": 60000,
              "fixedTimewindow": {
                "startTimeMs": 1698766152664,
                "endTimeMs": 1698852552664
              },
              "quickInterval": "CURRENT_DAY"
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": true,
          "backgroundColor": "#fff",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "8px",
          "settings": {},
          "title": "Device information",
          "useDashboardTimewindow": true,
          "displayTimewindow": true,
          "showTitleIcon": false,
          "titleTooltip": "",
          "dropShadow": true,
          "enableFullscreen": true,
          "widgetStyle": {},
          "widgetCss": "",
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "pageSize": 1024,
          "noDataDisplayMessage": ""
        },
        "row": 0,
        "col": 0,
        "id": "ad7fd1ef-7c90-5466-361b-e3e5175b4a6e"
      },
      "fceb9d11-df1c-1f36-d6f0-bfdcb7e5c06f": {
        "typeFullFqn": "system.maps_v2.openstreetmap",
        "type": "latest",
        "sizeX": 8.5,
        "sizeY": 6,
        "config": {
          "datasources": [
            {
              "type": "entity",
              "name": "",
              "entityAliasId": "372c912f-243c-fb83-07ef-88bc0b059356",
              "filterId": null,
              "dataKeys": [
                {
                  "name": "longitude",
                  "type": "timeseries",
                  "label": "longitude",
                  "color": "#2196f3",
                  "settings": {},
                  "_hash": 0.47664934574653406
                },
                {
                  "name": "latitude",
                  "type": "timeseries",
                  "label": "latitude",
                  "color": "#4caf50",
                  "settings": {},
                  "_hash": 0.9896107164838449
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              }
            }
          ],
          "timewindow": {
            "displayValue": "",
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 1,
              "interval": 1000,
              "timewindowMs": 60000,
              "quickInterval": "CURRENT_DAY"
            },
            "history": {
              "historyType": 0,
              "interval": 1000,
              "timewindowMs": 60000,
              "fixedTimewindow": {
                "startTimeMs": 1698766410522,
                "endTimeMs": 1698852810522
              },
              "quickInterval": "CURRENT_DAY"
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": true,
          "backgroundColor": "#fff",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "8px",
          "settings": {
            "provider": "openstreet-map",
            "mapProvider": "OpenStreetMap.Mapnik",
            "useCustomProvider": false,
            "customProviderTileUrl": "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
            "latKeyName": "latitude",
            "lngKeyName": "longitude",
            "xPosKeyName": "xPos",
            "yPosKeyName": "yPos",
            "defaultCenterPosition": "0,0",
            "disableScrollZooming": false,
            "disableDoubleClickZooming": false,
            "disableZoomControl": false,
            "fitMapBounds": true,
            "useDefaultCenterPosition": false,
            "mapPageSize": 16384,
            "markerOffsetX": 0.5,
            "markerOffsetY": 1,
            "posFunction": "return {x: origXPos, y: origYPos};",
            "draggableMarker": false,
            "showLabel": true,
            "useLabelFunction": false,
            "label": "${entityName}",
            "showTooltip": true,
            "showTooltipAction": "click",
            "autocloseTooltip": true,
            "useTooltipFunction": false,
            "tooltipPattern": "<b>${entityName}</b><br/><br/><b>Latitude:</b> ${latitude:7}<br/><b>Longitude:</b> ${longitude:7}<br/><b>Temperature:</b> ${temperature} °C<br/><small>See advanced settings for details</small>",
            "tooltipOffsetX": 0,
            "tooltipOffsetY": -1,
            "color": "#fe7569",
            "useColorFunction": true,
            "colorFunction": "var type = dsData[dsIndex]['Type'];\nif (type == 'colorpin') {\n\tvar temperature = dsData[dsIndex]['temperature'];\n\tif (typeof temperature !== undefined) {\n\t    var percent = (temperature + 60)/120 * 100;\n\t    return tinycolor.mix('blue', 'red', percent).toHexString();\n\t}\n\treturn 'blue';\n}\n",
            "useMarkerImageFunction": true,
            "markerImageSize": 34,
            "markerImageFunction": "var type = dsData[dsIndex]['Type'];\nif (type == 'thermometer') {\n\tvar res = {\n\t    url: images[0],\n\t    size: 40\n\t}\n\tvar temperature = dsData[dsIndex]['temperature'];\n\tif (typeof temperature !== undefined) {\n\t    var percent = (temperature + 60)/120;\n\t    var index = Math.min(3, Math.floor(4 * percent));\n\t    res.url = images[index];\n\t}\n\treturn res;\n}",
            "markerImages": [
              "data:image/png;base64,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",
              "data:image/png;base64,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",
              "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAB/CAYAAAD4mHJdAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAACWAAAAlgB7MGOJQAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAyUSURBVGiB7Zp7kFRVesB/5/S9PdMz/ZoHMwo4MICDuoGVIYICIuzGcn0vC+oWGuNjs8mua9ySP4wpgyaiVVupbHYTsLJmNT7WNXExwqqzrq8g4oNxdXUgyEMQARmZd3fPTE/3vfd8+ePenhlgBsFlrFSqb9Wpvn3vd77f+b7zne87ffsqjv+wE4nYDQqWl5aWfDUcLqkAyOUHunID+Q8EnkilMo8C7gnoPPaRTCYnVyQT71+1bKl80PK+HGw9KPv27ZPde3bLjp075NVXX5FLL7lYKpLx9yoqKuqOR6f6PIFYLFZtW7r54YcfqV+4aBEdHe3ywm+e39eb6etzPZfS0kj5woUX1EUipWrj6xtZedddu11P5mYymc5j6Q19HrgsUrL67r/7+8VLly7j8cce3d3X29vZ0DB9yplnfWXcrFmzxjU2NiaBXevWrUsv/trXKmzbqnz/9+9VDuTyz35hi2OxWHV1ZbJ1245d1ltvvpFtb293Kyoq7LKystKysnLKy8soKyujtDTCxx/vSW3fsT3c0NAQWbpkiZvp7a9Np9Ndo+nWxwJrLYvmzV9gAaxbt/75urrxd592Wp0Oh0tWHSkbiUQSv3unuQlgxoyZltZm0TF1H+umUnrC1KlTAaipqUpESmMzFIRjsVj3SPJTpkyJA0ycOBGMnviFwSISLolEAAiHbftYsgAlJREbwA6HESUlXxg8lkcRXAQXwUVwEVwEF8FFcBH8/xhsnZC0ksw49eQPI5mmNtP54ccAIvqgqbz4aYn8zYoTUXXcFnueyZ8eXtleZt75iQnpU0VUvYiqB5mvu5p+XH9w8RtgnJMOLut/7rd4+fpRBcS52hz65csnHdxQ8clZnyuT3NV40sHRUnfq58mUWFJ70sEn+yiCi+AiuAgugovgIrgILoKL4CK4CC6Ci+D/Q+Djf/higk8Jzs0IMjIGYDGAp0AUeBbiHf3Xs/HGAHyYlYaRX0EYC4txNeIFugvWHyXzua8cnDjYGMBoQIFhRFfLmLjaCxqAw8iuHing/nCwGlLuMrKrveNfnccPFnyLtQ8c0a1jElye8sGFAYwUSCN54Q8GB4ljKKpHkBmLOZbB4FLgjhLVYxNcDFnkMXJUj03m0kOKR0sgYzLHRvlwpcDYI7oaGYvl5HB4ZRrJ1cf9fP5E/5NwQUKM7uoTOI4/ql38kmgUOCMnEHMCL819sag2jJJAxgIs+HNY6PGlpUxXDQWXw5dXjxH8SFZBPf7SyqKrMQLKG7b/OkpmTBJI0BSjbwTGYo6Ni5+ZjMJDj1wkxmQ5iV+VsBh9BzImKbNQFhWjp8wx21c7dKIV9A94IxaJsdplZt9574JQVcUdpr3rzlEHdzLASslpg19EofLMMa3dc0Z9c9YMXT+s7/GCo9FojWWph87+6tmX3XTTzT7XA/F4xutXr4fyOuQZVQUQ0tLphY1nlcn5YqgAuOyyy3inefOtH+36aLJr5Obe3t72o4w68kIsFptuW7pp5d33TPne928hm83yLz+6b9PVb/4niRK9QNfUoquqUaUREEEG+jGd7Zi2Dnpy3qYHGr7OFdcsX2BZFs899ywP/fznu11PLslkMjtHBScSiXrL0m+uXr3mlEWLFrN58+auxD+u2HZWhb0gcvkyShZ/Ax2N+70KPcVvJpMm999NZJ99mi1dzsb3rviLGbNmz6rY0rKFVavubTWG83p6ej4psAbfr66trS03xtlw98p76s+bN5+nnvzFtouevK/s1AnJM+I/vB37j6aDziJeCtxhzUkhTgoYwJpchz3zbJI7fj/pzA829f6iR/bPPW9e9aS6utjbb715YWVl1SOZTMY5DGzb6scXf+OSS6+48kqanntu55+99shkOyLx8uuvIjSuDEzq6Ob5TdzgPJ9GhT2sCbV4W1vK57R+FP9lOrT33PnzKjOZTM2OD7dFB3L5FwaDq6KifGYiXvn95ddey4fbtmWv2fhIiVUqpbpMEao2SH4fiKCMgAbRggSuVkKwEQz22q4iVKtQEYUtJvzdlvX6+bq67PJrr41sbm6+VVv8W1dX7/9oADH6b//0+us1QO/jD6xPhGWSCgsqLJj8PsTdjzj7Ma7fxDkAzn5wjry+H3H2YfL7UGGDCguJEqnPPf3YOoDrrrtOe56+C8CKRqPjotHoN+fMmcObb7zRelsk9W1lC4QFCRlM9yfoKnsoEgOLVWCxDLfYBRwwnXmwDIQVyoMbo6lrfrq5+dCsxsbaaHlkqTFSpUMhvjV79mwLwHvjldewBGxQlqBswXn3Y6T/EDhtiNOGuG2I2444QXPb/WtOGzhtmL7PcN7di7IFFegiJDq3+ZVXAWbMmGlrzRJLKc6/4IJFGGO4MdQ+gxAQEn/2LcH0u+Sa27HO0IRq/V+MSqnBOUZARMAD75DB2w4mq8AKWkggpPiOtJ3dYgznzTuPt996+3xLoc8+vaGBlpaWzFybrygtqCPgeODtcTFtBl1hUBHfGgl+wNGv8FIayWjE6KCfD1UhBVqotPWZO3Zs7506dVpUaT1Lh21rPED7oUNtKH8OUYLSoHTwWRiEAsmBDIA4gCPIAJh8YL3lyw7vi5JAJ7QdamvXWmPbofGW0qEYQL4/0zeYjdTRTQ0Oxp9/Svx9jvKAkBocsCh1dP9AZ76vNwOglI5bnuflAaukPBo9bM8UpMIjvxeiWAUbATHK3/yNJM/h30vKozEAz/Ny2nXddoCKyqrKwc5GDYFMUJmM8peLqyCvkH6FZP1zXP+eGBXIFvQcrquyqroyALdrxGzv7u5i6rTTE3lX0gUL/DIYPPfwFDh+k5xCBhSS1Ui/9s9zQ/cLz0rEGxqEGMWAK92T6yfHu7q6MCLbtSj1UtPzTcTjcfW0E3t5EBSkv0FgPgAMQgtWa/9azpcZHICrhvR48B+52CvRaFS9/PJLKKVe1Mao9e+++zsAtk9rnIwbLBFHIQ5IACWvkJxGBjSSDeDZ4HxAIznty+SV38chGIA/PXumzZoK0PJBCyLq1zqdTn/U2dHxbmtrKxddfmXj1r7QRr9jMH/5Ye4d8OdV+odZ3F+AqyG3F/oFelr62PQnl14667PWVrq6ut5JpVJ7giLBygfWrMYOh3ll/pLx4iojR7p3QMGgpQX4kPUE8OFuF0chrjIvzL78VDsc5sEHH0SLWkmQLuhOp5v27t376tatW7nk8iun/UN8VhM5BblASS5w53BowdXD4L7Lg8EG7Z6SM36z+MILp25p2cqBA/s3dKXTLxRSBeDvtUpL7M0PPfRwYtLken791z9Y++fevmWE/WJBIelbgJbDtz4mePblBksrcPU/ubVrF65Yuayzs50Vt6/ozuXduel0etdhYIB4PH5RVWXy+WeeWR8aV1PDz+6/56W//PDFxbpELGULgwVEcwSYoWXkKExOuatqGl9b8p3vfb2vt5/b/uoWtyfVe0kqlXqpwDpql1lVlbwhUhr52VNPrQ3PPuccNm16PbXrR3f+9pvm0NV+pWEwhQKIqKHnm57iV9nydc6Smxc1zm5MHvj0AHfecUeuv7f/u509PY8N5wyCReRcYCEw6YknHi9bcfvtl9276r7qG2+6Gdd12bhhQ/rghhe3TdmywT4l2zkhEeIUgJTLZ62RygPbT5/rlv/xvLOmnzE9ns/lWb9uPY8++u9tP/3JPzd9e/nyLLAXeE0ptRlAicgk4BZgfDAGc/DgQb1790fWrT+45Zz58xdMue+++0kkk/5N8RO2iPiZ0BiMCMbz8FyXzq4u7l91L5ub3969Zs2/Np/eMM2rrT21YKQBPgPWKBFZAyQA093drTzPobu7uyPV3XNbR2enam5uZu3atdTW1LDsqqtYeMEipk2b5m8GANd12bVzJ69vfI2n1/6Kjo5OvrVsKefOPZeqqkpJJCtXJ5OJinBpRJLxeOF3bI8FZIAYoEN2SHmeJ6GQ2CiMUipUP2UK199wI59+2sp/rVvP6tVryKRTOE4eAcJ2mFg8wfgJE5nZeA4TJ4yntmYcSimUUsaydMi2wxIKKTXM6n4lIuMCV08m2O52dHSQzfbpvkxvZSqTbkinUnWpVDqUzvTS29dHNpvFcfy6aNsWkUgp0fJyYrEYiUTcSybin8RjiZ2lZeXd0WjUra6uDg2L/z3A6uHBNQNYAEwHqvAXTTl4Kp3O9HhOvk+FGMhmHXHdHGLEE8CytNY6rCKRsPY8VRoOh8tisfIkhFxgIAB2AtuA15VS20ZcTsEgEsBM4DTgFKASiAClQAnBig7EC8/8BoAc0AekgE+B/cAWpVTqSMb/AlY1WXIncMcxAAAAAElFTkSuQmCC",
              "data:image/png;base64,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"
            ],
            "showPolygon": false,
            "polygonKeyName": "perimeter",
            "editablePolygon": false,
            "showPolygonLabel": false,
            "usePolygonLabelFunction": false,
            "polygonLabel": "${entityName}",
            "showPolygonTooltip": false,
            "showPolygonTooltipAction": "click",
            "autoClosePolygonTooltip": true,
            "usePolygonTooltipFunction": false,
            "polygonTooltipPattern": "<b>${entityName}</b><br/><br/><b>TimeStamp:</b> ${ts:7}",
            "polygonColor": "#3388ff",
            "polygonOpacity": 0.2,
            "usePolygonColorFunction": false,
            "polygonStrokeColor": "#3388ff",
            "polygonStrokeOpacity": 1,
            "polygonStrokeWeight": 3,
            "usePolygonStrokeColorFunction": false,
            "showCircle": false,
            "circleKeyName": "perimeter",
            "editableCircle": false,
            "showCircleLabel": false,
            "useCircleLabelFunction": false,
            "circleLabel": "${entityName}",
            "showCircleTooltip": false,
            "showCircleTooltipAction": "click",
            "autoCloseCircleTooltip": true,
            "useCircleTooltipFunction": false,
            "circleTooltipPattern": "<b>${entityName}</b><br/><br/><b>TimeStamp:</b> ${ts:7}",
            "circleFillColor": "#3388ff",
            "circleFillColorOpacity": 0.2,
            "useCircleFillColorFunction": false,
            "circleStrokeColor": "#3388ff",
            "circleStrokeOpacity": 1,
            "circleStrokeWeight": 3,
            "useCircleStrokeColorFunction": false,
            "useClusterMarkers": false,
            "zoomOnClick": true,
            "maxClusterRadius": 80,
            "animate": true,
            "spiderfyOnMaxZoom": false,
            "showCoverageOnHover": true,
            "chunkedLoading": false,
            "removeOutsideVisibleBounds": true,
            "useIconCreateFunction": false
          },
          "title": "Device location",
          "dropShadow": true,
          "enableFullscreen": true,
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "useDashboardTimewindow": true,
          "showLegend": false,
          "widgetStyle": {},
          "actions": {},
          "displayTimewindow": true,
          "showTitleIcon": false,
          "titleTooltip": "",
          "widgetCss": "",
          "pageSize": 1024,
          "noDataDisplayMessage": ""
        },
        "row": 0,
        "col": 0,
        "id": "fceb9d11-df1c-1f36-d6f0-bfdcb7e5c06f"
      },
      "1e35b728-e184-7c0c-f030-fa8647beb363": {
        "typeFullFqn": "system.digital_gauges.simple_gauge_justgage",
        "type": "latest",
        "sizeX": 2,
        "sizeY": 2,
        "config": {
          "datasources": [
            {
              "type": "entity",
              "name": "",
              "entityAliasId": "372c912f-243c-fb83-07ef-88bc0b059356",
              "dataKeys": [
                {
                  "name": "RAM",
                  "type": "timeseries",
                  "label": "RAM",
                  "color": "#268D2B",
                  "settings": {},
                  "_hash": 0.7291832787185518,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              }
            }
          ],
          "timewindow": {
            "displayValue": "",
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 1,
              "interval": 1000,
              "timewindowMs": 60000,
              "quickInterval": "CURRENT_DAY"
            },
            "history": {
              "historyType": 0,
              "interval": 1000,
              "timewindowMs": 60000,
              "fixedTimewindow": {
                "startTimeMs": 1698772633853,
                "endTimeMs": 1698859033853
              },
              "quickInterval": "CURRENT_DAY"
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": true,
          "backgroundColor": "#ffffff",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "0px",
          "settings": {
            "maxValue": 100,
            "minValue": 0,
            "donutStartAngle": 90,
            "showValue": true,
            "showMinMax": true,
            "gaugeWidthScale": 0.75,
            "levelColors": [],
            "refreshAnimationType": ">",
            "refreshAnimationTime": 700,
            "startAnimationType": ">",
            "startAnimationTime": 700,
            "titleFont": {
              "family": "Roboto",
              "size": 12,
              "style": "normal",
              "weight": "500"
            },
            "labelFont": {
              "family": "Roboto",
              "size": 8,
              "style": "normal",
              "weight": "500"
            },
            "valueFont": {
              "family": "Roboto",
              "style": "normal",
              "weight": "500",
              "size": 32,
              "color": "#666666"
            },
            "minMaxFont": {
              "family": "Segment7Standard",
              "size": 12,
              "style": "normal",
              "weight": "500"
            },
            "neonGlowBrightness": 0,
            "dashThickness": 0,
            "decimals": 0,
            "gaugeColor": "#eeeeee",
            "gaugeType": "donut"
          },
          "title": "RAM usage",
          "dropShadow": true,
          "enableFullscreen": true,
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "useDashboardTimewindow": true,
          "displayTimewindow": true,
          "showTitleIcon": false,
          "titleTooltip": "",
          "widgetStyle": {},
          "widgetCss": "",
          "pageSize": 1024,
          "noDataDisplayMessage": ""
        },
        "row": 0,
        "col": 0,
        "id": "1e35b728-e184-7c0c-f030-fa8647beb363"
      },
      "5893118d-369b-d02c-20a7-40494cdc2a4f": {
        "typeFullFqn": "system.digital_gauges.horizontal_bar_justgage",
        "type": "latest",
        "sizeX": 7,
        "sizeY": 3,
        "config": {
          "datasources": [
            {
              "type": "entity",
              "name": "",
              "entityAliasId": "372c912f-243c-fb83-07ef-88bc0b059356",
              "dataKeys": [
                {
                  "name": "storage",
                  "type": "timeseries",
                  "label": "storage",
                  "color": "#268D2B",
                  "settings": {},
                  "_hash": 0.48225666361886155,
                  "aggregationType": "NONE",
                  "units": null,
                  "decimals": null,
                  "funcBody": null,
                  "usePostProcessing": null,
                  "postFuncBody": null
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              }
            }
          ],
          "timewindow": {
            "displayValue": "",
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 1,
              "interval": 1000,
              "timewindowMs": 60000,
              "quickInterval": "CURRENT_DAY"
            },
            "history": {
              "historyType": 0,
              "interval": 1000,
              "timewindowMs": 60000,
              "fixedTimewindow": {
                "startTimeMs": 1698773468217,
                "endTimeMs": 1698859868217
              },
              "quickInterval": "CURRENT_DAY"
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": false,
          "backgroundColor": "#ffffff",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "0px",
          "settings": {
            "maxValue": 100,
            "minValue": 0,
            "donutStartAngle": 90,
            "showValue": true,
            "showMinMax": true,
            "gaugeWidthScale": 0.75,
            "levelColors": [],
            "refreshAnimationType": ">",
            "refreshAnimationTime": 700,
            "startAnimationType": ">",
            "startAnimationTime": 700,
            "titleFont": {
              "family": "Roboto",
              "size": 12,
              "style": "normal",
              "weight": "500",
              "color": "#999999"
            },
            "labelFont": {
              "family": "Roboto",
              "size": 8,
              "style": "normal",
              "weight": "500"
            },
            "valueFont": {
              "family": "Roboto",
              "style": "normal",
              "weight": "500",
              "size": 18,
              "color": "#666666"
            },
            "minMaxFont": {
              "family": "Roboto",
              "size": 12,
              "style": "normal",
              "weight": "500",
              "color": "#666666"
            },
            "neonGlowBrightness": 0,
            "decimals": 0,
            "dashThickness": 0,
            "gaugeColor": "#eeeeee",
            "showTitle": true,
            "gaugeType": "horizontalBar"
          },
          "title": "Horizontal bar",
          "dropShadow": true,
          "enableFullscreen": true,
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "useDashboardTimewindow": true,
          "displayTimewindow": true
        },
        "row": 0,
        "col": 0,
        "id": "5893118d-369b-d02c-20a7-40494cdc2a4f"
      },
      "2000f877-8f26-f1cf-561b-eb9de0e2d92e": {
        "typeFullFqn": "system.cards.value_card",
        "type": "latest",
        "sizeX": 3,
        "sizeY": 3,
        "config": {
          "datasources": [
            {
              "type": "device",
              "name": "",
              "deviceId": "257d3bf0-7882-11ee-8b0f-f3667b03f9fc",
              "dataKeys": [
                {
                  "name": "request",
                  "type": "timeseries",
                  "label": "Request:",
                  "color": "#2196f3",
                  "settings": {},
                  "_hash": 0.9798854212627746
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              }
            }
          ],
          "timewindow": {
            "displayValue": "",
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 1,
              "interval": 1000,
              "timewindowMs": 60000,
              "quickInterval": "CURRENT_DAY"
            },
            "history": {
              "historyType": 0,
              "interval": 1000,
              "timewindowMs": 60000,
              "fixedTimewindow": {
                "startTimeMs": 1698782935199,
                "endTimeMs": 1698869335199
              },
              "quickInterval": "CURRENT_DAY"
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": false,
          "backgroundColor": "rgba(0, 0, 0, 0)",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "0px",
          "settings": {
            "labelPosition": "top",
            "layout": "square",
            "showLabel": true,
            "labelFont": {
              "family": "Roboto",
              "size": 16,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "labelColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.87)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "showIcon": false,
            "iconSize": 40,
            "iconSizeUnit": "px",
            "icon": "thermostat",
            "iconColor": {
              "type": "constant",
              "color": "#5469FF",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "valueFont": {
              "family": "Roboto",
              "size": 52,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "valueColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.87)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "showDate": true,
            "dateFormat": {
              "format": null,
              "lastUpdateAgo": true,
              "custom": false
            },
            "dateFont": {
              "family": "Roboto",
              "size": 12,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "dateColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.38)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "background": {
              "type": "color",
              "color": "#fff",
              "overlay": {
                "enabled": false,
                "color": "rgba(255,255,255,0.72)",
                "blur": 3
              }
            }
          },
          "title": "Value card",
          "dropShadow": true,
          "enableFullscreen": false,
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "units": "",
          "decimals": 0,
          "useDashboardTimewindow": true,
          "showLegend": false,
          "widgetStyle": {},
          "actions": {},
          "configMode": "basic",
          "displayTimewindow": true,
          "margin": "0px",
          "borderRadius": "0px",
          "widgetCss": "",
          "pageSize": 1024,
          "noDataDisplayMessage": "",
          "showTitleIcon": false,
          "titleTooltip": "",
          "titleFont": {
            "size": 12,
            "sizeUnit": "px",
            "family": null,
            "weight": null,
            "style": null,
            "lineHeight": "1.6"
          },
          "titleIcon": "",
          "iconColor": "rgba(0, 0, 0, 0.87)",
          "iconSize": "14px",
          "timewindowStyle": {
            "showIcon": true,
            "iconSize": "14px",
            "icon": "query_builder",
            "iconPosition": "left",
            "font": {
              "size": 12,
              "sizeUnit": "px",
              "family": null,
              "weight": null,
              "style": null,
              "lineHeight": "1"
            },
            "color": null
          }
        },
        "row": 0,
        "col": 0,
        "id": "2000f877-8f26-f1cf-561b-eb9de0e2d92e"
      },
      "0dd15baa-905a-229e-b14f-2edafc80ea7f": {
        "typeFullFqn": "system.cards.horizontal_value_card",
        "type": "latest",
        "sizeX": 5,
        "sizeY": 1.5,
        "config": {
          "datasources": [
            {
              "type": "device",
              "name": "",
              "deviceId": "257d3bf0-7882-11ee-8b0f-f3667b03f9fc",
              "dataKeys": [
                {
                  "name": "ipAddr",
                  "type": "timeseries",
                  "label": "IP Address",
                  "color": "#2196f3",
                  "settings": {},
                  "_hash": 0.6207407631466554
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              }
            }
          ],
          "timewindow": {
            "displayValue": "",
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 1,
              "interval": 1000,
              "timewindowMs": 60000,
              "quickInterval": "CURRENT_DAY"
            },
            "history": {
              "historyType": 0,
              "interval": 1000,
              "timewindowMs": 60000,
              "fixedTimewindow": {
                "startTimeMs": 1699023040645,
                "endTimeMs": 1699109440645
              },
              "quickInterval": "CURRENT_DAY"
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": false,
          "backgroundColor": "rgba(0, 0, 0, 0)",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "0px",
          "settings": {
            "labelPosition": "top",
            "layout": "horizontal",
            "showLabel": true,
            "labelFont": {
              "family": "Roboto",
              "size": 16,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "labelColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.87)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "showIcon": true,
            "iconSize": 40,
            "iconSizeUnit": "px",
            "icon": "wifi",
            "iconColor": {
              "type": "constant",
              "color": "#5469FF",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "valueFont": {
              "family": "Roboto",
              "size": 52,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "valueColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.87)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "showDate": true,
            "dateFormat": {
              "format": null,
              "lastUpdateAgo": true,
              "custom": false
            },
            "dateFont": {
              "family": "Roboto",
              "size": 12,
              "sizeUnit": "px",
              "style": "normal",
              "weight": "500"
            },
            "dateColor": {
              "type": "constant",
              "color": "rgba(0, 0, 0, 0.38)",
              "colorFunction": "var temperature = value;\nif (typeof temperature !== undefined) {\n  var percent = (temperature + 60)/120 * 100;\n  return tinycolor.mix('blue', 'red', percent).toHexString();\n}\nreturn 'blue';"
            },
            "background": {
              "type": "color",
              "color": "#fff",
              "overlay": {
                "enabled": false,
                "color": "rgba(255,255,255,0.72)",
                "blur": 3
              }
            }
          },
          "title": "Horizontal value card",
          "dropShadow": true,
          "enableFullscreen": false,
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "units": "",
          "decimals": 0,
          "useDashboardTimewindow": true,
          "showLegend": false,
          "widgetStyle": {},
          "actions": {},
          "configMode": "basic",
          "displayTimewindow": true,
          "margin": "0px",
          "borderRadius": "0px",
          "widgetCss": "",
          "pageSize": 1024,
          "noDataDisplayMessage": "",
          "showTitleIcon": false,
          "titleTooltip": "",
          "titleFont": {
            "size": 12,
            "sizeUnit": "px",
            "family": null,
            "weight": null,
            "style": null,
            "lineHeight": "1.6"
          },
          "titleIcon": "",
          "iconColor": "rgba(0, 0, 0, 0.87)",
          "iconSize": "14px",
          "timewindowStyle": {
            "showIcon": true,
            "iconSize": "14px",
            "icon": "query_builder",
            "iconPosition": "left",
            "font": {
              "size": 12,
              "sizeUnit": "px",
              "family": null,
              "weight": null,
              "style": null,
              "lineHeight": "1"
            },
            "color": null
          }
        },
        "row": 0,
        "col": 0,
        "id": "0dd15baa-905a-229e-b14f-2edafc80ea7f"
      },
      "ffa7053d-7922-516a-715c-5e4d7f830cde": {
        "typeFullFqn": "system.charts.basic_timeseries",
        "type": "timeseries",
        "sizeX": 8,
        "sizeY": 5,
        "config": {
          "datasources": [
            {
              "type": "device",
              "name": "",
              "deviceId": "257d3bf0-7882-11ee-8b0f-f3667b03f9fc",
              "dataKeys": [
                {
                  "name": "RAM",
                  "type": "timeseries",
                  "label": "Memory",
                  "color": "#2196f3",
                  "settings": {},
                  "_hash": 0.936919218820785,
                  "units": "",
                  "decimals": 0
                }
              ],
              "alarmFilterConfig": {
                "statusList": [
                  "ACTIVE"
                ]
              },
              "latestDataKeys": []
            }
          ],
          "timewindow": {
            "hideInterval": false,
            "hideLastInterval": true,
            "hideQuickInterval": true,
            "hideAggregation": false,
            "hideAggInterval": false,
            "hideTimezone": false,
            "selectedTab": 0,
            "realtime": {
              "realtimeType": 0,
              "timewindowMs": 86400000,
              "quickInterval": "CURRENT_DAY",
              "interval": 300000
            },
            "aggregation": {
              "type": "AVG",
              "limit": 25000
            }
          },
          "showTitle": true,
          "backgroundColor": "#fff",
          "color": "rgba(0, 0, 0, 0.87)",
          "padding": "8px",
          "settings": {
            "stack": false,
            "fontSize": 10,
            "fontColor": "#545454",
            "showTooltip": true,
            "tooltipIndividual": false,
            "tooltipCumulative": false,
            "hideZeros": false,
            "grid": {
              "verticalLines": true,
              "horizontalLines": true,
              "outlineWidth": 1,
              "color": "#545454",
              "backgroundColor": null,
              "tickColor": "#DDDDDD"
            },
            "xaxis": {
              "title": null,
              "showLabels": true,
              "color": "#545454"
            },
            "yaxis": {
              "min": null,
              "max": null,
              "title": null,
              "showLabels": true,
              "color": "#545454",
              "tickSize": null,
              "tickDecimals": 0,
              "ticksFormatter": ""
            },
            "shadowSize": 4,
            "smoothLines": false,
            "comparisonEnabled": false,
            "xaxisSecond": {
              "axisPosition": "top",
              "title": null,
              "showLabels": true
            },
            "showLegend": true,
            "legendConfig": {
              "direction": "column",
              "position": "bottom",
              "sortDataKeys": false,
              "showMin": false,
              "showMax": false,
              "showAvg": true,
              "showTotal": false,
              "showLatest": false
            },
            "customLegendEnabled": false
          },
          "title": "Memory usage",
          "dropShadow": true,
          "enableFullscreen": true,
          "titleStyle": {
            "fontSize": "16px",
            "fontWeight": 400
          },
          "configMode": "basic",
          "actions": {},
          "showTitleIcon": false,
          "titleIcon": "thermostat",
          "iconColor": "#1F6BDD",
          "useDashboardTimewindow": false,
          "displayTimewindow": true,
          "titleFont": null,
          "titleColor": null
        },
        "row": 0,
        "col": 0,
        "id": "ffa7053d-7922-516a-715c-5e4d7f830cde"
      }
    },
    "states": {
      "default": {
        "name": "Test",
        "root": true,
        "layouts": {
          "main": {
            "widgets": {
              "2e724852-1545-c353-9f70-45e71dcfa42d": {
                "sizeX": 3,
                "sizeY": 3,
                "row": 0,
                "col": 0
              },
              "2c88820b-8fca-83f7-2f0a-e103881904e0": {
                "sizeX": 6,
                "sizeY": 2,
                "row": 3,
                "col": 0
              },
              "ad7fd1ef-7c90-5466-361b-e3e5175b4a6e": {
                "sizeX": 10,
                "sizeY": 5,
                "row": 0,
                "col": 10
              },
              "fceb9d11-df1c-1f36-d6f0-bfdcb7e5c06f": {
                "sizeX": 12,
                "sizeY": 6,
                "row": 5,
                "col": 0
              },
              "1e35b728-e184-7c0c-f030-fa8647beb363": {
                "sizeX": 4,
                "sizeY": 3,
                "row": 0,
                "col": 6
              },
              "5893118d-369b-d02c-20a7-40494cdc2a4f": {
                "sizeX": 4,
                "sizeY": 2,
                "row": 3,
                "col": 6
              },
              "2000f877-8f26-f1cf-561b-eb9de0e2d92e": {
                "sizeX": 3,
                "sizeY": 3,
                "row": 0,
                "col": 3
              },
              "0dd15baa-905a-229e-b14f-2edafc80ea7f": {
                "sizeX": 8,
                "sizeY": 1,
                "row": 5,
                "col": 12
              },
              "ffa7053d-7922-516a-715c-5e4d7f830cde": {
                "sizeX": 8,
                "sizeY": 5,
                "row": 6,
                "col": 12
              }
            },
            "gridSettings": {
              "backgroundColor": "#eeeeee",
              "columns": 24,
              "margin": 10,
              "outerMargin": true,
              "backgroundSizeMode": "100%"
            }
          }
        }
      }
    },
    "entityAliases": {
      "372c912f-243c-fb83-07ef-88bc0b059356": {
        "id": "372c912f-243c-fb83-07ef-88bc0b059356",
        "alias": "Phone",
        "filter": {
          "type": "singleEntity",
          "resolveMultiple": false,
          "singleEntity": {
            "entityType": "DEVICE",
            "id": "257d3bf0-7882-11ee-8b0f-f3667b03f9fc"
          }
        }
      }
    },
    "filters": {},
    "timewindow": {
      "displayValue": "",
      "hideInterval": false,
      "hideLastInterval": false,
      "hideQuickInterval": false,
      "hideAggregation": false,
      "hideAggInterval": false,
      "hideTimezone": false,
      "selectedTab": 0,
      "realtime": {
        "realtimeType": 0,
        "interval": 1000,
        "timewindowMs": 60000,
        "quickInterval": "CURRENT_DAY"
      },
      "history": {
        "historyType": 0,
        "interval": 1000,
        "timewindowMs": 60000,
        "fixedTimewindow": {
          "startTimeMs": 1698735738556,
          "endTimeMs": 1698822138556
        },
        "quickInterval": "CURRENT_DAY"
      },
      "aggregation": {
        "type": "AVG",
        "limit": 25000
      }
    },
    "settings": {
      "stateControllerId": "entity",
      "showTitle": false,
      "showDashboardsSelect": true,
      "showEntitiesSelect": true,
      "showDashboardTimewindow": true,
      "showDashboardExport": true,
      "toolbarAlwaysOpen": true
    }
  },
  "externalId": null,
  "name": "Phone Dashboard"
}
```

# Weaknesses/Limitations

<li>This only supports HTTP. You can edit the code to support various message protocols, which might be more suitable for you.</li>
<li>`BackgroundService` should be less bloated and split into different classes.</li>
<li>Less global variables, more function returns.</li>
