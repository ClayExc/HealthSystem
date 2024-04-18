$(function () {
    $("#registerBtn").on('click',function () {

        var password=$("#userPassword").val();
        var reppassword=$("#userRePassword").val();
        var account=$("#username").val();
        var phone=$("#phone").val();

        var params={};
        params.user_name=account;
        params.user_password=password;
        //校验用户姓名：
         if (account.trim().length==0){
                alert("用户名不能为空");
                return false;
          }
          /*手机号校验*/
          if(!(/^1[3456789]\d{9}$/.test(phone))){
                            alert("手机号码有误，请重填");
                            return false;
                        }

       /*校验密码*/
           if (password.trim().length==0){
               alert("密码不能为空");
               return false;
           }
           if (password.trim()!=reppassword.trim()){
               alert("两次密码不一致");
               return false
           }

        return true;

    });
});


/*
function isTrueName(s)
{
    if (s.trim().length==0){
        return false;
    }
    return true;
}

*/
/*手机号校验*//*

function checkPhone(phone)
{
    if(!(/^1[3456789]\d{9}$/.test(phone))){
        alert("手机号码有误，请重填");
        return false;
    }
    return true;
}

*/
/*校验密码*//*

function checkPwd(pwd,repeatpwd)
{
    if (pwd.trim().length==0){
        alert("密码不能为空");
        return false;
    }
    if (pwd.trim()!=repeatpwd.trim()){
        alert("两次密码不一致");
        return false
    }
    return true;
}*/
