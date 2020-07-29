<template>

    <b-container>
      <b-row align-v="center">
        <myCard v-for="data in displayedData" :key="data.id" :id="data.id" :name="data.name" :price="data.price"/>
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
import {mapGetters} from "vuex";

export default {
  name: "Home",
  components: {
    'myCard':Card
  },
  computed:{
    ...mapGetters(["myData","displayedData","rows"])
  },
  mounted(){
    this.fetchData();
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
</style>
