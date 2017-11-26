import axios from 'axios'

export default {
  state: {
    lookingPerson: null
  },
  mutations: {
    setPerson (state, payload) {
      state.lookingPerson = payload
    }
  },
  actions: {
    getLookingForPerson ({ commit }, payload) {
      commit('setLoading', true)
      var params = new URLSearchParams()
      params.append('access_token', payload.access_token)
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
    }
  },
  getters: {
    lookingPerson (state) {
      return state.lookingPerson
    }
  }
}
