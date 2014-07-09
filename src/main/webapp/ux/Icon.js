var Icon = function(icon) {

	var iconEl = createIconEl(icon.text);
	
	iconEl.applyStyles('background-image:url(' + icon.imgUrl + ');');
	
	
	iconEl.addHoverEvent = function(){
			this.hover(function() {
				iconEl.addClass('icon_hover');
				iconEl.applyStyles('background-image:url('+icon.gifUrl+');');
			},function() {
				iconEl.removeClass('icon_hover');
				iconEl.applyStyles('background-image:url('+icon.imgUrl+');');
			});
	}

	
	function createIconEl(text){
		var el = document.createElement("div");
		for(var i =0;i<4;i++){
			var br = document.createElement("br");
			el.appendChild(br);
		}
		var textNode = document.createTextNode(text);
		el.setAttribute('class','icon');
		el.appendChild(textNode);
		
		return Ext.get(el);
	}
	
	return iconEl;
}
