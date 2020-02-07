//页面加载完成后，生成验证码
$(function () {
    $("#yzmImg").attr("src", '/preventpneumonia/validateCodeServlet?'+ new Date().getTime());
});

//点击提交按钮，开始查询小区信息
$("#submit").on("click", function () {
    var cityName = $("#cityName").val();
    //发送ajax请求
    $.ajax({
        url: "/preventpneumonia/district",
        data: {
            "cityName": cityName
        },
        type: "post",
        dataType: "json",
        async: true,
        cache: false,
        success: function(result) {
            debugger;
            // 增加一个标准返回结果，先判断returnCode是否是1，再进行处理逻辑
            $("#resultPool").empty();
            var tableHtml = "<h><mark>"+ cityName +"</mark> 新型肺炎确诊患者小区查询结果：</h><br/><br/>";
            if(result.returnCode == "1"){
                var data = result.beans;
                if(data.length == 0){
                    tableHtml += '<p>恭喜你，当前地区没有确诊病例</p>';
                } else {
                    tableHtml += '<table class="table table-hover table-striped">\n' +
                        '    <thead>\n' +
                        '      <tr>\n' +
                        '        <th >小区名</th>\n' +
                        // '        <th >确诊情况</th>\n' +
                        '        <th class="text-nowrap">地区</th>\n' +
                        '        <th class="text-nowrap">更新时间</th>\n' +
                        '      </tr>\n' +
                        '    </thead>\n' +
                        '    <tbody>';

                    data.forEach(function (item, index, array) {
                        tableHtml += '<div class="row">\n' +
                            '<tr>\n' +
                            '        <td class="">'+ item.patient_district_name +'</td>\n' +
                            // '        <td>'+ item.patient_desc +'</td>\n' +
                            '        <td class="text-nowrap">'+ item.city_name +'</td>\n' +
                            '        <td class="text-nowrap">'+ item.EV_MODIFY_TIME.split(" ")[0] +'</td>\n' +
                            '</tr>';
                    })
                    tableHtml += '</tbody>\n' +
                        '</table>';
                }
                $("#resultPool").append(tableHtml);
            }else {
                console.log("district查询异常");
            }

            // 使用div流式布局分界效果不好
           /* var tableHtml = "<p>"+ cityName +"新型肺炎确诊患者小区查询结果：</p>"+
                '<div class="row">\n' +
                '  <div class=" col text-primary">小区名</div>\n' +
                // '  <div class=" col-sm-3 col-md-3">确诊情况</div>\n' +
                '  <div class=" col text-success">地区</div>\n' +
                '  <div class=" col text-info">更新时间</div>\n' +
                '</div>';

            result.forEach(function (item, index, array) {
                tableHtml += '<div class="row">\n' +
                    '  <div class="col text-primary">'+ item.patient_district_name +'</div>\n' +
                    // '  <div class=" col-sm-3 col-md-3">'+ item.patient_desc +'</div>\n' +
                    '  <div class="col text-success">'+ item.city_name +'</div>\n' +
                    '  <div class="col text-info">'+ item.EV_MODIFY_TIME +'</div>\n' +
                    '</div>';
            })*/


        },
        error: function (error) {
            cosole.log("查询异常" + error);
        }
    });
});

/*$.ajax({
    type: "GET",
    url: "test.json",
    data: {username:$("#username").val(), content:$("#content").val()},
    success: function(data){
        $('#resText').empty();   //清空resText里面的所有内容
        var html = '';
        $.each(data, function(commentIndex, comment){
            html += '<div class="comment"><h6>' + comment['username']
                + ':</h6><p class="para"' + comment['content']
                + '</p></div>';
        });
        $('#resText').html(html);
    }
});*/

