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
        <h5 class="mb-0" align="left">Register</h5>
      </template>
    
    <div align="left">
     <b-form @submit="onSubmit" @reset="onReset">
      <b-form-group
        id="input-group-1"
        label="Username:"
        label-for="input-1"
        description="Input your username."
      >
        <b-form-input
          id="input-1"
          type="text"
          v-model="username"
          :state="validationUser"
          required
          placeholder="Enter username"
        ></b-form-input>
         <b-form-invalid-feedback :state="validationUser">
        Username should have a minimum length of 3.
        </b-form-invalid-feedback>
        <b-form-valid-feedback :state="validationUser">
        Looks Good.
        </b-form-valid-feedback>
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

        <b-form-group id="input-group-3" label="Confirm Password:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="passwordConfirmation"
          :type="passwordFieldType"
          :state="validation"
          required
          placeholder="Enter password again"
        ></b-form-input>
        <b-form-invalid-feedback :state="validation">
        It should be same with the password and have a minimum length of 6.
        </b-form-invalid-feedback>
        <b-form-valid-feedback :state="validation">
        Looks Good.
        </b-form-valid-feedback>
        </b-form-group>

        <b-form-group id="checkboxes-4">
        <b-form-checkbox-group v-model="show" id="checkboxes-4">
          <b-form-checkbox value="show">Show password</b-form-checkbox>
        </b-form-checkbox-group>
      </b-form-group>


      <b-button type="reset" variant="danger">Reset</b-button>
    
    </b-form> 
    </div>

      
      <template v-slot:footer>
        <div align="right">
            <b-button type="submit" @click="onSubmit" variant="primary" style="margin-right: 3px;">Sign Up</b-button>
        </div>
      </template>
    </b-card>
 </div>
    
</template>

<script>
import axios from 'axios';
export default {
     data() {
       return{
           username: "",
           password: "",
           passwordConfirmation:"",
           passwordFieldType: 'password',
           show:[]
       }
    },
    methods:{
        onSubmit(evt) {
            evt.preventDefault();
            const data = {
                username: this.username,
                password: this.password,
                passwordConfirmation: this.passwordConfirmation
            }
            axios.post("/register",data).then(response =>{
                console.log(response.data);
                this.$router.push({ path: '/login' });
                // document.location.replace("/login");
            })
        },
        onReset(evt) {
            evt.preventDefault();
            this.username = "";
            this.password = "";
            this.confirmPassword = "";
        }

    },
    watch: {
        show: function(val) {
            this.passwordFieldType = val.length === 0 ? 'password' : 'text';
        }
    },
    computed: {
        validation() {
        return (this.password == this.passwordConfirmation) && this.passwordConfirmation.length >= 6;
      },
      validationUser() {
          return this.username.length >= 3;
      }
    }
    
}
</script>

<style lang="scss" scoped>

</style>