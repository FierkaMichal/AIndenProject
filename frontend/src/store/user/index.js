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
          commit('setError', {type: 'error', message: error.error})
          console.log(error)
        })
    },
    userSignIn ({ commit }, payload) {
      commit('setLoading', true)
      axios.post('/oath/token', {
        grant_type: 'password',
        username: payload.login,
        password: payload.password
      })
        .then(response => {
          axios.get('/me', {
            access_token: response.data.access_token
          })
            .then(response => {
              commit('setLoading', false)
              // const newUser = {
              //   id: response.data.userId,
              //   name: response.data.
              //   login: response.data.login
              // }
              commit('setUser', response.data)
            })
            .catch(error => {
              console.log(error)
            })
        })
        .catch(error => {
          commit('setLoading', false)
          const newUser = {
            id: 1,
            login: 'dsd',
            access_token: 'fsdfdsf',
            name: 'Pawel',
            surname: 'Freliga'
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
