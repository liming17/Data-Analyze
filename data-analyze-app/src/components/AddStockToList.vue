<template>
  <b-card-group deck>
    <b-card :header="listName" id="mylist">
        <b-list-group>
        <b-list-group-item v-for="s in stock"
        :key="s.id">{{s.symbol}}</b-list-group-item>
        </b-list-group>
        </b-card>
    <b-card
      header="Choose Stocks from My Watch List"
      id="mycard"
      >
        <WatchList v-on:triggerItem="trigger" :addTrigger="true"/>
      </b-card>
  </b-card-group>
    
</template>

<script>
import WatchList from '@/components/WatchList.vue';
import {mapGetters} from "vuex";
export default {
    components:{
        "WatchList" : WatchList
    },
    props:['id','listName','stock'],
    data() {
        return {
           stocksToBeAdded: []
        }
    },
    computed: {
        ...mapGetters(["token"])
    },
    methods: {
       trigger(item) {
           this.stocksToBeAdded = item;
           alert(this.stocksToBeAdded);
           //axios.post
       }
    }
}
</script>

<style lang="scss" scoped>
#mylist {
    max-width: 25rem;
    margin-left: 13px;
}
</style>