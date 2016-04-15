var Movie = require('./movie');

var movie = new Movie();

movie.getData('http://film.spider.com.cn/guangzh-film--aq--/', callback);


function callback(data) {
	console.log(data);
}