<template>
  <div class="demo2">
    <h3>v-for example</h3>
    <ul>
      <!--(1)-->
      <li v-for="(item,index) in memberList" @click="memberDetails(index)">
        <span>{{item.custName}}</span>
        <span>{{item.age}}</span>
        <span>{{item.joinTime}}</span>
      </li>
    </ul>
    <br/>

    <!--(2)如果我们不想多创节点，又不想让p元素做为根节点，我们就可以用template来做渲染，在chorme开发者工具上，也能证实，最外层的的template会自动消失，不会创造出多余的节点。-->
    <template v-for="item in list">
      <p>{{item.content}}</p>
      <img :src="item.img"/>
    </template>
    <br/>

    <!--(3)-->
    <template>
      <p v-for="(value,key,index) in memberDetail">{{key}}:{{value}}</p>
      <p v-for="(value,index) in memberDetail">{{value}}</p>
    </template>
    <br/>

    <!--(4)组件循环复用
    1.首先们引入组件
    2.v-for来循环组件
    3.把每个文章的数据用 :art-list='item'传给myArticle组件-->
    <my-article v-for='item in artList' :art-list='item'></my-article>
    <br/>

  </div>
</template>

<script>
  import myArticle from "../../components/myArticle"

  export default {
    //(4)
    components: {
      myArticle
    },

    created() {
      //(1)就当看作是ajax在初始化进入的时候从后台获取的用户列表数据
      this.memberList = [
        {custName: "ziksang1", age: 20, joinTime: "2014-01-02", custId: 1},
        {custName: "ziksang2", age: 21, joinTime: "2014-01-03", custId: 2},
        {custName: "ziksang3", age: 22, joinTime: "2014-01-04", custId: 3},
        {custName: "ziksang4", age: 23, joinTime: "2014-01-05", custId: 4}
      ]
      //(2)比方说我们这里拿到前面的custId传给后台拿到用户数据
      this.memberDetail = {
        name: 'ziksang',
        age: 20,
        address: "xxx省xxxx市",
        tel: "15921898427"
      }
      //(4)比方说我们是从后台拿到的文章集合
      this.artList = [
        {name: 'ziksang1', startTime: '1小时前', content: 'aaaaaaaaa', good: 1},
        {name: 'ziksang2', startTime: '2小时前', content: 'bbbbbbbbb', good: 2},
        {name: 'ziksang3', startTime: '3小时前', content: 'ccccccccc', good: 3},
        {name: 'ziksang4', startTime: '4小时前', content: 'ddddddddd', good: 4}
      ]
    },
    data() {
      return {
        //(1)
        memberList: [],
        //(2)
        list: [
          {
            content: 'ziksang',
            img: 'https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1301074775,1382810875&fm=80&w=179&h=119&img.JPEG'
          },
          {
            content: 'ziksang2',
            img: 'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1312092207,1376369244&fm=80&w=179&h=119&img.JPEG'
          }
        ],
        //(3)
        memberDetail: {},
        //(4)
        artList : []
      }
    },
    methods: {
      //(1)
      memberDetails(index) {
        sessionStorage.custId = this.memberList[index].custId
        console.log(sessionStorage.custId)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
