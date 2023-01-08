window.onload = function () {
    layui.use(['rate'], function () {
        var rate = layui.rate

        var get_indices_url = 'http://localhost:8080/EPAS/TargetServlet/selectByCondition'

        // 考核指标
        var all_indices = ['工作质量','目标完成','工作效率','政治素养','思想品德','创新能力','贯彻执行能力','组织协调能力','解决问题能力','表达能力','事业心责任感','下属积极性']

        var myAssessment = new Vue({
            el: '#myAssessment',
            data: {
                indices: all_indices,
            },
            methods: {

            },
            watch: {  
                indices: function () {
                    this.$nextTick(function () {
                        // v-for 渲染完成之后再执行
                        update_stars()
                    })
                }
            }
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
        // 更新打分星星
        var update_stars = function () {
            // 打分
            for (var i = 0; i < 12; i++) {
                $($('.star')[i]).attr('id', 'star' + i)
            }
            for (var i = 0; i < 12; i++) {
                rate.render({
                    elem: '#star' + i.toFixed()
                    , value: 0
                    , text: true
                    // ,theme:'#fff'
                    , setText: function (value) { //自定义文本的回调
                        var arrs = {
                            '0': ' '
                            , '1': 'E'
                            , '2': 'D'
                            , '3': 'C'
                            , '4': 'B'
                            , '5': 'A'
                        };
                        this.span.text(arrs[value])
                    },
                    choose: function (value) {
                        $(this)[0].elem.val(value)
                    }
                })
            }
        }
        // 获取考核指标
        var get_indices = function () {
            var send_data = {
                dept: $('#user_dept').text(),
                pos: $('#user_pos').text(),
                year: Number($('#assess_year').text()),
                quarter: Number($('#assess_quart').text())
            }
            var index = loading()
            $.ajax({
                type: "post",
                url: get_indices_url,
                dataType: "json",
                data: send_data,
                timeout: 4000,
                success: function (res) {
                    if (res.msg == '成功') {
                        layer.msg('获取考核指标成功')
                        all_indices = res.data
                        all_indices.forEach(function (item, index) {
                            Vue.set(myAssessment.indices, index, item)
                        })
                    }
                    else {
                        layer.msg(res.msg)
                    }
                    layer.close(index)
                },
                error: function () {
                    console.log(send_data)
                    layer.msg('获取考核指标失败')
                    layer.close(index)

                    update_stars()
                }
            })
        }

        setTimeout(get_indices, 500)

    })




}