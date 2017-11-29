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
    <td class="text-xs-right">{{ props.item.role }} </td>
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
            <v-btn color="blue darken-1" flat @click.native="editUser(props.item)">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog v-model="deleteDialog" persistent max-width="290">
        <v-btn color="error" dark slot="activator">Delete
          <v-icon right>remove_circle</v-icon>
        </v-btn>
        <v-card>
          <v-card-title class="headline">Remove user</v-card-title>
          <v-card-text>Are you sure you want to remove the user?</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" flat @click.native="deleteDialog = false">Cancel</v-btn>
            <v-btn color="green darken-1" flat @click.native="deleteUser(props.item.userId)">Yes</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </td>
  </template>
</v-data-table>
</template>

<script>
export default {
  data () {
    return {
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
      ],
      items: [
        {
          login: 'hfabisiak',
          name: 'Hubert',
          surname: 'Fabisiak',
          role: 'Admin'
        }
      ]
    }
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
    onEditClick (user) {
      this.login = user.login
      this.name = user.name
      this.surname = user.surname
      if (user.role) {
        this.role = 'Admin'
      } else {
        this.role = 'User'
      }
      this.role = user.role
    },
    deleteUser (userId) {
      this.$store.dispatch('deleteUser', {
        userId: userId
      })
      this.deleteDialog = false
    },
    editUser (user) {
      user.login = this.login
      user.name = this.name
      user.surname = this.surname
      if (this.role === 'Admin') {
        user.role = true
      } else {
        user.role = false
      }
      user.role = this.role
      this.$store.dispatch('editUser', user)
      this.editDialog = false
    }
  }
}
</script>
