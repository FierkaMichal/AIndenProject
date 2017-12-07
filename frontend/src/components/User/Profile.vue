<template>
  <v-container>
    <v-layout row v-if="error">
      <v-flex xs12 sm6 offset-sm3 mt-4>
        <app-alert @dismissed="onErrorDismissed" :text="error.message" :type="error.type"></app-alert>
      </v-flex>
    </v-layout>
    <v-layout row v-if="message">
      <v-flex xs12 sm6 offset-sm3 mt-4>
        <app-alert @dismissed="onMessageDismissed" :text="message.message" :type="message.type"></app-alert>
      </v-flex>
    </v-layout>
    <v-layout row wrap>
      <v-flex xs12 sm6 offset-sm3 text-xs-center text-sm-left mt-4>
        <v-avatar size="80px">
          <img v-bind:src="user.avatar" alt="avatar" style="max-width:100%; height: auto;">
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
            <v-divider></v-divider>
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
                    <!--<v-flex xs12>-->
                      <!--<v-text-field label="Avatar URL" required v-model="user.avatar"></v-text-field>-->
                    <!--</v-flex>-->
                    <!--<v-flex xs12>-->
                      <!--<v-expansion-panel popout>-->
                        <!--<v-expansion-panel-content>-->
                          <!--<div slot="header">Photos</div>-->
                          <!--<div v-for="(photo, i) in user.photoArray" :key="i">-->
                            <!--<v-layout row>-->
                              <!--<v-flex p>-->
                                <!--<v-avatar tile size="40px">-->
                                  <!--<img :src="photo" alt="photo">-->
                                <!--</v-avatar>-->
                              <!--</v-flex>-->
                              <!--&lt;!&ndash;<v-flex xs12>&ndash;&gt;-->
                              <!--&lt;!&ndash;<v-text-field label="Photo URL" readonly v-model="photo.src"></v-text-field>&ndash;&gt;-->
                              <!--&lt;!&ndash;</v-flex>&ndash;&gt;-->
                              <!--<v-btn flat icon @click.native="removePhoto(photo)">-->
                                <!--<v-icon class="primary&#45;&#45;text">clear</v-icon>-->
                              <!--</v-btn>-->
                            <!--</v-layout>-->
                          <!--</div>-->
                        <!--</v-expansion-panel-content>-->
                      <!--</v-expansion-panel>-->
                    <!--</v-flex>-->
                    <!--<v-layout row>-->
                      <!--<v-flex xs12>-->
                        <!--<v-text-field label="Add Photo URL" v-model="photo"></v-text-field>-->
                      <!--</v-flex>-->
                      <!--<v-flex p>-->
                        <!--<v-btn flat icon @click.native="addPhoto">-->
                          <!--<v-icon class="primary&#45;&#45;text">add</v-icon>-->
                        <!--</v-btn>-->
                      <!--</v-flex>-->
                    <!--</v-layout>-->
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
      <v-flex xs12 sm6 offset-sm3 text-xs-center text-sm-right v-if="isLoggedInUser">
        <v-dialog v-model="photoDialog" persistent max-width="500px">
          <v-btn slot="activator" flat @click="onManagePhotos">
            <v-icon left>mode_edit</v-icon>
            Manage photos
          </v-btn>
          <v-card>
            <v-card-title>
              <span class="headline">Manage your photo</span>
            </v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <v-container grid-list-md>
                <v-layout row>
                  <v-flex text-xs-center>
                    <v-btn @click="onPickAvatar">Upload avatar</v-btn>
                    <input type="file" style="display: none;" accept="image/*" ref="avatarInput" @change="onAvatarPicked"></input>
                    <v-btn @click="onAvatarSave">Save avatar</v-btn>
                  </v-flex>
                </v-layout>
                <v-layout row>
                  <v-flex text-xs-center>
                    <img :src="avatarSelected" height="250">
                  </v-flex>
                </v-layout>
                <v-divider></v-divider>
                <v-layout row>
                  <v-flex text-xs-center>
                    <v-btn @click="onPickPhoto">Upload photo</v-btn>
                    <input type="file" style="display: none;" accept="image/*" ref="photoInput" @change="onPhotoPicked"></input>
                    <v-btn @click="onPhotoSave">Save photo</v-btn>
                  </v-flex>
                </v-layout>
                <v-layout row>
                  <v-flex text-xs-center>
                    <img :src="photoSelected" height="250">
                  </v-flex>
                </v-layout>
                <v-divider></v-divider>
                <v-layout row>
                  <v-flex>
                    <v-expansion-panel popout>
                      <v-expansion-panel-content>
                        <div slot="header">Photos</div>
                        <div v-for="(photo, i) in user.photoArray" :key="i">
                          <v-layout row>
                            <v-flex text-xs-center>
                              <img :src="photo" alt="photo" height="150">
                            </v-flex>
                            <v-btn flat icon @click.native="removePhoto(photo)">
                              <v-icon class="primary--text">clear</v-icon>
                            </v-btn>
                          </v-layout>
                        </div>
                      </v-expansion-panel-content>
                    </v-expansion-panel>
                  </v-flex>
                </v-layout>
                <!--<form @submit.prevent="onEditUserClick">-->
                  <!--<v-layout wrap>-->
                    <!--<v-flex xs12>-->
                      <!--<v-text-field label="Name" required v-model="user.name"></v-text-field>-->
                    <!--</v-flex>-->
                    <!--<v-flex xs12>-->
                      <!--<v-text-field label="Surname" required v-model="user.surname"></v-text-field>-->
                    <!--</v-flex>-->
                    <!--<v-flex xs12>-->
                      <!--<v-text-field-->
                        <!--v-model="user.description"-->
                        <!--label="Description"-->
                        <!--placeholder="Set description"-->
                        <!--multi-line-->
                        <!--rows="3"-->
                        <!--auto-grow>-->
                      <!--</v-text-field>-->
                    <!--</v-flex>-->
                    <!--<v-flex xs12>-->
                      <!--<v-text-field label="Avatar URL" required v-model="user.avatar"></v-text-field>-->
                    <!--</v-flex>-->
                    <!--<v-flex xs12>-->
                      <!--<v-expansion-panel popout>-->
                        <!--<v-expansion-panel-content>-->
                          <!--<div slot="header">Photos</div>-->
                          <!--<div v-for="(photo, i) in user.photoArray" :key="i">-->
                            <!--<v-layout row>-->
                              <!--<v-flex p>-->
                                <!--<v-avatar tile size="40px">-->
                                  <!--<img :src="photo" alt="photo">-->
                                <!--</v-avatar>-->
                              <!--</v-flex>-->
                              <!--&lt;!&ndash;<v-flex xs12>&ndash;&gt;-->
                                <!--&lt;!&ndash;<v-text-field label="Photo URL" readonly v-model="photo.src"></v-text-field>&ndash;&gt;-->
                              <!--&lt;!&ndash;</v-flex>&ndash;&gt;-->
                              <!--<v-btn flat icon @click.native="removePhoto(photo)">-->
                                <!--<v-icon class="primary&#45;&#45;text">clear</v-icon>-->
                              <!--</v-btn>-->
                            <!--</v-layout>-->
                          <!--</div>-->
                        <!--</v-expansion-panel-content>-->
                      <!--</v-expansion-panel>-->
                    <!--</v-flex>-->
                    <!--<v-layout row>-->
                      <!--<v-flex xs12>-->
                        <!--<v-text-field label="Add Photo URL" v-model="photo"></v-text-field>-->
                      <!--</v-flex>-->
                      <!--<v-flex p>-->
                        <!--<v-btn flat icon @click.native="addPhoto">-->
                          <!--<v-icon class="primary&#45;&#45;text">add</v-icon>-->
                        <!--</v-btn>-->
                      <!--</v-flex>-->
                    <!--</v-layout>-->
                  <!--</v-layout>-->
                <!--</form>-->
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" flat @click.native="photoDialog = false">Close</v-btn>
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
          <v-carousel-item v-for="(photo, i) in user.photoArray"  :key="i" contain>
            <img :src="getImageLink(photo)" alt="photo" style="max-width:100%; height: auto;">
          </v-carousel-item>
        </v-carousel>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import VueCookies from 'vue-cookies'
  export default {
    data () {
      return {
        url: '/rest/getFile?access_token=' + VueCookies.get('token') + '&photoId=',
        avatarSelected: '',
        avatarEdit: null,
        photoSelected: '',
        photoEdit: null,
        dialog: false,
        photoDialog: false,
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
      },
      onManagePhotos () {
        this.avatarSelected = ''
        this.avatarEdit = null
        this.photoSelected = ''
        this.photoEdit = null
      },
      onPickAvatar () {
        this.$refs.avatarInput.click()
      },
      onAvatarPicked (event) {
        const files = event.target.files
        let filename = files[0].name
        if (filename.lastIndexOf('.') <= 0) {
          return alert('Zly plik')
        }
        const fileReader = new FileReader()
        fileReader.addEventListener('load', () => {
          this.avatarSelected = fileReader.result
        })
        fileReader.readAsDataURL(files[0])
        this.avatarEdit = files[0]
      },
      onAvatarSave () {
        this.$store.dispatch('changeAvatar', this.avatarEdit)
      },
      onPickPhoto () {
        this.$refs.photoInput.click()
      },
      onPhotoPicked (event) {
        const files = event.target.files
        let filename = files[0].name
        if (filename.lastIndexOf('.') <= 0) {
          return alert('Zly plik')
        }
        const fileReader = new FileReader()
        fileReader.addEventListener('load', () => {
          this.photoSelected = fileReader.result
        })
        fileReader.readAsDataURL(files[0])
        this.photoEdit = files[0]
      },
      onPhotoSave () {
        this.$store.dispatch('addPhoto', this.photoEdit)
      },
      getImageLink (id) {
        return '/rest/getFile?access_token=' + VueCookies.get('token') + '&photoId=' + id
      }
    }
  }
</script>
