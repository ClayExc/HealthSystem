	var tds=document.getElementById('tb-calendar-week-body').getElementsByTagName('td');
  	var dates = document.getElementById('tb-calendar-week-body').getElementsByTagName('a');
  	var clen = tds.length;
    var currentFirstDate;//每周第一天
    var today;//今天
    var nowDay;//现在选择的日期
    var currentWeek=new Array(clen);//当前周所有日期对象
    
    $("#tb-calendar-week-body").on('tap','.tb-calendar-week-date',function(){
		changeSelect(this);
	});

	/**
	 * 切换选中状态
	 * @param {Object} 被点击的日期
	 */
	function changeSelect(obj){//
		for(var i = 0;i<clen;i++){
			if(tds[i]==obj)
			{
				tds[i].className = 'tb-calendar-week-date date-selected';
				setNowday(new Date(currentWeek[i]));
			}else{
				tds[i].className="tb-calendar-week-date";
			}
			setTodayColor(i);
      	}
	}

    var addDate= function(date,n){    
    	date.setDate(date.getDate()+n);
        return date;
    };
    var setDate = function(date){  //设置周历日期
    	var day = date.getDay()-1;
    	
        date = addDate(new Date(date.getTime()-24*60*60*1000),day*-1);//每周第一天为周日
        currentFirstDate = date;
        for(var i = 0;i<clen;i++){

        	if(i==0){
        		currentWeek[0]=date.getTime();
        		if(nowDay.getDate()==date.getDate()){
        			tds[i].className += 'date-selected';
        		}else{
        			tds[i].className="tb-calendar-week-date";
        		}
        		dates[i].innerHTML = date.getDate();
        		
        	}else{
        		theDay=addDate(date,1);
        		currentWeek[i]=theDay.getTime();
        		
        		if(nowDay.getDate()==theDay.getDate()){
        			tds[i].className += ' date-selected';
        		}else{
        			tds[i].className="tb-calendar-week-date";
        		}
        		dates[i].innerHTML = theDay.getDate();
        		
        	}
        	
        	setTodayColor(i);
        }        
    };   

	function setNowday(date){//设置当前日期
		$('#today').text(date.getFullYear()
    		+"."+(date.getMonth() + 1)
    		+"."+date.getDate());
    	nowDay=date;
    	changeMyDateEvent(nowDay);
	}
	function setToday(date){//今天日期
		today=date;
	}
	function setTodayColor(i){//设置今天日期的颜色
		if(today.getDate()==dates[i].innerHTML){//日期相同
			if(Math.abs(today.getTime()-nowDay.getTime())<24*60*60*1000*7){//两天之间为间隔超过7天则为当前月份
				tds[i].className+=" tb-calendar-week-date-today";
			}
		}
	}

	function pre(){//上一个月
		setDate(addDate(currentFirstDate,-7)); 
	    setNowday(currentFirstDate);
	    tds[clen-1].className += ' date-selected';
	}
	function next(){//下一个月
		var now=new Date(currentFirstDate.getTime()+24*60*60*1000);
		setNowday(now);
		setDate(addDate(currentFirstDate,7));
	    tds[0].className += ' date-selected';
	}
	
	/**
	 * 日期改变的实现的方法，可以重写
	 * @param {Date} d日期
	 */
	var changeMyDateEvent = function(date){
//		console.log(date)
	};