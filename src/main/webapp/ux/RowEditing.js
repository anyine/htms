     /** 
     * @class Ext.ux.grid.plugin.RowEditing 
     * @extends Ext.grid.plugin.RowEditing 
     * @xtype ux.rowediting 
     *  
     * 对Ext原有的RowEditing插件增加新功能.<br/> 
     * Improve Ext.ux.grid.plugin.RowEditing,add some usefull features.<br/> 
     *  
     * @author      tz <atian25@qq.com> <br/> 
     * @date        2011-08-20  <br/> 
     * @version     1.4   <br/> 
     * @blog        http://atian25.iteye.com    <br/> 
     * @forum       http://www.sencha.com/forum/showthread.php?131482-Ext.ux.RowEditing-add-some-usefull-features<br/> 
     * 
     */  
    Ext.define('Ext.ux.RowEditing', {  
        extend: 'Ext.grid.plugin.RowEditing',  
        alias: 'plugin.ux.rowediting',   
          
        /** 
         * 是否添加记录在当前位置<br/> 
         * whether add record at current rowIndex.<br/> 
         * see {@link #cfg-addPosition} 
         * @cfg {Boolean} 
         */  
        addInPlace: false,  
          
        /** 
         * 添加记录的具体位置 <br/> 
         * * 当addInPlace为true时,该参数<=0代表当前位置之前,否则代表当前位置之后<br/> 
         * * 当addInPlace为false时,代表具体的rowIndex,-1则为最后<br/> 
         * Special rowIndex of added record.<br/> 
         * * when {@link #cfg-addInPlace} is true, this cfg means before(<=0) or after(>0) current rowIndex.<br/> 
         * * when {@link #cfg-addInPlace} is false, this cfg means the exact rowIndex.-1 means at the end. 
         * @cfg {Number} 
         */  
        addPosition: 0,  
          
        /** 
         * 是否点击触发事件,0代表不触发,1代表单击,2代表双击,默认为双击.<br/> 
         * The number of clicks on a grid required to display the editor (disable:0,click:1,dblclick:2) 
         * @cfg {Number} 
         */  
        clicksToEdit:2,  
          
        /** 
         * 是否在取消编辑的时候自动删除添加的记录 
         * if true, auto remove phantom record on cancel,default is true. 
         * @cfg {Boolean} 
         */  
        autoRecoverOnCancel: true,  
          
        /** 
         * adding flag 
         * @private 
         * @type Boolean 
         */  
        adding: false,  
          
        autoCancel:true,  
          
        /** 
         * when add record, hide error tooltip for the first time 
         * @private 
         * @type Boolean 
         */  
        hideTooltipOnAdd: true,  
          
        /** 
         * register canceledit event && relay canceledit event to grid 
         * @param {Ext.grid.Panel} grid 
         * @override 
         * @private 
         */  
        init:function(grid){  
            var me = this;  
            /** 
             * 取消编辑事件.<br/> 
             * Fires canceledit event.And will be relayEvents to Grid.<br/> 
             * @see {@link Ext.ux.grid.RowActions#event-beforeaction} <br/> 
             * @event canceledit 
             * @param {Object} context 
             */  
            me.addEvents('canceledit');  
            me.callParent(arguments);  
            grid.addEvents('canceledit');  
            grid.relayEvents(me, ['canceledit']);  
        },  
          
        /** 
         * 提供默认的Editor配置 
         *      @example 
         *      {header:'手机',dataIndex:'phone',fieldType:'numberfield',field:{allowBlank:true}} 
         * provide default field config 
         * @param {String} fieldType 可选值:numberfield,checkboxfield,passwordField 
         * @return {Object}  
         * @protected 
         */  
        getFieldCfg: function(fieldType){  
            switch(fieldType){  
                case 'passwordField':  
                    return {  
                        xtype: 'textfield',  
                        inputType: 'password',  
                        allowBlank:false  
                    }  
                case 'numberfield':  
                    return {  
                        xtype: 'numberfield',  
                        hideTrigger: true,  
                        keyNavEnabled: false,  
                        mouseWheelEnabled: false,  
                        allowBlank:false  
                    }  
                      
                case 'checkboxfield':  
                    return {  
                        xtype: 'checkboxfield',  
                        inputValue: 'true',  
                        uncheckedValue: 'false'  
                    }  
                }  
        },  
          
        /** 
         * Help to config field,just giving a fieldType and field as additional cfg. 
         * see {@link #getFieldCfg} 
         * @private 
         * @override 
         */  
        getEditor: function() {  
            var me = this;  
      
            if (!me.editor) {  
                Ext.each(me.grid.headerCt.getGridColumns(),function(item,index,allItems){  
                    if(item.fieldType){  
                        item.field = Ext.applyIf(item.field||{},this.getFieldCfg(item.fieldType))  
                    }  
                },this)  
                // keep a reference for custom editor..  
                me.editor = me.initEditor();  
            }  
            me.editor.editingPlugin = me  
            return me.editor;  
        },  
          
        /** 
         * if clicksToEdit===0 then mun the click/dblclick event 
         * @private 
         * @override 
         */  
        initEditTriggers: function(){  
            var me = this   
            var clickEvent = me.clicksToEdit === 1 ? 'click' : 'dblclick'  
            me.callParent(arguments);   
            if(me.clicksToEdit === 0){  
                me.mun(me.view, 'cell' + clickEvent, me.startEditByClick, me);   
            }  
        },  
          
        /** 
         * 添加记录 
         * add a record and start edit it (will not sync store) 
         * @param {Model/Object} data Data to initialize the Model's fields with <br/> 
         * @param {Object} config see {@link #cfg-addPosition}.  
         */  
        startAdd: function(data,config){  
            var me = this;  
            var cfg = Ext.apply({  
                addInPlace: this.addInPlace,  
                addPosition: this.addPosition,  
                colIndex: 0  
            },config)  
              
            //find the position  
            var position;  
            if(cfg.addInPlace){  
                var selected = me.grid.getSelectionModel().getSelection()  
                if(selected && selected.length>0){  
                    position = me.grid.store.indexOf(selected[0])   
                    console.log('a',position)  
                    position += (cfg.addPosition<=0) ? 0: 1  
                }else{  
                    position = 0  
                }  
            }else{  
                position = (cfg.addPosition==-1 ? me.grid.store.getCount() : cfg.addPosition) || 0  
            }  
              
            var record = data.isModel ? data : me.grid.store.model.create(data);  
            var autoSync = me.grid.store.autoSync;  
            me.grid.store.autoSync = false;  
            me.grid.store.insert(position, record);  
            me.grid.store.autoSync = autoSync;  
              
            me.adding = true  
            me.startEdit(position,cfg.colIndex);  
              
            //since autoCancel:true dont work for me  
            if(me.hideTooltipOnAdd && me.getEditor().hideToolTip){  
                me.getEditor().hideToolTip()  
            }  
        },  
          
        /** 
         * 编辑之前,自动取消编辑 
         * Modify: if is editing, cancel first. 
         * @private 
         * @override 
         */  
        startEdit: function(record, columnHeader) {  
            var me = this;  
            if(me.editing){  
                me.cancelEdit();   
            }  
            me.callParent(arguments);  
        },  
          
        /** 
         * 修改adding的状态值 
         * Modify: set adding=false 
         * @private 
         * @override 
         */  
        completeEdit: function() {  
            var me = this;  
            if (me.editing && me.validateEdit()) {  
                me.editing = false;  
                me.fireEvent('edit', me.context);  
            }  
            me.adding = false  
        },  
          
        /** 
         * 取消编辑 
         * 1.fireEvent 'canceledit' 
         * 2.when autoRecoverOnCancel is true, if record is phantom then remove it 
         * @private 
         * @override 
         */  
        cancelEdit: function(){  
            var me = this;  
            if (me.editing) {  
                me.getEditor().cancelEdit();  
                me.editing = false;  
                me.fireEvent('canceledit', me.context);   
                if (me.autoRecoverOnCancel){  
                    if(me.adding){  
                        me.context.record.store.remove(me.context.record);  
                        me.adding = false  
                    }else{  
                        //不需要reject,因为Editor没有更改record.  
                        //me.context.record.reject()  
                    }  
                }  
            }  
        }  
    })  
      
    //ext-lang-zh_CN  
    if (Ext.grid.RowEditor) {  
        Ext.apply(Ext.grid.RowEditor.prototype, {  
            saveBtnText: '保存',  
            cancelBtnText: '取消',  
            errorsText: '错误信息',  
            dirtyText: '已修改,你需要提交或取消变更'  
        });  
    }  