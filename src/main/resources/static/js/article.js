//삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
  deleteButton.addEventListener('click', event => {
    let id = document.getElementById('article-id').value;
    fetch(`/api/articles/${id}`, {
      method: 'DELETE'
    })
    .then(() => {
      alert('삭제가 완료되었습니다.');
      location.replace('/articles');
    });
  });
}

//HTML에서 id를 delete-btn으로 설정한 엘리먼트를 찾아 클릭 이벤트가 발생
//-> fetch() 메서드를 통해 DELETE요청을 보내는 역할
//fetch() 메서드에 이어지는 then()메서드 : fetch()가 잘 완료되면 연이어 실행되는 메서드
//alert() 메서드 : then() 메서드가 실행되는 시점에 웹 브라우저 화면으로 삭제가 완료되었음을 알림
//location.replace() : 실행시 사용자의 웹 브라우저 화면을 현재 주소를 기반해 옮겨줌


//수정 기능
//1. id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
//2. 클릭이벤트 감지 -> 수정 API요청 -> id가 title, content인 엘리먼트 값을 가져옴
 modifyButton.addEventListener('click', event => {
    let params = new URLSearchParams(location.search);
    let id = params.get('id');

    fetch(`/api/articles/${id}`, {     //수정 API로 PUT요청
      method: 'PUT',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        title: document.getElementById('title').value,
        content: document.getElementById('content').value
      })
    })
    .then(()=>{     //then 메서드로 마무리
      alert('수정이 완료되었습니다.');
      location.replace(`/articles/${id}`);
    });
 });
}

//등록 기능
//1. id가 create-btn인 엘리먼트
const createButton = document.getElementById("create-btn");
if (createButton) {
//2. 클릭 이벤트가 감지되면 title, content인 엘리먼트 값을 가져와
//fetch() 메서드를 통해 생성 API로 POST 요청
    createButton.addEventListener("click", (event) => {
      fetch("/api/articles", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          title: document.getElementById("title").value,
          content: document.getElementById("content").value,
        }),
      }).then(() => {
        alert("등록 완료되었습니다.");
      });
    });
}
















