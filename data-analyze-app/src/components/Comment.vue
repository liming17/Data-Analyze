<template>
<div align="left">
    <hr>
    <h4>{{user}}</h4>
    <p id="body">{{body}}</p>
</div>
</template>

<script>
export default {
    props:["user","body"],
    
}
</script>

<style lang="scss" scoped>
h4 {
   font-size: 15px;
   color:#1a74a3; 
}

#body {
   font-size: 15px;
}


</style>