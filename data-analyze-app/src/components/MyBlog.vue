<template>
<div>
        <b-col>
          <b-card
          :title="title"
          :sub-title="dateCreated"
          tag="article"
          style="max-width: 40rem;"
          footer-tag="footer"
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
            :body="comment.text" />
            <template v-slot:footer>
                <b-form inline>
                   <label class="sr-only" for="inline-form-input-name">comment</label>
                    <b-input
                      id="inline-form-input-comment"
                      class="mb-2 mr-sm-2 mb-sm-0"
                      placeholder="Comment here"
                      v-model="text"
                    ></b-input>
                    <b-button variant="primary" @click="submitComment">Comment</b-button>
                </b-form>
            </template>
          </b-card>
        </b-col>
    </div>
    
</template>

<script>
import axios from 'axios';
import Comment from './Comment.vue';
import {mapGetters} from "vuex";
export default {
    props:["dateCreated","title","body","id"],
    components: {
      "Comment": Comment
    },
    computed: {
      ...mapGetters(["token"])
    },
    data() {
      return{
        comments: [],
        text: ""
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
      },
      submitComment(evt) {
        evt.preventDefault();
        const comment = {
          text: this.text,
          postId: this.id
        }
        axios.post("/post/postComment?access_token=" + this.token, comment).then((response)=>{
          if(response.data) {
            alert("You have added a comment!");
            this.text = "";
            this.getComment();
            }else{
              alert("This post doesn't exist! Login again");
            }
        }).catch((err)=>{
          alert(err);
        });
      }
    }
}
</script>

<style lang="scss" scoped>

</style>