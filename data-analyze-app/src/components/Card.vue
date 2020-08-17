<template>
    <div>
        <b-col>
          <b-card
          :title="name"
          :sub-title="lastUpdate"
          class="mb-2"
          tag="article"
          footer="Card Footer"
          align="left"
          >
            <div>
              
            </div>
            

          </b-card>
        </b-col>
    </div>
</template>

<script>
export default {
    props: ["name","symbol"],
    computed: {
      
    },
    data() {
      return {
         lastUpdate: new Date()
      }
    }
}
</script>

<style lang="scss" scoped>

</style>