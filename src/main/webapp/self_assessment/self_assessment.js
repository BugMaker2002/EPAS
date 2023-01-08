window.onload = function () {

    // url参数设置
    var get_self_assessment_url = 'http://localhost:8080/EPAS/SelfAssess/select'  // 获取自评
    var submit_self_assessment_url = 'http://localhost:8080/EPAS/SelfAssess/submit'  // 提交自评


    user_data = {}  //接收index页传来的用户数据
    window.addEventListener('message', function (event) {
        user_data = event.data
        console.log('接收到用户数据')
        console.log(user_data)
    });

    layui.use(['layer', 'dropdown'], function () {
        var dropdown = layui.dropdown
        var layer = layui.layer
        // 加载层
        var loading = function() {
            var index = layer.open({
                type: 3,
                title: "",
                content: "",
                shade: 0.1,
            })
            return index
        }


        var curr_date = new Date()
        var curr_year = curr_date.getFullYear()
        var year_data = []

        var selected_year = 2020 // 选中的年份
        var selected_quarter =  1
        console.log(selected_quarter)
        // 设置下拉框初始值
        $('#show_year').text(selected_year)
        switch(selected_quarter) {
            case 1: $('#show_quarter').text('第一季度');break;
            case 2: $('#show_quarter').text('第二季度');break;
            case 3: $('#show_quarter').text('第三季度');break;
            case 4: $('#show_quarter').text('第四季度');break;
            default:break;
        }


        // 年份下拉框，可选择前六年
        for (var i = curr_year - 5; i <= curr_year; i++) {
            year_data.push({
                title: i + '年',
                id: i
            })
        }
        console.log('下拉框年份：')
        console.log(year_data)
        dropdown.render({
            elem: '.year_selector'
            , data: year_data
            , click: function (obj) {
                selected_year = obj.id
                $('#show_year').text(obj.title)
                console.log('选择了' + obj.title)
            }
        });

        // 季度下拉框
        dropdown.render({
            elem: '#show_quarter'
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

        // 点击选择按钮
        $('#choose').click(function () {
            console.log('点击了选择，发送数据')
            var data = {
                id: user_data.id,
                year: selected_year,
                quarter: selected_quarter,
            }
            console.log(data)
            index = loading()
            $.ajax({
                type: "post",
                url: get_self_assessment_url,
                dataType: "json",
                data: {
                    id: user_data.id,
                    year: selected_year,
                    quarter: selected_quarter,
                },
                timeout: 4000,
                success: function (res) {
                    if (res.msg == '成功') {
                        layer.msg('获取自评成功')
                        $('#description').val(res.data)
                    }
                    else {
                        layer.msg(res.msg)
                    }
                    layer.close(index)
                },
                error: function () {
                    layer.msg('没有该时间段的数据！')
                    layer.close(index)
                }
            })
        })

        // 点击提交按钮
        $('#submit').click(function () {
            console.log('点击了提交，发送数据')
            var data = {
                id: user_data.id,
                year: selected_year,
                quarter: selected_quarter,
                content: $('#description').val()
            }
            //data=JSON.stringify(data)
            console.log(data)
            console.log(data.id)
            index = loading()
            $.ajax({
                type: "post",
                url: submit_self_assessment_url,
                dataType: "json",
                data: {
                    id: user_data.id,
                    year: selected_year,
                    quarter: selected_quarter,
                    content: $('#description').val()
                },
                timeout: 4000,
                success: function (res) {
                    if (res.msg == '成功') {
                        layer.msg('提交成功')
                    }
                    else {
                        layer.msg(res.msg)
                    }
                    layer.close(index)
                },
                error: function () {
                    layer.msg('似乎出了点问题..')
                    layer.close(index)
                }
            })
        })
    })
}


// $.ajax({
//     type: "post",
//     url: "http://xx.xx.xx.xx:xxxx/",
//     dataType: "json",
//     data: ,
//     timeout: 4000,
//     success: function (res) {

//     },
//     error: function () {

//     }
// })


// http://192.168.43.61:8080/user/index/getRecommendedBooks