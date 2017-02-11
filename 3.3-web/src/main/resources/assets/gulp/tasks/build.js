const path = require('path');
const fs = require('fs');
const gulp = require('gulp');
const plumber = require('gulp-plumber');
const sass = require('gulp-sass');

gulp.task('build:css', () => {
	return gulp.src('css/**/*.scss')
		.pipe(plumber({
			errorHandler: function(err) {
				console.error(err.messageFormatted);
				this.emit('end');
			}
		}))
		.pipe(sass({
				//outputStyle: 'compressed'
			}).on('error', sass.logError))
		.pipe(gulp.dest('../static/assets/css'))
});

gulp.task('build', [
	'build:css',
]);
