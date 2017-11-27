import axios from 'axios'
import VueCookies from 'vue-cookies'

export default {
  state: {
    lookingPerson: null,
    matches: null
  },
  mutations: {
    setPerson (state, payload) {
      state.lookingPerson = payload
    },
    setMatches (state, payload) {
      state.matches = payload
    }
  },
  actions: {
    getLookingForPerson ({ commit }, payload) {
      commit('setLoading', true)
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      params.append('login', payload.login)
      axios.get('rest/user/login?' + params)
        .then(response => {
          commit('setLoading', false)
          commit('setPerson', response.data)
        })
        .catch(error => {
          commit('setLoading', false)
          console.log(error)
        })
    },
    getMatchesForUser ({ commit }, payload) {

    }
  },
  getters: {
    lookingPerson (state) {
      return state.lookingPerson
    },
    matches (state) {
      return state.matches
    }
  }
}
