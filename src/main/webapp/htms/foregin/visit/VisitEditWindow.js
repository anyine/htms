Ext.define('App.htms.foregin.visit.VisitEditWindow', {
	extend : 'Ext.window.Window',
	layout : 'column',
	modal : true,
	resizable : false,
	title : '编辑对象',
	iconCls : 'modify',
	alias : 'widget.visiteditwindow',
	buttonAlign : 'center',
	initComponent : function() {
		var me = this;
		var readOnly = true;
		var fieldStyle = "";// color: #888888;
		if (me.editable == 'edit') {
			readOnly = false;
			fieldStyle = "";
		}
		var form = Ext.create('Ext.form.Panel', {
			width : 640,
			height : 500,
			bodyPadding : 10,
			layout : 'column',
			defaultType : 'textfield',
			defaults : {
				labelWidth : 130,
				margin : '6 0 10 0',
				columnWidth : 1.0,
				readOnly : readOnly,
				fieldStyle : fieldStyle
			},
			items : [ {
				xtype : 'textareafield',
				fieldLabel : '拟邀请单位名称',
				name : 'inviteName',
				allowBlank : false,
				preventScrollbars : true,
				rows : 2
			}, {
				xtype : 'numberfield',
				fieldLabel : '人数',
				name : 'count',
				allowBlank : false
			}, {
				xtype : 'textareafield',
				fieldLabel : '护照号码',
				name : 'passportNum',
				preventScrollbars : true,
				rows : 3
			}, {
				fieldLabel : '来访时间',
				name : 'comeDate'
			}, {
				fieldLabel : '停留',
				name : 'remain'
			}, {
				fieldLabel : '来华事由',
				name : 'reason'
			}, {
				fieldLabel : '申请单位名称',
				name : 'applyName',
				allowBlank : false
			}, {
				fieldLabel : '联系人',
				name : 'linkMan'
			}, {
				fieldLabel : '联系电话',
				name : 'phone'
			}, {
				xtype : 'datefield',
				fieldLabel : '审核日期',
				format : 'Y-m-d',
				name : 'date',
				allowBlank : false
			}, {
				xtype : 'hiddenfield',
				name : 'id'
			} ]
		});
		Ext.applyIf(me, {
			items : [ form ],
			buttons : me.editable == 'edit' ? [ {
				text : '保存',
				listeners : {
					click : me.save
				}
			} ] : [ {
				text : '确定',
				listeners : {
					click : me.ok
				}
			} ].concat([ {
				text : '取消',
				scope : this,
				handler : this.close
			} ])
		});
		me.callParent(arguments);
	},
	ok : function(button) {
		var win = button.up('window');
		win.close();
	},
	save : function(button) {
		var win = button.up('window');
		var formPanel = win.down('form');
		var id = formPanel.down('hiddenfield[name=id]');
		formPanel.submit({
			url : "cn/wizool/htms/servlet/VisitServlet?method=edit",
			method : "POST",
			params : {
				id : id == null ? null : id
			},
			success : function(form, action) {
				var response = action.result;
				if (response.success) {
					win.store.load();
					Ext.example.msg("提示", "操作成功");
					win.close();
				} else {
					Ext.ux.Error.show("提 示", "保存失败！");
				}
			},
			failure : function(form, action) {
				switch (action.failureType) {
				case Ext.form.action.Action.CLIENT_INVALID:
					break;
				case Ext.form.action.Action.CONNECT_FAILURE:
					Ext.ux.Error.show('提示', '网络链接出现问题，请检查后重试！');
					break;
				case Ext.form.action.Action.SERVER_INVALID:
					Ext.ux.Error.show('提示', '程序异常，请联系管理员！');
				}
			}
		});
	}
});