$(function () {
    var totalRecord,currentPage;
    //显示所有数据
    to_page(1);

    function to_page(pn) {

        $.ajax({
            url: "/user/getAllUser",
            data:"pn"+ pn,
            type: "GET",
            dataType: "json",
            success: function (data) {
                //alert(data.msg);
                //解析并显示员工数据表
                build_users_table(data)

                //2.解析并显示分页信息
                build_page_info(data);

                //3.解析并显示分页条数据
                build_page_nav(data);
            }
        })
    }

    //解析并显示员工数据表
    function build_users_table(data) {
        //清空table表格
        $("#users_table tbody").empty();
        var users = data.pageInfo.list;

        //遍历元素
        $.each(users, function (index, item) {
            var checkBox=$("<td><input type='checkbox' class='check_item'/></td>");
            var id = $("<td></td>").append(item.id);
            var username = $("<td></td>").append(item.username);
            var sex = $("<td></td>").append(item.sex);
            var city = $("<td></td>").append(item.city);
            var age = $("<td></td>").append(item.age);

            var button1 = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil").attr("aria-hidden", true)).append("编辑");
            var button2 = $("<button></button>").addClass("tn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-trash").attr("aria-hidden", true)).append("删除");
            var td_btn = $("<td></td>").append(button1).append(" ").append(button2);
            $("<tr></tr>").append(checkBox).append(id).append(username).append(sex).append(city).append(age)
                .append(td_btn ).appendTo("#users_table tbody");

        })
    }

    //解析显示分页信息
    function build_page_info(data) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前" + data.pageInfo.pageNum + "页,总共" + data.pageInfo.pages +
            "页，总共" + data.pageInfo.total + "条记录");
        totalRecord = data.pageInfo.total;
        currentPage=data.pageInfo.pageNum;
    }

    //解析显示分页导航条
    function build_page_nav(data) {
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href", "#"));
        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href", "#"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
        //如果没有前一页，前一页和首页就不能点
        if (data.pageInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //首页
            firstPageLi.click(function () {
                to_page(1);
            });
            prePageLi.click(function () {
                to_page(data.pageInfo.pageNum - 1);
            });
        }
        if (data.pageInfo.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            //构建点击事件

            nextPageLi.click(function () {
                to_page(data.pageInfo.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page(data.pageInfo.lastPage);
            })
        }
        //添加首页和前一页
        ul.append(firstPageLi).append(prePageLi);
        //遍历添加页码
        $.each(data.pageInfo.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
            //如果是当前选中页面，添加active标识
            if (data.pageInfo.pageNum == item) {
                numLi.addClass("active");
            }
            //给每个页码添加点击就跳转
            numLi.click(function () {
                to_page(item);
            });
            ul.append(numLi);
        });
        //添加下一页和末页
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }

});
