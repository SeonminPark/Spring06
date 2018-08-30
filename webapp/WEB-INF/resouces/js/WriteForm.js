/**
 * 
 */
	function inputCheck(){
		var writer = document.getElementById('writer');
		if(writer.value == ''){
			alert('작성자를 입력하세요.')
			return false;
		}
		var title = document.getElementById('title');
		if(title.value == ''){
			alert('제목을 입력하세요.')
			return false;
		}
		var cont = document.getElementById('cont');
		if(cont.value == ''){
			alert('내용을 입력하세요.');
			return false;
		}
		return true;
	}
	window.onload = function(){
		var frm = document.getElementById('frmWrite');
		frm.onsubmit = function(){
			var result = inputCheck();
			return result;
		}	
		
		var btn4 = document.getElementById('btn4');
		btn4.onclick = function(){
			//frm.action = '/Board/List?menu_id=${menu_id}';
		 /* if(btn4.value=='A')
				frm.action = '/Board/List';
			if(btn4.value=='B')
				frm.action = '/Board/WriteForm'; */
			var result = inputCheck();
			if(result == true)
				frm.submit(); //submit 이벤트가 발생되지 않고 submit 된다.
		}
		
		var btn5 = document.getElementById('btn5');
		btn5.onclick = function(e){
			alert('sss');
			e.preventDefault(); //기본이벤트 제거 
			
 			frm.action = '/Board/List?menu_id=MENU01';
			frm.submit();	
		}
	}