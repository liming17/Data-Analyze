<template>
<div>
    <b-table sticky-header striped hover :items="items" :fields="fields" id="resultTable">
             <template v-slot:cell(view)="row">
                <b-button size="sm" @click="viewStock(row.item)" class="mr-1">
                 Overview
                </b-button>
            </template>
             <template v-slot:cell(actions)="row">
                <b-button size="sm" @click="addStock(row.item)" class="mr-1">
                Choose
                </b-button>
            </template>
    </b-table>   

</div>
    
</template>

<script>
import axios from 'axios';
import {mapGetters} from "vuex";

export default {
    data() {
        return {
           items:[],
           fields: [
               "symbol",
               "name",
               {key: "price", sortable: true},
               {key: "change_percent", sortable: true, tdClass: "setTdClass" },
               {key: "view", label: "View"},
               {key: "actions", label: "Actions"}
           ],
           myStocks: []
        }
    },
    mounted() {
        this.getData();
    },
    methods: {
        viewStock(item){
          alert(item);
          this.$router.push('/stockOverview/' + item.symbol);
        },
        addStock(item){
            alert(item);
            // go to add to list component
        },
        getData() {
           axios.get("/stock/viewAllStocks").then(response=>{
               this.myStocks = response.data;
           }).catch(err => {
                    console.log(err);
                });
        },
        setTdClass(value) {
        var firstChar = value.charAt(0);
        if(firstChar === '-')
            return 'text-danger'
        else
            return 'text-success'
        }

    },
    watch: {
        myStocks: function(val){
            var promises = [];
            var stocks = [];
            val.forEach(element => {
                stocks.push({id:element.id, name:element.name});
                var promise = axios.get(this.API_prefix + "function=GLOBAL_QUOTE&symbol="+ element.symbol + "&apikey=" + this.API_token);
                promises.push(promise); 
            });
            axios.all(promises)
                 .then(response=>{
                console.log(response);
                var stock_price = [];
                var name_idx = 0;
                response.forEach(element => {
                    var data = element.data["Global Quote"];
                    if(data != null && Object.prototype.hasOwnProperty.call(data, '01. symbol')){
                        var stockName = stocks[name_idx].name;
                        var stockId = stocks[name_idx].id;
                        var stock = {id: stockId, symbol : data["01. symbol"], name: stockName, price : data["05. price"], change_percent : data["10. change percent"]};
                        stock_price.push(stock);
                    }
                    name_idx++;
                });
                this.items = stock_price;
                }).catch(err => {
                    console.log(err);
                });
        }
    },
    computed: {
        ...mapGetters(["API_token","API_prefix","token"])
    }
}
</script>

<style lang="scss" scoped>

</style>