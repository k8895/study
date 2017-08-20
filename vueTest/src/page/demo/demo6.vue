<template>
  <div>
    <h4>class与style绑定 : https://juejin.im/post/58ef1cc60ce463006b95fb8a</h4>
    <br/>
    <ul>
      <li v-for='(item,index) in message' @click='item.isred = !item.isred' :class='{ red : item.isred }'>
        {{item.name}}
      </li>
    </ul>
    <button :class='allchoose'>必须全选才可以提交</button>
    <br/><br/>

    <ul>
      <li v-for='(item,index) in message' @click='item.isred = !item.isred' :class="['red',sizeClass]">{{item.name}}
      </li>
    </ul>
    <br/><br/>

    <ul>
      <li v-for='(item,index) in message' @click='item.isred = !item.isred' :class="[item.isred == true?'red':'green']">
        {{item.name}}
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    created () {
      let data = [
        {name: '1111111111111'},
        {name: '2222222222222'},
        {name: '3333333333333'}
      ]
      for (let i of data) {
        // 在对象中设置一个对象里的属性，没有就添加，有就修改
        this.$set(i, 'isred', false)
      }
      this.message = data
    },
    data () {
      return {
        message: [],

        sizeClass: 'size'
      }
    },
    computed: {
      allchoose () {
        for (let i of this.message) {
          if (i.isred === false) {
            return {yellow: false}
          }
        }
        return {yellow: true}
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .red {
    background: red;
  }

  button {
    background: gray;
  }

  .yellow {
    background: yellow
  }

  .size {
    font-size: 20px;
  }
</style>
