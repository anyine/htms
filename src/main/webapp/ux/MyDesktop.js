var MyDesktop = function(option) {
	
	var me = this;
	
	for (var p in option) {
		this[p] = option[p];
	}
	
	/**
	 * 初始化属性
	 */

	var render = this["render"] ? this["render"] : Ext.getBody();
	var region = this["region"]?this["region"]:'left';
	var icons = this["icons"];
	var backgroundImage = this["backgroundImage"];
	
	this.play = function() {

		var background = new Background(backgroundImage);
		background.render();

		var iparr = [];  
				
		Ext.each(icons,function(thisIP,index){
			
			var iconPanel = new IconPanel(thisIP,icons,render);
			iconPanel.addMouseEvent(iparr,index,render);
			iparr.push(iconPanel);
			if(index==0){
				render.appendChild(iconPanel);
				iconPanel.addDD();
				iconPanel.onWindowResize(iconPanel.select('div').elements);
			}
		});
	}
}

