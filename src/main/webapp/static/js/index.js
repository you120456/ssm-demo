$(function () {
    var totalRecord,currentPage;
    //1.显示所有数据
    to_page(1);
    //2.添加用户
    addUser();
    //3.修改用户
    reviseUser();
    //4.单个删除用户
    deleteUser();

    /**
     * 1.显示所用用户
     * @param pn
     */
    function to_page(pn) {

        $.ajax({
            url: "/user/getAllUser",
            data:"pn="+pn,
            type: "GET",
            dataType: "json",
            contentType:"application/json;charset=UTF-8",
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

    /**
     * 解析并显示员工数据表
     * @param data
     */
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

    /**
     * 解析显示分页信息
     * @param data
     */
    function build_page_info(data) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前" + data.pageInfo.pageNum + "页,总共" + data.pageInfo.pages +
            "页，总共" + data.pageInfo.total + "条记录");
        totalRecord = data.pageInfo.total;
        currentPage=data.pageInfo.pageNum;
    }

    /**
     * 解析显示分页导航条
     * @param data
     */
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

    /**
     * 2.实现新增功能
     * @returns {boolean}
     */
    function addUser() {

        //为新增按钮添加modal
        $("#user_add_modal_btn").click(function () {
            //清除表单数据
            $("#userAddModal form")[0].reset();
            $("#userAddModal").modal({
                backdrop: "static"
            })
        });

        /**
         * 2.保存用户信息
         * 校验该用户是否存在,如果存在就不能添加该用户
         */
        $("#username_add_input").change(function () {
            var username = $("#username_add_input").val();
            //发送Ajax请求校验姓名是否可用
            $.ajax({
                url: "/user/checkUser/"+username,
                //data: "username=" + username,
                type: "POST",
                success: function (data) {
                    //alert(data.pageInfo.username);
                    //表示成功，用户名可用
                    if (data.code == 200 && data.pageInfo != null) {
                        alert("用户名**"+ data.pageInfo.username +"**已经存在");
                        $("#user_save_btn").attr("ajax-va", "error");
                    }  else if (data.code == 200 && data.pageInfo == null) {
                        //为保存按钮添加属性
                        $("#user_save_btn").attr("ajax-va", "success");
                    }
                }
            })
        });

        /**
         * 保存用户信息
         */
        $("#user_save_btn").click(function () {
            var username = $("#username_add_input").val();
            var sex =$("input[name=sex]:checked").val();
            var city =$("#city_add_input").val();
            var age =$("#age_add_input").val();
            //2.发送ajax请求保存员工
            $.ajax({
                url: "/user/saveUser",
                type: "POST",
                data: JSON.stringify({username:username,sex:sex,city:city,age:age}),
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                success: function (data) {
                   if (data.code == 200){
                       //1.关闭modal框
                       $("#userAddModal").modal('hide');
                       //2.来到最后一页，显示刚才保存的数据
                       to_page(totalRecord);
                   }
                }
            });
        });
    }

    /**
     * 3.修改用户
     */
    function reviseUser() {
        //为编辑按钮绑定弹出modal框事件
        //1.因为在按钮创建之前就绑定了click，所以用普通click方法绑定不上

        $(document).on("click",".edit_btn",function () {
            //清除表单数据
            $("#userReviseModal form")[0].reset();
            $("#username_revise_input").next("span").text("");

            //修改框中用户信息回显
            var id= $(this).parent().parent().children("td").eq(1).text();
            //将id的值传递给修改按钮的属性，方便发送Ajax请求
            $("#user_revise_btn").attr("edit-id",id);
            var username=$(this).parent().parent().children("td").eq(2).text();
            var sex=$(this).parent().parent().children("td").eq(3).text();
            var city=$(this).parent().parent().children("td").eq(4).text();
            var age=$(this).parent().parent().children("td").eq(5).text();

            $("#username_revise_input").val(username);
            $("#userReviseModal input[name=sex]").val([sex]);
            $("#city_revise_input").val(city);
            $("#age_revise_input").val(age);
            $("#userReviseModal").modal({
                backdrop: "static"
            })
        });
        //2.为模态框中的修改按钮绑定事件，更新员工信息
        $("#user_revise_btn").click(function () {

            //2.验证通过后发送ajax请求保存更新的员工数据
            //如果要直接发送PUT之类的请求
            //在WEB.xml配置HttpPutFormContentFilter过滤器即可
            //这里未使用如上所述方法
            var id = $(this).attr("edit-id");
            var username = $("#username_revise_input").val();
            var sex = $("#userReviseModal input[name=sex]").val();
            var city =$("#city_revise_input").val();
            var age =$("#age_revise_input").val();
            $.ajax({
                url:"/user/updateUser",
                type:"POST",
                data:JSON.stringify({id:id,username:username,sex:sex,city:city,age:age}),
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                success:function () {
                    //1.关闭modal框
                    $("#userReviseModal").modal('hide');
                    //2.来到当前页，显示刚才保存的数据
                    to_page(currentPage);

                }
            })

        })
    }

    /**
     * 4.删除用户
     */
    function deleteUser() {
        $(document).on("click",".delete_btn",function () {
            //1.弹出确认删除对话框
            var username=$(this).parents("tr").find("td:eq(2)").text();
            var id=$(this).parents("tr").find("td:eq(1)").text();
            if(confirm("确认删除【"+username+"】吗？")){
               // alert(id);
                //确认，发送ajax请求删除
                $.ajax({
                    url:"/user/deleteUser/"+id,
                    type:"DELETE",
                    success:function (data) {
                        alert(data.msg);
                        to_page(currentPage);
                    }
                })


            }
        })
    }
});
