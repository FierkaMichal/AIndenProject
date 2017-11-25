<template>
  <v-app dark>
    <v-navigation-drawer temporary v-model="drawer" light absolute>
      <v-list>
        <v-list-tile v-for="item in menuItems" :key="item.title" :to="item.link">
          <v-list-tile-action>
            <v-icon>{{item.icon}}</v-icon>
          </v-list-tile-action>
          <v-list-content>{{item.title}}</v-list-content>
        </v-list-tile>

        <v-list-tile v-if="userIsAuthenticated" @click="onLogout">
          <v-list-tile-action>
            <v-icon>exit_to_app</v-icon>
          </v-list-tile-action>
          <v-list-content>Logout</v-list-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>

    <v-toolbar app>
      <v-toolbar-side-icon
        @click.stop="drawer = !drawer"
        class="hidden-md-and-up"></v-toolbar-side-icon>
      <v-toolbar-title>
        <router-link to="/" tag="span" style="cursor: pointer">AInder</router-link></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-item class="hidden-sm-and-down">
        <v-btn flat v-for="item in menuItems" :key="item.title" :to="item.link">
          <v-icon left>{{item.icon}}</v-icon>
          {{item.title}}
        </v-btn>
        <v-btn v-if="userIsAuthenticated" flat @click="onLogout">
          <v-icon left>exit_to_app</v-icon>
          Logout
        </v-btn>
      </v-toolbar-item>
    </v-toolbar>

    <main>
      <router-view></router-view>
    </main>

    <v-footer app>
      <v-spacer></v-spacer>
      <div>© AInder {{ new Date().getFullYear() }}</div>
    </v-footer>
  </v-app>
</template>

<script>
  export default {
    data () {
      return {
        drawer: null
      }
    },
    computed: {
      menuItems () {
        let menuItems = [
          {icon: 'face', title: 'Sign Up', link: '/signUp'},
          {icon: 'lock_open', title: 'Sign In', link: '/signIn'}
        ]
        if (this.userIsAuthenticated) {
          menuItems = [
            {icon: 'face', title: 'My profile', link: '/profile'},
            {icon: 'face', title: 'Communicator', link: '/communicator'},
            {icon: 'face', title: 'Search users', link: '/matcher'}
          ]
        }
        return menuItems
      },
      userIsAuthenticated () {
        return this.$store.getters.user !== null && this.$store.getters.user !== undefined
      }
    },
    methods: {
      onLogout () {
        this.$store.dispatch('userLogout', this.$store.getters.user.access_token)
      }
    }
  }
</script>
