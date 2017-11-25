<template>
  <v-container>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-media
            class="white--text"
            src="https://repostuj.pl/upload/2017/08/12/1e160952-7371-4628-922b-633f11767628.jpg"
            height="500px">
            <v-container fill-height fluid>
              <v-layout fill-height>
                <v-flex xs12 align-end flexbox>
                  <span class="headline">Imie nazwisko</span>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-media>
          <v-card-title>
            <div>
              <h2>Imie i nazwisko</h2>
            </div>
            <v-container>
              <v-layout row wrap>
                <v-flex>
                  <v-expansion-panel popout>
                    <v-expansion-panel-content>
                      <div slot="header">Description</div>
                      <v-card>
                        <v-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</v-card-text>
                      </v-card>
                    </v-expansion-panel-content>
                  </v-expansion-panel>
                </v-flex>

              </v-layout>
            </v-container>
          </v-card-title>
          <v-card-actions>
            <v-btn flat>
              <v-icon left>person</v-icon>
                See Profile</v-btn>
            <v-spacer></v-spacer>
            <v-btn flat>
              <v-icon left>forward</v-icon>
              Next</v-btn>
            <v-btn flat>
              <v-icon left>whatshot</v-icon>
              Give Like</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    data () {
      return {

      }
    }
  }
</script>
