var DB = require('mysql');
var connection = DB.createConnection({
    host: '127.0.0.1',
    port: '3306',
    user: "root",
    password: "123456",
    database: 'sekko',
});
/*
Table:movie
+---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| Id            | int(11)     | NO   | PRI | NULL    | auto_increment |
| name          | varchar(64) | NO   |     | NULL    |                |
| img           | varchar(64) | YES  |     | NULL    |                |
| on_time       | varchar(64) | YES  |     | NULL    |                |
| score         | varchar(64) | YES  |     | NULL    |                |
| description   | varchar(64) | YES  |     | NULL    |                |
| time_language | varchar(64) | YES  |     | NULL    |                |
| type          | varchar(64) | YES  |     | NULL    |                |
| stars         | varchar(64) | YES  |     | NULL    |                |
+---------------+-------------+------+-----+---------+----------------+
*/
var test = {
    img: 'http://pic.spider.com.cn/pic//filmpic/jdt/1461133757470_mobile2.jpg',
    name: '分歧者3：忠诚世界',
    'on-Time': '已热映1天',
    score: '8.4',
    description: '忠诚抉择',
    type: 'love',
    'time-language': '片长/语言：121分钟/英语',
    actors: '导演/演员：罗伯特·斯文克/谢琳·伍德蕾/提奥·詹姆斯/迈尔斯·特勒/安塞尔·艾尔高特/娜奥米·沃茨/杰夫·丹尼尔斯/比尔·斯卡斯加德'
};

connection.connect();

function movieDB() {
    this.getMovie = function() {

    };
    
    this.insertMovie = function(movie, callback) {
    	
        var movieAddSql = 'INSERT INTO movie(name,img,on_time,score,description,time_language,type,stars) VALUES(?,?,?,?,?,?,?,?)';
        var movieParamList = ['img', 'name', 'on-Time', 'score', 'description', 'type', 'time-language', 'actors'];
        var movieAddSqlParams = [];
        
        //需要对传入的movie对象做预处理
        // ..........
        //生成唯一ID
        movieParamList.forEach(function(ele) {
            movieAddSqlParams.push(movie[ele]);
        });
        connection.query(movieAddSql, movieAddSqlParams, function(err, result) {
            if (err) {
                console.log('[INSERT ERROR] - ', err.message);
                return;
            }

            console.log('--------------------------INSERT----------------------------');
            console.log('INSERT ID:',result.insertId);        
            //console.log('INSERT ID:', result);
            console.log('------------------------------------------------------------\n\n');
        });
    };

    this.updateMovie = function() {

    };
    this.deleteMovie = function() {

    };
}


/*
mc.query('use iSysu', function(err, res, fields) {
	if (!err) {
		console.log(res);
		console.log(fields);
	} else {
		console.log('[ Query Error ]' + err);
	}
});

mc.query('select * from play', function(err, res, fields) {
	if (!err) {
		console.log(res);
		console.log(fields);
	} else {
		console.log('[ Query Error ]' + err);
	}
});
*/
