window.onload = function () {
    var Global = G2.Global; // 获取 Global 全局对象
    Global.setTheme('dark');

    user_data = {}  //接收index页传来的用户数据
    window.addEventListener('message', function (event) {
        user_data = event.data
        console.log('接收到用户数据')
        console.log(user_data)
    });

    // url
    get_single_assessment_result_url = 'http://192.168.43.61:8080/u'
    get_multi_assessment_result_url = 'http://192.168.43.61:8080/u'

    var selected_year = 0
    var selected_quarter = 0
    var selected_start_year = 0
    var selected_start_quarter = 0
    var selected_end_year = 0
    var selected_end_quarter = 0

    layui.use(['layer', 'dropdown'], function () {
        var dropdown = layui.dropdown
        var layer = layui.layer



        var curr_date = new Date()
        var curr_year = curr_date.getFullYear()
        var year_data = []

        selected_year = selected_end_year = curr_year // 选中的年份
        selected_quarter = selected_end_quarter = selected_start_quarter = Math.round((curr_date.getMonth() + 1) / 3)
        selected_start_year = curr_year - 1

        // 设置下拉框初始值
        $('#show_year').text(selected_year)
        switch (selected_quarter) {
            case 1: $('#show_quarter').text('第一季度'); break;
            case 2: $('#show_quarter').text('第二季度'); break;
            case 3: $('#show_quarter').text('第三季度'); break;
            case 4: $('#show_quarter').text('第四季度'); break;
            default: break;
        }

        // 单季度 年份下拉框，可选择前六年
        for (var i = curr_year - 5; i <= curr_year; i++) {
            year_data.push({
                title: i + '年',
                id: i
            })
        }
        console.log('下拉框年份：')
        console.log(year_data)
        dropdown.render({
            elem: '#single_year_selector'
            , data: year_data
            , click: function (obj) {
                selected_year = obj.id
                $('#show_year').text(obj.title)
                console.log('单次年份选择了' + obj.title)
            }
        });


        // 单季度 季度下拉框
        dropdown.render({
            elem: '#single_quarter_selector'
            , data: [{
                title: '第一季度',
                id: 1
            }
                , {
                title: '第二季度',
                id: 2
            }
                , {
                title: '第三季度',
                id: 3
            }
                , {
                title: '第四季度',
                id: 4
            }
            ]
            , click: function (obj) {
                selected_quarter = obj.id
                $('#show_quarter').text(obj.title)
                console.log('单次季度选择了' + obj.title)
            }
        });


        // 结束年份、下拉框
        $('#end_year').text(selected_end_year)
        switch (selected_end_quarter) {
            case 1: $('#end_quarter').text('第一季度'); break;
            case 2: $('#end_quarter').text('第二季度'); break;
            case 3: $('#end_quarter').text('第三季度'); break;
            case 4: $('#end_quarter').text('第四季度'); break;
            default: break;
        }


        dropdown.render({
            elem: '#end_year_selector'
            , data: year_data
            , click: function (obj) {
                selected_end_year = obj.id
                $('#end_year').text(obj.title)
                console.log('结束年份选择了' + obj.title)
            }
        });


        // 结束季度下拉框
        dropdown.render({
            elem: '#end_quarter_selector'
            , data: [{
                title: '第一季度',
                id: 1
            }
                , {
                title: '第二季度',
                id: 2
            }
                , {
                title: '第三季度',
                id: 3
            }
                , {
                title: '第四季度',
                id: 4
            }
            ]
            , click: function (obj) {
                selected_end_quarter = obj.id
                $('#end_quarter').text(obj.title)
                console.log('结束季度选择了' + obj.title)
            }
        });




        // 开始年份、下拉框
        $('#start_year').text(selected_start_year)
        switch (selected_start_quarter) {
            case 1: $('#start_quarter').text('第一季度'); break;
            case 2: $('#start_quarter').text('第二季度'); break;
            case 3: $('#start_quarter').text('第三季度'); break;
            case 4: $('#start_quarter').text('第四季度'); break;
            default: break;
        }


        dropdown.render({
            elem: '#start_year_selector'
            , data: year_data
            , click: function (obj) {
                selected_start_year = obj.id
                $('#start_year').text(obj.title)
                console.log('开始年份选择了' + obj.title)
            }
        });


        // 开始季度下拉框
        dropdown.render({
            elem: '#start_quarter_selector'
            , data: [{
                title: '第一季度',
                id: 1
            }
                , {
                title: '第二季度',
                id: 2
            }
                , {
                title: '第三季度',
                id: 3
            }
                , {
                title: '第四季度',
                id: 4
            }
            ]
            , click: function (obj) {
                selected_start_quarter = obj.id
                $('#start_quarter').text(obj.title)
                console.log('开始季度选择了' + obj.title)
            }
        });

    })
    // 点击单季度确定按钮
    var choose_single = function () {
        var data = {
            id: user_data.id,
            year: selected_year,
            quarter: selected_quarter,
        }
        console.log(data)
        index = loading()
        $.ajax({
            type: "post",
            url: get_single_assessment_result_url,
            dataType: "json",
            data: {
                id: user_data.id,
                year: selected_year,
                quarter: selected_quarter,
            },
            timeout: 4000,
            success: function (res) {
                if (res.msg == '成功') {
                    layer.msg('获取单季度考核结果成功')
                    // 渲染第一二三个表格
                    render_single_result(res.data, res.grade, res.proximity)
                }
                else {
                    layer.msg(res.msg)
                }
                layer.close(index)
            },
            error: function () {
                layer.msg('获取单季度考核结果失败')
                layer.close(index)
            }
        })
    }
    // 点击多季度确定按钮
    var choose_multi = function () {
        var data = {
            id: user_data.id,
            start_year: selected_start_year,
            start_quarter: selected_start_quarter,
            end_year: selected_end_year,
            end_quarter: selected_end_quarter
        }
        console.log(data)
        index = loading()
        $.ajax({
            type: "post",
            url: get_multi_assessment_result_url,
            dataType: "json",
            data: {
                id: user_data.id,
                start_year: selected_start_year,
                start_quarter: selected_start_quarter,
                end_year: selected_end_year,
                end_quarter: selected_end_quarter
            },
            timeout: 4000,
            success: function (res) {
                if (res.msg == '成功') {
                    layer.msg('获取历史考核结果成功')
                    // 渲染第四五个表格
                    render_multi_result(res.proximity_data, res.grade_proportion)
                }
                else {
                    layer.msg(res.msg)
                }
                layer.close(index)
            },
            error: function () {
                layer.msg('获取历史考核结果失败')
                layer.close(index)
            }
        })
    }

    $('#choose1').click(choose_single)
    $('#choose2').click(choose_multi)
    // 加载层
    var loading = function () {
        var index = layer.open({
            type: 3,
            title: "",
            content: "",
            shade: 0.1,
        })
        return index
    }

    // 渲染第一二三个图，数据分别是 评分详情、等级、接近度
    var render_single_result = function (table1_data, table2_data, table3_data) {
        // 1——————————————————————————————————————————————
        var _DataSet = DataSet,
            DataView = _DataSet.DataView;
        var _G = G2,
            Chart = _G.Chart;


        var data = table1_data
        // var data = [
        //     {
        //         target: '工作质量',
        //         A: 8,
        //         B: 5,
        //         C: 6,
        //         D: 3,
        //         E: 2
        //     },
        // {
        //     target: '目标完成',
        //     A: 11,
        //     B: 5,
        //     C: 6,
        //     D: 3,
        //     E: 2
        // }, {
        //     target: '工作效率',
        //     A: 5,
        //     B: 5,
        //     C: 12,
        //     D: 3,
        //     E: 2
        // },
        // {
        //     target: '政治素养',
        //     A: 8,
        //     B: 9,
        //     C: 3,
        //     D: 3,
        //     E: 2
        // },
        // {
        //     target: '思想品德',
        //     A: 8,
        //     B: 3,
        //     C: 11,
        //     D: 3,
        //     E: 2
        // },
        //     {
        //         target: '创新能力',
        //         A: 5,
        //         B: 2,
        //         C: 6,
        //         D: 3,
        //         E: 8
        //     },
        //     {
        //         target: '贯彻执行能力',
        //         A: 13,
        //         B: 8,
        //         C: 3,
        //         D: 2,
        //         E: 1
        //     },
        //     {
        //         target: '组织协调能力',
        //         A: 7,
        //         B: 7,
        //         C: 3,
        //         D: 7,
        //         E: 1
        //     },
        //     {
        //         target: '解决问题能力',
        //         A: 14,
        //         B: 4,
        //         C: 3,
        //         D: 0,
        //         E: 0
        //     },
        //     {
        //         target: '表达能力',
        //         A: 6,
        //         B: 8,
        //         C: 4,
        //         D: 0,
        //         E: 2
        //     },
        //     {
        //         target: '事业心责任感',
        //         A: 11,
        //         B: 5,
        //         C: 6,
        //         D: 3,
        //         E: 2
        //     },
        //     {
        //         target: '下属积极性',
        //         A: 3,
        //         B: 9,
        //         C: 8,
        //         D: 2,
        //         E: 4
        //     },

        // ];
        var assessments = ['A', 'B', 'C', 'D', 'E'];
        var dv = new DataView();
        dv.source(data).transform({
            type: 'fold',
            fields: assessments,
            key: 'age',
            value: 'population',
            retains: ['target']
        }).transform({
            type: 'map',
            callback: function callback(obj) {
                var key = obj.age;
                var type = void 0;
                if (key === 'A') {
                    type = 'a';
                } else if (key === 'B') {
                    type = 'b';
                } else if (key === 'C') {
                    type = 'c';
                } else if (key == 'D') {
                    type = 'd';
                }
                else {
                    type = 'e';
                }
                obj.type = type;
                return obj;
            }
        });
        var colorMap = {
            'A': '#00DD11',
            'B': '#00AAAA',
            'C': '#0086FA',
            'D': '#FFBF00',
            'E': '#F5222D'
        };
        var chart1 = new Chart({
            container: 'table1',
            forceFit: true,
            height: 400,
            padding: [20, 160, 80, 60]
        });
        chart1.source(dv, {
            population: {
                tickInterval: 5
            }
        });
        chart1.axis('population', {
            label: {
                formatter: function formatter(val) {
                    return val + '';
                }
            }
        });
        chart1.legend({
            position: 'right'
        });
        chart1.interval().position('target*population').color('age', function (age) {
            return colorMap[age];
        }).tooltip('age*population', function (age, population) {
            return {
                name: age,
                value: population
            };
        }).adjust([{
            type: 'dodge',
            dodgeBy: 'type', // 按照 type 字段进行分组
            marginRatio: 0 // 分组中各个柱子之间不留空隙
        }, {
            type: 'stack'
        }]);
        chart1.render();

        // 2————————————————————————————————————————————————————————————————————————————
        {
            var grade = 3
            switch (table2_data) {
                case 'A': grade = 5; break;
                case 'B': grade = 4; break;
                case 'C': grade = 3; break;
                case 'D': grade = 2; break;
                case 'E': grade = 1; break;

            }
            function creatData(grade) {
                var data = [];
                var val = grade
                val = val.toFixed(1);
                data.push({
                    value: val * 1
                });
                return data;
            }

            var Shape = G2.Shape;
            // 自定义Shape 部分
            Shape.registerShape('point', 'pointer', {
                drawShape: function drawShape(cfg, group) {
                    var center = this.parsePoint({ // 获取极坐标系下画布中心点
                        x: 0,
                        y: 0
                    });
                    // 绘制指针
                    group.addShape('line', {
                        attrs: {
                            x1: center.x,
                            y1: center.y,
                            x2: cfg.x,
                            y2: cfg.y,
                            stroke: cfg.color,
                            lineWidth: 5,
                            lineCap: 'round'
                        }
                    });
                    return group.addShape('circle', {
                        attrs: {
                            x: center.x,
                            y: center.y,
                            r: 9.75,
                            stroke: cfg.color,
                            lineWidth: 4.5,
                            fill: '#fff'
                        }
                    });
                }
            });

            var color = ['#F5222D', '#FFBF00', '#0086FA', '#00AAAA', '#00DD11'];
            var chart2 = new G2.Chart({
                container: 'table2',
                forceFit: true,
                height: 300,
                padding: [0, 0, 30, 0],
            });


            chart2.coord('polar', {
                startAngle: -9 / 8 * Math.PI,
                endAngle: 1 / 8 * Math.PI,
                radius: 0.75
            });
            chart2.scale('value', {
                min: 0,
                max: 5,
                tickInterval: 1,
                nice: false
            });

            chart2.axis('1', false);
            chart2.axis('value', {
                zIndex: 2,
                line: null,
                label: {
                    formatter: function formatter(val) {
                        var ret = ''
                        switch (val - 1) {
                            case 0: ret = 'E'; break;
                            case 1: ret = 'D'; break;
                            case 2: ret = 'C'; break;
                            case 3: ret = 'B'; break;
                            case 4: ret = 'A'; break;
                            default: break;
                        }
                        return ret;
                    },

                    offset: 10,
                    textStyle: {
                        fontSize: 18,
                        fill: '#CBCBCB',
                        textAlign: 'center',
                        textBaseline: 'middle'
                    }
                },
                tickLine: {
                    length: -24,
                    stroke: '#fff',
                    strokeOpacity: 1
                },
                grid: null
            });
            chart2.legend(false);
            chart2.point().position('value*1').shape('pointer').color('value', function (val) {
                if (val < 1) {
                    return color[0];
                } else if (val <= 2) {
                    return color[1];
                } else if (val <= 3) {
                    return color[2];
                } else if (val <= 4) {
                    return color[3];
                } else if (val <= 5) {
                    return color[4];
                }
            }).active(false);

            draw(creatData(grade));
            //   setInterval(function() {  //定时刷新数据
            //     draw(creatData());
            //   }, 1000);

            function draw(data) {
                var val = data[0].value;
                var lineWidth = 25;
                chart2.guide().clear();
                // 绘制仪表盘背景
                chart2.guide().arc({
                    zIndex: 0,
                    top: false,
                    start: [0, 0.92],
                    end: [5, 0.92],
                    style: { // 底灰色
                        stroke: '#CBCBCB',
                        lineWidth: lineWidth
                    }
                });

                val >= 1 && chart2.guide().arc({
                    zIndex: 1,
                    start: [0, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[0],
                        lineWidth: lineWidth
                    }
                });

                val >= 2 && chart2.guide().arc({
                    zIndex: 1,
                    start: [1, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[1],
                        lineWidth: lineWidth
                    }
                });

                val >= 3 && chart2.guide().arc({
                    zIndex: 1,
                    start: [2, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[2],
                        lineWidth: lineWidth
                    }
                });

                val >= 4 && chart2.guide().arc({
                    zIndex: 1,
                    start: [3, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[3],
                        lineWidth: lineWidth
                    }
                });

                val >= 4 && chart2.guide().arc({
                    zIndex: 1,
                    start: [4, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[4],
                        lineWidth: lineWidth
                    }
                });



                val > 4 && val <= 5 && chart2.guide().arc({
                    zIndex: 1,
                    start: [4, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[4],
                        lineWidth: lineWidth
                    }
                });

                val > 3 && val <= 4 && chart2.guide().arc({
                    zIndex: 1,
                    start: [3, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[3],
                        lineWidth: lineWidth
                    }
                });


                val > 2 && val <= 3 && chart2.guide().arc({
                    zIndex: 1,
                    start: [2, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[2],
                        lineWidth: lineWidth
                    }
                });

                val > 1 && val <= 2 && chart2.guide().arc({
                    zIndex: 1,
                    start: [1, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[1],
                        lineWidth: lineWidth
                    }
                });

                val < 1 && chart2.guide().arc({
                    zIndex: 1,
                    start: [0, 0.92],
                    end: [val, 0.92],
                    style: {
                        stroke: color[0],
                        lineWidth: lineWidth
                    }
                });

                var grade_color = color[grade - 1]
                // 绘制指标数字
                chart2.guide().html({
                    position: ['50%', '95%'],
                    html: '<div style="width: 300px;text-align: center;">' + '<p style="font-size: 20px; color: #AAAAAA;margin: 16px;">评分</p>' + '<p style="font-size: 50px;color:' + grade_color + ';margin: 0;">' + table2_data + '</p>' + '</div>'
                });
                chart2.changeData(data);
            }
        }
        // 3——————————————————————————————————————————————————————————————————
        {
            var proximity = table3_data
            var data1 = [];
            for (var i = 1; i <= 100; i++) {
                var item = {};
                item.type = i + '';
                item.value = 10;
                data1.push(item);
            }

            var data2 = [];
            for (var _i = 1; _i <= 100; _i++) {
                var _item = {};
                _item.type = _i + '';
                _item.value = 10;
                if (_i === Math.round(proximity)) {
                    _item.value = 15;
                }
                if (_i > Math.round(proximity)) {
                    _item.value = 0;
                }
                data2.push(_item);
            }

            var chart3 = new G2.Chart({
                container: 'table3',
                forceFit: true,
                height: 300,
                padding: 0
            });
            chart3.scale({
                type: {
                    range: [0, 1]
                },
                value: {
                    sync: true
                }
            });
            chart3.legend(false);
            chart3.tooltip(false);
            var view1 = chart3.view();
            view1.source(data1);
            view1.axis(false);
            view1.coord('polar', {
                startAngle: -9 / 8 * Math.PI,
                endAngle: 1 / 8 * Math.PI,
                innerRadius: 0.75,
                radius: 0.8
            });
            view1.interval().position('type*value').color('#333333').size(6);

            var view2 = chart3.view();
            view2.source(data1, {
                type: {
                    tickCount: 3
                }
            });
            view2.axis('value', false);
            view2.axis('type', {
                grid: null,
                line: null,
                tickLine: null,
                label: {
                    offset: -15,
                    textStyle: {
                        textAlign: 'center',
                        fill: '#CBCBCB',
                        fontSize: 18
                    },
                    formatter: function formatter(val) {
                        if (val === '49') {
                            return 50;
                        }

                        return val;
                    }
                }
            });
            view2.coord('polar', {
                startAngle: -9 / 8 * Math.PI,
                endAngle: 1 / 8 * Math.PI,
                innerRadius: 0.95,
                radius: 0.55
            });
            view2.interval().position('type*value').color('#333333').size(6);

            var view3 = chart3.view();
            view3.source(data2);
            view3.axis(false);
            view3.coord('polar', {
                startAngle: -9 / 8 * Math.PI,
                endAngle: 1 / 8 * Math.PI,
                innerRadius: 0.75,
                radius: 0.8
            });

            var view3_color = color[grade - 1]
            view3.interval().position('type*value').color('value', '#000000-' + view3_color).opacity(1).size(6);
            view3.guide().text({
                position: ['50%', '65%'],
                content: proximity,
                style: {
                    fill: '#CBCBCB',
                    fontSize: 64,
                    textAlign: 'center',
                    textBaseline: 'middle'
                }
            });
            chart3.guide().html({
                position: ['50%', '95%'],
                html: '<div style="width: 300px;text-align: center;">' + '<p style="font-size: 20px; color: #AAAAAA;margin: 16px;">接近度</p>' + '<p style="font-size: 50px;color:' + '' + ';margin: 0;">' + '</p>' + '</div>'
            });
            chart3.render();
        }



    }



    var render_multi_result = function (table4_data, table5_data) {
        //4————————————————————————————————————————————————————————
        {
            var data = table4_data
            var ds = new DataSet();
            var dv = ds.createView().source(data);
            // fold 方式完成了行列转换，如果不想使用 DataSet 直接手工转换数据即可
            dv.transform({
                type: 'fold',
                fields: ['我', '平均'], // 展开字段集
                key: 'who', // key字段
                value: 'proximity' // value字段
            });
            var chart4 = new G2.Chart({
                container: 'table4',
                forceFit: true,
                height: 400
            });
            chart4.source(dv, {
                month: {
                    range: [0, 1]
                }
            });
            chart4.tooltip({
                crosshairs: {
                    type: 'line'
                }
            });
            chart4.axis('proximity', {
                label: {
                    formatter: function formatter(val) {
                        return val;
                    }
                }
            });
            chart4.line().position('time*proximity').color('who').shape('smooth');
            chart4.point().position('time*proximity').color('who').size(4).shape('circle').style({
                stroke: '#fff',
                lineWidth: 1
            });
            chart4.render();
        }


        //   5——————————————————————————————————————————————————————————
        {
            var data = table5_data
            var chart5 = new G2.Chart({
                container: 'table5',
                forceFit: true,
                height: 400
            });
            chart5.source(data, {
                percent: {
                    formatter: function formatter(val) {
                        val = val * 100 + '%';
                        return val;
                    }
                }
            });
            chart5.coord('theta', {
                radius: 0.9
            });
            chart5.tooltip({
                showTitle: false,
                itemTpl: '<li><span style="background-color:{color};" class="g2-tooltip-marker"></span>{name}: {value}</li>'
            });
            chart5.intervalStack().position('percent').color('item').label('percent', {
                formatter: function formatter(val, item) {
                    return item.point.item + ': ' + val;
                }
            }).tooltip('item*percent', function (item, percent) {
                percent = percent * 100 + '%';
                return {
                    name: item,
                    value: percent
                };
            }).style({
                lineWidth: 1,
                stroke: '#fff'
            });
            chart5.render();
        }

    }

    // // 图一二三 示例
    // render_single_result(
    //     [
    //         {
    //             target: '工作质量',
    //             A: 8,
    //             B: 5,
    //             C: 6,
    //             D: 3,
    //             E: 2
    //         },
    //         {
    //             target: '目标完成',
    //             A: 11,
    //             B: 5,
    //             C: 6,
    //             D: 3,
    //             E: 2
    //         }, {
    //             target: '工作效率',
    //             A: 5,
    //             B: 5,
    //             C: 12,
    //             D: 3,
    //             E: 2
    //         },
    //         {
    //             target: '政治素养',
    //             A: 8,
    //             B: 9,
    //             C: 3,
    //             D: 3,
    //             E: 2
    //         },
    //         {
    //             target: '思想品德',
    //             A: 8,
    //             B: 3,
    //             C: 11,
    //             D: 3,
    //             E: 2
    //         },
    //         {
    //             target: '创新能力',
    //             A: 5,
    //             B: 2,
    //             C: 6,
    //             D: 3,
    //             E: 8
    //         },
    //         {
    //             target: '贯彻执行能力',
    //             A: 13,
    //             B: 8,
    //             C: 3,
    //             D: 2,
    //             E: 1
    //         },
    //         {
    //             target: '组织协调能力',
    //             A: 7,
    //             B: 7,
    //             C: 3,
    //             D: 7,
    //             E: 1
    //         },
    //         {
    //             target: '解决问题能力',
    //             A: 14,
    //             B: 4,
    //             C: 3,
    //             D: 0,
    //             E: 0
    //         },
    //         {
    //             target: '表达能力',
    //             A: 6,
    //             B: 8,
    //             C: 4,
    //             D: 0,
    //             E: 2
    //         },
    //         {
    //             target: '事业心责任感',
    //             A: 11,
    //             B: 5,
    //             C: 6,
    //             D: 3,
    //             E: 2
    //         },
    //         {
    //             target: '下属积极性',
    //             A: 3,
    //             B: 9,
    //             C: 8,
    //             D: 2,
    //             E: 4
    //         },

    //     ], 'A', 89)

    // // 图四五 示例
    // render_multi_result(
    //     [
    //     {
    //         time: '2021一季度',
    //         我: 80,
    //         平均: 78
    //     },
    //     {
    //         time: '2021二季度',
    //         我: 82,
    //         平均: 79.3
    //     },
    //     {
    //         time: '2021三季度',
    //         我: 81,
    //         平均: 78.9
    //     },
    //     {
    //         time: '2021四季度',
    //         我: 75,
    //         平均: 80
    //     },
    //     {
    //         time: '2022一季度',
    //         我: 83,
    //         平均: 79
    //     },
    // ], 
    // [{
    //     item: 'A',
    //     count: 40,
    //     percent: 0.4
    // }, {
    //     item: 'B',
    //     count: 21,
    //     percent: 21 / 100
    // }, {
    //     item: 'C',
    //     count: 17,
    //     percent: 0.17
    // }, {
    //     item: 'D',
    //     count: 13,
    //     percent: 0.13
    // }, {
    //     item: 'E',
    //     count: 9,
    //     percent: 0.09
    // }])

    choose_single()
    choose_multi()
}
