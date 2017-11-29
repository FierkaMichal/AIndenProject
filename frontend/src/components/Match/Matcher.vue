<template>
  <v-container>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3 mt-4>
        <v-slider
          label="Distance"
          hint="Distance to search for people"
          min="1"
          max="250"
          thumb-label
          v-model="distKm"
        ></v-slider>
      </v-flex>
      <v-flex p>
        <v-btn flat @click.native="getNextMatcherPerson()">
          Search
        </v-btn>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3 mt-4>
        <v-card v-if="!havePersonInDistance">
          <v-card-title>
            Change distance to find other user
          </v-card-title>
        </v-card>
        <v-card v-if="havePersonInDistance">
          <v-card-media
            class="white--text"
            :src="matcherPerson.avatar"
            height="500px">
            <v-container fill-height fluid>
              <v-layout fill-height>
                <v-flex xs12 align-end flexbox>
                  <span class="headline">{{ matcherPerson.name }} {{ matcherPerson.surname }}</span>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-media>
          <v-card-title>
            <div>
              <h2>{{ matcherPerson.name }} {{ matcherPerson.surname }}</h2>
            </div>
            <v-container>
              <v-layout row wrap>
                <v-flex>
                  <v-expansion-panel popout>
                    <v-expansion-panel-content>
                      <div slot="header">Description</div>
                      <v-card>
                        <v-card-text>{{ this.matcherPerson.description }}</v-card-text>
                      </v-card>
                    </v-expansion-panel-content>
                  </v-expansion-panel>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-title>
          <v-card-actions>
            <v-btn flat @click.native="showProfile()">
              <v-icon left>person</v-icon>
                See Profile</v-btn>
            <v-spacer></v-spacer>
            <v-btn flat @click.native="getNextMatcherPerson">
              <v-icon left>forward</v-icon>
              Next</v-btn>
            <v-btn flat @click.native="giveLike">
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
        distKm: 5
      }
    },
    mounted () {
      this.$store.commit('clearError')
      this.$store.commit('clearMessage')
    },
    beforeDestroy () {
      this.$store.commit('clearError')
      this.$store.commit('clearMessage')
    },
    computed: {
      matcherPerson () {
        if (this.$store.getters.matcherPerson === null || this.$store.getters.matcherPerson === undefined) {
          this.getNextMatcherPerson()
        }
        return this.$store.getters.matcherPerson
      },
      havePersonInDistance () {
        if (this.$store.getters.matcherPerson === null || this.$store.getters.matcherPerson === undefined) {
          return false
        }
        return true
      }
    },
    methods: {
      getNextMatcherPerson () {
        this.$store.dispatch('getNextMatcherPerson', {
          lastId: this.$store.getters.lastId,
          longitude: this.$store.getters.position.coords.longitude,
          latitude: this.$store.getters.position.coords.latitude,
          distKm: this.distKm
        })
      },
      showProfile (login) {
        this.$router.push('/profile/' + this.matcherPerson.login)
      },
      giveLike () {
        this.$store.dispatch('giveLike', {userId: this.matcherPerson.userId})
        this.getNextMatcherPerson()
      }
    }
  }
</script>
