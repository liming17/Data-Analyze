<template>
<div>
    <b-card
        :title="symbol"
        id="mycard"
        align="center"
        >
    <GChart          
      type="CandlestickChart"                       
      :data="chartData"
      :options="chartOptions"
    />
    </b-card>
    <b-card
      header="Overview"
      id="mycard"
      align="center"
      >
        <b-table sticky-header striped hover :items="items"></b-table>
    </b-card>
</div>
    
</template>

<script>
// import Axios from 'axios'
import { GChart } from 'vue-google-charts'
export default {
    components:{
        GChart
    },
    data() {
        return{
            symbol: "",
            items:[],
            chartData: [],
            chartOptions: {
                legend:'none',
                candlestick: {
                    risingColor: {stroke: '#4CAF50', fill: 'white'},
                    fallingColor: {stroke: '#F44336'}
                },
                colors: ['magenta'],
                vAxis:{baseline: 20}
            },
            publicPath: process.env.BASE_URL
        }
    },
    mounted() {
        this.symbol = this.$route.params.id;
        this.getOverview();
        this.getChartReady();
    },
    methods: {
        async getOverview() {
            const res = await fetch(this.publicPath+this.symbol+"_overview.json").catch(err=>{console.log(err)});
            const companyOverview = await res.json();
            var data = [];
            for(var key in companyOverview){
                var row = {index: key, value: companyOverview[key]};
                data.push(row);
            }
            console.log(data);
            this.items= data;
        },
        async getChartReady() {
            const res = await fetch(this.publicPath+this.symbol+"_interdaily.json").catch(err=>{console.log(err)});
            const val = await res.json();
            const interdaily = val["Time Series (Daily)"]
            var data = [];
            var header = [{ type: "string", label: "date", id: "date" },
          { type: "number", label: "low", id: "From" },
          { type: "number", label: "open", id: "open" },
          { type: "number", label: "close", id: "close" },
          { type: "number", label: "high", id: "high" }];
            for(var key in interdaily){
                var row = [key,parseFloat(interdaily[key]["3. low"]),
                parseFloat(interdaily[key]["1. open"]),
                parseFloat(interdaily[key]["4. close"]),
                parseFloat(interdaily[key]["2. high"])];
                data.unshift(row);
            }
           data.unshift(header);
           this.chartData = data;
            }
        }
}
</script>

<style lang="scss" scoped>
#mycard {
  margin-bottom: 2rem;
  margin: auto;
  max-width: 60rem;

}
</style>