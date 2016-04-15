var cheerio = require('cheerio'),
    request = require('request');

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

var option = {
	url:movieTypeUrl['恐怖'],
	method: 'GET',
	//headers:[{}]
};


module.exports = function() {
	this.data = [];
	this.getData = function(url, callback) {
		var that = this;
		request(url, function(err, response, body) {
			if (err) {
				console.log('error:'+err+' when visit '+requestUrl);
				return;
			}
			if (response.statusCode == 200) {
				var $ = cheerio.load(body);
				//console.log(body);
				$('.res_cinema > .res_movie_in').each(function(index, ele) {
					var info = {};
					var ele = $(ele);
					
					info['img'] = ele.find('.res_movie_pic img').attr('src');
					info['name'] = ele.find('.res_movie_text .cy_res_title a').attr('title');
					info['on-Time'] = ele.find('.res_movie_text .fs9.ml5').text().replace(/[\[\]]/g, '');
					info['score'] = ele.find('.res_movie_text .res_movie_text_in:first-child .redfc').text();
					info['description'] = ele.find('.res_movie_text .res_movie_text_in .res_movie_ms').text();
					ele.find('.res_movie_text .res_movie_text_in.mt7 a').each(function(index, ele) {
						if (!info['type']) info['type'] = [];
						info['type'].push($(ele).text());
					});
					info['time-language'] = ele.find('.res_movie_text .res_movie_text_in.mt7 .res_movie_dy').prev()['0'].prev['data'].replace(/[\r\n\t ]/g, '');
					info['actors'] = ele.find('.res_movie_text .res_movie_text_in.mt7 .res_movie_dy').text().replace(/[\r\n\t ]/g, '');
					that.data.push(info);
				});
				callback(that.data);
			}
			return;
		});
	}
}

