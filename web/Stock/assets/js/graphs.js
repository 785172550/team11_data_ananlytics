window.onload = function () {
			var thisURL = document.URL;    
			var getval =thisURL.split('?')[1];  
			var showval= getval.split("=")[1];  
            $.post("/stock/" + showval, function (data,status) {               
                /*data = [{
					"date":2013/1/24,
					"max":7989,
					"min":899,
					"start":4356,
					"end": 6987,
					"capity":849389
				}];*/
				showCCD(data);
            });
}

// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('show_Stock'));
function mergeData(type){
	var thisURL = document.URL;    
	var getval =thisURL.split('?')[1];  
	var showval= getval.split("=")[1];

	var type = arguments[0]?arguments[0]:"0";
	console.log(type);
	var res = [];
	if("0" == type){
		$.post("/stock/" + showval, function (data,status) {               
			data = [{
				"date":'2013/1/24',
				"max":7989,
				"min":899,
				"start":4356,
				"end": 6987,
				"capity":849389
			}];
			res = data;
		});
	}else{
		$.post("/type/" + type, function (data,status) {               
			data = [{
				"date":'2013/3/23',
				"max":7989,
				"min":899,
				"start":4356,
				"end": 6987,
				"capity":849389
			}];
			res = data;
		});
	}
	
	var rawData = [];//二维数组
	var minData = [];
	for(var i = 0; i < res.length; i++){
		minData[0] = res[i]["date"];
		minData[1] = res[i]["max"];
		minData[2] = res[i]["min"];
		minData[3] = res[i]["start"];
		minData[4] = res[i]["end"];
		minData[5] = res[i]["capity"];
		rawData.push(minData);
	}
	console.log(rawData);
	return rawData;
}
//加载日K线图
function showCCD(rawData){
	var data = splitData(rawData);
	var option = {
		backgroundColor: '#eee',
        animation: false,
        legend: {
            bottom: 10,
            left: 'center',
            data: ['Dow-Jones index', 'MA5', 'MA10', 'MA20', 'MA30']
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 10,
            textStyle: {
                color: '#000'
            },
            position: function (pos, params, el, elRect, size) {
                var obj = {top: 10};
                obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
                return obj;
            },
            extraCssText: 'width: 170px'
        },
        axisPointer: {
            link: {xAxisIndex: 'all'},
            label: {
                backgroundColor: '#777'
            }
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: false
                },
                brush: {
                    type: ['lineX', 'clear']
                }
            }
        },
        brush: {
            xAxisIndex: 'all',
            brushLink: 'all',
            outOfBrush: {
                colorAlpha: 0.1
            }
        },
        visualMap: {
            seriesIndex: 5,
            dimension: 2,
            pieces: [{
                value: 1,
                color: '#2f4554'
            }, {
                value: -1,
                color: '#c23531'
            }]
        },
        grid: [
            {
                left: '10%',
                right: '8%',
                height: '50%'
            },
            {
                left: '10%',
                right: '8%',
                top: '63%',
                height: '16%'
            }
        ],
        xAxis: [
            {
                type: 'category',
                data: data.categoryData,
                scale: true,
                boundaryGap : false,
                axisLine: {onZero: false},
                splitLine: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax',
                axisPointer: {
                    z: 100
                }
            },
            {
                type: 'category',
                gridIndex: 1,
                data: data.categoryData,
                scale: true,
                boundaryGap : false,
                axisLine: {onZero: false},
                axisTick: {show: false},
                splitLine: {show: false},
                axisLabel: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax',
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            var seriesValue = (params.seriesData[0] || {}).value;
                            return params.value
                            + (seriesValue != null
                                ? '\n' + echarts.format.addCommas(seriesValue)
                                : ''
                            );
                        }
                    }
                }
            }
        ],
        yAxis: [
            {
                scale: true,
                
                splitArea: {
                    show: true
                }
            },
            {
                scale: true,
                gridIndex: 1,
                splitNumber: 2,
                axisLabel: {show: false},
                axisLine: {show: false},
                axisTick: {show: false},
                splitLine: {show: false}
            }
        ],
        dataZoom: [
            {
                type: 'inside',
                xAxisIndex: [0, 1],
                start: 12,
                end: 98
            },
            {
                show: true,
                xAxisIndex: [0, 1],
                type: 'slider',
                top: '85%',
                start: 12,
                end: 98
            }
        ],
        series: [
            {
                name: 'Dow-Jones index',
                type: 'candlestick',
                data: data.values,
				barMinHeight: '-100%',
                itemStyle: {
                    normal: {
                        borderColor: null,
                        borderColor0: null
                    }
                },
                tooltip: {
                    formatter: function (param) {
                        param = param[0];
                        return [
                            'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
                            'Open: ' + param.data[0] + '<br/>',
                            'Close: ' + param.data[1] + '<br/>',
                            'Lowest: ' + param.data[2] + '<br/>',
                            'Highest: ' + param.data[3] + '<br/>'
                        ].join('');
                    }
                }
            },
            {
                name: 'MA5',
                type: 'line',
                data: calculateMA(5, data),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA10',
                type: 'line',
                data: calculateMA(10, data),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA20',
                type: 'line',
                data: calculateMA(20, data),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA30',
                type: 'line',
                data: calculateMA(30, data),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'Volume',
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: data.volumes
            }
        ]
	}; 
	myChart.setOption(option, true);
}

