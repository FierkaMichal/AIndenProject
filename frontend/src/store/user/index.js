import axios from 'axios'
import VueCookies from 'vue-cookies'
import Router from '@/router/'

export default {
  state: {
    user: null
  },
  mutations: {
    setUser (state, payload) {
      // payload.lastLongitude = this.getters.position.coords.longitude
      // payload.lastLatitude = this.position.coords.latitude
      state.user = payload
    },
    addUserPhoto (state, payload) {
      if (state.user.photoArray !== null && state.user.photoArray !== undefined) {
        state.user.photoArray.push(payload)
      } else {
        const photoArray = [payload]
        state.user.photoArray = photoArray
      }
    },
    removeUserPhoto (state, payload) {
      const photo = state.user.photoArray.find(photo => {
        return photo === payload.src
      })
      state.user.photoArray.splice(state.user.photoArray.indexOf(photo), 1)
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
          Router.push('/signIn')
        })
        .catch(error => {
          commit('setLoading', false)
          commit('setError', {type: 'error', message: error.error})
          console.log(error)
        })
    },
    userSignIn ({ commit, dispatch }, payload) {
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
          VueCookies.set('token', response.data.access_token, Infinity)
          dispatch('userDetails')
        })
        .catch(error => {
          // VueCookies.remove('token')
          VueCookies.set('token', 'dasdasd', Infinity)
          commit('setUser', {id: 23, photoArray: null})
          commit('setLoading', false)
          commit('setError', {type: 'error', message: error.error})
          console.log(error)
        })
    },
    userDetails ({ commit }) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      axios.get('/me?' + params)
        .then(response => {
          commit('setLoading', false)
          commit('setUser', response.data)
          Router.push('/')
        })
        .catch(error => {
          VueCookies.remove('token')
          commit('setLoading', false)
          commit('setError', {type: 'error', message: error.error})
          console.log(error)
        })
    },
    userLogout ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      axios.post('rest/logout?' + params, {
        access_token: payload
      })
        .then(response => {
          VueCookies.remove('token')
          commit('setUser', null)
          commit('setMessagePerson', null)
          commit('setMessages', null)
          commit('setPerson', null)
          commit('setUserMatches', null)
          commit('setLastId', null)
          commit('setMatcherPerson', null)
          commit('setMessage', null)
          commit('setLoading', null)
          commit('clearMessage')
          commit('clearError')
          Router.push('/')
          console.log(response.data)
        })
        .catch(error => {
          VueCookies.remove('token')
          commit('setUser', null)
          commit('setMessagePerson', null)
          commit('setMessages', null)
          commit('setPerson', null)
          commit('setUserMatches', null)
          commit('setLastId', null)
          commit('setMatcherPerson', null)
          commit('setMessage', null)
          commit('setLoading', null)
          commit('clearMessage')
          commit('clearError')
          Router.push('/signIn')
          console.log(error)
        })
    },
    editUser ({ commit }, payload) {
      commit('setLoading', true)
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      axios.put('rest/user/edit?' + params, payload)
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
    },
    addPhoto ({ commit }, payload) {
      commit('addUserPhoto', payload)
    },
    removePhoto ({ commit }, payload) {
      commit('removeUserPhoto', payload)
    }
  },
  getters: {
    user (state) {
      return state.user
    }
  }
}
