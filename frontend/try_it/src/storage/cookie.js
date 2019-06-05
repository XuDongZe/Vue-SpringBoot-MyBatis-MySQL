//设置cookie
export function setCookie(key, value, day=3) {
  var exdate = new Date(); //获取时间
  exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * day); //保存的天数，我这里写的是100年
  //字符串拼接cookie
  window.document.cookie = key + "=" + value + ";path=/;expires=" + exdate.toGMTString();
}
//读取cookie
export function getCookie(key) {
  var value = '';
  if (document.cookie.length > 0) {
    var arr = document.cookie.split('; '); //这里显示的格式需要切割一下自己可输出看下
    for (var i = 0; i < arr.length; i++) {
      var arr2 = arr[i].split('='); //再次切割
      //判断查找相对应的值
      if (arr2[0] == key) {
        value = arr2[1];
        break;
      }
    }
    return value;
  }
}

export function clearCookie(key) {
  setCookie(key, "", -1)
}
