var Movie = require('./movie');

var movie = new Movie();

movie.getData('http://film.spider.com.cn/guangzh-film--kb--/');


setTimeout(function() {
	console.log(movie.data);
}, 10000);