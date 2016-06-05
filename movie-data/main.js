var Movie = require('./movie/movie');
var movie = new Movie();
var MovieDao = require('./movie/Dao.js');
var movieDao = new MovieDao();

var Cinema = require('./cinema/cinema');
var cinema = new Cinema();
var CinemaDao = require('./cinema/Dao.js');
var cinemaDao = new CinemaDao();


var hotMovieIndexUrl = ["http://film.spider.com.cn/guangzh-film----/",
    "http://film.spider.com.cn/guangzh-film-----2/",
    "http://film.spider.com.cn/guangzh-film-----3/",
    "http://film.spider.com.cn/guangzh-film-----4/"
];

var movieTypeUrl = {
    '爱情': 'http://film.spider.com.cn/guangzh-film--aq--/',
    '剧情': 'http://film.spider.com.cn/guangzh-film--jq--/',
    '冒险': 'http://film.spider.com.cn/guangzh-film--mx--/',
    '奇幻': 'http://film.spider.com.cn/guangzh-film--qh--/',
    '动作': 'http://film.spider.com.cn/guangzh-film--dz--/',
    '犯罪': 'http://film.spider.com.cn/guangzh-film--fz--/',
    '惊悚': 'http://film.spider.com.cn/guangzh-film--js--/',
    '家庭': 'http://film.spider.com.cn/guangzh-film--jt--/',
    '动画': 'http://film.spider.com.cn/guangzh-film--dh--/',
    '喜剧': 'http://film.spider.com.cn/guangzh-film--xj--/',
    '科幻': 'http://film.spider.com.cn/guangzh-film--kh--/',
    '恐怖': 'http://film.spider.com.cn/guangzh-film--kb--/',
};



var cinemaList = ['http://film.spider.com.cn/guangzh-cinema-baiyq----/',
		'http://film.spider.com.cn/guangzh-cinema-haizq----/',
		'http://film.spider.com.cn/guangzh-cinema-tianhq----/',
		'http://film.spider.com.cn/guangzh-cinema-fanyq----/',
		'http://film.spider.com.cn/guangzh-cinema-yuexq----/',
		'http://film.spider.com.cn/guangzh-cinema-liwxq----/',
		'http://film.spider.com.cn/guangzh-cinema-zhengcq----/',
		'http://film.spider.com.cn/guangzh-cinema-huadq----/',
		'http://film.spider.com.cn/guangzh-cinema-huangpq----/',
		'http://film.spider.com.cn/guangzh-cinema-nansq----/'];
var ontime = 'http://film.spider.com.cn/jquery-second033.html?filmId=201603786108&showDate=2016-06-08&area=guangzhou&type=cinema&regionId=&subwayId=&cinemaId=44003301';
//var details = 'http://film.spider.com.cn/jquery-second033.html?filmId=' + movieId + '&showDate=' + date + '&area=guangzhou&type=cinema&regionId=&subwayId=&cinemaId=' + cinemaId;

/*
cinemaList.forEach(function(url) {
	cinema.getData(url, callback);

    function callback(data) {
        if (!data['state']) {
            console.log('[ ERROR - ] FAILED TO GRAB DATA FROM SOURCE WEBSITE');
            return;
        }
        data['cinemas'].forEach(function(cinema) {
            cinemaDao.create(cinema, function(isSuccess, result) {

            });
        });
    }
});
*/


for (key in movieTypeUrl) {
    movie.getData(movieTypeUrl[key], callback);

    function callback(data) {
        if (!data['state']) {
            console.log('[ ERROR - ] FAILED TO GRAB DATA FROM SOURCE WEBSITE');
            return;
        }
        data['movies'].forEach(function(movie) {
            movieDao.create(movie, function(isSuccess, result) {

            });
        });
    }
}




/*
movie.getData('http://film.spider.com.cn/guangzh-film--aq--/', function(data) {
    console.log('into');
    if (!data['state']) {
        console.log('[ ERROR - ] FAILED TO GRAB DATA FROM SOURCE WEBSITE');
        return;
    }
    console.log(data);
});
*/
/*
movieDao.delete({}, function(n) {
    console.log('delete ' + n);
});
*/
