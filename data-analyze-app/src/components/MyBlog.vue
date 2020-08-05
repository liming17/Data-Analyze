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
            <b-card-text>
              {{body}}
            </b-card-text>
          </b-card>
        </b-col>
    </div>
    
</template>

<script>
export default {
    props:["dateCreated","title","body"]
}
</script>

<style lang="scss" scoped>

</style>