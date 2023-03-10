var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() { 
    var socket = new SockJS('/chatServer'); //서버접속
    stompClient = Stomp.over(socket); //웹접속을 바꿈
    stompClient.connect({}, function (frame) { //커넥트
        setConnected(true); //커넥트하고나면 실행할 콜백함수
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat', function (greeting) { //구독신청  //컨트롤러에서 리턴값이 들어오면
            showGreeting(JSON.parse(greeting.body).content); //보내준다.
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()})); //app은 일반 매핑으로 가는거 아님 메세지매핑으로!!
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault(); //기본기능 막음
    });
    $( "#connect" ).click(function() { connect(); });  //각각의 함수는 위에 다 있음
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});