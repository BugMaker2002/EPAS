window.onload = function () {
    const body = document.body
    const eye = document.querySelector('.eye')
    const pswInput = document.getElementById('psw')

    // 动画，密码可见性

    eye.addEventListener('click', function(e){
        e.preventDefault()
        body.classList.toggle('show-password')

        if(eye.getAttribute('val') == '0') {
            pswInput.type = 'text'
            eye.setAttribute('val', '1')
            console.log(eye)
        }
        else {
            pswInput.type = 'password'
            eye.setAttribute('val', '0')
            console.log(eye)
        }
    })

    const staffBtn = document.getElementById('staffLogin')
    const adminBtn = document.getElementById('adminLogin')
    const slide = document.querySelector('.animation')

    staffBtn.addEventListener('click', function(){
        slide.setAttribute('class', 'animation staff')
    })

    adminBtn.addEventListener('click', function(){
        slide.setAttribute('class', 'animation admin')
    })
    
    // 登录

    url = "http://localhost:8080/EPAS/loginServlet"

    layui.use(["layer"], function () {
        var layer = layui.layer
    })


    function check() {
        var success = 1;
        if (accInput.value == "") {
            layer.msg("账号不能为空");
            success = 0;
        }
        if (pswInput.value == "") {
            layer.msg("密码不能为空");
            success = 0;
        }
        return success;
    }

    const accInput = document.getElementById('account')
    const loginBtn = document.getElementById('loginBtn')

    loginBtn.addEventListener('click', function(){
        if (check() == 1) {
            var data = {
                type: slide.classList.value == 'animation staff' ? 'staff login' : 'admin login',
                account: accInput.value,
                password: pswInput.value,
            }
            data = JSON.stringify(data);  // 转成json格式

            layer.msg(data);

            // 加载层
            var index = layer.open({
                type: 3,
                title: "",
                content: "",
                shade: 0.1,
                offset: ['62%', '47.8%'],
            })

            $.ajax({
                type: "post",
                url: url,
                dataType: "json",
                data: data,

                success: function (res) {
                    console.log(res.data)
                    if (res.data == "成功") {
                        //alert("登录成功")
                        //alert("欢迎您！管理员！")
                        layer.msg('登录成功')
                        if (res.msg=="admin login")
                        {
                            window.location.href = "../AdministratorPage/adminNavigation.html"
                        }
                        else if (res.msg=="staff login")
                        {
                            window.location.href = "../index/index.html?id=" + res.id
                        }
                    }
                    else {
                        layer.msg(res.msg)
                        layer.close(index)
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest);
                    alert(textStatus);
                    alert(errorThrown)
                    layer.msg("请求超时")
                    layer.close(index)
                }
            })
        }
    })
}