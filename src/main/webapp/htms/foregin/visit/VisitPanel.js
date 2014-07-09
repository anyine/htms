Ext
		.define(
				'App.htms.foregin.visit.VisitPanel',
				{
					extend : 'Ext.panel.Panel',
					layout : 'fit',
					alias : 'widget.visitgridpanel',
					requires : [ 'App.htms.foregin.visit.VisitListGridTemplate' ],
					initComponent : function() {
						var me = this;
						var items = [];
						// 参数 ????????????
						me.params = Ext.decode(me.classType);

						var str = [ '护照号码', '申请单位名称', '审核日期' ];
						var store = Ext.create('Ext.data.Store', {
							fields : [ 'id', 'num', 'inviteName', 'count',
									'passportNum', 'comeDate', 'remain',
									'reason', 'applyName', 'linkMan', 'phone',
									'date' ],
							pageSize : 30,
							proxy : {
								type : 'rest',
								url : 'cn/wizool/htms/servlet/VisitServlet',
								actionMethods : 'post',
								reader : {
									type : 'json',
									root : 'root',
									totalProperty : 'total',
									successProperty : 'success'
								}
							}
						});
						store.on('beforeload', function(thiz) {
							thiz.proxy.extraParams = {
								passportNum : me.down(
										'textfield[fieldLabel=' + str[0] + ']')
										.getValue(),
								applyName : me.down(
										'textfield[fieldLabel=' + str[1] + ']')
										.getValue(),
								year : me.params.year,
								beginDate : me.down(
										'textfield[fieldLabel=' + str[2] + ']')
										.getValue(),
								endDate : me.down('textfield[fieldLabel=至]')
										.getValue()
							}
						});
						var getWidth = function(str) {
							var ct = 1;
							for ( var i = 0; i < str.length; i++) {
								if (str.charCodeAt(i) < 256) {
									ct++;
								} else {
									ct += 2;
								}
							}
							return ct * 7;
						};
						var search = [ {
							xtype : 'textfield',
							labelWidth : getWidth(str[0]),
							width : getWidth(str[0]) + 90,
							fieldLabel : str[0],
							listeners : {
								specialkey : function(field, e) {
									if (e.getKey() == e.ENTER) {
										store.load();
									}
								}
							}
						}, {
							xtype : 'textfield',
							labelWidth : getWidth(str[1]),
							width : getWidth(str[1]) + 90,
							fieldLabel : str[1],
							listeners : {
								specialkey : function(field, e) {
									if (e.getKey() == e.ENTER) {
										store.load();
									}
								}
							}
						}, {
							xtype : 'datefield',
							labelWidth : getWidth(str[2]),
							width : getWidth(str[2]) + 135,
							format : 'Y-m-d',
							editable : false,
							emptyText : '格式：2012-01-01',
							fieldLabel : str[2],
							listeners : {
								specialkey : function(field, e) {
									store.load();
								}
							}
						}, {
							xtype : 'datefield',
							labelWidth : 30,
							width : 160,
							format : 'Y-m-d',
							editable : false,
							emptyText : '格式：2012-01-01',
							fieldLabel : '至',
							listeners : {
								specialkey : function(field, e) {
									store.load();
								}
							}
						}, {
							xtype : 'button',
							text : '查询',
							iconCls : 'query',
							handler : function() {
								store.load();
							}
						} ];
						var btn = [ '->', {
							xtype : 'button',
							text : '新增',
							iconCls : 'create',
							listeners : {
								click : me.create
							}
						}, {
							xtype : 'button',
							text : '删除',
							iconCls : 'delete',
							listeners : {
								click : me.deleteVisit
							}
						}, {
							xtype : 'button',
							text : '修改',
							iconCls : 'modify',
							listeners : {
								click : me.update
							}
						}, "-", {
							xtype : 'button',
							text : '导出Excel',
							iconCls : 'modify',
							listeners : {
								click : me.sendExcel
							}
						} ];

						if (me.params.type == 'view') {// 查询视图
							items.push(search);
						}
						if (me.params.type == 'edit') {// 编辑视图
							items.push(search, btn);
						}

						Ext.applyIf(me, {
							items : [ Ext.widget('visitListGrid', {
								name : 'gridPanel',
								store : store,
								dockedItems : [ {
									xtype : 'toolbar',
									dock : 'top',
									items : items
								}, {
									xtype : 'pagingtoolbar',
									store : store,
									afterPageText : '页 共{0}页',
									beforePageText : '当前第',
									displayInfo : true,
									displayMsg : '第{0}条-第{1}条 共{2}条',
									emptyMsg : '没有可以显示的数据',
									firstText : '首页',
									lastText : '尾页',
									prevText : '上一页',
									nextText : '下一页',
									refreshText : '刷新',
									dock : 'bottom'
								} ],
								listeners : {
									itemdblclick : me.showUpdate
								}
							}) ]
						});
						me.callParent(arguments);
					},
					create : function(button) {
						var win = button.up('panel');
						this.createVisitWindow = Ext
								.create(
										'App.htms.foregin.visit.VisitEditWindow',
										{
											iconCls : 'create',
											title : '新增',
											editable : this.up("panel").up(
													"panel").params.type,
											store : win.getStore()
										});

						this.createVisitWindow.show();
					},
					deleteVisit : function(button) {
						var me = this;
						var model = button.up('gridpanel').getSelectionModel()
								.getSelection()[0];
						if (model) {
							Ext.Msg
									.confirm(
											'提示窗口？',
											'您将删除此对象类型？',
											function(btn) {
												if (btn == 'yes') {
													Ext.Ajax
															.request({
																url : 'cn/wizool/htms/servlet/VisitServlet?method=delete',
																params : {
																	ids : function() {
																		var grid = button
																				.up('gridpanel');
																		var ids = grid
																				.getSelectionModel()
																				.getSelection();
																		var strArray = [];
																		for ( var i = 0; i < ids.length; i++) {
																			strArray
																					.push(ids[i]
																							.get('id'));
																		}
																		return strArray;
																	}()
																},
																success : function(
																		response) {
																	var text = Ext
																			.decode(response.responseText);
																	var flag = text.success;
																	if (flag) {
																		Ext.example
																				.msg(
																						"提示",
																						"操作成功");
																		// 数据重新加载
																		button
																				.up(
																						'gridpanel')
																				.getStore()
																				.load();
																	} else {
																		Ext.ux.Error
																				.show(
																						"提 示",
																						text.message);
																	}
																}
															});
												}
											}, this);
						} else {
							Ext.ux.Error.show("提 示", "请选中一项");
						}
					},
					showUpdate : function() {
						var win = this.up("panel").down('panel');
						var model = win.getSelectionModel().getSelection();
						this.createVisitWindow = Ext.create(
								'App.htms.foregin.visit.VisitEditWindow', {
									iconCls : 'modify',
									title : '修改',
									editable : this.up("panel").params.type,
									store : win.getStore()
								});
						var form = this.createVisitWindow.down('form');
						form.loadRecord(model[0]);
						this.createVisitWindow.show();
					},
					update : function(button) {
						var win = button.up('panel');
						var model = button.up('gridpanel').getSelectionModel()
								.getSelection();
						if (model.length == 1) {
							this.createVisitWindow = Ext
									.create(
											'App.htms.foregin.visit.VisitEditWindow',
											{
												iconCls : 'modify',
												title : '修改',
												editable : this.up("panel").up(
														"panel").params.type,
												store : win.getStore()
											});
							var form = this.createVisitWindow.down('form');
							form.loadRecord(model[0]);
							this.createVisitWindow.show();
						} else {
							Ext.example.msg("提 示", "请选中一项！");
						}
					}
				});
