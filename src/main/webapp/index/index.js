window.onload = function () {
    layui.use(['layer'], function () {
        var layer = layui.layer
    })

    var get_user_info_url = 'http://localhost:8080/EPAS/afterLoginServlet'

    var iframe = document.getElementById('rightBody')

    // 传数据
    var senMsg = function (data) {
        iframe.contentWindow.postMessage(data)
    }
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

    var user_data = {
        id: 0,
        name: 'user_name',
        department: 'department',
        position: 'position'
    }

    var param = decodeURI(location.search)
    strs = param.split("=")
    if (param.indexOf("?") != -1 && strs[0] === "?id") {
        var _userId = strs[1]
        user_data.id = Number(_userId)
        $('#user_id').text("工号：" + _userId)
    }
    else {
        window.location.href = "../login/login.html"
    }

    console.log(user_data)

    var get_user_info = function () {
        var index = loading()
        $.ajax({
            type: "post",
            url: get_user_info_url,
            dataType: "json",
            data: {
                id: user_data.id
            },
            timeout: 4000,
            success: function (res) {
                if (res.msg == '成功') {
                    layer.msg('获取用户信息成功')
                    console.log(res)
                    user_data.name = res.name
                    user_data.department = res.department
                    user_data.position = res.position
                    $('#user_name').text(res.name)
                    $('#user_dept').text(res.department)
                    $('#user_pos').text(res.position)

                    console.log(user_data)
                }
                else {
                    layer.msg(res.msg)
                }
                layer.close(index)
            },
            error: function () {
                layer.msg('获取用户信息失败')
                layer.close(index)
                console.log(user_data)
            }
        })
    }

    //get_user_info()
    setTimeout(get_user_info,500)

    var exit = function () {
        layer.confirm("确定退出登录？", function (index) {
            layer.close(index);
            window.location.href = '../login/login.html';
        })
    }
    var modify_password = function () {
        iframe.setAttribute('src', 'modifyPsw.html')
        setTimeout(function () { senMsg(user_data) }, 1000)
        $('#top_text').text('修改密码')
    }
    var show_home_page = function () {
        iframe.setAttribute('src', '../home/home.html')
        setTimeout(function () { senMsg(user_data) }, 1000)
        $('#top_text').text('首页')
    }
    var show_result_page = function () {
        iframe.setAttribute('src', '../assessment_result/assessment_result.html')
        setTimeout(function () { senMsg(user_data) }, 1000)
        $('#top_text').text('考核结果')

    }
    var show_self_assessment_page = function () {
        iframe.setAttribute('src', '../self_assessment/self_assessment.html')
        setTimeout(function () { senMsg(user_data) }, 1000)
        $('#top_text').text('自评')
    }
    var show_mutual_assessment_page = function () {
        iframe.setAttribute('src', '../mutual_assessment/mutual_assessment.html')
        setTimeout(function () { senMsg(user_data) }, 1000)
        $('#top_text').text('互评')
    }

    $('#modify_password').click(modify_password)
    $('#exit').click(exit)
    $('#index').click(show_home_page)
    $('#self_assessment').click(show_self_assessment_page)
    $('#mutural_assessment').click(show_mutual_assessment_page)
    $('#assessment_result').click(show_result_page)

}