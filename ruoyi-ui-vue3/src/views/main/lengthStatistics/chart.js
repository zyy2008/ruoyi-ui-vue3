import * as echarts from "echarts";

// 初始化柱状图
export function init_bar(dom, tableData) {


    let barChartRef = document.getElementById(dom);
    echarts.dispose(barChartRef); //释放掉
    const barChart = echarts.init(barChartRef);

    const barData = tableData.reduce((acc, item) => {
        if (acc[item.subType] === undefined) {
            acc[item.subType] = { subType: item.subType, length: parseFloat(item.length) };
        } else {
            acc[item.subType].length += parseFloat(item.length);
        }
        return acc;
    }, {});

    const barOption = {
        title: {
            text: "管线长度统计图 单位(米)",
            left: "center",
            textStyle: {
                color: '#fff'
            }
        },
        grid: {
            top: 40,
            left: 0,
            right: 0,
            bottom: 0, // 增加底部空间，确保文字不被遮挡
            containLabel: true,
        },
        tooltip: {},
        xAxis: {
            type: "category",
            data: Object.keys(barData),
            axisLabel: {
                rotate: 270, // 旋转轴标签
                interval: 0, // 确保所有标签都显示
                color: '#fff'
            },
        },
        yAxis: {
            type: "value",
            axisLabel: {
                color: '#fff'
            },
        },
        series: [
            {
                data: Object.values(barData).map((item) => item.length),
                type: "bar",
            },
        ],
    };

    barChart.setOption(barOption);

}


// 初始化饼状图
export function init_pie(dom, tableData) {

    let pieChartRef = document.getElementById(dom);
    echarts.dispose(pieChartRef); //释放掉
    const pieChart = echarts.init(pieChartRef);
    const pieData = tableData.reduce((acc, item) => {
        if (acc[item.mainType] === undefined) {
            acc[item.mainType] = { mainType: item.mainType, sum: parseFloat(item.sum) };
        } else {
            acc[item.mainType].sum += parseFloat(item.sum);
        }
        return acc;
    }, {});

    const pieOption = {
        title: {
            text: "管线长度占比图",
            left: "center",
            textStyle: {
                color: '#fff'
            }
        },
        tooltip: {
            trigger: "item",
        },
        legend: {
            orient: "vertical",
            left: "left",
            textStyle: {
                color: '#fff'
            }
        },
        series: [
            {
                name: "管线类型",
                type: "pie",
                radius: "60%",
                tooltip: {
                    trigger: "item",
                    formatter: function (params) {
                        const percent =
                            (params.value / Object.values(pieData).reduce((a, b) => a + b.sum, 0)) *
                            100;
                        return `${params.name}: ${params.value} (${percent.toFixed(1)}%)`;
                    },
                },
                data: Object.values(pieData).map((item) => ({
                    name: item.mainType,
                    value: item.sum,
                })),
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: "rgba(0, 0, 0, 0.5)",
                    },
                },
                label: {
                    textStyle: {
                        color: '#fff'
                    },
                    show: true, // 显示标签
                    formatter: function (params) {
                        const percent =
                            (params.value / Object.values(pieData).reduce((a, b) => a + b.sum, 0)) *
                            100;
                        return `${params.name}:(${percent.toFixed(1)}%)`;
                    },
                },
            },
        ],
    };

    pieChart.setOption(pieOption);

}