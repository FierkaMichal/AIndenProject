<template>
  <v-container>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-media
            class="white--text"
            src="lookingPerson.avatar"
            height="500px">
            <v-container fill-height fluid>
              <v-layout fill-height>
                <v-flex xs12 align-end flexbox>
                  <span class="headline">{{ lookingPerson.name }} {{ lookingPerson.surname }}</span>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-media>
          <v-card-title>
            <div>
              <h2>{{ lookingPerson.name }} {{ lookingPerson.surname }}</h2>
            </div>
            <v-container>
              <v-layout row wrap>
                <v-flex>
                  <v-expansion-panel popout>
                    <v-expansion-panel-content>
                      <div slot="header">Description</div>
                      <v-card>
                        <v-card-text>{{ lookingPerson.description }}</v-card-text>
                      </v-card>
                    </v-expansion-panel-content>
                  </v-expansion-panel>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-title>
          <v-card-actions>
            <v-btn flat @click.native="showProfile(lookinPerson.login)">
              <v-icon left>person</v-icon>
                See Profile</v-btn>
            <v-spacer></v-spacer>
            <v-btn flat @click.native="getNextLookingPerson">
              <v-icon left>forward</v-icon>
              Next</v-btn>
            <v-btn flat>
              <v-icon left @click.native="giveLike">whatshot</v-icon>
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
        distKm: 5
      }
    },
    computed: {
      lookingPerson () {
        if (this.$store.getters.lookingPerson === null || this.$store.getters.lookingPerson === undefined) {
          this.$store.dispatch('getNextLookingPerson', {
            lastId: this.$store.getters.lastId,
            longitude: navigator.geolocation.getCurrentPosition.coords.longitude,
            latitude: navigator.geolocation.getCurrentPosition.coords.latitude,
            distKm: this.distKm
          })
        }
        return this.$store.getters.lookingPerson
      }
    },
    methods: {
      getNextLookingPerson () {
        this.$store.dispatch('getNextLookingPerson', {
          lastId: this.$store.getters.lastId,
          longitude: navigator.geolocation.getCurrentPosition.coords.longitude,
          latitude: navigator.geolocation.getCurrentPosition.coords.latitude,
          distKm: this.distKm
        })
      },
      showProfile (login) {
        this.$router.push('/profile/' + login)
      },
      giveLike () {
        this.$store.dispatch('giveLike', {userId: this.lookingPerson.userId})
        this.getNextLookingPerson()
      }
    }
  }
</script>
