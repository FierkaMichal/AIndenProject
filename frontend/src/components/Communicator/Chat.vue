<template>
  <v-container>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title>
            <div>Chat with </div>
            <v-chip @click="goToProfile(otherPerson.login)">
              <v-avatar>
                <img :src="otherPerson.avatar">
              </v-avatar>
              {{ otherPerson.name }} {{ otherPerson.surname }}
            </v-chip>
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <v-container
              style="max-height: 400px"
              class="scroll-y">
              <v-layout row v-if="!isAnyMessages">
                Nie ma zadnych wiadomosci
              </v-layout>
              <v-layout row v-for="(message, i) in messages" :key="i">
                <v-flex v-if="isMyMessage(message.otherPersonId)" text-xs-right>
                    <v-chip  disabled small>
                      {{ message.message }}
                    </v-chip>
                </v-flex>
                <v-flex v-if="!isMyMessage(message.otherPersonId)" text-xs-left>
                    <v-chip disabled small>
                      {{ message.message }}
                    </v-chip>
                </v-flex>
              </v-layout>
              <!--<v-layout row v-if="isAnyMessages">-->
                <!--<v-flex>-->
                  <!--<span v-for="(message, i) in messages" :key="i">-->
                    <!--<v-chip v-if="isMyMessage(message.otherPersonId)" disabled small style="text-align: right;">-->
                      <!--<v-avatar>-->
                        <!--<img :src="user.avatar">-->
                      <!--</v-avatar>-->
                      <!--{{ message.message }}-->
                    <!--</v-chip>-->
                    <!--<v-chip v-if="!isMyMessage(message.otherPersonId)" disabled small>-->
                      <!--<v-avatar>-->
                        <!--<img :src="otherPerson.avatar">-->
                      <!--</v-avatar>-->
                      <!--{{ message.message }}-->
                    <!--</v-chip>-->
                  <!--</span>-->
                <!--</v-flex>-->
              <!--</v-layout>-->
            </v-container>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-layout row>
              <v-flex xs12>
                <v-text-field label="Enter message" v-model="message" multi-line rows="1" auto-grow></v-text-field>
              </v-flex>
              <v-flex p>
                <v-btn flat icon @click.native="addMessage">
                  <v-icon class="primary--text">add</v-icon>
                </v-btn>
              </v-flex>
            </v-layout>
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
        message: null,
        interval: ''
      }
    },
    mounted () {
      this.getMessages()

      this.interval = setInterval(function () {
        console.log('dsadas')
        this.getMessages()
      }.bind(this), 1000)
    },
    beforeDestroy () {
      clearInterval(this.interval)
    },
    computed: {
      messages () {
//        if (this.$store.getters.messages === null || this.$store.getters.messages === undefined || this.$store.getters.messages.length === 0) {
//          this.$store.dispatch('getMessages', {
//            personId: this.$store.getters.messagePerson.userId
//          })
//        }
        return this.$store.getters.messages
      },
      user () {
        return this.$store.getters.user
      },
      otherPerson () {
        return this.$store.getters.messagePerson
      }
    },
    methods: {
      isMyMessage (userId) {
        console.log(userId === this.user.userId)
        return userId === this.user.userId
      },
      getMessages () {
        this.$store.dispatch('getMessages', {
          personId: this.$store.getters.messagePerson.userId
        })
      },
      goToProfile (login) {
        this.$router.push('/profile/' + login)
      },
      addMessage () {
        this.$store.dispatch('addMessage', {
          message: this.message,
          myId: this.user.userId,
          otherPersonId: this.otherPerson.userId,
          time: new Date().toJSON()
        })
        this.message = ''
      },
      isAnyMessages () {
        if (this.messages === null || this.messages === undefined || this.messages.length === 0) {
          return false
        } else {
          return true
        }
      }
    }
  }
</script>
