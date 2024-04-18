$(function () {

    /*文本框获取焦点后，将账号自动填充*/
    $("#userName").focus(function(){
        var cookie_account=$.cookie("userName");
        $("#userName").val(cookie_account);
    });
    /*文本框获取焦点后，将密码自动填充*/
    $("#userPassword").focus(function () {
        var cookie_password=$.cookie("user_password");
        $("#userPassword").val(cookie_password);
    });

    // 提交表单验证
    $("#login-btn").on('click',function () {
        var account=$("#userName").val();
        var password=$("#userPassword").val();
        var params={};
        params.userName=account;
        params.userPassword=password;

        // 用表单来初始化
        var formData = new FormData();
        formData.append("username", account);
        formData.append("password", password);
        console.log("测试1");
        if (account.length<=0){
            alert("请输入账号2！");
        }else if(password.length<=0){
            alert("请输入密码！");
        }else{

            //进行后台传值
            $.ajax({
                type:"POST",
                url:"admin/login.do",
                data:formData,
                processData: false,   // jQuery不要去处理发送的数据
                contentType: false,   // jQuery不要去设置Content-Type请求头
                datatype:"json",
                //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                success:function (data) {
                        console.log(data);
                    if (data.msg=="登录成功") {
                        //如果验证成功则在全局中保存

                        window.location.href="http://localhost:8088/mana_index.html";
                    }else{
                        alert("账号或密码不正确1！");
                    }
                }
            });
        }
    });


});
