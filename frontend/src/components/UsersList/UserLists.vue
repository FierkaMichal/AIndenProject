<template>
  <v-data-table
  v-bind:headers="headers"
  :items="usersList.user"
  hide-actions
  class="elevation-1"
  >
  <template slot="items" slot-scope="props">
    <td>{{ props.item.login }}</td>
    <td class="text-xs-right">{{ props.item.name }} </td>
    <td class="text-xs-right">{{ props.item.surname }}</td>
    <td class="text-xs-right" v-if="props.item.admin">Admin</td>
    <td class="text-xs-right" v-if="!props.item.admin">User</td>
    <td>
      <v-dialog v-model="editDialog" persistent max-width="500px">
        <v-btn color="primary" dark slot="activator" @click="onEditClick(props.item)">Edit
          <v-icon right>mode_edit</v-icon>
        </v-btn>
        <v-card>
          <v-card-title>
            <span class="headline">User Profile</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12 sm6 md4>
                  <v-text-field label="First name" hint="Your first name"
                  persistent-hint
                  aria-required
                  v-model="name"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field label="Last name" hint="Your last name"
                  persistent-hint
                  required
                  v-model="surname"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field label="Login" hint="Your login"
                  persistent-hint
                  required
                  v-model="login"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6>
                  <v-select
                  label="Role"
                  required
                  :items="['Admin', 'User']"
                  v-model="role"
                  ></v-select>
                </v-flex>
              </v-layout>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click.native="editDialog = false">Close</v-btn>
            <v-btn color="blue darken-1" flat @click.native="editUser()">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <!--<v-dialog v-model="deleteDialog" persistent max-width="290">-->
        <!--<v-btn color="error" dark slot="activator">Delete-->
          <!--<v-icon right>remove_circle</v-icon>-->
        <!--</v-btn>-->
        <!--<v-card>-->
          <!--<v-card-title class="headline">Remove user</v-card-title>-->
          <!--<v-card-text>Are you sure you want to remove the user?</v-card-text>-->
          <!--<v-card-actions>-->
            <!--<v-spacer></v-spacer>-->
            <!--<v-btn color="green darken-1" flat @click.native="deleteDialog = false">Cancel</v-btn>-->
            <!--<v-btn color="green darken-1" flat @click.native="deleteUser(props.item.userId)">Yes</v-btn>-->
          <!--</v-card-actions>-->
        <!--</v-card>-->
      <!--</v-dialog>-->
    </td>
  </template>
</v-data-table>
</template>

<script>
export default {
  data () {
    return {
      userId: '',
      login: '',
      name: '',
      surname: '',
      role: '',
      editDialog: false,
      deleteDialog: false,
      headers: [
        {
          text: 'Login',
          align: 'left',
          sortable: false,
          value: 'login'
        },
        { text: 'First name', value: 'name', sortable: false },
        { text: 'Last name', value: 'surname', sortable: false },
        { text: 'Role', value: 'role', sortable: false }
      ]
    }
  },
  mounted () {
    this.$store.dispatch('getAdminUserList')
  },
  computed: {
    usersList () {
      if (this.$store.getters.usersList === null || this.$store.getters.usersList === undefined) {
        this.$store.dispatch('getAdminUserList')
      }
      return this.$store.getters.usersList
    }
  },
  methods: {
    onEditClick (person) {
      this.userId = person.userId
      this.login = person.login
      this.name = person.name
      this.surname = person.surname
      if (person.admin) {
        this.role = 'Admin'
      } else {
        this.role = 'User'
      }
    },
    deleteUser (userId) {
      this.$store.dispatch('deleteUser', {
        userId: userId
      })
      this.deleteDialog = false
    },
    editUser () {
      var aaa = false
      if (this.role === 'Admin') {
        aaa = true
      }
      this.$store.commit('editAdminUserList', {
        userId: this.userId,
        name: this.name,
        surname: this.surname,
        login: this.login,
        admin: aaa

      })
      const userToEdit = this.usersList.user.find(user => {
        return user.userId === this.userId
      })
      this.$store.dispatch('editUser', userToEdit)
      this.editDialog = false
    }
  }
}
</script>
