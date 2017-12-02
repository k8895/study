//windows连接命令
进入 F:\MongoDB\Server\3.4\bin -> mongo.exe

//启动
./mongod --config=mongodb.conf
//shell连接命令
mongodb/bin -> ./mongo 172.30.34.42:2717/webchat

//查看当前数据库
show dbs

//先在admin中建立超级管理员
use admin
db.createUser({user: "root",pwd: "123123",roles: [{ role: "root", db: "admin" }]})

//选择webchat数据库
use webchat

//添加用户(给予权限)
//read：允许用户读取指定数据库 
//readWrite：允许用户读写指定数据库 
//dbAdmin：允许用户在指定数据库中执行管理函数，如索引创建、删除，查看统计或访问system.profile 
//userAdmin：允许用户向system.users集合写入，可以找指定数据库里创建、删除和管理用户 
//相关文档 http://blog.csdn.net/jianlong727/article/details/53889990
db.createUser({user: "userAdmin",pwd: "123123",roles: [{ role: "userAdmin", db: "webchat" }]})
db.createUser({user: "dbadmin",pwd: "123123",roles: [{ role: "dbAdmin", db: "webchat" }]})
db.createUser({user: "vveuser",pwd: "123123",roles: [{ role: "readWrite", db: "webchat" }]})
db.createUser({user: "readuser",pwd: "123123",roles: [{ role: "read", db: "webchat" }]})

//用户验证,成功返回1
db.auth("userAdmin", "123123")



//windows重启mongodb，带验证参数 --auth
cd F:\MongoDB\Server\3.4\bin
mongod --auth --dbpath F:\data\db

//nodejs连接带账号密码
mongoose.connect('mongodb://localhost/webchat', {useMongoClient: true, user: 'vveuser', pass: '123123'});


//linux关闭
ps -A |grep mongod
kill -pid