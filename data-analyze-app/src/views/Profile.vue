<template>
    <div>
        <h3>Profile {{$route.params.id}}</h3>
        <h4>{{$route.query}}</h4>
        <router-link to="/profile/35/info">nested router</router-link>
        <router-view />
    </div>
</template>

<script>
export default {
    
}
</script>

<style scoped>

</style>