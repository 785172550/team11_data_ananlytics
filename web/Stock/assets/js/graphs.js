
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('show_Stock'));

function splitData(rawData) {
    var categoryData = [];
    var values = [];
    var volumes = [];
    for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].splice(0, 1)[0]);
        values.push(rawData[i]);
        volumes.push([i, rawData[i][4], rawData[i][0] > rawData[i][1] ? 1 : -1]);
    }
   return {
        categoryData: categoryData,
        values: values,
        volumes: volumes
    };
}

function calculateMA(dayCount, data) {
	  // console.log(data);
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
	var data;
    var option;

	var collection = $(".flag");
	$.each(collection, function () {
		$(this).removeClass("end");
		$(this).addClass("start");
	});
	$(dom).removeClass("start");
	$(dom).addClass("end");
	if("K-line1" == dom.value){
		data = splitData([
			['2013/1/24', 2320.26,2320.26,2287.3,2362.94,1688900000],
			['2013/1/25', 2300,2291.3,2288.26,2308.38,1688900000],
			['2013/1/28', 2295.35,2346.5,2295.35,2346.92,1688900000],
			['2013/1/29', 2347.22,2358.98,2337.35,2363.8,1688900000],
			['2013/1/30', 2360.75,2382.48,2347.89,2383.76,1688900000],
			['2013/1/31', 2383.43,2385.42,2371.23,2391.82,1688900000],
			['2013/2/1', 2377.41,2419.02,2369.57,2421.15,1688900000],
			['2013/2/4', 2425.92,2428.15,2417.58,2440.38,1688900000],
			['2013/2/5', 2411,2433.13,2403.3,2437.42,1688900000],
			['2013/2/6', 2432.68,2434.48,2427.7,2441.73,1688900000],
			['2013/2/7', 2430.69,2418.53,2394.22,2433.89,1688900000],
			['2013/2/8', 2416.62,2432.4,2414.4,2443.03,1688900000],
			['2013/2/18', 2441.91,2421.56,2415.43,2444.8,1688900000],
			['2013/2/19', 2420.26,2382.91,2373.53,2427.07,1688900000],
			['2013/2/20', 2383.49,2397.18,2370.61,2397.94,1688900000],
			['2013/2/21', 2378.82,2325.95,2309.17,2378.82,1688900000],
			['2013/2/22', 2322.94,2314.16,2308.76,2330.88,1688900000]
		]);
		option = {
			backgroundColor: '#eee',
			animation: false,
			legend: {
				bottom: 10,
				left: 'center',
				data: ['TEAM11 index', 'MA10', 'MA20']
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
					colorAlpha: 0.8
				}
			},
			visualMap: {
				seriesIndex: 3,
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
					start: 10,
					end: 80
				},
				{
					show: true,
					xAxisIndex: [0, 1],
					type: 'slider',
					top: '85%',
					start: 10,
					end: 80
				}
			],
			series: [
				{
					name: 'TEAM11 index',
					type: 'candlestick',
					data: data.values,
					itemStyle: {
						normal: {
							borderColor: null,
							borderColor0: null
													// opacity: 1
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
					},
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
					name: 'Volume',
					type: 'bar',
					xAxisIndex: 1,
					yAxisIndex: 1,
					data: data.volumes
				}
			]
		};   
	}
	else if("K-line2" == dom.value){
		data = [
			[[28604,77,17096869,'Australia',1990],[31163,77.4,27662440,'Canada',1990],[1516,68,1154605773,'China',1990],[13670,74.7,10582082,'Cuba',1990],[28599,75,4986705,'Finland',1990],[29476,77.1,56943299,'France',1990],[31476,75.4,78958237,'Germany',1990],[28666,78.1,254830,'Iceland',1990],[1777,57.7,870601776,'India',1990],[29550,79.1,122249285,'Japan',1990],[2076,67.9,20194354,'North Korea',1990],[12087,72,42972254,'South Korea',1990],[24021,75.4,3397534,'New Zealand',1990],[43296,76.8,4240375,'Norway',1990],[10088,70.8,38195258,'Poland',1990],[19349,69.6,147568552,'Russia',1990],[10670,67.3,53994605,'Turkey',1990],[26424,75.7,57110117,'United Kingdom',1990],[37062,75.4,252847810,'United States',1990]],
			[[44056,81.8,23968973,'Australia',2015],[43294,81.7,35939927,'Canada',2015],[13334,76.9,1376048943,'China',2015],[21291,78.5,11389562,'Cuba',2015],[38923,80.8,5503457,'Finland',2015],[37599,81.9,64395345,'France',2015],[44053,81.1,80688545,'Germany',2015],[42182,82.8,329425,'Iceland',2015],[5903,66.8,1311050527,'India',2015],[36162,83.5,126573481,'Japan',2015],[1390,71.4,25155317,'North Korea',2015],[34644,80.7,50293439,'South Korea',2015],[34186,80.6,4528526,'New Zealand',2015],[64304,81.6,5210967,'Norway',2015],[24787,77.3,38611794,'Poland',2015],[23038,73.13,143456918,'Russia',2015],[19360,76.5,78665830,'Turkey',2015],[38225,81.4,64715810,'United Kingdom',2015],[53354,79.1,321773631,'United States',2015]]
		];
		option = {
			backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
				offset: 0,
				color: '#f7f8fa'
			}, {
				offset: 1,
				color: '#cdd0d5'
			}]),
			title: {
				text: '1990 与 2015 年各国家人均寿命与 GDP'
			},
			legend: {
				right: 10,
				data: ['1990', '2015']
			},
			xAxis: {
				splitLine: {
					lineStyle: {
						type: 'dashed'
					}
				}
			},
			yAxis: {
				splitLine: {
					lineStyle: {
						type: 'dashed'
					}
				},
				scale: true
			},
			series: [{
				name: '1990',
				data: data[0],
				type: 'scatter',
				symbolSize: function (data) {
					return Math.sqrt(data[2]) / 5e2;
				},
				label: {
					emphasis: {
						show: true,
						formatter: function (param) {
							return param.data[3];
						},
						position: 'top'
					}
				},
				itemStyle: {
					normal: {
						shadowBlur: 10,
						shadowColor: 'rgba(120, 36, 50, 0.5)',
						shadowOffsetY: 5,
						color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
							offset: 0,
							color: 'rgb(251, 118, 123)'
						}, {
							offset: 1,
							color: 'rgb(204, 46, 72)'
						}])
					}
				}
			}, {
				name: '2015',
				data: data[1],
				type: 'scatter',
				symbolSize: function (data) {
					return Math.sqrt(data[2]) / 5e2;
				},
				label: {
					emphasis: {
						show: true,
						formatter: function (param) {
							return param.data[3];
						},
						position: 'top'
					}
				},
				itemStyle: {
					normal: {
						shadowBlur: 10,
						shadowColor: 'rgba(25, 100, 150, 0.5)',
						shadowOffsetY: 5,
						color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
							offset: 0,
							color: 'rgb(129, 227, 238)'
						}, {
							offset: 1,
							color: 'rgb(25, 183, 207)'
						}])
					}
				}
			}]
		};
	}
	myChart.setOption(option);
}