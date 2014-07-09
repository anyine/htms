var IconPanel = function(ip,icons,render){
	
	var me = this;
	var x_x = 100; // 图标之间间隔(包含图标自身宽高)
	var y_y = 100;
	var iconPanelEl = createEl({
		class:'iconPanel',
		index:ip.index
	});
	
	Ext.each(ip.items,function(thisIcon,index){
		
		var ic = new Icon(thisIcon);
		ic.addHoverEvent();
		
		iconPanelEl.appendChild(ic);
	});
	
	iconPanelEl.unselectable();
	
	indexCache={};
	
	iconPanelEl.addMouseEvent = function(iparr,index,render){
		this.addListener('mousedown',function(a){
				indexCache.oldX = a.browserEvent.clientX;
				indexCache.oldY = a.browserEvent.clientY;
			});
		this.addListener('mouseup',function(b){
				var newX = b.browserEvent.clientX;
				var newY = b.browserEvent.clientY;
				var oldX = indexCache.oldX;
				var oldY = indexCache.oldY;
				if((newX-oldX)>200&&index-1>=0&&oldX!=null){
								
					this.slideOut('r',{remove:true,duration:0.4});
					render.appendChild(iparr[index-1]);
					iparr[index-1].onWindowResize();
					iparr[index-1].addDD();
					iparr[index-1].slideIn('l',{duration:0.4});
					
				}else if((newX-oldX)<-200&&index+1<iparr.length&&oldX!=null){
					
					this.slideOut('l',{remove:true,duration:0.4});
					render.appendChild(iparr[index+1]);
					iparr[index+1].onWindowResize();
					iparr[index+1].addDD();
					iparr[index+1].slideIn('r',{duration:0.4});
										
				}else if(oldY-newY<-300&&oldY!=null){
					me.reducePanel(iparr,this);
				}
				indexCache.oldX = null;
				indexCache.oldY = null;
				
			});
	}
	/**
	 * 桌面图标排序方法 comp:对此图标容器下的图标(div)进行排序
	 */
	
	iconPanelEl.onWindowResize = function(mix,r,x,y) {

		if(mix==null){
			var comp = this.select('div').elements;
		}else if(mix!=null){
			var comp = Ext.isArray(mix)?mix:mix.select('div').elements;
		}
		
		var region = r==null?'left':r;
		var height = x==null?Ext.fly(document.getElementById('comp0')).getHeight():x;
		var width = y==null?Ext.fly(document.getElementById('comp0')).getWidth():y;

		var num = comp.length;
	
		if (region == 'left') {
			var x = 0, y = 20, lineCount = 1;
			for (var i = 0; i < num; i++) {
				var el = Ext.fly(comp[i]);
				el.setLocation(x, y);
				
				console.log("x:"+x+"--"+"y:"+y);
				
				y = y + y_y;
				lineCount++;
				if (y_y * (lineCount) > height) {
					x = x + x_x;
					y = 20;
					lineCount = 1;
				}
			}
		} else if (region == 'right') {
			var x = width - x_x, y = 20, lineCount = 1;
			for (var i = 0; i < num; i++) {
				var el = Ext.fly(comp[i]);
				el.setLocation(x, y);
				y = y + y_y;
				lineCount++;
				if (y_y * (lineCount) > height) {
					x = x - x_x;
					y = 20;
					lineCount = 1;
				}
			}
		} else if (region == 'top') {
			var x = 0, y = 20, lineCount = 1;
			for (var i = 0; i < num; i++) {
				var el = Ext.fly(comp[i]);
				el.setLocation(x, y);
				x = x + x_x;
				lineCount++;
				if (x_x * (lineCount) > width) {
					y = y + y_y;
					x = 0;
					lineCount = 1;
				}
			}
		} else if (region == 'bottom') {
			var x = 0, y = height - y_y, lineCount = 1;
			for (var i = 0; i < num; i++) {
				var el = Ext.fly(comp[i]);
				el.setLocation(x, y);
				x = x + x_x;
				lineCount++;
				if (x_x * (lineCount) > width) {
					y = y - y_y;
					x = 0;
					lineCount = 1;
				}
			}
		}
	}
	
	this.onWindowResize = iconPanelEl.onWindowResize;
	
	iconPanelEl.addDD = function(){
		divArr = this.select('div').elements;
		Ext.each(divArr,function(item,index){
			var proxy = new Ext.dd.DDProxy(item.id);
			proxy.onMouseUp = function(a,e,id){
				var startX = 0;stopX = startX+x_x;
				var length = Math.ceil(window.innerWidth/x_x);
				var newArr = [];
				for(var i =0;i<length;i++){
					var arrCache = [];
					for(var j =0;j<divArr.length;j++){
						var centerP = me.getCenterPoint(divArr[j]);
						if(centerP.x>=startX&&centerP.x<stopX){
							arrCache.push(divArr[j]);
						}
					}
					for(var k =0;k<arrCache.length;k++){
						for(var l =0;l<arrCache.length-k-1;l++){
							var c1 = me.getCenterPoint(arrCache[l]);
							var c2 = me.getCenterPoint(arrCache[l+1]);
							if(c1.y>c2.y){
								var temp = arrCache[l];
								arrCache[l] = arrCache[l+1];
								arrCache[l+1] = temp;
							}
						}
					}
					newArr = newArr.concat(arrCache);
					
					startX = stopX;
					stopX += x_x;
				}
				iconPanelEl.onWindowResize(newArr);
			}
		});
	}
	
	this.getCenterPoint = function(div){
		
		var divX = div.clientWidth;
		var divY = div.clientHeight;
		
		var marginX = div.offsetLeft;
		var marginY = div.offsetTop;
		
		var halfX = Math.round(divX/2);
		var halfY = Math.round(divY/2);
		
		return { x: marginX+halfX , y: marginY+halfY };
		
	}
	
	this.reducePanel = function(arr,thisIp){
		thisIp.slideOut('b',{remove:true});
		
		var reduceDiv = createEl({class:'reduceDiv'});
		
		for(var i=0;i<icons.length;i++){
			var reduceItem = createEl({class:'reduceItem'});
			var iconItems = icons[i].items
			for(var j =0;j<iconItems.length;j++){
				var iconImg = createEl('img',{class:'iconImg',src:iconItems[j].imgUrl});
				reduceItem.appendChild(iconImg);
			}
			reduceDiv.appendChild(reduceItem);
//			me.onWindowResize(reduceDiv.select('img').elements,'left',300,400);
			reduceItem.addListener('click',function(){
				var cIndex = i;
				return function(){
					reduceDiv.fadeOut({remove:true});
					var el =arr[cIndex];
					render.appendChild(el);
					el.fadeIn();
				}
			}());
		}
		render.appendChild(reduceDiv);
		
	}
	
	window.onresize = function() {
		var el = Ext.fly(Ext.DomQuery.select('div[class=iconPanel x-unselectable]')[0]);
		me.onWindowResize(el);
	}
	
	return iconPanelEl;
}