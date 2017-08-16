<template>
  <div class="other" id="app">
    <p>OtherPage</p>
    <router-view></router-view>
    <router-view name="othera"></router-view>
    <router-view name="otherb"></router-view>
  </div>
</template>

<script>
  export default {
    name: 'other',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
