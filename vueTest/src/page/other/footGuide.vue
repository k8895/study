<template>
  <yd-tabbar fixed id='foot_guide'>
    <yd-tabbar-item title="首页" link="#">
      <yd-icon name="home" slot="icon" size="0.54rem"></yd-icon>
    </yd-tabbar-item>
    <yd-tabbar-item title="购物车" link="#" :active="active">
      <yd-icon name="shopcart" slot="icon" size="0.54rem"></yd-icon>
    </yd-tabbar-item>
    <yd-tabbar-item title="个人中心" link="#">
      <yd-icon name="ucenter-outline" slot="icon" size="0.54rem"></yd-icon>
    </yd-tabbar-item>
  </yd-tabbar>
</template>

<script>
  export default {
    data() {
      return {
        active: false
      }
    },
    methods: {
      toVgou: function () {
        console.log("test")
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
