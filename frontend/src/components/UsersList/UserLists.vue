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
        <v-btn color="primary">Edit</v-btn>
        <v-btn color="error">Delete</v-btn>
      </td>
    </template>
  </v-data-table>
</template>

<script>
  export default {
    data () {
      return {
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
    }
  }
</script>
