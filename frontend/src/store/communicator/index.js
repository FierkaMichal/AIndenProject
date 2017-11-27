import axios from 'axios'
import VueCookies from 'vue-cookies'

export default {
  state: {
    messages: null
  },
  mutations: {
    setMessages (state, payload) {
      state.messages = payload
    },
    addMessage (state, payload) {
      if (state.messages === null || state.messages === undefined) {
        state.messages = [payload]
      } else {
        state.messages.push(payload)
      }
    }
  },
  actions: {
    getMessages ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      params.append('personId', payload.personId)
      axios.get('rest/message?' + params)
        .then(response => {
          commit('setMessages', response.data)
        })
        .catch(error => {
          console.log(error)
        })
    },
    addMessage ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      axios.post('rest/message/add?' + params)
        .then(response => {
          commit('addMessage', payload)
        })
        .catch(error => {
          commit('addMessage', payload)
          console.log(error)
        })
    }
  },
  getters: {
    messages (state) {
      return state.messages
    }
  }
}
