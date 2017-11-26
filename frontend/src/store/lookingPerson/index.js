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
      axios.get('rest/user', {
        login: payload.login,
        access_token: payload.access_token
      })
        .then(response => {
          commit('setLoading', false)
          const newPerson = {
            name: response.data.name,
            surname: response.data.surname,
            description: response.data.description,
            photo: response.data.photo
          }
          commit('setPerson', newPerson)
          console.log(response.data)
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
