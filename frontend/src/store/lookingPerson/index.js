import axios from 'axios'
import VueCookies from 'vue-cookies'

export default {
  state: {
    lastId: 0,
    lookingPerson: null,
    matches: null
  },
  mutations: {
    setPerson (state, payload) {
      state.lookingPerson = payload
    },
    setMatches (state, payload) {
      state.matches = payload
    },
    setLastId (state, payload) {
      state.lastId = payload
    }
  },
  actions: {
    getLookingForPerson ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      params.append('login', payload.login)
      axios.get('rest/user/login?' + params)
        .then(response => {
          commit('setPerson', response.data)
        })
        .catch(error => {
          console.log(error)
        })
    },
    getNextLookingPerson ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      params.append('lastId', payload.lastId)
      params.append('distKm', payload.distKm)
      params.append('longitude', payload.longitude)
      params.append('latitude', payload.latitude)
      axios.get('rest/matcher/getNext?' + params)
        .then(response => {
          commit('setPerson', response.data)
          commit('setLastId', response.data.userId)
        })
        .catch(error => {
          console.log(error)
        })
    },
    giveLike ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      params.append('userId', payload.userId)
      axios.post('rest/like?' + params)
        .catch(error => {
          console.log(error)
        })
    }
  },
  getters: {
    lookingPerson (state) {
      return state.lookingPerson
    },
    matches (state) {
      return state.matches
    },
    lastId (state) {
      return state.lastId
    }
  }
}
