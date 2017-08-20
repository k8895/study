<template>
  <div>
    <h2>计算属性/computed/watch</h2>

    <h4>---------------------------------</h4>
    <p @click="count++">{{count + '分'}}</p>
    <input v-model='message'>
    <p>{{message.split('').reverse().join('') }}</p>
    <br/>

    <!--使用filters：
    用filter的优势
    filter给我们用于计算和过滤一些模板表达式和v-bind:属性表达式一些弊端的地方进行计算，他会返回当前计算的值，可以进行传参在多地方共用这个过滤方法，
    劣势
    如果我们要计算多个数据不同变化结合而成的地方那filter就能难过到了，本质上filter我认为就是1v1,对单个数据进行过滤，可以进行传参，同方法，但不同参，非常适用-->
    <input v-model='message2'>
    <p>{{message2 | reverseString}}</p>
    <br/><br/>


    <h4>---------------------------------</h4>
    <h4>computed 依赖方法里所有的数据，只要一个数据发生变化，则会从新计算，来更新视图的改变</h4>
    <textarea v-model="content" :maxlength="totalcount"></textarea>
    <p>你还可以输入{{reduceCount}}字</p>
    <br/>
    <h1>比赛时间：{{time}}s</h1>
    <h2>直播播报：{{result}}</h2>
    <div>
      <p>中国队进球数：{{team.china}}</p>
      <button @click="team.china++">点击中国队进一球</button>
      <p>韩国队进球数：{{team.korea}}</p>
      <button @click="team.korea++">点击韩国队进一球</button>
    </div>
    <br/><br/>


    <h4>-------------------------------------</h4>
    <h4>watch</h4>
    <div v-show=show>
      <img src="https://img.alicdn.com/simba/img/TB14sYVQXXXXXc1XXXXSutbFXXX.jpg" alt="">
      <img src="//img.alicdn.com/tfs/TB1iZ6EQXXXXXcsXFXXXXXXXXXX-520-280.jpg_q90_.webp" alt="">
      <img src="https://img.alicdn.com/simba/img/TB1C0dOPXXXXXarapXXSutbFXXX.jpg" alt="">
      <img src="//img.alicdn.com/tfs/TB1iZ6EQXXXXXcsXFXXXXXXXXXX-520-280.jpg_q90_.webp" alt="">
    </div>


  </div>
</template>

<script>
  export default {
    created () {
      let time = setInterval(() => {
        this.time++
        if (this.time === 90) {
          clearInterval(time)
        }
      }, 1000)
    },
    data () {
      return {
        count: 0,
        message: '',
        message2: '',

        totalcount: 200, // 总共只给输入200字
        content: '',

        time: 0,
        team: {
          china: 0,
          korea: 0
        },

        show: false
      }
    },
    filters: {
      reverseString (value) {
        if (!value) return ''
        value = value.split('').reverse().join('')
        return value
      }
    },
    computed: {
      reduceCount () {
        return this.totalcount - this.content.length
      },

      result () {
        if (this.time < 90) {
          if (this.team.china > this.team.korea) {
            return '中国队领先'
          } else if (this.team.china < this.team.korea) {
            return '韩国队领先'
          } else {
            return '双方僵持'
          }
        } else {
          if (this.team.china > this.team.korea) {
            return '中国队赢'
          } else if (this.team.china < this.team.korea) {
            return '韩国队赢'
          } else {
            return '平局'
          }
        }
      }
    },
    // -----------------------------------------------------
    mounted () {
      let imgs = document.querySelectorAll('img')
      console.log(imgs)
      Array.from(imgs).forEach((item) => {
        let img = new Image()
        img.onload = () => {
          this.count++
        }
        img.src = item.getAttribute('src')
      })
    },
    watch: {
      count (val, oldval) {
        if (val === 4) {
          this.show = true
          alert('加载完毕')
          // 然后可以对后台发送一些ajax操作
        }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
