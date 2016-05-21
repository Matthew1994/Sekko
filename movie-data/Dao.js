var Sequelize = require('sequelize');
var mysql = require('mysql');
var crypto = require("crypto");
function md5(data) {
    var Buffer = require("buffer").Buffer;
    var buf = new Buffer(data);
    var str = buf.toString("binary");
    return crypto.createHash("md5").update(str).digest("hex");
}
var sequelize = new Sequelize('sekko', 'root', '123456', {
    host: '127.0.0.1',
    port: 3306,
    dialect: 'mysql',
    pool: {
        max: 5,
        min: 0,
        idle: 10000
    },
});

var test = {
    img: 'http://pic.spider.com.cn/pic//filmpic/jdt/1461133757470_mobile2.jpg',
    name: '分歧者3：忠诚世界',
    url: 'http://film.spider.com.cn/film-201603477707/3',
    onTime: '已热映2天',
    score: '8.6',
    description: '忠诚抉择',
    type: '爱情,动作,科幻,冒险',
    timeAndLanguage: '片长/语言：121分钟/英语',
    actors: '导演/演员：罗伯特·斯文克/谢琳·伍德蕾/提奥·詹姆斯/迈尔斯·特勒/安塞尔·艾尔高特/娜奥米·沃茨/杰夫·丹尼尔斯/比尔·斯卡斯加德'
};

/*
定义表
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| id              | int(11)      | NO   | PRI | NULL    | auto_increment |
| img             | varchar(255) | YES  |     | NULL    |                |
| name            | varchar(255) | NO   |     | NULL    |                |
| onTime          | varchar(255) | YES  |     | NULL    |                |
| score           | varchar(255) | YES  |     | NULL    |                |
| description     | text         | YES  |     | NULL    |                |
| type            | text         | NO   |     | NULL    |                |
| timeAndLanguage | varchar(255) | YES  |     | NULL    |                |
| actors          | text         | YES  |     | NULL    |                |
| url             | varchar(255) | NO   |     | NULL    |                |
| createdAt       | datetime     | NO   |     | NULL    |                |
| updatedAt       | datetime     | NO   |     | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+

*/
var movie = sequelize.define('movie', {
    img: {
        type: Sequelize.STRING,
        field: 'img'
    },
    name: {
        type: Sequelize.STRING,
        field: 'name',
        allowNull: false
    },
    onTime: {
        type: Sequelize.STRING,
        field: 'onTime'
    },
    score: {
        type: Sequelize.STRING,
        field: 'score'
    },
    description: {
        type: Sequelize.TEXT,
        field: 'description'
    },
    type: {
        type: Sequelize.TEXT,
        field: 'type',
        allowNull: false
    },
    timeAndLanguage: {
        type: Sequelize.STRING,
        field: 'timeAndLanguage'
    },
    actors: {
        type: Sequelize.TEXT,
        field: 'actors'
    },
    url: {
        type: Sequelize.STRING,
        field: 'url',
        allowNull: false,
        unique: true //根据url排重
    }
}, {
    freezeTableName: true
});


//创建表
/*
movie.sync({
    force: true
}).then(function() {
    // Table created
    return movie.create(test);
});
*/
movie.create(test).then(function(m) {
	console.log(m);
}).catch(function(err) {
	//console.log(err);
});
