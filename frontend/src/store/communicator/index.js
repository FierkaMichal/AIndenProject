import axios from 'axios'
import VueCookies from 'vue-cookies'

export default {
  state: {
    messages: null,
    messagePerson: null
  },
  mutations: {
    setMessagePerson (state, payload) {
      state.messagePerson = payload
    },
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
    setMessagePerson ({ commit }, payload) {
      commit('setMessagePerson', payload)
    },
    getMessages ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      params.append('personId', payload.personId)
      axios.get('rest/messages?' + params)
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
      params.append('myId', payload.myId)
      params.append('otherPersonId', payload.otherPersonId)
      params.append('time', payload.time)
      params.append('message', payload.message)
      axios.post('rest/message?' + params)
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
    messagePerson (state) {
      return state.messagePerson
    },
    messages (state) {
      return state.messages
    }
  }
}
