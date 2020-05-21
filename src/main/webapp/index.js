function submit(){
   	var singlePrice=$("#singlePrice").val();
   	var num=$("#num").val();
	  var flag=$("#flag").val();
    $(".res_area").show();
	//print("test");
   	$.ajax({
   		url: 'https://rammsteinlp.cn/STHomeWork02/client/getPrice',
   		type: 'POST',
   		data: {
			singlePrice:singlePrice,
			num:num,
			flag:flag
   		},
   		success:function(data){
   		    if(data.SUCCESS===1){
   		    	console.log(data.cart);
            var html="<thead><th>"+"购买详情"+"</th></thead>";
				    var cart = data.cart;
				    for(var k in cart) {
					     console.log(cart[k]);
               html += "<tr><td>"+cart[k]+"</td></tr>";
				    }
            $(".res_list").html(html);
          }else{
            alert("请正确输入金额!")
          }
   		}
   	})

}

function reset(){
	$(".res_area").show();
	//print("test");
	$.ajax({
		url: 'https://rammsteinlp.cn/STHomeWork02/client/reset',
		type: 'POST',
		data: {

		},
		success:function(data){
			if(data.SUCCESS===1){
				var html="<thead><th>"+"购买详情"+"</th></thead>";
				$(".res_list").html(html);
			}else{
				alert("操作失败!")
			}
		}
	})

}