<template>
<div>
        <b-col>
          <b-card
          :title="title"
          :sub-title="dateCreated"
          tag="article"
          style="max-width: 20rem;"
          footer="Card Footer"
          class="mb-2"
          align="left"
          >
            <div>
              {{body}}
            </div>
            <Comment v-for="comment in comments" 
            :key="comment.id" 
            :id="comment.id" 
            :user="comment.post.creator.username"
            :body="comment.text">
          </b-card>
        </b-col>
    </div>
    
</template>

<script>
import axios from 'axios';
import Comment from 'Comment.vue';
export default {
    props:["dateCreated","title","body","id"],
    components: {
      "Comment": Comment
    },
    data() {
      return{
        comments: []
      }
    },
    mounted() {
      this.getComment()
    },
    methods: {
      getComment() {
          axios.get("/comments/" + this.id).then((response)=>{
             this.comments = response.data;
          }).catch((err)=>{
            console.log(err);
          })
      }
    }
}
</script>

<style lang="scss" scoped>

</style>