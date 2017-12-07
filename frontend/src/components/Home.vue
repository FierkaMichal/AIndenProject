<template>
  <v-container>
    <v-layout row>
      <v-flex xs12 text-xs-center mt-4>
        Welcome in AInder
      </v-flex>
    </v-layout>
    <v-layout row v-if="isUserLoggedIn">
      <v-flex xs12 sm6 text-xs-center text-sm-right mt-4>
        <v-btn v-if="isUserLoggedIn" to="/profile/my">
          <v-icon left>exit_to_app</v-icon>
          Profile
        </v-btn>
      </v-flex>
      <v-flex xs12 sm6 text-xs-center text-sm-left mt-4>
        <v-btn v-if="isUserLoggedIn" to="/matcher">
          <v-icon left>exit_to_app</v-icon>
          Matcher
        </v-btn>
      </v-flex>
    </v-layout>
    <v-layout row v-if="!isUserLoggedIn">
      <v-flex xs12 sm6 text-xs-center text-sm-right mt-4>
        <v-btn to="/signUp">
          <v-icon left>exit_to_app</v-icon>
          Sign Up
        </v-btn>
      </v-flex>
      <v-flex xs12 sm6 text-xs-center text-sm-left mt-4>
        <v-btn to="/signIn">
          <v-icon left>exit_to_app</v-icon>
          Sign In
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    data () {
      return {
      }
    },
    beforeMount () {
      this.$store.dispatch('findPosition')
    },
    mounted () {
      if (this.$store.getters.user !== null) {
        this.$store.getters.user.lastLongitude = this.$store.getters.position.lng
        this.$store.getters.user.lastLatitude = this.$store.getters.position.lat
        this.$store.dispatch('editUser', this.$store.getters.user)
      }
    },
    computed: {
      isUserLoggedIn () {
        if (this.$store.getters.user === null || this.$store.getters.user === undefined) {
          return false
        } else {
          return true
        }
      }
    }
  }
</script>
