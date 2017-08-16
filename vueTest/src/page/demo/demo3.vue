<template>
  <div>
    <h3>操作符</h3>
    <!--触发视图更新-->
    <my-article v-for='item in artList' :art-list='item'></my-article>
    <button @click='push'>添加文章</button>
    <button @click='pop'>尾部去除一篇文章</button>
    <button @click='unshift'>在头部加入一条数据</button>
    <button @click='shift'>在头部去除一条数据</button>
    <button @click='reverse'>把所有数据进行反转</button>
    <button @click='clear'>清除所有数据</button>
    <button @click='splice'>从第2项开始，删除两项</button>
    <br/><br/>

    <ul>
      <li v-for = " (item,index) in list" v-text='`${item} - ${index} `'></li>
    </ul>
    <button @click="filter">数组进行取余过滤</button>
    <button @click="concat">数组进行合并</button>
    <button @click="map">数组印射</button>
    <button @click="slice">返回截取后的数组</button>

  </div>
</template>

<script>
  import myArticle from "../../components/myArticle"

  export default {
    components: {
      myArticle
    },
    created() {
      this.artList = [
        {name: 'ziksang1', startTime: '1小时前', content: 'aaaaaaaaa', good: 1},
        {name: 'ziksang2', startTime: '2小时前', content: 'bbbbbbbbb', good: 2},
        {name: 'ziksang3', startTime: '3小时前', content: 'ccccccccc', good: 3},
        {name: 'ziksang4', startTime: '4小时前', content: 'ddddddddd', good: 4}
      ]
    },
    data() {
      return {
        artList: [],

        list : [ 1, 2, 3, 4],
        list2 : [ 7, 8, 9, 0 ]
      }
    },
    methods: {
      push() {
        this.artList.push({
          name: 'ziksang5',
          startTime: '5小时前',
          content: 'eeeeeeeeee',
          good: 5
        })
      },
      pop() {
        this.artList.pop()
      },
      shift() {
        this.artList.shift()
      },
      unshift() {
        this.artList.unshift({
          name: 'ziksang3',
          age: 40
        })
      },
      reverse() {
        this.artList.reverse()
      },
      clear() {
        this.artList = []
      },
      splice(){
        //(开始项下标，要删除的几项)
        this.artList.splice(1,2)
      },

      //---------------------------------------------------
      filter () {
        this.list = this.list.filter((item)=>{
          return item % 2
        })
      },
      concat () {
        //以上两种方法都可以
        //1.第一种是es6的解构操作符
        //2.第二种是传统的数组合并
        this.list = [...this.list,...this.list2]
        //this.list = this.list.concat(this.list2)
      },
      map () {
        this.list = this.list.map(item =>{
          return `${item} map`
        })
      },
      slice () {
        this.list = this.list.slice(2)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
