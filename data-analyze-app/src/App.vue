<template>
  <div id="app">
    <div id="nav">
      <top-header/>
    </div>
    <router-view />
  </div>
</template>

<script>
import TopHeader from '@/components/TopHeader.vue';
export default {
  components:{
    'top-header' : TopHeader
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 0px;
  margin-bottom: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
