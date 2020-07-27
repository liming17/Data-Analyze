<template>

    <b-container>
      <b-row align-v="center">
        <myCard v-for="data in displayedData" :key="data.id" :name="data.name"/>
      </b-row>
      <b-row class="text-center" align="center"
      justify="center" id="footer">
      <b-col>
      </b-col>
        <b-col>
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          first-text="First"
          prev-text="Prev"
          next-text="Next"
          last-text="Last"
          @input="paginate(currentPage)"
        ></b-pagination>
        </b-col>
        <b-col>
      </b-col>
      </b-row>
    </b-container>

</template>

<script>
// @ is an alias to /src
import Card from '@/components/Card.vue';

export default {
  name: "Home",
  components: {
    'myCard':Card
  },
  mounted(){
    this.fetchData();
  },
  data(){
    return{
      myData:[],
      displayedData:[],
      currentPage: 1,
      rows: 1,
      perPage: 3
    }
  },
  methods:{
    async fetchData(){
      const res = await fetch("data.json");
      const val = await res.json();
      this.myData = val;
      this.displayedData = val.slice(0,3);
      this.rows = this.myData.length;
    },
    paginate(currentPage){
      const start = (currentPage -1)*this.perPage;
      this.displayedData = this.myData.slice(start,start+3);

    }
  }
};
</script>

<style lang="scss" scoped>
#footer{
  margin-top: 30px;
}
</style>