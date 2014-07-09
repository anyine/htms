Ext.onReady(function() {

	var login_btn = Ext.get('login_btn');
	var reset_btn = Ext.get('reset_btn');
	var pwd = Ext.get('textfield2');

	var onClick = function(ev, target) {
		var name = document.getElementById('textfield').value;
		var password = document.getElementById('textfield2').value;
		if (name == 'admin' && password == 'admin') {
			location.href = 'content.html';
		} else {
			Ext.ux.Error.show('提示', '用户名或密码错误！');
		}
	};
	
	login_btn.on('click', onClick, this);
	new Ext.KeyMap(pwd, {
		key : Ext.EventObject.ENTER,
		fn : onClick,
		scope : login_btn
	});
	reset_btn.on('click', function(button) {
		document.getElementById('textfield').value = "";
		document.getElementById('textfield2').value = "";
	});
});

// Ext.Ajax.request({
// url : 'cn/wizool/htms/servlet/LoginServlet',
// method : "POST",
// params : {
// username : document.getElementById('textfield').value,
// password : document.getElementById('textfield2').value
// },
// success : function(from, action) {
// var flag = Ext.decode(from.responseText);
// if (flag.success) {
// location.href = 'content.html';
// } else {
// Ext.ux.Error.show('提示', '用户名或密码错误！');
// }
// },
// failure : function(form, action) {
// Ext.ux.Error.show('提示', '用户名或密码错误 ');
// }
// });
