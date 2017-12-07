<template>
  <v-app dark>
    <v-navigation-drawer temporary v-model="drawer" absolute>
      <v-toolbar v-if="user" flat class="transparent">
        <v-list class="pa-0">
          <v-list-tile avatar>
            <v-list-tile-avatar>
              <img v-bind:src="user.avatar"/>
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>{{ user.name }} {{ user.surname }}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-toolbar>
      <v-list>
        <v-divider></v-divider>
        <v-list-tile v-for="item in menuItems" :key="item.title" :to="item.link">
          <v-list-tile-action>
            <v-icon>{{item.icon}}</v-icon>
          </v-list-tile-action>
          <v-list-content>{{item.title}}</v-list-content>
        </v-list-tile>
        <v-divider></v-divider>
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
        <v-btn v-if="userIsAuthenticated" flat @click.stop="onLogout">
          <v-icon left>exit_to_app</v-icon>
          Logout
        </v-btn>
      </v-toolbar-item>
    </v-toolbar>
    <!--<v-navigation-drawer v-model="aaa" right clipped>-->
      <!--<v-toolbar v-if="user" flat class="transparent">-->
        <!--<v-list class="pa-0">-->
          <!--<v-list-tile avatar>-->
            <!--<v-list-tile-avatar>-->
              <!--<img src="https://randomuser.me/api/portraits/men/85.jpg" />-->
            <!--</v-list-tile-avatar>-->
            <!--<v-list-tile-content>-->
              <!--<v-list-tile-title>{{ user.id }}</v-list-tile-title>-->
            <!--</v-list-tile-content>-->
          <!--</v-list-tile>-->
        <!--</v-list>-->
      <!--</v-toolbar>-->
    <!--</v-navigation-drawer>-->
    <main>
      <router-view>
      </router-view>
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
        drawer: null,
        aaa: true
      }
    },
	beforeMount () {
      this.$store.dispatch('findPosition')
    },
    computed: {
      menuItems () {
        let menuItems = [
          {icon: 'face', title: 'Sign Up', link: '/signUp'},
          {icon: 'lock_open', title: 'Sign In', link: '/signIn'}
        ]
        if (this.userIsAuthenticated) {
          menuItems = [
            {icon: 'face', title: 'My profile', link: '/profile/my'},
            {icon: 'face', title: 'Communicator', link: '/communicator'},
            {icon: 'face', title: 'Search users', link: '/matcher'}
          ]
          if (this.user.admin) {
            menuItems.push({
              icon: 'face',
              title: 'Manage users',
              link: '/admin/usersList'
            })
          }
        }
        return menuItems
      },
      user () {
        if (this.$store.getters.user === null && this.$cookies.isKey('token')) {
          this.$store.dispatch('userDetails')
        }
        return this.$store.getters.user
      },
      userIsAuthenticated () {
        return this.$store.getters.user !== null && this.$store.getters.user !== undefined && this.$cookies.isKey('token')
      }
    },
    methods: {
      onLogout () {
        this.$store.dispatch('userLogout', this.$store.getters.user.access_token)
      }
    }
  }
</script>
