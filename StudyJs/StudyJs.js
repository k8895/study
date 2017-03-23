/**
 * 高阶函数：
 *
 * map :调用Array的map()方法，传入我们自己的函数，就得到了一个新的Array作为结果
 */
function pow(x) {
    return x * x;
}

var arr = [1, 2, 3, 4, 5, 6, 7, 8, 9];
// console.log(arr.map(pow));

//-------------------------------
//闭包就是携带状态的函数，并且它的状态可以完全对外隐藏起来

var Students = {
    name: 'Robot',
    height: 1.6,
    run: function () {
        console.log(this.name + 'is running...')
    }
};

var xiaoming = {
    name: '小明'
};

//xiaoming的原型指向了Students,类似继承
xiaoming.__proto__ = Students;

// console.log(xiaoming.name);
// console.log(xiaoming.height);
// console.log(xiaoming.run());


//js中所有对象都是实例，所谓继承关系不过是把一个对象的原型指向另一个对象而已
//不要直接用obj.__proto__去改变一个对象的原型
function createStudent(name) {
    //基于Student原型创建一个新对象
    var s = Object.create(Students);
    //初始化新对象
    s.name = name;
    return s;
}

var xiaohong = createStudent('小红');
// xiaohong.run();


//--------------------------

function Student1(name) {
    this.name = name;
    this.hello = function () {
        console.log('Hello, ' + this.name + '!');
    }
}

//构造函数
var xiaoming1 = new Student1('小明1');
// console.log(xiaoming1.name);
// xiaoming1.hello();


//------------------------------------

function Student2(props) {
    this.name = props.name || '匿名';
    this.grade = props.grade || 1;
}

Student2.prototype.hello = function () {
    console.log('Hello, ' + this.name + '!');
};

function createStudent2(props) {
    return new Student2(props || {})
}

//这个createStudent()函数有几个巨大的优点：一是不需要new来调用，二是参数非常灵活，可以不传，也可以这么传：
var xiaoming2 = createStudent2({
    name: '小明2'
});

//------------------------------

function Student3(props) {
    this.name = props.name || 'Unnamed';
}

Student3.prototype.hello = function () {
    alert('Hello, ' + this.name + '!');
}

function primaryStudent3(props) {
    //调用Student3构造函数，绑定this变量，调用了Student构造函数不等于继承了Student
    Student3.call(this, props);
    this.grade = props.grade || 1;
}















