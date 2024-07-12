function handleSubmitAllClick() { // 일반데이터 객체로 전환하는 과정
	const forms = document.querySelectorAll("form")
	const formData1 = new FormData(forms[0]); // form 2개 
	const formData2 = new FormData(forms[1]);
	
	let reqData = {}
	
	/*
		formData1 = {
			username: "admin",
			password: "1234"
		}
		변환시
		entries = [ 
			["username" , "admin"],
			 ["password" , "1234"]
		]
	 */
	
	for(let entry of formData1.entries()){
		const [ key, value ] = entry;
		reqData = {
			...reqData,
			[key]:value // ["username" , "admin"],["password" , "1234"]
		}
	}
	
	
	/*
		formData2 = {
			chk: "chk1",
			chk: "chl2",
			rdo: "rdo1"
		}
		변환시
		entries = [ 
			["chk" , "chk1"],
			 ["chk" , "chl2"],
			 ["rdo" , "rdo1"]
		]
	 */
	 
	let duplicatedKeys = []; // checkbox말고 다른 것들도 중복되는게 있을수 있기때문에 배열로 만듬 (2개이상의 같은 키가 담기기위한 공간)
	let keyCount = {};
	
	for(let key of formData2.keys()){
		if(Object.keys(keyCount).includes(key)){ // 현재의 키값과 같다면 기존의 키값을 1더해줘서 중복을피함
		// Object 내장 객체
			keyCount = {
				...keyCount,
				[key] : keyCount[key] + 1
			}	
			continue;
		}
		keyCount = {
			...keyCount,
			[key] : 1
		}
	}
	
	for(let key of Object.keys(keyCount)){
		if(keyCount[key] > 1){
			duplicatedKeys = [ ...duplicatedKeys, key ]; // key에 chk값
		}
	}
	
	console.log(keyCount);
	console.log(duplicatedKeys);	
	
	for(let entry of formData2.entries()){ // entry [ key , value ] entries 배열

		const [ key, value ] = entry;
		if(duplicatedKeys.includes(key)) {
			reqData = {
				...reqData,
				[key]:[... (!reqData[key] ? [] : reqData[key]) , value]
				// 처음엔 reqData[key](배열) = undefind => reqData를 빈배열로 만들어서 스프레드 해줌
			}
			continue;
		}
		reqData = {
			...reqData,
			[key] : value
		}// 페이지가 이동해서 데이터를 가져올수없음
	}
	
	console.log(reqData);
	
	const queryString = new URLSearchParams(reqData).toString();
	// 쿼리 스트링 바꾸는 법 
	
	fetch(`http://localhost:8080/dvd/form?${queryString}`)
	.then(response => {
		//console.log(response.text()); response.text() -> promise
		response.text()
		.then(data => { //수정 해야댐
			const body = document.querySelector("body");
			body.innerHTML += `<h1>${data}</h1>`
			console.log(data);
		}) // 서버사이드렌더링에서 css 접합
	});
	
}