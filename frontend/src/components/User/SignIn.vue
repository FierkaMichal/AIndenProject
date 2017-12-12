<template>
  <v-container>
    <v-layout row v-if="error">
      <v-flex xs12 sm6 offset-sm3>
        <app-alert @dismissed="onDismissed" :text="error.message" :type="error.type"></app-alert>
      </v-flex>
    </v-layout>
    <v-layout row v-if="message">
      <v-flex xs12 sm6 offset-sm3>
        <app-alert @dismissed="onMessageDismissed" :text="message.message" :type="message.type"></app-alert>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-text>
            <v-container>
              <form @submit.prevent="onSignIn">
                <v-layout row>
                  <v-flex xs12>
                    <v-text-field
                      name="login"
                      label="Login"
                      id="login"
                      type="text"
                      v-model="login"
                      required>
                    </v-text-field>
                  </v-flex>
                </v-layout>
                <v-layout row>
                  <v-flex xs12>
                    <v-text-field
                      name="password"
                      label="Password"
                      id="password"
                      type="password"
                      v-model="password"
                      required>
                    </v-text-field>
                  </v-flex>
                </v-layout>

                <v-layout row>
                  <v-flex xs12>
                    <v-btn
                      type="submit"
                      :loading="loading"
                      :disabled="loading">
                      Sign In
                    </v-btn>
                  </v-flex>
                </v-layout>
              </form>
            </v-container>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    data () {
      return {
        login: '',
        password: ''
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
      user () {
        return this.$store.getters.user
      },
      error () {
        return this.$store.getters.error
      },
      loading () {
        return this.$store.getters.loading
      },
      message () {
        return this.$store.getters.message
      }
    },
    watch: {
      user (value) {
        if (value !== null && value !== undefined) {
          this.$router.push('/')
        }
      }
    },
    methods: {
      onSignIn () {
        this.$store.dispatch('userSignIn', {login: this.login, password: this.password})
      },
      onDismissed () {
        this.$store.dispatch('clearError')
      },
      onMessageDismissed () {
        this.$store.dispatch('clearMessage')
      }
    }
  }
</script>
