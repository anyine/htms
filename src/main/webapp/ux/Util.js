var createEl = function() {

	var id = 0;

	return function(tag, attributes) {

		if (typeof tag === 'object' || tag == null) {
			attributes = tag;
			tag = 'div';
		}

		var el = document.createElement(tag);
		if (attributes != null)
			for (var n in attributes) {
				el.setAttribute(n, attributes[n]);
		}

		el.setAttribute('id', 'comp' + id++);

		return Ext.get(el);
	}
}()