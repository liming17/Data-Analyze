<template>

    <div>
      <b-card
      header="My Watch List"
      id="mycard"
      >
        <WatchList/>
      </b-card>

      <b-card header="Search Quotes" border-variant="light" id="mycard">
        <StockTbl/>
      </b-card>
     

    </div>

</template>

<script>
// @ is an alias to /src
import StockTbl from '@/components/StockTbl.vue';
import WatchList from '@/components/WatchList.vue';
import {mapGetters} from "vuex";

export default {
  name: "Home",
  components: {
    'StockTbl' : StockTbl,
    "WatchList" : WatchList
  },
  computed:{
    ...mapGetters(["myData","displayedData","rows"])
  },
  mounted(){
    //this.fetchData();
  },
  data(){
    return{
      currentPage: 1,
      perPage: 3
    }
  },
  methods:{
    async fetchData(){
      await this.$store.dispatch("fetchMyData");
    },
    paginate(currentPage){
      this.$store.dispatch("paginate",{currentPage,perPage:this.perPage});
    }
  }
};
</script>

<style lang="scss" scoped>
#footer {
  margin-top: 30px;
}

#mycard {
  width: 50rem; 
  margin-bottom: 2rem;
  margin-left: auto;
  margin-right: auto;
}
</style>
