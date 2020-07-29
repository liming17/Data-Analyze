<template>
  <div id="app">
    <div class="page" v-if="showSpinner">
       <b-spinner class="spinner" key="primary" variant="primary" label="Spinning"></b-spinner>
    </div>
    <div id="nav">
      <top-header/>
    </div>
    <router-view />
  </div>
</template>

<script>
import TopHeader from '@/components/TopHeader.vue';
import { mapGetters } from 'vuex';
export default {
  components:{
    'top-header' : TopHeader
  },
  computed: {
    ...mapGetters(["showSpinner"])
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

  .page {
    position: absolute;
    background: rgba(0, 0 , 0, 0.3);
    z-index: 25;
    width: 100%;
    height: 100%;
  }

  .spinner{
    position: relative;
    top: 50%;
  }
</style>
