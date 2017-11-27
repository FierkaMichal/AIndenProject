<template>
  <v-container>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3 mt-4>
        <v-slider
          label="Distance"
          hint="Distance to search for people"
          min="1"
          max="10000000"
          thumb-label
          v-model="distKm"
        ></v-slider>
      </v-flex>
      <v-flex p>
        <v-btn flat @click.native="getNextLookingPerson()">
          Search
        </v-btn>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3 mt-4>
        <v-card v-if="havePersonInDistance">
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
          this.getNextLookingPerson()
        }
        return this.$store.getters.lookingPerson
      },
      havePersonInDistance () {
        if (this.$store.getters.lookingPerson === null || this.$store.getters.lookingPerson === undefined) {
          return false
        }
        return true
      }
    },
    methods: {
      getNextLookingPerson () {
        this.$store.dispatch('getNextLookingPerson', {
          lastId: this.$store.getters.lastId,
          longitude: this.$store.getters.position.coords.longitude,
          latitude: this.$store.getters.position.coords.latitude,
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
