import axios from 'axios'

export default {
  state: {
    user: null
  },
  mutations: {
    setUser (state, payload) {
      state.user = payload
    }
  },
  actions: {
    userSignUp ({ commit }, payload) {
      commit('setLoading', true)
      axios.post('/rest/signUp', {
        login: payload.login,
        password: payload.password,
        name: payload.name,
        surname: payload.surname
      })
        .then(response => {
          commit('setLoading', false)
          commit('setMessage', 'Successful Signed Up')
          this.$router.push('/signIn')
        })
        .catch(error => {
          commit('setLoading', false)
          commit('setError', {type: 'error', message: error.message})
          console.log(error)
        })
    },
    userSignIn ({ commit }, payload) {
      commit('setLoading', true)
      axios.post('rest/signIn', {
        login: payload.login,
        password: payload.password
      })
        .then(response => {
          commit('setLoading', false)
          const newUser = {
            id: 1,
            login: response.data.login
          }
          commit('setUser', newUser)
        })
        .catch(error => {
          commit('setLoading', false)
          const newUser = {
            id: 1,
            login: 'dsd',
            access_token: 'fsdfdsf'
          }
          commit('setUser', newUser)
          commit('setError', {type: 'error', message: error.message})
          console.log(error)
        })
    },
    userLogout ({ commit }, payload) {
      axios.post('rest/logout', {
        access_token: payload
      })
        .then(response => {
          commit('setUser', null)
          this.$router.push('/')
          console.log(response.data)
        })
        .catch(error => {
          commit('setUser', null)
          this.$router.push('/signIn')
          console.log(error)
        })
    }
  },
  getters: {
    user (state) {
      return state.user
    }
  }
}
