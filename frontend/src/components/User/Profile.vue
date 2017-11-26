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
        <v-dialog v-model="dialog" persistent max-width="500px">
          <v-btn slot="activator" flat>
            <v-icon left>mode_edit</v-icon>
            Edit profile
          </v-btn>
          <v-card>
            <v-card-title>
              <span class="headline">My Profile</span>
            </v-card-title>
            <v-card-text>
              <v-container grid-list-md>
                <v-layout wrap>
                  <v-flex xs12>
                    <v-text-field label="Name" required></v-text-field>
                  </v-flex>
                  <v-flex xs12>
                    <v-text-field label="Surname" required></v-text-field>
                  </v-flex>
                  <v-flex xs12>
                    <v-text-field
                      v-model="description"
                      label="Description"
                      placeholder="Set description"
                      multi-line
                      rows="3"
                      auto-grow>
                    </v-text-field>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" flat @click.native="dialog = false">Close</v-btn>
              <v-btn color="blue darken-1" flat @click.native="dialog = false">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-flex>
    </v-layout>
    <v-layout>
      <v-flex xs12 text-xs-center mt-4>
        <v-text-field
          readonly
          v-model="description"
          label="Description"
          placeholder="Click edit to set description"
          multi-line
          rows="1"
          auto-grow>
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
        dialog: false,
        description: null,
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
