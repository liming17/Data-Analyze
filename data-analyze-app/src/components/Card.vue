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
              The stock price is: {{price}}
            </b-card-text>
            <b-button v-b-modal="modalId" variant="primary" v-b-tooltip.hover title="view modal here">View</b-button>
            <b-modal :id="modalId" title="This is a modal">
              <p class="my-4"> Hello from modal </p>
            </b-modal>
          </b-card>
        </b-col>
    </div>
</template>

<script>
export default {
    props: ['id','name','price'],
    computed: {
      modalId(){
        return `modal-${this.id}`;
      }
    }
}
</script>

<style lang="scss" scoped>

</style>