<template>
<div>
  <b-card-group deck>
    <b-card
        title="NASDAQ Composite"
        sub-title=".IXIC:NASDAQ"
        align="left"
        id="mychart"
        >
        <GChart
          type="LineChart"
          :data="chartDataN"
          :options="chartOptionsN"
        />
        </b-card>
    <b-card
      header="My Watch List"
      id="mycard"
      >
        <WatchList :key="listIdx"/>
      </b-card>
  </b-card-group>
  <b-card-group deck>
     <b-card
        title=" Dow Jones Industrial Average"
        sub-title="INDEXDJX: .DJI"
        align="left"
        id="mychart"
        >
        <GChart
          type="LineChart"
          :data="chartDataD"
          :options="chartOptionsD"
        />
        </b-card>
    <b-card header="Search Quotes" border-variant="light" id="mycard">
        <StockTbl  v-on:updateWatchList="handleUpdate"/>
      </b-card>
  </b-card-group>
</div>


</template>

<script>
// @ is an alias to /src
import StockTbl from '@/components/StockTbl.vue';
import WatchList from '@/components/WatchList.vue';
import {mapGetters} from "vuex";
import { GChart } from 'vue-google-charts'

export default {
  name: "Home",
  components: {
    'StockTbl' : StockTbl,
    "WatchList" : WatchList,
    GChart
  },
  computed:{
    ...mapGetters(["myData","displayedData","rows"])
  },
  mounted(){
    //this.fetchData();
    this.fetchDow();
    this.fetchNASDAQ();
  },
  data(){
    return{
      currentPage: 1,
      perPage: 3,
      listIdx : 0,
      chartDataD: [],
      chartOptionsD: {
        colors: ['#d95f02'],
        height: 300,
        hAxis: { textPosition: 'none' }
      },
      chartDataN: [],
      chartOptionsN: {
        colors: ['#7570b3'],
        height: 300,
        hAxis: { textPosition: 'none' }
      },
    }
  },
  methods:{
    async fetchData(){
      await this.$store.dispatch("fetchMyData");
    },
    paginate(currentPage){
      this.$store.dispatch("paginate",{currentPage,perPage:this.perPage});
    },
    handleUpdate() {
       this.listIdx++;
       alert(this.listIdx);
    },
    async fetchDow() {
      const res = await fetch("DIA_VWAP.json");
      const val = await res.json();
      const data = val["Technical Analysis: VWAP"];
      var tbl = []
      let tblHeader = ["Time", "VWAP"];
      tbl.push(tblHeader);
      for(var key in data){
        var row = [key,parseFloat(data[key]["VWAP"])];
        tbl.push(row);
      }
      this.chartDataD = tbl;
    },
    async fetchNASDAQ(){
      const res = await fetch("ONEQ_VWAP.json");
      const val = await res.json();
      const data = val["Technical Analysis: VWAP"];
      var tbl = []
      let tblHeader = ["Time", "VWAP"];
      tbl.push(tblHeader);
      for(var key in data){
        var row = [key,parseFloat(data[key]["VWAP"])];
        tbl.push(row);
      }
      this.chartDataN = tbl;
    }
  }
};
</script>

<style lang="scss" scoped>
#footer {
  margin-top: 30px;
}

#mycard {
  width: 40rem; 
  margin-bottom: 2rem;
  margin-left: 2rem;
  margin-right: 2rem;
}
#mychart {
  margin-bottom: 2rem;
  margin-left: 2rem;
}
</style>
