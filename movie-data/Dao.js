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
    force: false
}).error(function(err, result) {
	console.error('[ ERROR - ] MOVIE TABLE BUILD ERROR');
	console.error(err);
	console.error(result);
}).done(function() {
	console.log('[ DONE - ] MOVIE TABLE BUILD');
});
*/

module.exports = function() {
    this.create = function(record, callback) {
        movie.create(record)
            .then(function(result) {
                console.log('[SUCCESS -] INSERTED AN RECORD INTO MOVIE ' + record['name']);
                if (!!callback)
                	callback(result);
            })
            .catch(function(err) {
                console.log('[ERROR -] FAILED TO INSERT AN RECORD INTO MOVIE') + record['name'];
                console.log(err);
            });
    };
    this.retrieve = function() {
    	
    }
}
