var request = require('request');
var Movie = require('./movie/movie');
var movie = new Movie();
var MovieDao = require('./movie/Dao');
var movieDao = new MovieDao();

var Cinema = require('./cinema/cinema');
var cinema = new Cinema();
var CinemaDao = require('./cinema/Dao');
var cinemaDao = new CinemaDao();

var Detail = require('./cinema/details');
var detail = new Detail();

var conf = require('./conf')

var ontime = 'http://film.spider.com.cn/jquery-second033.html?filmId=201603786108&showDate=2016-06-08&area=guangzhou&type=cinema&regionId=&subwayId=&cinemaId=44003301';

var Spider = {
    grabMovie: function() {
        var movieTypeUrl = conf['hotMovieIndexUrl'];
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
    },
    grabCinema: function() {
        var cinemaList = conf['cinemaList'];
        cinemaList.forEach(function(url) {
            cinema.getData(url, callback);

            function callback(data) {
                if (!data['state']) {
                    console.log('[ ERROR - ] FAILED TO GRAB DATA FROM SOURCE WEBSITE');
                    return;
                }
                data['cinemas'].forEach(function(cinema) {
                    detail.getData(cinema['url'], function(d) {
                        if (!d['state']) {
                            return;
                        }
                        var c = cinema;
                        c['movies'] = JSON.stringify(d['details']['movies']);
                        c['coordinate'] = d['details']['coordinate'];
                        c['tel'] = d['details']['tel'];
                        cinemaDao.create(c, function(isSuccess, result) {

                        });
                    })
                });
            }
        });
    },
    clearMovie: function() {
        movieDao.delete({}, function(n) {
            console.log('delete ' + n + ' Movie');
        });
    },
    clearCinema: function() {
        cinemaDao.delete({}, function(n) {
            console.log('delete ' + n + ' Cinema');
        });
    }
}


//'http://film.spider.com.cn/jquery-second033.html?filmId=' + movieId + '&showDate=' + date + '&area=guangzhou&type=cinema&regionId=&subwayId=&cinemaId=' + cinemaId;



//Spider.clearMovie();
//Spider.grabMovie();
//Spider.clearCinema();
//Spider.grabCinema();


var movieId = '201603786108';
var cinemaId = '44015001';
var date = '2016-06-09';
var times = 'http://film.spider.com.cn/jquery-second033.html?filmId=' + movieId + '&showDate=' + date + '&area=guangzhou&type=cinema&regionId=null&subwayId=null&cinemaId=' + cinemaId;

request(times, function(err, res, body) {
    console.log(body);
})
/*
cinemaDao.find({}, function(isSuccess, cinemas) {
    if (isSuccess) {
        console.log(cinemas);
    }
})
*/