import Vue from 'vue'
import VueCookies from 'vue-cookies'
import lodash from 'lodash'
import VueLodash from 'vue-lodash'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.css'
import App from './App'
import router from './router'
import { store } from './store'
import AlertCmp from './components/Shared/Alert.vue'

Vue.use(Vuetify)
Vue.use(VueLodash, lodash)
Vue.use(VueCookies)
Vue.config.productionTip = false

Vue.component('app-alert', AlertCmp)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  mounted: function () {
    navigator.geolocation.watchPosition(position => {
      console.log('Longitude: ' + position.coords.longitude + ' Latitude: ' + position.coords.latitude)
      this.$store.commit('setPosition', position)
    })
  },
  router,
  store,
  render: h => h(App)
})
