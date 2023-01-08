window.onload = function () {

    // url
    var get_self_assessment_url = 'http://localhost:8080/EPAS/SelfAssess/select'
    var get_all_people_data_url = 'http://localhost:8080/EPAS/MutualServlet/getAllPeopleData'
    var get_mutual_assessment_data = 'http://localhost:8080/EPAS/MutualServlet/getMutualData'
    var submit_result_url = 'http://localhost:8080/EPAS/MutualServlet/receiveData'

    user_data = {}  //接收index页传来的用户数据
    window.addEventListener('message', function (event) {
        user_data = event.data
        console.log('接收到用户数据')
        console.log(user_data)
        click_choose()
    });

    var curr_date = new Date()
    var curr_year = curr_date.getFullYear()
    var year_data = []
    var selected_year = 2020 // 选中的年份
    var selected_quarter = 1
    var _all_people_data = []
        // var _all_people_data = [
    //     {
    //         id: 21,
    //         name: '12',
    //         dept: '21',
    //         pos: '9fv',
    //         state: 't'
    //     },
    //     {
    //         id: 21,
    //         name: '12',
    //         dept: '21',
    //         pos: '9fv',
    //         state: 'f'
    //     }
    // ]

    layui.use(['layer', 'dropdown', 'rate'], function () {
        dropdown = layui.dropdown
        layer = layui.layer
        rate = layui.rate

        var render_stars = function (data) {

        }

        // 设置下拉框初始值
        $('#show_year').text(selected_year)
        switch (selected_quarter) {
            case 1: $('#show_quarter').text('第一季度'); break;
            case 2: $('#show_quarter').text('第二季度'); break;
            case 3: $('#show_quarter').text('第三季度'); break;
            case 4: $('#show_quarter').text('第四季度'); break;
            default: break;
        }
        // 年份下拉框，可选择前六年
        for (var i = curr_year - 5; i <= curr_year; i++) {
            year_data.push({
                title: i + '年',
                id: i,
            })
        }
        console.log('下拉框年份：')
        console.log(year_data)
        dropdown.render({
            elem: '.year_selector'
            , data: year_data
            , click: function (obj) {
                selected_year = obj.id
                $('#show_year').text(selected_year + '年')
                console.log('选择了' + selected_year)
            }
        });
        // 季度下拉框
        dropdown.render({
            elem: '.quarter_selector'
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
                console.log('选择了' + obj.title)
            }
        });
    })
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
    // 更新状态标签
    var update_state_tag = function () {
        $('i').each(function () {
            if ($(this).text() == '已评价') {
                $(this).addClass('layui-bg-green')
            }
        })
    }
    // 点击‘选择’按钮
    var click_choose = function () {
        var send_data = {
            id: user_data.id,
            year: selected_year,
            quarter: selected_quarter
        }
        console.log(send_data)
        var index = loading()
        $.ajax({
            type: "post",
            url: get_all_people_data_url,
            dataType: "json",
            data: send_data,
            timeout: 4000,
            success: function (res) {
                if (res.msg == '成功') {
                    layer.msg('获取部门数据成功')
                    _all_people_data = res.data
                    console.log('部门数据')
                    console.log((_all_people_data))
                    _all_people_data.forEach(function (item, index) {
                        Vue.set(main.all_people_data, index, item)
                    })
                }
                else {
                    layer.msg(res.msg)
                }
                layer.close(index)
            },
            error: function () {
                layer.msg('获取部门数据失败')
                layer.close(index)
            }
        })
    }

    var main = new Vue({
        el: '#main',
        data: {
            all_people_data: _all_people_data
        },
        methods: {
            //点击按钮（查看/评价），显示考核界面
            click_button: function (id, name, dept, pos, state, _index) {
                var that = this
                var btn_text = state == 't'? '确定' : '提交'
                layer.open({
                    type: 2
                    , title: false
                    , closeBtn: 1
                    , btn: [btn_text]
                    , shade: 0.4
                    , content: '../assessment_page/assessment_page.html'
                    , area: ['1000px', '600px']
                    , success: function (layero, index) {
                        var body = layer.getChildFrame('body', index);
                        body.find('#assess_id').text(user_data.id)
                        body.find('#assess_year').text(selected_year)
                        body.find('#assess_quart').text(selected_quarter)
                        body.find('#user_name').text(name)
                        body.find('#user_id').text(id)
                        body.find('#user_dept').text(dept)
                        body.find('#user_pos').text(pos)
                        // 已评价
                        if (state == 't') {
                            var be_assess_data = {
                                assess_id: user_data.id,
                                be_assessed_id: id,
                                year: selected_year,
                                quarter: selected_quarter
                            }
                            // 如果已评价，获取结果
                            $.ajax({
                                type: "post",
                                url: get_mutual_assessment_data,
                                dataType: "json",
                                data: be_assess_data,
                                timeout: 4000,
                                success: function (res) {
                                    if (res.msg == '成功') {
                                        layer.msg('获取互评数据成功')
                                        var temp = res.data
                                        // temp = [1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4]

                                        setTimeout(function(){
                                            for (var i = 0; i < 12; i++) {
                                                var t = 'none'
                                                switch (temp[i]) {
                                                    case 1: t = 'E';break;
                                                    case 2: t = 'D'; break;
                                                    case 3: t = 'C'; break;
                                                    case 4: t = 'B'; break;
                                                    case 5: t = 'A'; break;
                                                    default: break;
                                                }
                                                body.find('#star' + i + ' ' + 'span').text(t)
                                            }
                                        },2000)
                                    }
                                    else {
                                        layer.msg(res.msg)
                                    }
                                    layer.close(index)
                                },
                                error: function () {
                                    layer.msg('获取互评数据失败')
                                    layer.close(index)
                                }
                            })
                        }

                        var send_data1 = {  // 要获取自评 提交的数据
                            id: id,
                            year: selected_year,
                            quarter: selected_quarter,
                        }
                        // 获取自评
                        console.log(send_data1)
                        var index = loading()
                        $.ajax({
                            type: "post",
                            url: get_self_assessment_url,
                            dataType: "json",
                            data: send_data1,
                            timeout: 4000,
                            success: function (res) {
                                if (res.msg == '成功') {
                                    body.find('#description').text('获取到的自评')
                                    body.find('#description').text(res.data)
                                }
                                else {
                                    layer.msg(res.msg)
                                }
                                layer.close(index)
                            },
                            error: function () {
                                console.log(send_data1)
                                layer.msg('获取自评内容失败')
                                layer.close(index)
                                body.find('#description').text('获取自评内容失败')
                            }
                        })

                        var btn = layero.find('.layui-layer-btn');

                        btn.find('.layui-layer-btn0').click(function () {
                            if($(this).text() == '确定') {
                                return
                            }

                            // 提交考核结果
                            var _result = []
                            for (var i = 0; i < 12; i++) {
                                if (body.find('#star' + i).val() == '') {
                                    layer.msg('未评价所有指标，提交失败')
                                    return
                                }
                                _result.push(Number(body.find('#star' + i).val()))
                            }
                            var send_data2 = { // 提交考核结果的数据
                                assess_id: Number(body.find('#assess_id').text()),
                                be_assessed_id: Number(body.find('#user_id').text()),
                                relation: body.find("#user_relation").val(),
                                year: Number(body.find('#assess_year').text()),
                                quarter: Number(body.find('#assess_quart').text()),
                                result: JSON.stringify(_result)
                            }
                            console.log('send_data2')
                            console.log(send_data2)
                            var index = loading()
                            $.ajax({
                                type: "post",
                                url: submit_result_url,
                                dataType: "json",
                                data: send_data2,
                                timeout: 4000,
                                success: function (res) {
                                    if (res.msg == '成功') {
                                        layer.msg('提交成功')
                                        console.log(index)
                                        that._data.all_people_data[_index].state = 't'  // 更新评价状态
                                        setTimeout(update_state_tag, 100)
                                    }
                                    else {
                                        layer.msg(res.msg)
                                    }
                                    layer.close(index)
                                },
                                error: function () {
                                    layer.msg('提交失败')
                                    layer.close(index)
                                }
                            })

                        });
                    }
                });
            }
        },
        watch: {
            all_people_data: function () {
                this.$nextTick(function () {
                    update_state_tag()
                })
            }
        }
    })

    update_state_tag()

    // 点击‘选择’按钮
    $('#choose').click(click_choose)
}