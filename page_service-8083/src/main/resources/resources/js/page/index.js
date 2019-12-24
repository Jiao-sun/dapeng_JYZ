/**
 *定义列
 */
var columns = [{
  checkbox: "true"
},
  {
    sortable: "true",
    field: "pid",
    title: "项目编号",
    align: "center",
    formatter: function (value, row, index) {
      return "<span class='pid'>" + value + "</span>";
    }
  },
  {
    field: "pojName",
    title: "项目名称",
    align: "center",
    formatter: function (value, row, index) {
      return "<a onclick=\"edit(" + row["pid"]
          + ",this)\"><span class='pname'>" + value + "</span></a>";

    }
  },
  {
    field: "category",
    title: "项目类型",
    align: "center",
    formatter: function (value, row, index) {
      return "<span class='category'>" + value + "</span>";
    }
  },
  {
    field: "createBy",
    title: "创建人",
    align: "center",
    formatter: function (value, row, index) {
      return "<span class='createBy'>" + value + "</span>";
    }
  },
  {
    sortable: "true",
    field: "startDate",
    title: "开始时间",
    align: "center",
    formatter: function (value, row, index) {
      if (row["startDate"] != null) {
        var date = new Date(row["startDate"]);
        return "<span class='startDate'>" + date.getFullYear() + "-"
            + ((eval(date.getMonth() + "+1") < 10) ? ('0' + eval(
                date.getMonth() + "+1")) : (eval(date.getMonth() + "+1"))) + "-"
            + ((date.getDate() < 10) ? ('0' + date.getDate())
                : (date.getDate()))
            + "</span>";
      }

    }
  },
  {
    sortable: "true",
    field: "finishDate",
    title: "完成时间",
    align: "center",
    formatter: function (value, row, index) {
      if (row["finishDate"] != null) {
        var date = new Date(row["finishDate"]);
        return "<span class='finishDate'>" + date.getFullYear() + "-"
            + ((eval(date.getMonth() + "+1") < 10) ? ('0' + eval(
                date.getMonth() + "+1")) : (eval(date.getMonth() + "+1"))) + "-"
            + ((date.getDate() < 10) ? ('0' + date.getDate())
                : (date.getDate()))
            + "</span>";
      }
    }
  },
  {
    title: "操作",
    type: 'text',
    align: "center",
    formatter: function (value, row, index) {
      return "<span><a onclick=\"edit(" + row["pid"]
          + ",this)\" class='editLink'>编辑</a> | <a onclick=\"delet("
          + row["pid"] + ",this)\">删除</a></span>"
    }
  }
]

/*初始化表*/
$("#dataTable").bootstrapTable({
  url: "/project/get",
  pagination: true,
  columns: columns,
  pageSize: 10,
  pageList: [5, 10, 15, 20],
  showColumns: true,
  showRefresh: false,
  locale: "zh-CN",
  striped: true,
  method: "get",
  /*paginationDetailHAlign: "right",*/
  maintainSelected: "true"
  // clickToSelect: "false"
});
/** 动态查询*/
$("form").submit(function () {
  var subData = $("#form_1").serialize();
  $('#dataTable').bootstrapTable('refresh', {url: "/project/get?" + subData});
  return false;
});

/*修改编辑项目*/
function edit(pid, elem) {
  var tr = $(elem).parents("tr");
  $("#myModal .modal-dialog").css("margin", "20% auto");
  $("#myModal .modal-title div").text("修改项目");
  $("#addForm").attr("id", "editForm");
  $(".modalFooter button").attr("onclick",
      "editOk(" + tr.find('.pid').text() + ")")
  $(".modalFooter button").text("完成编辑")
  $("#pname").val(tr.find(".pname").text());
  var category = tr.find(".category").text()
  $.each($("#category option"), function (index, item) {
    if ($(item).val() == category) {
      $(item).attr("selected", "selected")
      $("#category").val(category);
    }
  })
  $("#createBy").val(tr.find(".createBy").text())
  $("#editForm input[name='startDate']").val(tr.find(".startDate").text())
  $("#editForm input[name='finishDate']").val(tr.find(".finishDate").text())
  $('#myModal').modal("show");
}

