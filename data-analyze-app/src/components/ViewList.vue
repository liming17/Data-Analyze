<template>
  <b-card :header="listName">
    <b-list-group>
      <b-list-group-item v-for="s in stock"
      :key="s.id">{{s.symbol}}</b-list-group-item>
    </b-list-group>
    <b-button @click="go()" pill variant="primary" style="margin-top:10px;">Add Stock to List</b-button>
  </b-card>
    
</template>

<script>
export default {
    props:['id','listName','stock'],
    data(){
        return{

        }
    },
    methods: {
        go() {
            this.$router.push({
                name: "AddStockToList",
                params:{
                    id: this.id,
                    listName: this.listName,
                    stock: this.stock
                }
            });
        }
    }
}
</script>

<style lang="scss" scoped>

</style>