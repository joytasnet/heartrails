window.onload=function(){
	var tds=document.getElementsByTagName("td");
	for(var i=0;i<tds.length;i++){
		tds[i].addEventListener("click",function(){
			this.classList.toggle("clicked");
		});
	}

};