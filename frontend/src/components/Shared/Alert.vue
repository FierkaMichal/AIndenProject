<template>
  <v-alert :color=type dismissible @input="onClose" :value="true">
    {{ text }}
  </v-alert>
</template>

<script>
  export default {
    props: [
      'text',
      'type'
    ],
    methods: {
      onClose () {
        this.$emit('dismissed')
      }
    }
  }
</script>
