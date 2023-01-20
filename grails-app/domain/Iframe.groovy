class Iframe {

	String component
	String iframe
	String url
	String component_name
	
	static mapping = {
		table 'jvkk.neural_iframe'
		id generator: 'identity' 		
		version false
		columns {
			component column:'component'		
			iframe column:'iframe'
			url column:'url'
			component_name column:'component_name'
		}			
	}
}
