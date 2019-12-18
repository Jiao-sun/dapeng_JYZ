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
    align: "center"
  },
  {
    field: "pojName",
    title: "项目名称",
    align: "center",
    formatter: function (value, row, index) {
      return "<a onclick=\"edit(" + row["pid"]
          + ",this)\"><span class='itemEdit'>" + value + "</span></a>"
    }
  },
  {
    field: "category",
    title: "项目类型",
    align: "center",
    formatter: function (value, row, index) {
      return "<span class='itemEdit'>" + value + "</span>"
    }
  },
  {
    field: "createBy",
    title: "创建人",
    align: "center",
    formatter: function (value, row, index) {
      return "<span class='itemEdit'>" + value + "</span>"
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
        return "<span class='itemEdit'>" + date.getFullYear() + "-"
            + eval(date.getMonth() + "+1") + "-" + date.getDate() + "</span>";
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
        return "<span class='itemEdit'>" + date.getFullYear() + "-"
            + date.getMonth() + "-" + date.getDate() + "</span>";
      }

    }
  },
  {
    title: "操作",
    type: 'text',
    align: "center",
    formatter: function (value, row, index) {

      return "<span><a onclick=\"edit(" + row["pid"]
          + ",this)\" class='editLink'>编辑</a> | <a onclick=\"delete("
          + row["pid"] + ",this)\">删除</a></span>"
    }
  }
]

/**
 * 初始化表
 */
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
  paginationDetailHAlign: "right",
  maintainSelected: "true"
  // clickToSelect: "false"
});

/** 动态查询*/
$("form").submit(function () {
  var subData = $("#form_1").serialize();
  $('#dataTable').bootstrapTable('refresh', {url: "/project/get?" + subData});
  return false;
});

/*编辑*/
function edit(pid, elem) {
  $('.editLink').text("完成")
  $('.editLink').attr("onclick", "EiditOk(" + pid + ")")
  $(elem).parents("tr").find(".itemEdit").attr("contenteditable", "true")
  $(elem).parents("tr").find(".itemEdit").addClass("onEdit")

}

/**显示时间 */
function setData() {
  var date = new Date();
  var str = "现在是: " + date.getFullYear() + "年" + eval(date.getMonth() + "+1")
      + "月" + date.getDate() + "日 "
      + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
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

/*添加*/
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
          $('#myModal').hide();
          $('.modal-backdrop').remove();
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
