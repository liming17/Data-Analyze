<template>
 <div>
    <b-card  
    header-tag="header" 
    footer-tag="footer"
    class="mb-2"
    style="max-width: 30rem;
           left: 50%;
           transform: translateX(-50%);">
      <template v-slot:header>
        <h5 class="mb-0" align="left">Login</h5>
      </template>
    
    <div align="left">
     <b-form @submit="onSubmit" @reset="onReset">
      <b-form-group
        id="input-group-1"
        label="Username:"
        label-for="input-1"
        description="Login with your username."
      >
        <b-form-input
          id="input-1"
          type="text"
          v-model="username"
          required
          placeholder="Enter username"
        ></b-form-input>
        </b-form-group>

         <b-form-group id="input-group-2" label="Password:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="password"
          :type="passwordFieldType"
          required
          placeholder="Enter password"
        ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-3">
        <b-form-checkbox-group v-model="show" id="checkboxes-3">
          <b-form-checkbox value="show">Show password</b-form-checkbox>
        </b-form-checkbox-group>
      </b-form-group>


      <b-button type="reset" variant="danger">Reset</b-button>
    
    </b-form> 
    </div>

      
      <template v-slot:footer>
        <div align="right">
            <b-button type="submit" @click="onSubmit" variant="primary" style="margin-right: 3px;">Sign In</b-button>
            <b-button to="/register" variant="info">Sign Up</b-button>
        </div>
      </template>
    </b-card>
 </div>
</template>

<script>
import axios from 'axios';
import {mapGetters} from "vuex";
export default {
    data() {
       return{
           username: "",
           password: "",
           passwordFieldType: 'password',
           show:[]
       }
    },
    methods:{
        onSubmit(evt) {
            evt.preventDefault();
            var params = new URLSearchParams();
            params.append('grant_type', 'password');
            params.append('username', this.username);
            params.append('password',this.password);
            axios({
                method:'post',
                url:'/oauth/token',
                auth:{username:'my-trusted-client',password:'secret'},
                headers: {"Content-type": "application/x-www-form-urlencoded; charset=utf-8"},
                data:params
            }).then(response => {

               this.$store.dispatch("setToken", {token: response.data.access_token}).then(()=>{
                   console.log(this.token);
                   this.$router.push({ path: '/' });
               });

            }).catch((err)=>{
                alert("fail to login");
                console.log(err);
            });

        },
        onReset(evt) {
            evt.preventDefault();
            this.username = "";
            this.password = "";
        }

    },
    watch: {
        show: function(val) {
            this.passwordFieldType = val.length === 0 ? 'password' : 'text';
        }
    },
    computed: {
         ...mapGetters(["token"])
    }
    
}
</script>

<style lang="scss" scoped>

</style>