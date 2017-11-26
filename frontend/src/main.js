import Vue from 'vue'
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
Vue.config.productionTip = false

Vue.component('app-alert', AlertCmp)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  mounted: function () {
    navigator.geolocation.watchPosition(function (position) {
      console.log('Longitude: ' + position.coords.longitude + ' Latitude: ' + position.coords.latitude)
    },
    function (error) {
      if (error.code === error.PERMISSION_DENIED) {
        console.log('Permission denied!')
      }
    })
  },
  router,
  store,
  render: h => h(App)
})
