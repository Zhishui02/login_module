<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
// import HomePage from "@/views/HomePage.vue";
export default {
  name: 'App',
  components: {
    // HomePage
  }
}
</script>

<style>
*{
  margin: 0;
  padding: 0;
}
html,body{
  width: 100%;
  height: 100%;
}
#app {
  width: 100%;
  height: 100%;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center
}
</style>
