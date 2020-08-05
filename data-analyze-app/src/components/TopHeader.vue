<template>
    <div>
          <b-navbar toggleable="lg" type="dark" variant="info">
                <b-navbar-brand href="#">NavBar</b-navbar-brand>

                <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

                <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item to="/">Home</b-nav-item>
                    <b-nav-item to="/blog">Blog</b-nav-item>
                    <b-nav-item to="/login">Login</b-nav-item>
                    <b-nav-item :to="{name: 'Profile', params: {id: '2333'}, query: {plan: 'private'}}">Profile</b-nav-item>
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
export default {
    methods: {
        search(){
            this.$store.dispatch("search", {text: this.searchText});
        }
    },
    data() {
        return{
            searchText:''
        }
    }

}
</script>

<style lang="scss" scoped>
#nav a.router-link-exact-active{
    color: white;
}
</style>