var Background = function() {
	/**
	 * 创建背景
	 */
	var background = createEl({class:'background'});
	var backgroundImg = createEl('img',{class:'backgroundimg',src:arguments[0]});
	background.appendChild(backgroundImg);

	this.getBackground = function() {
		return background;
	}
	
	this.render = function(){
		var render
		if(arguments[0]==null){
			render = Ext.getBody();
		}
		
		render.appendChild(background);
	}
}