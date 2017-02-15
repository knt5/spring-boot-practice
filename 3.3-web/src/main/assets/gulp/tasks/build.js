const path = require('path');
const fs = require('fs');
const gulp = require('gulp');
const plumber = require('gulp-plumber');
const sass = require('gulp-sass');

gulp.task('build:css', () => {
	return gulp.src('css/views/**/*.scss')
		.pipe(plumber({
			errorHandler: function(err) {
				console.error(err.messageFormatted);
				this.emit('end');
			}
		}))
		.pipe(sass({
				includePaths: ['./css'],
				//outputStyle: 'compressed'
			}).on('error', sass.logError))
		.pipe(gulp.dest('../resources/static/assets/css'))
});

gulp.task('build', [
	'build:css',
]);