//加载周-月-年K线图
function showCC(rawData){
	var data = splitData(rawData);
	var option = {
		backgroundColor: '#eee',
        animation: false,
        legend: {
            bottom: 10,
            left: 'center',
            data: ['Dow-Jones index', 'weekly', 'monthly', 'yearly']
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 10,
            textStyle: {
                color: '#000'
            },
            position: function (pos, params, el, elRect, size) {
                var obj = {top: 10};
                obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
                return obj;
            },
            extraCssText: 'width: 170px'
        },
        axisPointer: {
            link: {xAxisIndex: 'all'},
            label: {
                backgroundColor: '#777'
            }
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: false
                },
                brush: {
                    type: ['lineX', 'clear']
                }
            }
        },
        brush: {
            xAxisIndex: 'all',
            brushLink: 'all',
            outOfBrush: {
                colorAlpha: 0.1
            }
        },
        visualMap: {
            seriesIndex: 1,
            dimension: 2,
            pieces: [{
                value: 1,
                color: '#2f4554'
            }, {
                value: -1,
                color: '#c23531'
            }]
        },
        grid: [
            {
                left: '10%',
                right: '8%',
                height: '50%'
            },
            {
                left: '10%',
                right: '8%',
                top: '63%',
                height: '16%'
            }
        ],
        xAxis: [
            {
                type: 'category',
                data: data.categoryData,
                scale: true,
                boundaryGap : false,
                axisLine: {onZero: false},
                splitLine: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax',
                axisPointer: {
                    z: 100
                }
            },
            {
                type: 'category',
                gridIndex: 1,
                data: data.categoryData,
                scale: true,
                boundaryGap : false,
                axisLine: {onZero: false},
                axisTick: {show: false},
                splitLine: {show: false},
                axisLabel: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax',
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            var seriesValue = (params.seriesData[0] || {}).value;
                            return params.value
                            + (seriesValue != null
                                ? '\n' + echarts.format.addCommas(seriesValue)
                                : ''
                            );
                        }
                    }
                }
            }
        ],
        yAxis: [
            {
                scale: true,
                
                splitArea: {
                    show: true
                }
            },
            {
                scale: true,
                gridIndex: 1,
                splitNumber: 2,
                axisLabel: {show: false},
                axisLine: {show: false},
                axisTick: {show: false},
                splitLine: {show: false}
            }
        ],
        dataZoom: [
            {
                type: 'inside',
                xAxisIndex: [0, 1],
                start: 12,
                end: 98
            },
            {
                show: true,
                xAxisIndex: [0, 1],
                type: 'slider',
                top: '85%',
                start: 12,
                end: 98
            }
        ],
        series: [
            {
                name: 'Dow-Jones index',
                type: 'candlestick',
                data: data.values,
                itemStyle: {
                    normal: {
                        borderColor: null,
                        borderColor0: null
                    }
                },
                tooltip: {
                    formatter: function (param) {
                        param = param[0];
                        return [
                            'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
                            'Open: ' + param.data[0] + '<br/>',
                            'Close: ' + param.data[1] + '<br/>',
                            'Lowest: ' + param.data[2] + '<br/>',
                            'Highest: ' + param.data[3] + '<br/>'
                        ].join('');
                    }
                }
            },
            {
                name: 'Volume',
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: data.volumes
            }
        ]
	}; 
	myChart.setOption(option, true);
}

