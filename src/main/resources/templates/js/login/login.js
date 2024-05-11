$(function () {
$("body").keydown(function (event) {
            if (event.keyCode == 13) {
              $('#login-btn').click();
            }
          });
    // tabSwicth
    var phoneTabcont = $(".tabContentPhone");
    var accountTabcont = $(".tabContentAccount");
    $("ul.tabBoxSwitchUl").on('click', 'li', function () {
        var i = $(this).index();
        // $(this).attr("data-id",i);
        // console.log(i);
        $(this).addClass("tab-active").siblings('li').removeClass("tab-active");
        $("div.tabcont").eq(i).addClass("active").siblings().removeClass("active");

        // let module;//smsFrom accountFrom
        formType(i);
    });

    function formType(i) {
        if (i == 0) {
            $("#forgetpswd").css("display","none")
            $("form button.out-login-btn").hide();
            $("#rmpswd").hide();
            $("form button.fromSubmit").attr("data-type", "smsSubmit").text("登录");

        } else if (i == 1) {
            $("#forgetpswd").css("display","block")
            $("form button.out-login-btn").show();
            $("#rmpswd").css("display","block");
            $("form button.fromSubmit").attr("data-type", "accountSubmit").text("登录");
            forgeClick();
        }
    }
    // 密码登录：海外手机/账号
    $("button[data-clilck='isClick']").click(function (e) {
        e.preventDefault();
        var _this = $(this);
        var _show = $(".login-out-phoneBox");
        if (_show.is(':hidden')) {
            _show.show()
            _this.text("邮箱账号登录");
        } else {
            _show.hide()
            _this.text("海外手机号登录");

        }
    });
    // 忘记密码点击
    /*var forgeClick = function () {
        $("#forgetpswd").click(function (e) {

            window.open("/bsfile/html/forgetpwd.html");//忘记密码页面

        });
    }*/

    $("button.selectBtn").click(function (e) {
        if ($(".selectConentent").is(':hidden')) {
            $(".selectConentent").show();

        } else {
            $(".selectConentent").hide();
        }
        $(document).one('click', function () {
            $(".selectConentent").hide();
        });
        e.stopPropagation();
    });
    $(".selectConentent").on('click', function (e) {
        e.stopPropagation();
    })
    // 国际区号json
    $.ajax({
        type: "GET",
        url: "../js/selectOptions.json",
        data: "data",
        dataType: "JSON",
        success: function (data) {
            // console.log(data);
            $.each(data.CountryNum, function (i, item) {
                // console.log(item.countryName, item.number);
                var btns = " <button class='phone-btn selectBtn select-option' type='button' data-type='option'" + "data-id=" + i + ">" + item.countryName + "   " + item.number + "</button>";
                $(".selectOptions").append(btns);
                chooseBtn();
                // console.log(btns);
            });
        }
    });


    function chooseBtn() {
        $("button[data-type='option']").each(function () {
            $(this).click(function () {
                var txt = $(this).text();
                $("button[data-type='selected']").attr("data-fid", $(this).index());
                $("button[data-type='selected'] span").text(txt);
                $(".selectConentent").hide();
                $(".selectOptions").scrollTop($(this).index() * 40);
            });
            $(this).hover(function () {
                $(this).css("background-color", "#f6f6f6");
            }, function () {
                $(this).css("background-color", "#ffffff");
            });
        });
    };




    //失去焦点;获得焦点

    Focuss($(".msgInput"), "输入 6 位短信验证码");
    Focuss($(".phoneInput"), "手机号");
    Focuss($(".accountUsername"), "用户名/手机号/邮箱");
    Focuss($(".verificationCode"), "验证码");
    Focuss($(".accountPwd"), "密码");

    Blurr($(".phoneInput"), "请输入手机号");
    Blurr($(".msgInput"), "请输入短信验证码");
    Blurr($(".accountUsername"), "请输入用户名/手机号/邮箱");
    Blurr($(".verificationCode"), "请输入验证码");
    Blurr($(".accountPwd"), "请输入密码");



    function Focuss(ele, content) {
        ele.focus(function (e) {
            e.preventDefault();
            var _this = $(this);
            _this.parent().removeClass('isShow');
            _this.attr("placeholder", content);
        })
    }

    function Blurr(eleb, contentb) {
        eleb.blur(function (e) {
            e.preventDefault();
            var _this = $(this);
            if (_this.val() == null || _this.val() == "" || _this.val() == undefined) {
                // let content = "请输入短信验证码"
                _this.parent().addClass('isShow').attr('data-content', contentb);
                _this.attr("placeholder", " ");
            } else {
                _this.parent().removeClass('isShow');
            }
        })
    }

    // 60s倒计时
    $(".msgBtn").click(function () {
        var pval = $(".phoneInput").val();
        if (pval == "" || pval == null || pval == undefined) {
            $(".msgBtn").text("重新发送短信验证码");
            var content = "请输入手机号";
            $(".phoneInput").parent().addClass('isShow').attr('data-content', content);
            $(".phoneInput").attr("placeholder", " ");
        } else {
            $(".msgBtn").css("color", "#b7b7b7");
            $(".msgBtn").attr("disabled", true);
            getRandom();
        }
        // getRandom();

    })

    var time = 60;
    function getRandom() {
        if (time === 0) {
            $(".msgBtn").text("发送短信验证码");
            $(".msgBtn").css("color", "#175199");
            $(".msgBtn").attr("disabled", false);
            return
        } else {
            time--;

            $(".msgBtn").text(time + " 秒后可重发");
        }
        setTimeout(function () {
            getRandom();
        }, 1000)

    }
    // ercode tab
    $(".swicth-ercode").click(function (e) {
        e.preventDefault();
        $("form#form_key").hide();
        $(".ercodeSignBox").show();
        makeCode();
    });
    $(".switch-input").click(function (e) {
        e.preventDefault();
        $("form#form_key").show();
        $(".ercodeSignBox").hide();
    });

    //ercode

    // var qrcode = new QRCode('qrcode', {
    //     text: 'your content',
    //     width: 150,
    //     height: 150,
    //     colorDark: '#0084ff',
    //     colorLight: '#ffffff',
    //     correctLevel: QRCode.CorrectLevel.H
    // });
    function makeCode() {
        qrcode.clear();
        var txt = '';
        qrcode.makeCode(txt);
    }

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
        var remberPwed=$('#remberPwed').prop("checked");
        var params={};
        params.userName=account;
        params.userPassword=password;

        // 用表单来初始化
        var formData = new FormData();
        formData.append("userName", account);
        formData.append("userPassword", password);
        console.log("测试"+formData);
        if (account.length<=0){
            alert("请输入账号2！");
        }else if(password.length<=0){
            alert("请输入密码！");
        }else{

            //进行后台传值
            $.ajax({
                type:"POST",
                url:"user/login.do",
                data:formData,
                processData: false,   // jQuery不要去处理发送的数据
                contentType: false,   // jQuery不要去设置Content-Type请求头
                datatype:"json",
                //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                success:function (data) {
                        console.log(data.data.userId);
                    if (data.msg=="登录成功") {
                        //如果验证成功则在全局中保存
                        $.cookie("ACCOUNT",account,{expires:7,path:"/"});
                        /*将账号和密码保存在本地cookie中*/
                        $.cookie("user_id",data.data.userId);
                        $.cookie("user_account",account,{expires:7,path:"/"});
                        $.cookie("user_password",password,{expires:7,path:"/"});
                        /*如果不存在基本信息则进入主界面*/
                        window.location.href="http://localhost:8088/index.html";
                    }else{
                        alert("账号或密码不正确1！");
                    }
                }
            });
        }
    });




});
