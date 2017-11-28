<template>
  <v-layout row>
    <v-flex xs12 sm6 offset-sm3 mt-4>
      <v-card>
        <v-toolbar>
          <v-toolbar-title class="text-xs-center">Communicator</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon>
            <v-icon>search</v-icon>
          </v-btn>
        </v-toolbar>
        <v-list>
          <v-list-tile v-if="!haveMatches" @click="goToMatcher">
            <v-list-tile-content>
              <v-list-tile-title>You do not have any matches go give some like</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
          <v-list-tile avatar v-for="(match, i) in userMatches.user" :key="i" @click="">
            <v-list-tile-avatar>
              <img :src="match.avatar"/>
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>{{ match.name }} {{ match.surname }}</v-list-tile-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-btn flat icon @click.native="goToChat(match)">
                <v-icon>chat_bubble</v-icon>
              </v-btn>
            </v-list-tile-action>
            <v-list-tile-action>
              <v-btn flat icon @click.native="seeProfile(match.login)">
                <v-icon>account_circle</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>
        </v-list>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    data () {
      return {
      }
    },
    computed: {
      userMatches () {
        if (!this.haveMatches) {
          this.$store.dispatch('getMatchedList')
        }
        console.log(this.$store.getters.userMatches)
        return this.$store.getters.userMatches
      },
      haveMatches () {
        if (this.$store.getters.userMatches === null || this.$store.getters.userMatches === undefined || this.$store.getters.userMatches.length === 0) {
          return false
        }
        return true
      }
    },
    methods: {
      goToMatcher () {
        this.$router.push('/matcher')
      },
      goToChat (user) {
        this.$store.dispatch('setMessagePerson', user)
        this.$router.push('/communicator/chat')
      },
      seeProfile (login) {
        this.$router.push('/profile/' + login)
      }
    }
  }
</script>
