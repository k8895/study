<template>
  <div class="demo1">
    <p v-if="isSee">show</p>
    <br/>


    <ol>
      <li v-for="todo in todos">
        {{ todo.text }}
      </li>
    </ol>
    <br/>


    <p>{{ msg }}</p>
    <button v-on:click="setMsg">change</button>
    <input v-model="msg">
    <br/>


    <do-item></do-item>

    <props_item
      v-for="item in todos"
      v-bind:param="item"
      v-bind:key="item.id">
    </props_item>
    <br/>


    <a :href='href'>href</a>
    <p :id='id'>id</p>
    <img :src="path" alt="图片">
    <button :disabled='disabled'>按钮</button>
    <br/><br/>


    <p>{{count + 1}}</p>
    <p>{{ count < 0 ? '负数' : '正数'}}</p>
    <button @click='count ++'>增加</button>
    <button @click='count --'>减少</button>
    <br/><br/>


    //过滤
    <p v-for='tel in telephone'>{{tel | newTel}}</p>
    <br/>

    <a v-for='(newUrl,index) in urlList' :href='newUrl.url | getQuery(newUrl.name,newUrl.age)'>{{newUrl.url}}</a>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        isSee: true,
        todos: [
          {text: '学习 JavaScript'},
          {text: '学习 Vue'},
          {text: '整个牛项目'}
        ],
        msg: 'hello',

        id: 2,
        href: 'http://www.baidu.com',
        path: '',
        disabled: true,

        count: 0,

        telephone: [
          10000000000,
          10000000001,
          10000000002,
          10000000003,
          10000000004,
          10000000005,
          10000000006,
          10000000007,
        ],
        urlList: [
          {url: 'http://www.baidu.com', name: 'ziksang', age: 20},
          {url: 'http://www.google.com', name: 'ziksang2', age: 30}
        ]
      }
    },
    methods: {
      setMsg: function () {
        this.msg = 'new hello'
      }
    },
    filters: {
      newTel(value) {
        if (!value) return ''
        value = value.toString().substr(7, 4)
        value = '*'.repeat(7) + value
        let endMember = value.substr(-1, 1)
        if (endMember % 2) {
          value = value + '中奖'
        } else {
          value = value + '谢谢参与'
        }
        return value
      },
      getQuery(value, name, age) {
        if (!value) return ""
        return `${value}?name=${name}&age=${age}`
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
