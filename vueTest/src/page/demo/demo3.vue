<template>
  <div>
    <h2>操作符,数组</h2>
    <h4>----------------------------</h4>

    <!--触发视图更新-->
    <my-article v-for='item in artList' :art-list='item'></my-article>
    <button @click='push'>添加文章</button>
    <button @click='pop'>尾部去除一篇文章</button>
    <button @click='unshift'>在头部加入一条数据</button>
    <button @click='shift'>在头部去除一条数据</button>
    <button @click='reverse'>把所有数据进行反转</button>
    <button @click='clears'>清除所有数据</button>
    <button @click='splice'>从第2项开始，删除两项</button>
    <br/><br/>

    <h4>----------------------------</h4>
    <ul>
      <li v-for=" (item,index) in list" v-text='`${item} - ${index}`'></li>
    </ul>
    <button @click="filter">数组进行取余过滤</button>
    <button @click="concat">数组进行合并</button>
    <button @click="map">数组印射</button>
    <button @click="slice">返回截取后的数组</button>
    <br/><br/>

    <h4>----------------------------</h4>
    <h4>改变数组的长度，改变下标的某一个元素</h4>
    <ul>
      <li v-for="(item,index) in list" v-text="`${item} - ${index}`"></li>
    </ul>
    <button @click="changeList">通过下标来改变整个数组里的值也是行不通的</button>
    <button @click="changeList2">通过数组长度改变改个数组里的值是行不通的</button>
    <button @click="change3">改变数组第3个值,改成0</button>
    <button @click="change4">改变数组第2个值,改成5</button>
    <br/><br/>

    <h4>----------------------------</h4>
    <h4>显示过滤/排序效果/副本过滤</h4>
    <!--在不改动data选项原数组的同时，对新数组进行改变，同时也不创造出多余的数据值-->
    <ul>
      <li v-for=" (item,index) in list3" v-text='`${item} - ${index} `'></li>
    </ul>
    <br/>
    <ul>
      <li v-for=" (item,index) in newList" v-text='`${item} - ${index} `'></li>
    </ul>
    <button @click="randomList">随机重置数组的值</button>

  </div>
</template>

<script>
  import myArticle from '../../components/myArticle'
  import Vue from 'vue'

  export default {
    components: {
      myArticle
    },
    created () {
      this.artList = [
        {name: 'ziksang1', startTime: '1小时前', content: 'aaaaaaaaa', good: 1},
        {name: 'ziksang2', startTime: '2小时前', content: 'bbbbbbbbb', good: 2},
        {name: 'ziksang3', startTime: '3小时前', content: 'ccccccccc', good: 3},
        {name: 'ziksang4', startTime: '4小时前', content: 'ddddddddd', good: 4}
      ]
    },
    data () {
      return {
        artList: [],

        list: [1, 2, 3, 4],
        list2: [7, 8, 9, 0],

        list3: [1, 2, 3, 4, 5, 6]
      }
    },
    methods: {
      push () {
        this.artList.push({
          name: 'ziksang5',
          startTime: '5小时前',
          content: 'eeeeeeeeee',
          good: 5
        })
      },
      pop () {
        this.artList.pop()
      },
      shift () {
        this.artList.shift()
      },
      unshift () {
        this.artList.unshift({
          name: 'ziksang3',
          age: 40
        })
      },
      reverse () {
        this.artList.reverse()
      },
      clears () {
        this.artList = []
      },
      splice () {
        // (开始项下标，要删除的几项)
        this.artList.splice(1, 2)
      },

      // ---------------------------------------------------
      filter () {
        this.list = this.list.filter((item) => {
          return item % 2
        })
      },
      concat () {
        // 以上两种方法都可以
        // 1.第一种是es6的解构操作符
        // 2.第二种是传统的数组合并
        this.list = [...this.list, ...this.list2]
        // this.list = this.list.concat(this.list2)
      },
      map () {
        this.list = this.list.map(item => {
          return `${item} map`
        })
      },
      slice () {
        this.list = this.list.slice(2)
      },

      // ---------------------------------------------------
      // 通过下标来改变整个数组里的值也是行不通的
      changeList () {
        this.list[2] = 0
      },
      // 通过数组长度改变改个数组里的值是行不通的
      changeList2 () {
        this.length = 1
      },

      // 第一我们可以通过，vue.set实列方法来改变，但我们要在开头再引一入下vue包
      // 1 第一个值代表需要改变的数组
      // 2 第二个代表改变那一项
      // 3 第三个代表改成什么值
      // 样式语法 Vue.set(example1.items, indexOfItem, newValue)
      change3 () {
        Vue.set(this.list, 2, 0)
      },
      // 通过 Array.prototype.splice 数组原型上的方法来改变整个数组的长度或者内容
      // 这个方法大家肯定常用，我就不细说了
      change4 () {
        this.list.splice(1, 1, 5)
      },

      // -------------------------------------------------
      // 这里就是随着改变data原数组里的值
      randomList () {
        this.list3 = this.list3.map(item => {
          return item + Math.round(Math.random() * 9 + 1)
        })
      }
    },
    // 检测计算 随着原数组用随机数改变的同时，来筛选出不同的过滤结果，筛选结果是原数组的里面为偶数的值拼成一个新数组
    // 1.我们还可以用methods方法，但是不能一直监控
    computed: {
      newList () {
        return this.list3.filter((item) => {
          return item % 2
        })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
