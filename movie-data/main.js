var Movie = require('./movie');
var Dao = require('./Dao.js');
Dao = new Dao();
var movie = new Movie();

movie.getData('http://film.spider.com.cn/guangzh-film--aq--/', callback);

function callback(data) {
	if (!data['state']) {
		console.log('[ ERROR - ] FAILED TO GRAB DATA FROM SOURCE WEBSITE');
		return;
	}
	data['movies'].forEach(function(movie) {
		Dao.create(movie, null);
	});
}