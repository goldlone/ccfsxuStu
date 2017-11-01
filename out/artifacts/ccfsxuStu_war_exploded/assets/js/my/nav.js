// 左侧导航栏点击
function navClick(elem) {
var arr = document.getElementsByClassName('active');
for(var i=0; i<arr.length; i++) {
  arr[i].className = "";
}
	elem.className = "active";
}