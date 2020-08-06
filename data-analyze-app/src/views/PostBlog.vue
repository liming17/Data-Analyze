<template>
    <b-card
          header-tag="header" 
          tag="article"
          footer-tag="footer"
          class="mb-2"
          style="max-width: 40rem;
           left: 50%;
           transform: translateX(-50%);"
    >
    <template v-slot:header>
        <h5 class="mb-0" align="left">Share Your thoughts</h5>
    </template>
    <div align="left">
    <b-form>
        <label class="sr-only" for="inline-form-input-name">Title</label>
        <b-input
            id="form-input-title"
            class="mb-2 mr-sm-2 mb-sm-0"
            placeholder="Title"
            required
            v-model="title"
        ></b-input> 
        <b-form-textarea
            id="textarea-rows"
            placeholder="Today..."
            required
            v-model="text"
            rows="8"
            style="margin-top: 3px;"
        ></b-form-textarea>
    </b-form> 
    </div>
    <template v-slot:footer>
        <div align="right">
            <b-button type="submit" @click="onSubmit" variant="primary" style="margin-right: 3px;">Post</b-button>
        </div>
    </template>
</b-card>
    
</template>

<script>
import axios from 'axios';
import {mapGetters} from "vuex";
export default {
    data() {
        return {
            title: "",
            text: ""
        }
    },
    methods: {
        onSubmit(evt) {
            evt.preventDefault();
            const data = {
                title: this.title,
                body: this.text
            }
            axios.post("/post?access_token=" + this.token,data).then((response)=>{
                alert(response.data);
                this.$router.push({path : "/blog"});
            }).catch((err)=>{
                alert(err);
                console.log(err);
            });
        }
    },
    computed: {
        ...mapGetters(["token"])
    }
}
</script>

<style lang="scss" scoped>

</style>