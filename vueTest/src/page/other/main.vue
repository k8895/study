<template>
  <div class="main">
    <router-view></router-view>
    <foot-guide></foot-guide>
  </div>
</template>

<script>
  import footGuide from './footGuide'

  export default {
    name: 'main',
    data() {
      return {

      }
    },
    components: {
      footGuide
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