function splitData(rawData) {
    var categoryData = [];
    var values = [];
    var volumes = [];
    for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].date);
		var temp = [rawData[i].open, rawData[i].close, rawData[i].low, rawData[i].high];
        values.push(temp);
        volumes.push([i, rawData[i].volume, rawData[i].open > rawData[i].close ? 1 : -1]);
    }
   return {
        categoryData: categoryData,
        values: values,
        volumes: volumes
    };
}

function calculateMA(dayCount, data) {
    var result = [];
    for (var i = 0, len = data.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += data.values[i - j][1];
        }
        result.push(+(sum / dayCount).toFixed(3));
    }
    return result;
}

//增加按钮样式
$(function () {
    //加载事件
        var collection = $(".flag");
        $.each(collection, function () {
            $(this).addClass("start");
        });
});

//单击事件
function changeGraph(dom) {
	var collection = $(".charts");
	var domValue = dom.value;
	console.log(domValue);
	
	$.each(collection, function () {
		$(this).removeClass("end");
		$(this).addClass("start");
	});
	$(dom).removeClass("start");
	$(dom).addClass("end");	
		
	switch(domValue){
		case "CC-D": 
			myChart.clear();
			var rawData = mergeData("CC-D");
			showCCD(rawData);
			break;		
		case "CC-W":
			/*$.post("/type/" + "CC-W", function (data,status) {               
                data = [{
					"date":2013/1/24,
					"max":7989,
					"min":899,
					"start":4356,
					"end": 6987,
					"capity":849389
				}];*/
				myChart.clear();
				var rawData = mergeData("CC-W");
				showCC(rawData);
				break;
            //});
			/*var data = splitData([
				['2013/1/24', 2320.26,2320.26,2287.3,2362.94,1688900000],
				['2013/1/25', 2300,2291.3,2288.26,2308.38,1688900000],
				['2013/1/28', 2295.35,2346.5,2295.35,2346.92,1688900000],
				['2013/1/29', 2347.22,2358.98,2337.35,2363.8,1688900000],
				['2013/1/30', 2360.75,2382.48,2347.89,2383.76,1688900000],
				['2013/1/31', 2383.43,2385.42,2371.23,2391.82,1888900000],
				['2013/2/1', 2377.41,2419.02,2369.57,2421.15,1688900000],
				['2013/2/4', 2425.92,2428.15,2417.58,2440.38,1688900000],
				['2013/2/5', 2411,2433.13,2403.3,2437.42,1688900000],
				['2013/2/6', 2432.68,2434.48,2427.7,2441.73,1688900000],
				['2013/2/7', 2430.69,2418.53,2394.22,2433.89,1682900000],
				['2013/2/8', 2416.62,2432.4,2414.4,2443.03,-1688900000],
				['2013/2/18', 2441.91,2421.56,2415.43,2444.8,1688900000],
				['2013/2/19', 2420.26,2382.91,2373.53,2427.07,1688900000],
				['2013/2/20', 2383.49,2397.18,2370.61,2397.94,1688900000],
				['2013/2/21', 2378.82,2325.95,2309.17,2378.82,1688900000],
				['2013/2/22', 2322.94,2314.16,2308.76,2330.88,1688900000]
			]);
			myChart.clear();
			showCC(data);
			break;*/
		case "CC-M":			
			myChart.clear();
			var rawData = mergeData("CC-M");
			showCC(rawData);
			break;
		case "CC-Y":
			myChart.clear();
			var rawData = mergeData("CC-Y");
			showCC(rawData);
			break;
		case "TSC":
			myChart.clear();
			var rawData = mergeData("CC-Y");
			showCC(rawData);
			break;
	}
}