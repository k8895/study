<template>
  <div>
    <h1 v-if="result == 'No'">申请参与</h1>
    <h1 v-else>取消参与</h1>
    <br/><br/>

    <h4>v-for 加载模版</h4>
    <div v-for='(item,index) in insurance'>
      <template v-if='nowTime < item.endtime'>
        <p>{{item.code}}</p>
        <p>{{item.name}}</p>
        <p>{{item.tel}}</p>
      </template>
      <!--<br/>-->
      <template v-else-if='nowTime > item.endtime'>
        <button @click='item.arrow = !item.arrow'>展开/收缩过期详情</button>
        <p>{{item.code}}</p>
        <p v-show='item.arrow == true'>{{item.name}}</p>
        <p v-show='item.arrow == true'>{{item.tel}}</p>
      </template>
      <br/>
    </div>
  </div>
</template>

<script>
  export default {
    created () {
      let myDate = new Date()
      let year = myDate.getFullYear() // 获取完整的年份(4位,1970-????)
      let month = myDate.getMonth() + 1 // 获取当前月份(0-11,0代表1月)
      let day = myDate.getDate() // 获取当前日(1-31)
      if (day < 10) {
        day = '0' + day
      }
      if (month < 10) {
        month = '0' + month
      }
      this.nowTime = `${year}-${month}-${day}`
      var insurance = [
        {endtime: '2017-02-01', code: '111111111', name: 'ziksang', tel: '1000000000', arrow: false},
        {endtime: '2017-02-03', code: '222222222', name: 'ziksang2', tel: '2000000000', arrow: false},
        {endtime: '2017-09-10', code: '333333333', name: 'ziksang3', tel: '3000000000', arrow: false},
        {endtime: '2999-02-01', code: '444444444', name: 'ziksang4', tel: '4000000000', arrow: false}
      ]
      this.insurance = insurance
    },
    data () {
      return {
        result: 'No',

        nowTime: '',
        insurance: []
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
