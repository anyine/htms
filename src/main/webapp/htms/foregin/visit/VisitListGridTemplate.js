Ext.define('App.htms.foregin.visit.VisitListGridTemplate', {
	extend : 'Ext.grid.Panel',
	layout : 'fit',
	alias : 'widget.visitListGrid',
	forceFit : true,// 自动填充
	defaults : {
		align : 'center'
	},
	initComponent : function() {
		var me = this;
		me.columns = [ {
			text : '序号',
			dataIndex : 'num'
		}, {
			text : '拟邀请单位名称',
			dataIndex : 'inviteName'
		}, {
			text : '人数',
			dataIndex : 'count'
		}, {
			text : '护照号码',
			dataIndex : 'passportNum'
		}, {
			text : '来访时间',
			dataIndex : 'comeDate'
		}, {
			text : '停留',
			dataIndex : 'remain'
		}, {
			text : '来华事由',
			dataIndex : 'reason'
		}, {
			text : '申请单位名称',
			dataIndex : 'applyName'
		}, {
			text : '联系人',
			dataIndex : 'linkMan'
		}, {
			text : '联系电话',
			dataIndex : 'phone'
		}, {
			text : '审核日期',
			dataIndex : 'date'
		} ];
		me.selModel = Ext.create('Ext.selection.CheckboxModel');
		me.callParent(arguments);
	}
});
