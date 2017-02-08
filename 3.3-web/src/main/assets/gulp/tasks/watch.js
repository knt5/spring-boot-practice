const gulp = require('gulp');

gulp.task('watch', () => {
	// css
	gulp.watch(['css/**/*.scss'], ['build:css']);
});
