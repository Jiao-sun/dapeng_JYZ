//注册用密码检查
function checkPassword() {
  var password = $("#regPWD").val();
  var checkPwd = /^[0-9a-zA-Z]{3,12}$/
  if (password == "" || password.length == 0) {
    $("#regPWDError").text("密码不能为空");
  } else if (!checkPwd.test(password)) {
    $("#regPWDError").text("密码长度须大于3-12位的字母或数字");
    return false;
  } else {
    $("#regPWDError").text("");
  }
  return true;
}

//注册用电话检查
function checktel() {
  var checkNmae = /^1\d{10}$/;
  var regTEL = $("#regTEL").val();
  if (!checkNmae.test(regTEL)) {
    $("#regTELError").text("手机号似乎不对哦！");
    return false
  } else {
    $("#regTELError").text("");
    return true;
  }
}

//注册用验证用户名
function ckeckName() {
  var userName = $("#regUserName").val();
  if (userName.length == 0) {
    $("#regNameError").text("请输入用户名");
    return false
  } else if (userName.length > 10) {
    $("#regNameError").text("太长了！");
    return false
  } else {
    $("#regNameError").text("");
    return true;
  }
}

//异步验证用户名可用性
$("#regUserName").blur(ckeckName/*function () {
  var userName = $("#regUserName").val();
  if (ckeckName) {
    $.get({
          url: "user/get?userName=" + userName,
          dataType: "text",
          success: function (data) {
            if (data.length > 0) {
              $("#regNameError").text("用户名已被占用！");
            } else {
              $("#regNameError").text("");
            }
          }
        }
    )
  }

}*/);

//切换注册
function goreg() {
  $(".logGet").animate({top: '-800px'});
  $(".regGet").animate({top: '20%'});
  $("input").text("");
  $("input[type='checkbox']").checked = false;
}

//切换登录
function gologin() {
  $(".logGet").animate({top: '20%'});
  $(".regGet").animate({top: '100%'});
  $("input").val("");
  $("input[type='checkbox']").checked = false;
}

//异步登录
$("#loginForm").submit(function () {
  if ($(".lgD input[name='userNo']").val().length == 0) {
    $("#userNoError").text("请输入用户账号")
  } else if ($(".lgD input[name='password']").val().length == 0) {
    $("#userNoError").text("")
    $("#passwordError").text("请输入密码")
  } else {
    $("#userNoError").text("")
    $("#passwordError").text("")
    $.post({
      url: "user/login",
      data: $("#loginForm").serialize(),
      success: function (msg) {
        switch (msg) {
          case "userNoError":
            $("#userNoError").text("账号有误或不存在")
            break;
          case "passwordError":
            $("#passwordError").text("密码错误")
            break;
          case "ok":
            popup({
              type: 'success', msg: "注册成功", delay: 1000, callBack: function () {
                window.location.href = "/"
              }
            });
            break;
        }
      }
    })
  }
  return false
});

$(".newUser").click(goreg)
$(".regDtip .p2").click(gologin);

//异步注册
$("#regForm").submit(function () {
  var flag = true;
  if (!checkPassword()) {
    flag = false
  }
  if (!checktel()) {
    flag = false
  }
  if (!ckeckName()) {
    flag = false
  }

  if (flag) {
    var subData = $("#regForm").serialize();
    $.post({
      url: "/user/add",
      data: subData,
      success: function (data) {
        alert(data)
        if (data.status == true) {
          popup({
            type: 'success', msg: "注册成功<br/> 请记住你的账号："+data.userNo, delay: 2000, callBack: function () {
              gologin();
              $(".lgD input[name='userNo']").val(data.userNo);
              $(".lgD input[name='userNo']").focus();
              $("#userNoError").text("↖👆请牢记您的账号")
            }
          });
        } else {
          popup({
            type: 'error', msg: "失败了！！<br/>请再试一次！", delay: 3000
          });
        }
      }
    })
  }

  return false;
})

$(function () {
  //禁用滚动条
  var he = window.outerHeight;
  $(".logDiv").css("height", he);
  window.scrollbars = false;

  $("#regPWD").blur(checkPassword);
  $("#regTEL").blur(checktel);

})
