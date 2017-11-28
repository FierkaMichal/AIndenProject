<template>
  <v-data-table
  v-bind:headers="headers"
  :items="items"
  hide-actions
  class="elevation-1"
  >
  <template slot="items" slot-scope="props">
    <td>{{ props.item.login }}</td>
    <td class="text-xs-right">{{ props.item.firstName }} </td>
    <td class="text-xs-right">{{ props.item.lastName }}</td>
    <td class="text-xs-right">{{ props.item.role }} </td>
    <td>
      <v-dialog v-model="editDialog" persistent max-width="500px">
        <v-btn color="primary" dark slot="activator">Edit
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
                  required
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field label="Last name" hint="Your last name"
                  persistent-hint
                  required
                  ></v-text-field>
                </v-flex>
                <v-text-field label="Login" hint="Your login"
                persistent-hint
                required
                ></v-text-field>
              </v-flex>
              <v-flex xs12 sm6>
                <v-select
                label="Role"
                required
                :items="['Admin', 'User']"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="editDialog = false">Close</v-btn>
          <v-btn color="blue darken-1" flat @click.native="editDialog = false">Save</v-btn>
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
          <v-btn color="green darken-1" flat @click.native="deleteDialog = false">Yes</v-btn>
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
      editDialog: false,
      deleteDialog: false,
      headers: [
        {
          text: 'Login',
          align: 'left',
          sortable: false,
          value: 'login'
        },
        { text: 'First name', value: 'firstName', sortable: false },
        { text: 'Last name', value: 'lastName', sortable: false },
        { text: 'Role', value: 'role', sortable: false }
      ],
      items: [
        {
          login: 'hfabisiak',
          firstName: 'Hubert',
          lastName: 'Fabisiak',
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
    deleteUser (userId) {
      this.$store.dispatch('deleteUser', {
        userId: userId
      })
    },
    editUser () {

    }
  }
}
</script>
