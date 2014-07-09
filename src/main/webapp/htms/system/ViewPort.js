Ext.onReady(function() {
	Ext.Ajax.request({
		url : '../system/content.js',
		success : function(response) {
			var con = Ext.decode(response.responseText);
			var tabpanel = Ext.create('Ext.tab.Panel',{
			    activeTab: 0,
			    width : 230
			});
			for ( var i = 0; i < con[0].children.length; i++) {
				var items = [];
				for ( var j = 0 ; j < con[0].children[i].children.length; j++) {
					var text = con[0].children[i].children[j].text;
					var tree = con[0].children[i].children[j].subtree;
					if ( tree ) {
						items.push({
							xtype : 'treepanel',
							title : text,
							cls : 'text_cls',
							layout : 'fit',
							lines : false,
							root : {
								expanded : true,
								children : tree || []
							},
							rootVisible : false
						});
					}
				}
				var panel = Ext.create('Ext.panel.Panel',{
					closable : false,
					title : con[0].children[i].text,
					cls : 'font-size : 20px',
					layout : {
						type : 'accordion',
						hideCollapseTool : true
					},
					activeTab : 0,
					items : [items]
				});
				tabpanel.add(panel);
			}
			var tabpanel_right = Ext.create('Ext.tab.Panel',{
				activeTab : 0,
				items : [{
					xtype : 'panel',
					title : '首页',
					id : 'index',
					closable : false,
					autoScroll : true,
					html : '<img src="background/background_index.jpg" style="width:100%;height:100%;"></img>'
				}]
			});
			
			var viewport = Ext.create('Ext.container.Viewport', {
				layout : 'border',
				items : [ {
					region : 'north',
					height : 104,
					autoHeight : true,
					margins : '0 0 5 0',
					html : '<img src="background/head_center.jpg" style="width:100%;height:104px;position:absolute;z-index:1;">'+
					'<img src="background/head_left.jpg" style="position:absolute;z-index:2;"></img>'+
					'<img src="background/head_right.jpg" style="position:absolute;right:0px;z-index:2;"></img>'
				}, {
					region : 'west',
					xtype : 'panel',
					layout : 'fit',
					title : con[0].text,
					items : [tabpanel],
					collapsible : true,
					split : true
				}, {
					region : 'center',
					items : [tabpanel_right]
				} ]
			});
		}
	});
});
