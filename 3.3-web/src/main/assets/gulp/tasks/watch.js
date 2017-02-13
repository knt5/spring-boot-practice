const gulp = require('gulp');

gulp.task('watch', () => {
	// css
	gulp.watch(['css/**/*.scss'], ['build:css']);
	
	// js
	//gulp.watch(['js/**/*.js'], ['build:js']);
});
