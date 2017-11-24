<template>
  <v-app dark>
    <v-navigation-drawer temporary v-model="drawer" light absolute>
      <v-list>
        <v-list-tile>
          <v-list-tile-action>
            <v-icon>face</v-icon>
          </v-list-tile-action>
          <v-list-content>Sign Up</v-list-content>
        </v-list-tile>
        <v-list-tile>
          <v-list-tile-action>
            <v-icon>lock_open</v-icon>
          </v-list-tile-action>
          <v-list-content>Sign In</v-list-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>

    <v-toolbar app>
      <v-toolbar-side-icon
        @click.native.stop="drawer = !drawer"
        class="hidden-sm-and-up"></v-toolbar-side-icon>
      <v-toolbar-title>AInder</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-item class="hidden-xs-only">
        <v-btn flat>
          <v-icon left>face</v-icon>
          Sign Up
        </v-btn>
        <v-btn flat>
          <v-icon left>lock_open</v-icon>
          Sign In
        </v-btn>
      </v-toolbar-item>
    </v-toolbar>

    <main>

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
        drawer: false
      }
    }
  }
</script>
