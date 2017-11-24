<template>
  <v-app dark>

    <v-toolbar app>
      <v-toolbar-side-icon></v-toolbar-side-icon>
      <v-toolbar-title>AInder</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-item>
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
      }
    }
  }
</script>
