<template>
  <b-card :header="listName">
    <b-list-group>
      <b-list-group-item v-for="s in stock"
      :key="s.id">{{s.symbol}}</b-list-group-item>
    </b-list-group>
  </b-card>
    
</template>

<script>
export default {
    props:['id','listName','stock'],
    data(){
        return{

        }
    }
}
</script>

<style lang="scss" scoped>

</style>