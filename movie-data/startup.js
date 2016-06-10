var CronJob = require('cron').CronJob;
var Spider = require('./Spider');

//- 获取电影
new CronJob('0 10 15 * * *', function() {
	console.log('获取电影定时任务开始！');
	Spider.clearMovie();
	Spider.grabMovie();
}, function() {
    console.log('获取电影定时任务停止！');
}, true);

//-获取电影院信息
new CronJob('0 12 15 * * *', function() {
	console.log('获取电影院定时任务开始！');
	Spider.clearCinema();
	Spider.grabCinema();
}, function() {
    console.log('获取电影院定时任务停止！');
}, true);

//- 删除场次
new CronJob('0 14 15 * * *', function() {
	console.log('删除场次定时任务开始！');
	Spider.clearTimes();
}, function() {
    console.log('删除场次定时任务停止！');
}, true);

//- 获取今天场次
new CronJob('0 20 15 * * *', function() {
	console.log('获取今天场次定时任务开始！');
	Spider.grabTimes(0);
}, function() {
    console.log('获取今天场次定时任务停止！');
}, true);

//- 获取明天场次
new CronJob('0 25 15 * * *', function() {
	console.log('获取明天场次定时任务开始！');
	Spider.grabTimes(1);
}, function() {
    console.log('获取明天场次定时任务停止！');
}, true);

//- 获取后天场次
new CronJob('0 30 15 * * *', function() {
	console.log('获取后天场次定时任务开始！');
	Spider.grabTimes(2);
}, function() {
    console.log('获取后天场次定时任务停止！');
}, true);
