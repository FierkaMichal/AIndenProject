<template>
  <v-app dark>
    <v-navigation-drawer temporary v-model="drawer" light absolute>
      <v-list>
        <v-list-tile v-for="item in menuItems" :key="item.title" router :to="item.link">
          <v-list-tile-action>
            <v-icon>{{item.icon}}</v-icon>
          </v-list-tile-action>
          <v-list-content>{{item.title}}</v-list-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>

    <v-toolbar app>
      <v-toolbar-side-icon
        @click.native.stop="drawer = !drawer"
        class="hidden-sm-and-up"></v-toolbar-side-icon>
      <v-toolbar-title>
        <router-link to="/" tag="span" style="cursor: pointer">AInder</router-link></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-item class="hidden-xs-only">
        <v-btn flat v-for="item in menuItems" :key="item.title" router :to="item.link">
          <v-icon left>{{item.icon}}</v-icon>
          {{item.title}}
        </v-btn>
      </v-toolbar-item>
    </v-toolbar>

    <main>
      <router-view></router-view>
    </main>

    <v-footer fixed="true" app>
      <v-spacer></v-spacer>
      <div>© AInder {{ new Date().getFullYear() }}</div>
    </v-footer>
  </v-app>
</template>

<script>
  export default {
    data () {
      return {
        drawer: false,
        menuItems: [
          {icon: 'face', title: 'Sign Up', link: '/signUp'},
          {icon: 'lock_open', title: 'Sign In', link: '/signIn'},
          {icon: 'face', title: 'My profile', link: '/profile'},
          {icon: 'face', title: 'Communicator', link: '/communicator'}
        ]
      }
    }
  }
</script>
