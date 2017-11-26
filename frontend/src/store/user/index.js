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
      var params = new URLSearchParams()
      params.append('grant_type', 'password')
      params.append('username', payload.login)
      params.append('password', payload.password)
      axios({
        method: 'post',
        url: 'oauth/token',
        auth: {username: 'my-trusted-client', password: 'secret'},
        headers: {'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'},
        data: params
      })
        .then(response => {
          var params = new URLSearchParams()
          params.append('access_token', response.data.access_token)
          axios.get('/me?' + params)
            .then(response => {
              commit('setLoading', false)
              commit('setUser', response.data)
            })
            .catch(error => {
              commit('setLoading', false)
              commit('setError', {type: 'error', message: error.message})
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
    },
    editUser ({ commit }, payload) {
      commit('setLoading', true)
      axios.put('rest/user/edit', payload)
        .then(response => {
          commit('setLoading', false)
          commit('setMessage', {type: 'success', message: 'User data edit successful'})
          console.log(response)
        })
        .catch(error => {
          commit('setLoading', false)
          commit('setError', {type: 'error', message: 'Error while edit user data'})
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
