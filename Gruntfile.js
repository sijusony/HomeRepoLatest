/**
 * 
 */



module.exports = function(grunt){
	
		/*grunt.registerTask("default","",function(){
			grunt.log.write("This is  a pointless task");
		});
		*/
		
		grunt.initConfig({
			
			//basic settings
			pkg : grunt.file.readJSON('package.json'),
		
			// name of plugin wihtout "grunt-contrib-" 
		
			cssmin: {
				
				combine : {
					files : {
						'html/css/main.css' : ['html/css/test1.css','html/css/test2.css']
					}
				}
			},
			
			//uglify
			uglify : {
				
				dist : {
					files : {
						'html/js/toggle.min.js' : ['html/js/toggle.js']
					}
				}
			}
			
		});	
		
		
		//load the plugin
		grunt.loadNpmTasks('grunt-contrib-cssmin');
		grunt.loadNpmTasks('grunt-contrib-uglify');
		
		
		//do task
		grunt.registerTask("default",['cssmin','uglify']);
		

};
