let index = {
    init: function(){
        $("#btn-save").on("click",()=> { // function(){} 이걸 안쓰는 이유는  ()=>{} 이걸써서 this를 바인디하기 위해서 사용함
            this.save();
        });
    },

    save: function(){
//        alert('user의 save 함수 호출됨');
    let data = {
        username : $("username").val(),
        password : $("password").val(),
        email : $("email").val()
    };

//    console.log(data);
    // ajax 호출시 default가 비동기 호출
    // 통신을 통해서 3개의 데이터를 json으로 변경하여 insert 요청!!
    $.ajax({
        type: "POST",
        url: "/api/user",
        data: JSON.stringify(data), // http body 데이터를
        contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
        dataType:"json" // 요청을 서버로해서 응답이 왔을때, 기본적으로 모든것이 문자열임. 생긴게 Json이면
//        console.log(resp);
    // 회원가입 수행 요청
    }).done(function(resp){
//        alert("회원가입이 완료 되었습니다.");
        console.log(resp);

        location.href = "/";
    }).fail(function(error){
        alert(JSON.stringify(error));
    });
    }
}

index.init();