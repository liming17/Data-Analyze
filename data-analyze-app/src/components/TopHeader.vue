<template>
    <div>
          <b-navbar toggleable="lg" type="dark" variant="info">
                <b-navbar-brand href="#">DataA.</b-navbar-brand>

                <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

                <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item to="/">Home</b-nav-item>
                    <b-nav-item-dropdown text="Blog" right>
                        <b-dropdown-item v-if="token.length == 0" to="/login">My Blog</b-dropdown-item>
                        <b-dropdown-item v-else to="/blog">My Blog</b-dropdown-item>
                        <b-dropdown-item v-if="token.length == 0" to="/login">Post Blog</b-dropdown-item>
                        <b-dropdown-item v-else to="/postBlog">Post Blog</b-dropdown-item>
                    </b-nav-item-dropdown>
                    <b-nav-item :to="{name: 'Profile', params: {id: '2333'}, query: {plan: 'private'}}">Profile</b-nav-item>
                    <b-nav-item v-if="token.length == 0" to="/login">Login</b-nav-item>
                    <b-nav-item v-else @click="logout()">Logout</b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <b-nav-form @submit.prevent="search">
                    <b-form-input v-model="searchText" size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                    <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                    </b-nav-form>
                </b-navbar-nav>
                </b-collapse>
            </b-navbar>
    </div>
</template>


<script>
import {mapGetters} from "vuex";
export default {
    methods: {
        search() {
            this.$store.dispatch("search", {text: this.searchText});
        },
        logout() {
            this.$store.dispatch("removeToken", {token: this.token});
        }
    },
    data() {
        return{
            searchText:''
        }
    },
    computed: {
         ...mapGetters(["token"])
    }

}
</script>

<style lang="scss" scoped>
#nav a.router-link-exact-active{
    color: white;
}
</style>