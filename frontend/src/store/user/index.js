import axios from 'axios'
import VueCookies from 'vue-cookies'
import Router from '@/router/'

export default {
  state: {
    adminUserList: null,
    user: null
  },
  mutations: {
    setUser (state, payload) {
      state.user = payload
    },
    changeAvatar (state, payload) {
      state.user.avatar = payload
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
    },
    deleteUser (state, payload) {
      const userToDelete = state.adminUserList.user.find(user => {
        return user.userId === payload
      })
      state.adminUserList.user.splice(state.adminUserList.user.indexOf(userToDelete), 1)
    },
    setAdminUserList (state, payload) {
      state.adminUserList = payload
    },
    editAdminUserList (state, payload) {
      const userToEdit = state.adminUserList.user.find(user => {
        return user.userId === payload.userId
      })
      userToEdit.login = payload.login
      userToEdit.name = payload.name
      userToEdit.surname = payload.surname
      userToEdit.admin = payload.admin
    }
  },
  actions: {
    getAdminUserList ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      axios.get('rest/user/getAll?' + params)
        .then(response => {
          commit('setAdminUserList', response.data)
        })
        .catch(error => {
          console.log(error)
        })
    },
    deleteUser ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      params.append('userId', payload.userId)
      axios.put('rest/user/delete?' + params)
        .then(response => {
          commit('deleteUser', payload.userId)
        })
        .catch(error => {
          console.log(error)
        })
    },
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
          VueCookies.remove('token')
          commit('setLoading', false)
          commit('setUser', {avatar: 'http://di.com.pl/pic/photo/Screen_Shot_2014_08_21_at_101244_AM_640x834_1408689031.png', name: 'dsad', surname: 'dsada', description: 'dsad', photoArray: ['http://t-eska.cdn.smcloud.net/regionalna/t/2/t/image/1c62fcdad261b54757dd160d126afdf1RAtQqTIl-lama.jpg/ru-0-r-660,660-q-80-n-1c62fcdad261b54757dd160d126afdf1RAtQqTIllama.jpg', 'http://t-eska.cdn.smcloud.net/regionalna/t/2/t/image/62ed2dc4cee358c9088d8cb3a7eb33b6LbOj61vo-dsc.JPG/ru-0-r-660,660-q-80-n-62ed2dc4cee358c9088d8cb3a7eb33b6LbOj61vodsc.JPG']})
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
          commit('setLastId', 0)
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
          commit('setLastId', 0)
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
    changeAvatar ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      axios.put('rest/images/avatar?' + params, payload)
        .then(response => {
          commit('changeAvatar', response.data)
        })
        .catch(error => {
          console.log(error)
        })
    },
    addPhoto ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      axios.post('rest/uploadFile?' + params, {file: payload}, {headers: {'Content-Type': 'application/json'}})
        .then(response => {
          commit('addUserPhoto', response.data.error)
        })
        .catch(error => {
          console.log(error)
        })
    },
    removePhoto ({ commit }, payload) {
      var params = new URLSearchParams()
      params.append('access_token', VueCookies.get('token'))
      axios.put('rest/images/remove?' + params, payload)
        .then(response => {
          commit('removeUserPhoto', payload)
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
  getters: {
    user (state) {
      return state.user
    },
    usersList (state) {
      return state.adminUserList
    }
  }
}
