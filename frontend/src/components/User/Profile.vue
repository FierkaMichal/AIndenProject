<template>
  <v-container>
    <v-layout row v-if="error">
      <v-flex xs12 sm6 offset-sm3>
        <app-alert @dismissed="onErrorDismissed" :text="error.message" :type="error.type"></app-alert>
      </v-flex>
    </v-layout>
    <v-layout row v-if="message">
      <v-flex xs12 sm6 offset-sm3>
        <app-alert @dismissed="onMessageDismissed" :text="message.message" :type="message.type"></app-alert>
      </v-flex>
    </v-layout>
    <v-layout row wrap>
      <v-flex xs12 sm6 offset-sm3 text-xs-center text-sm-left>
        <v-avatar size="100px">
          <img v-bind:src="user.avatar" alt="avatar">
        </v-avatar>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ user.name }} {{ user.surname }}
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 text-xs-center text-sm-right v-if="isLoggedInUser">
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
                <form @submit.prevent="onEditUserClick">
                  <v-layout wrap>
                    <v-flex xs12>
                      <v-text-field label="Name" required v-model="user.name"></v-text-field>
                    </v-flex>
                    <v-flex xs12>
                      <v-text-field label="Surname" required v-model="user.surname"></v-text-field>
                    </v-flex>
                    <v-flex xs12>
                      <v-text-field
                        v-model="user.description"
                        label="Description"
                        placeholder="Set description"
                        multi-line
                        rows="3"
                        auto-grow>
                      </v-text-field>
                    </v-flex>
                    <v-flex xs12>
                      <v-text-field label="Avatar URL" required v-model="user.avatar"></v-text-field>
                    </v-flex>
                    <v-flex xs12>
                      <v-expansion-panel popout>
                        <v-expansion-panel-content>
                          <div slot="header">Photos</div>
                          <div v-for="(photo, i) in user.photoArray" :key="i">
                            <v-layout row>
                              <v-flex p>
                                <v-avatar tile size="40px">
                                  <img :src="photo" alt="photo">
                                </v-avatar>
                              </v-flex>
                              <!--<v-flex xs12>-->
                                <!--<v-text-field label="Photo URL" readonly v-model="photo.src"></v-text-field>-->
                              <!--</v-flex>-->
                              <v-btn flat icon @click.native="removePhoto(photo)">
                                <v-icon class="primary--text">clear</v-icon>
                              </v-btn>
                            </v-layout>
                          </div>
                        </v-expansion-panel-content>
                      </v-expansion-panel>
                    </v-flex>
                    <v-layout row>
                      <v-flex xs12>
                        <v-text-field label="Add Photo URL" v-model="photo"></v-text-field>
                      </v-flex>
                      <v-flex p>
                        <v-btn flat icon @click.native="addPhoto">
                          <v-icon class="primary--text">add</v-icon>
                        </v-btn>
                      </v-flex>
                    </v-layout>
                  </v-layout>
                </form>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" flat @click.native="dialog = false">Close</v-btn>
              <v-btn type="submit" color="blue darken-1" flat @click.native="onEditUserClick">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-flex>
    </v-layout>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3 mt-4>
        <v-text-field
          readonly
          v-model="user.description"
          label="Description"
          placeholder="Click edit to set description"
          multi-line
          rows="1"
          auto-grow>
        </v-text-field>
      </v-flex>
    </v-layout>
    <v-layout row wrap>
      <v-flex xs12 sm6 offset-sm3 mt-4>
        <v-carousel>
          <v-carousel-item v-for="(photo, i) in user.photoArray" :src="photo" :key="i" contain></v-carousel-item>
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
        photo: null
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
      isLoggedInUser () {
        return this.$route.params.login === 'my'
      },
      user () {
        if (this.$route.params.login === 'my') {
          return this.$store.getters.user
        }
        if (this.$store.getters.lookingPerson !== null && this.$store.getters.lookingPerson !== undefined) {
          if (this.$route.params.login === this.$store.getters.lookingPerson.login) {
            return this.$store.getters.lookingPerson
          }
        }
        this.$store.dispatch('getLookingForPerson',
          {
            login: this.$route.params.login
          })
        return this.$store.getters.lookingPerson
      },
      error () {
        return this.$store.getters.error
      },
      message () {
        return this.$store.getters.message
      }
    },
    methods: {
      onEditUserClick () {
        this.$store.dispatch('editUser', this.user)
        this.dialog = false
      },
      onErrorDismissed () {
        this.$store.dispatch('clearError')
      },
      onMessageDismissed () {
        this.$store.dispatch('clearMessage')
      },
      addPhoto () {
        if (this.photo !== null) {
          this.$store.dispatch('addPhoto', this.photo)
          this.photo = null
        }
      },
      removePhoto (src) {
        this.$store.dispatch('removePhoto', {src: src})
      },
      giveLike () {
        this.$store.dispatch('giveLike', {userId: this.lookingPerson.userId})
      }
    }
  }
</script>
