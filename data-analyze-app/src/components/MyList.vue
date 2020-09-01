<template>
<b-container class="bv-example-row">
     <b-row class="text-center">
        <b-col>
        <b-card no-body header="My Lists" id="listCard">
            <b-list-group>
                <b-list-group-item to="/MyList/0/defaultList">Default Watch List</b-list-group-item> 
                <b-list-group-item v-for="item in list" 
                :key="item.id"
                button
                @click="go(item)">{{ item.listName}}</b-list-group-item>
                <b-list-group-item href="#">Create new List</b-list-group-item>    
            </b-list-group>
        </b-card>
        </b-col>
        <b-col cols="8">
            <router-view />
        </b-col>
    </b-row>
 </b-container>
</template>

<script>
import axios from 'axios';
import {mapGetters} from "vuex";

export default {
    data(){
        return{
           list: [],
        }
    },
    methods: {
    getMyList(){
        axios.get("/stockList/viewMyLists?access_token="+this.token)
             .then(response=>{
                 this.list = response.data;
             }).catch(err=>{
                 console.log(err);
             })
    },
    go(item) {
        this.$router.push({
            path:"/MyList/"+item.id+"/ViewList",
            name: 'ViewList',
            params:{
                id:item.id,
                listName: item.listName,
                stock: item.stocks
            }
        })
    }
    },
    mounted(){
        this.getMyList();
    },
    computed: {
        ...mapGetters(["token"])
    }
}
</script>

<style lang="scss" scoped>
#listCard {
     max-width: 20rem; 
}
</style>