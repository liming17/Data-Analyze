<template>
    <div>
        <b-col>
          <b-card
          :title="name"
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Card Img"
          img-top
          tag="article"
          style="max-width:20rem"
          class="mg-2"
          footer="Card Footer"
          >
            <b-card-text>
              This is a card
            </b-card-text>
            <b-button variant="primary">Apply</b-button>
          </b-card>
        </b-col>
    </div>
</template>

<script>
export default {
    props:['name']
}
</script>

<style lang="scss" scoped>

</style>