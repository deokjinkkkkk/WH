$(document).ready(function(){
		getComList() 
	})
	
	$("#butn").click(function(){ //댓글 등록
		let comContent = $("#comContent").val(); //댓글내용
		let comId =$("#id").val(); //회원아이디
		let comOrder = 1 //댓글순서
		let comBlock = 1 //댓글계층
		let comFlag = $("#comFlag").val();  //게시물구분
		let comBoardCode = $("#comBoardCode").val(); //게시물번호
	
		let comLock = $("#lockChk").val() //비밀댓글
		if($("#lockChk").is(':checked') == true){
			comLock = 0
		}
		if(comId == ""){
			alert("로그인 후 이용하세요")
			return;
		}; // 로그인 안했을시 댓글 작성 x
		
		$.ajax({
			url:"/comInsert",
			type:"POST",
			data: JSON.stringify(
				{
					"block" : comBlock, //댓글계층
					"comContent" :comContent,
					"id" :comId,
					"comLock" : comLock,
					"comOrder" : comOrder,
					"comFlag" : comFlag, //게시물 구분
					"comBoardCode" : comBoardCode// 게시물 번호
				}
			),
			contentType:'application/json',
			success : function(data){
				console.log("댓글등록")
				$("#comContent").val("")
				console.log("====")
				getComList()
			},
			error:function(){
				alert('등록실패');
			}
		});
	});
	
	function getComList(){ //댓글 리스트
		let str = ""
		id = $("#id").val()
		author = $("#perm").val()
		perm = author.slice(1,-1)
		console.log(perm)
		console.log(id)
		    str += '<div class="title-g margin-30px-bottom">'
			str +='<h3>댓글</h3>'
			str +='</div>'
		let comArea =$(".comments-area");
		$.ajax({
			url:"/comGetList",
			success : function(result){
				$(".title-g margin-30px-bottom").remove()
				$(result).each(function(index,item){
					if(item.block == 1){
						str += "<div class='comment-box' id='"+item.comCode+"'>"
						str += '<div class="author-thumb">'
						str += '<img src="img/blog/01.png" alt="" class="rounded-circle width-85px width-100px" /> </div>'
						str += '<div class="comment-info">'
						str += '<h6> <a href="javascript:void(0);" >'+item.name+' </a></h6>'
						if(item.comLock != 0 || item.id == id || perm == "ADMIN"){
						str	+= '<p>'+item.comContent+'</p>'
						}else{
						str += 	'<p>비밀 댓글입니다.</p>'
						}
						if( perm == "ADMIN" || item.id == id){
						str += '<button type="button" class="btnDel" onclick="comDelete()">삭제</button>'
						str += '<button type="button" class="btnDel" onclick="comUpdateForm()">수정</button>'
						}
						str += '<input type="hidden" name="comGroup" id="comGroup" value="'+item.comGroup+'">'
					}else{
						str += "<div class='comment-box' id='"+item.comCode+"' style='margin-left: 100px;'>"
						str += '<div class="author-thumb">'
						str += '<img src="img/blog/01.png" alt="" class="rounded-circle width-85px width-100px" /> </div>'
						str += '<div class="comment-info"> '
						str += '<h6> <a href="javascript:void(0);" >'+item.name+'</a></h6>'
						if(item.comLock != '0' || item.id == id || perm == "ADMIN"){
						str	+= '<p>'+item.comContent+'</p>'
						}else{
						str += 	'<p>비밀 댓글입니다.</p>'
						}
						if(perm == "ADMIN" || item.id == id){
						str += '<button type="button" class="btnDel" onclick="reComDelete()">삭제</button>'
						str += '<button type="button" class="btnDel" onclick="comUpdateForm()">수정</button>'
						}
					}
				//if(item.block == 1){
					str += '<div class="reply">'
					str += '<h6>'+item.comDate+'</h6>'
					str += '<a href="javascript:void(0);" onclick="comReply()"> <i class="fa fa-reply" aria-hidden="true"></i> 답글</a>' 
					str += '</div> </div> </div>'
					str += '<div class="rep-form" id="'+item.comCode+'" style="display:none">'
					str += 	'<form id="reply-form " method="post">'
					str +=  '<div class="controls">'
					str +=  '<div class="row">'
					str += '<div class="col-md-12 form-group">'
					str += '<textarea id="comRep" name="comContent" class="comRep"'
					str += 'placeholder="답글" rows="2" required="required">#'+item.id+'</textarea>'
					str += '<input type="hidden" name="comGroup" id="comGroup" value="'+item.comGroup+'">'
					str += '<button type="button" id="reButn" class="reButn" onclick="comReplyInsert()">답글등록</button>'
					str += '<label><input type="checkbox" class="secChk" value="1">비밀</label>'
					str +=	'</div>'
					str += '<div class="col-md-12">'
					str += '<input type="hidden" name="comGroup" value="'+item.comGroup+'"'
					str += '</div></div></div></form></div></div>'
					//}
				})
				comArea.html(str);
			}
		})
	}
	
	function reComDelete(){ //댓글 삭제
		let comBox = $(event.target).closest(".comment-box")
		let reply = comBox.next()
		console.log($(event.target).text())
		let comGroup = $(event.target).find("#comGroup").val()
		let comOrder = 1
		let comCode = comBox.attr('id')
		console.log(comCode)
		//alert 후 true 일경우 삭제 누른 댓글 삭제
		//false 후 
		$.ajax({
			url:"/reComDelete",
			type:"delete",
			data:{
							
							
					 comCode
							//게시물 번호
						    //댓글 비밀여부
						}
			,
			success : function(data){
				console.log(data)
				getComList()
			}
		})
	}
	
	function comDelete(){ //댓글 삭제
		let comBox = $(event.target).closest(".comment-box")
		let reply = comBox.next()
		let comGroup = $(event.target).next().next().val()
		//alert 후 true 일경우 삭제 누른 댓글 삭제
		//false 후 
		$.ajax({
			url:"/comDelete",
			type:"delete",
			data:{	
				comGroup		
			}	   
			,
			success : function(data){
				console.log(data)
				getComList()
			}
		})
	}
	
	function comReply(){ //답글창 on,off
		let comBox = $(event.target).closest(".comment-box")
		let recom = comBox.next()
		if(recom.is(':visible') != true){	
			recom.show();
		}else{
			recom.hide();
		}
		
	}
	//대댓글 댓글 그룹,  
	function comReplyInsert(){
		let rep = $(event.target).closest(".rep-form").attr('id')
		let reContent =$(event.target).prev().prev().val()
		let comId =$("#id").val();
		let comGroup = $(event.target).prev().val()
		let comLock = $(event.target).next().val() //비밀댓글
		let comBlock = '2'
		let comFlag = $("#comFlag").val();
		let comBoardCode = $("#comBoardCode").val();
		if($(event.target).next().is(':checked') == true){
			comLock = 0
		}
		$.ajax({
			url:"/reComInsert",
			type:"POST",
			data: JSON.stringify(
				{
					"comContent" :reContent,
					"id" :comId,
					"comGroup" : comGroup,
					"comLock" : comLock,
					"block" : comBlock,
					"comFlag" : comFlag, //게시물 구분
					"comBoardCode" : comBoardCode// 게시물 번호
					//게시물 번호
				   //댓글 비밀여부
				}
			),
			contentType:'application/json',
			success : function(data){
				console.log("답글등록")
				$(event.target).prev().val("")
				getComList()
			},
			error:function(){
				alert('등록실패');
			}
		});
	}
	$(".btnUp").on("click")
	
	 function comUpdate(){
		let comContent =$(event.target).prev().prev().val();
		let comCode =$(event.target).closest(".comment-box").attr('id');
		$.ajax({
			url:"/comUpdate",
			type:"POST",
			data: JSON.stringify(
				{
					"comContent" :comContent,
					"comCode" : comCode,
				}
			),
			contentType:'application/json',
			success : function(data){
				console.log("답글등록")
				getComList()
			},
			error:function(){
				alert('등록실패');
			}
		});
	}
	
	function comUpdateForm(){
		
		let pTag = $(event.target).prev().prev()
		let content = pTag.text()
		console.log(content)
		let delBtn =  $(event.target).prev()
		let upBtn = $(event.target)
		
		$( pTag ).replaceWith('<textarea id="coUpText" name="comContent" class="coUpText">'+content+'');
		$(delBtn).replaceWith('<button type="button" class="btnRet" onclick="resetBtn()">취소</button>')
		let up = $(upBtn).replaceWith('<button type="button" class="btnUp" onclick="comUpdate()">저장</button>')
	}
	
	function resetBtn(){
		getComList()
	}