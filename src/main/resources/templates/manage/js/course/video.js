 var xhr; //异步请求对象
        var ot; //时间
        var oloaded; //大小
        //上传文件方法
        function UpladFile() {
            var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
            if (fileObj.name) {
                $(".el-upload-list").css("display", "block");
                $(".el-upload-list li").css("border", "1px solid #20a0ff");
                $("#videoName").text(fileObj.name);
            } else {
                alert("请选择文件");
            }
        }
        /*点击取消*/
        function del() {
            $("#file").val('');
            $(".el-upload-list").css("display", "none");
        }
        /*点击提交*/
        function sub() {
            var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
            if (fileObj == undefined || fileObj == "") {
                alert("请选择文件");
                return false;
            };

            var url = "{php echo webUrl('goods/iframe.upload')}"; // 接收上传文件的后台地址
            var video_add = new FormData(); // FormData 对象
            video_add.append("file", fileObj); // 文件对象
            /*课程名称*/
            var course_name=$("#course_name").val();
            /*课程类型*/
            var course_type=$("#course_type").val();

            /*课程时长*/
            var course_time=$("#course_time").val();

             var course_age=[];
             var equipment=[];
             var disease=[];
            /*禁忌疾病*/
          //校验禁忌疾病是否为空
          $("[name='dropdown-group05']:checked").each(function (index,element) {
             disease.push($(this).val());
          });

          if (disease.length==0){
             alert("请至少选择一个禁忌疾病");
             return;
          }



            /*运动器材*/
         //校验运动器材是否为空
         $("[name='dropdown-group02']:checked").each(function (index,element) {
           equipment.push($(this).val());
          });

         if (equipment.length==0){
            alert("请至少选择一个运动器材");
             return;
         }
            /*适宜人群*/
            var course_person  =$("#course_person").val();
            /*适宜年龄*/
        //校验运动效果是否为空
        $("[name='dropdown-group03']:checked").each(function (index,element) {
            course_age.push($(this).val());
        });

        if (course_age.length==0){
            alert("请至少选择一个运动效果");
            return;
        }
            /*练习人次*/
            var course_num=$("#course_num").val();

            /*课程介绍*/
            var course_introduce=$("#course_introduce").val();

            /*注意事项*/
            var course_notice=$("#course_notice").val();

            video_add.append("name", course_name); // 文件对象
            video_add.append("type",course_type);
            video_add.append("suitableAgeId",course_age);
            video_add.append("equipmentId",equipment);
            video_add.append("diseaseId",disease);
            video_add.append("genderPreference",course_person);
            video_add.append("time",course_time);


            video_add.append("practiceNumber",course_num);
            video_add.append("courseDescription",course_introduce);
            video_add.append("notice",course_notice);

            $.ajax({
                type:"post",
                url:"course/addCourse.do",
                data:video_add,
                processData:false,
                contentType:false,
                datatype:"json",
                success:function(data){
                    alert("添加成功");
                    console.log(data);
                }

                 })
            xhr = new XMLHttpRequest(); // XMLHttpRequest 对象
            xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
            xhr.onload = uploadComplete; //请求完成
            xhr.onerror = uploadFailed; //请求失败
            xhr.upload.onprogress = progressFunction; //【上传进度调用方法实现】
            xhr.upload.onloadstart = function () { //上传开始执行方法
                ot = new Date().getTime(); //设置上传开始时间
                oloaded = 0; //设置上传开始时，以上传的文件大小为0
            };
            xhr.send(video_add); //开始上传，发送form数据
        }

        //上传进度实现方法，上传过程中会频繁调用该方法
        function progressFunction(evt) {
            // event.total是需要传输的总字节，event.loaded是已经传输的字节。如果event.lengthComputable不为真，则event.total等于0
            if (evt.lengthComputable) {
                $(".el-progress--line").css("display", "block");
                /*进度条显示进度*/
                $(".el-progress-bar__inner").css("width", Math.round(evt.loaded / evt.total * 100) + "%");
                $(".el-progress__text").html(Math.round(evt.loaded / evt.total * 100) + "%");
            }

            var time = document.getElementById("time");
            var nt = new Date().getTime(); //获取当前时间
            var pertime = (nt - ot) / 1000; //计算出上次调用该方法时到现在的时间差，单位为s
            ot = new Date().getTime(); //重新赋值时间，用于下次计算

            var perload = evt.loaded - oloaded; //计算该分段上传的文件大小，单位b
            oloaded = evt.loaded; //重新赋值已上传文件大小，用以下次计算

            //上传速度计算
            var speed = perload / pertime; //单位b/s
            var bspeed = speed;
            var units = 'b/s'; //单位名称
            if (speed / 1024 > 1) {
                speed = speed / 1024;
                units = 'k/s';
            }
            if (speed / 1024 > 1) {
                speed = speed / 1024;
                units = 'M/s';
            }
            speed = speed.toFixed(1);
            //剩余时间
            var resttime = ((evt.total - evt.loaded) / bspeed).toFixed(1);
            time.innerHTML = '上传速度：' + speed + units + ',剩余时间：' + resttime + 's';
            if (bspeed == 0)
                time.innerHTML = '上传已取消';
        }
        //上传成功响应
        function uploadComplete(evt) {
            //服务断接收完文件返回的结果  注意返回的字符串要去掉双引号
            if (evt.target.responseText) {
                var str = "../shiping/" + evt.target.responseText;
                alert("上传成功！");

            } else {
                alert("上传失败");
            }
        }
        //上传失败
        function uploadFailed(evt) {
            alert("上传失败！");
        }

