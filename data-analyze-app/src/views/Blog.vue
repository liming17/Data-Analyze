<template>
  <div class="blog">
    <b-container>
      <b-row align-v="center">
        <myBlog v-for="blog in displayedBlogs" :key="blog.id" :id="blog.id" :dateCreated="blog.dateCreated" :title="blog.title" :body="blog.body"/>
      </b-row>
    </b-container>
    <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          first-text="First"
          prev-text="Prev"
          next-text="Next"
          last-text="Last"
          style="margin-top: 5rem;"
          @input="paginate(currentPage)"
          id="myPagination"
        ></b-pagination>
  </div>
</template>

<script>
import axios from 'axios';
import MyBlog from '@/components/MyBlog.vue';
export default {
  components: {
    'myBlog': MyBlog
  },
  data() {
    return{
      blogs:[],
      displayedBlogs:[],
      currentPage: 1,
      perPage: 6,
      rows:0
    }
  },
  mounted(){
    this.getBlogs();
  },
  methods:{
    getBlogs(){
       axios.get("/posts").then(response=>{
         this.blogs = response.data;
         this.paginate(this.currentPage);
         this.rows = response.data.length;
         console.log(this.blogs);
       });
    },
    paginate(currentPage){
      const start = (currentPage-1)*this.perPage;
      this.displayedBlogs = this.blogs.slice(start, start + this.perPage);
    }
  }
}
</script>

<style lang="scss" scoped>
#myPagination {
  position:absolute; 
  bottom: 10em;
  left: 50%;
  transform: translateX(-50%);
}

</style>
