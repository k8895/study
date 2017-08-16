<template>
  <div>
    <p>{{artList.name}}</p>
    <p>{{artList.startTime}}</p>
    <p>{{artList.content}}</p>
    <p>{{artList.good}}</p>
  </div>
</template>

<script>
  export default {
    props: ['artList']
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
