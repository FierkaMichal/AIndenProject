<template>
  <v-container>
    <v-layout row wrap>
      <v-flex xs12 sm6 text-xs-center text-sm-left>
        <v-avatar size="100px">
          <img src="http://www.kenia1100.pl/wp-content/uploads/2016/07/pikatchu-pokemon-300x300.png" alt="avatar">
        </v-avatar>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IMIE I NAZWISKO
      </v-flex>
      <v-flex xs12 sm6 text-xs-center text-sm-right>
        <v-btn flat>
          <v-icon left>mode_edit</v-icon>
          Edit
        </v-btn>
      </v-flex>
    </v-layout>
    <v-layout>
      <v-flex xs12 text-xs-center mt-4>
        <v-text-field
          disabled
          v-model="description"
          label="Description"
          multi-line
          rows="1"
          auto-grow
          elevation-22>
        </v-text-field>
      </v-flex>
    </v-layout>
    <v-layout row wrap>
      <v-flex xs12 text-xs-center mt-4>
        <v-carousel>
          <v-carousel-item v-for="(item,i) in items" v-bind:src="item.src" :key="i"></v-carousel-item>
        </v-carousel>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    data () {
      return {
        description: 'sfasasdfas',
        items: [
          {
            src: 'http://www.kenia1100.pl/wp-content/uploads/2016/07/pikatchu-pokemon-300x300.png'
          },
          {
            src: 'http://segritta.pl/wp-content/uploads/2016/08/landscape-1456483171-pokemon2.jpg'
          }
        ]
      }
    }
  }
</script>
