Ext.Loader.setConfig({
	enabled : true,
	paths : {
		'App.htms' : 'htms',
		'Ext.ux' : 'ux'
	}
});

Ext.ns("Ext.ux");

Ext.define("Ext.ux.Error", {
	statics : {
		show : function(title, msg) {
			Ext.Msg.alert("错误：" + title, msg);
		}
	}
});