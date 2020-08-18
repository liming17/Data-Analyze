<template>
<div>
    <div>
        <b-form inline id="searchForm">
            <label class="sr-only" for="inline-form-input-search">search</label>
            <b-input
            id="inline-form-input-search"
            class="mb-2 mr-sm-2 mb-sm-0"
            placeholder="search quotes"
            v-model="searchText"
            ></b-input>
            <b-button variant="primary" @click="search">Search</b-button>
        </b-form>
    </div>
    <div>
        <b-table sticky-header striped hover :items="items" :fields="fields" id="resultTable">
             <template v-slot:cell(actions)="row">
                <b-button size="sm" @click="addStock(row.item)" class="mr-1">
                Add Stock to My Watch List
                </b-button>
            </template>
        </b-table>
    </div>
</div>
    
</template>

<script>
import axios from 'axios';
import {mapGetters} from "vuex";

export default { 
    data() {
        return {
           searchText:"",
           items:[],
           fields: [
               "symbol",
               "name",
               {key: "actions", label: "Actions"}
           ]
        }
    }  , 
    methods:{
        search(evt) {
           evt.preventDefault();
           var stocks = [];
           axios.get(this.API_prefix + "function=SYMBOL_SEARCH&datatype=json&keywords=" + this.searchText + "&apikey=" + this.API_token
            ).then(response=>{
               console.log(response.data.bestMatches);
               var data = response.data.bestMatches;
               data.forEach(element => {
                   var stock = {name: element["2. name"], symbol: element["1. symbol"]};
                   stocks.push(stock);
               });
               this.items = stocks;
            }).catch(err => {
                console.log(err);
            });
        },
        addStock(item) {
        var stock = {symbol : item.symbol, name: item.name, price : 0}
        axios.post("/stock/saveStock",stock).then((response)=>{
                alert(response.data);
                this.$emit('updateWatchList');
            }).catch((err)=>{
                alert(err);
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
        // searchResults: function(val) {
        //     var promises = [];
        //     var name = [];
        //     val.forEach(element => { 
        //         name.push(element["2. name"])
        //         var promise = axios.get(this.API_prefix + "function=GLOBAL_QUOTE&symbol="+ element["1. symbol"] + "&apikey=" + this.API_token);
        //         promises.push(promise);
        //     });
        //     axios.all(promises)
        //          .then(response=>{
        //         console.log(response);
        //         var stock_price = [];
        //         var name_idx = 0;
        //         response.forEach(element => {
        //             var data = element.data["Global Quote"];
        //             if(data != null && Object.prototype.hasOwnProperty.call(data, '01. symbol')){
        //                 var stockName = name[name_idx];
        //                 var stock = {symbol : data["01. symbol"], name: stockName, price : data["05. price"], change_percent : data["10. change percent"]};
        //                 stock_price.push(stock);
        //             }
        //             name_idx++;
        //         });
        //         this.items = stock_price;
        //         }).catch(err => {
        //             console.log(err);
        //         });
        // }
    },
    computed: {
        ...mapGetters(["API_token","API_prefix","token"])
    }
    
}
</script>

<style lang="scss" scoped>
#searchForm {
   margin-bottom: 2rem;
}


</style>