//完成项目修改
function editOk(pid) {
  var data = {
    pId: pid,
    pojName: $("#pname").val(),
    category: $("#category").val(),
    createBy: $("#createBy").val(),
    startDate: $("#editForm input[name='startDate']").val(),
    finishDate: $("#editForm input[name='finishDate']").val()
  };
  $.ajax({
    url: "/project/update",
    type: "PUT",
    data: data,
    success: function (data) {

      if (data == 0) {
        popup({
          type: 'tip',
          msg: "没有做任何修改！",
          delay: 900
        });
      }
      if (data == 1) {
        popup({
          type: 'success',
          msg: "修改成功！！",
          delay: 1000,
          callBack: function () {
            $('#myModal').modal("hide");
            $('#dataTable').bootstrapTable('refresh');
          }
        });
      }
    },
    error: function (data) {

    }
  })
}

//模态框恢复
$('#myModal').on("hidden.bs.modal", function () {
  $("#myModal .modal-dialog").css("margin", "30px auto");
  $("#myModal .modal-title div").text("添加项目");
  $(".modalFooter button").attr("onclick", "add()")
  $(".modalFooter button").text("完成添加")
  $("#editForm").attr("id", "addForm");
  $("#pname").val("");
  $("#category").val("")
  $.each($("#category option"), function (index, item) {
    $(item).attr("selected", false);
    $("#category").val("")
  })
  $("#createBy").val("")
  $("#addForm input[name='startDate']").val("")
  $("#addForm input[name='finishDate']").val("")
});

/**显示时间 */
function setData() {
  var date = new Date();
  var str = "现在是: " + date.getFullYear() + "年" + eval(date.getMonth() + "+1")
      + "月" + date.getDate() + "日 "
      + ((date.getHours() < 10) ? ('0' + date.getHours()) : (date.getHours())) +
      ":" + ((date.getMinutes() < 10) ? ('0' + date.getMinutes())
          : (date.getMinutes())) +
      ":" + ((date.getSeconds() < 10) ? ('0' + date.getSeconds())
          : (date.getSeconds()));
  $("#date").text(str);
}

$(function () {
  setInterval(setData, 1000);
})

/*个人信息*/
function togelInfo() {
  $("#info").toggle()
}

$("#info").mouseleave(function () {
  $("#info").hide();
})

function logout() {
  $.post({
    url: "user/logout",
    success: function (data) {
      window.location.href = "/";
    }

  })
}

/**********验证函数***********/
function checkPojNmae() {
  var pname = $("#pname").val();
  if (pname.length <= 0) {
    $("#errorMsg").text("请填写项目名称");
    return false;
  }
  return true;
}

function checkcreateBy() {
  var createBy = $("#createBy").val();
  if (createBy.length <= 0) {
    $("#errorMsg").text("请填写创建人");
    return false;
  }
  return true;
}

function checkcategory() {
  var category = $("#category").val();
  if (category.length <= 0) {
    $("#errorMsg").text("请选择类型");
    return false;
  }
  return true;
}

/*添加项目*/
function add() {
  var flag = true;

  if (!checkcreateBy()) {

  }
  if (!checkcategory()) {
    flag = false;
  }
  if (!checkPojNmae()) {
    flag = false;
  }

  if (flag) {
    $("#errorMsg").text("");
    var data = $("#addForm").serialize();

    $.post({
      url: "/project/add",
      data: data,
      success: function (data) {
        if (data == 1) {
          $('#myModal').modal("hide");
          popup({
            type: 'success',
            msg: "添加成功！",
            delay: 1500,
            callBack: function () {
              $('#dataTable').bootstrapTable('refresh');
            }
          });
        }
      }
    })

  }
  return false;
}

/*删除项目*/
function delet(pid, elem) {
  if (confirm("确认删除编号为《" + pid + "》的任务")) {
    $.ajax({
      url: "/project/delete/" + pid,
      type: "DELETE",
      success: function (data) {
        if (data == 1) {
          popup({
            type: 'success',
            msg: "删除成功！",
            delay: 1000,
            callBack: function () {
              $('#dataTable').bootstrapTable('refresh');
            }
          });
        }
      }
    })
  }
}