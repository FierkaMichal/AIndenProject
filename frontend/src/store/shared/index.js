import axios from 'axios'

export default {
  state: {
    position: null,
    message: null,
    loading: false,
    error: null
  },
  mutations: {
    setPosition (state, payload) {
      state.position = payload
    },
    setMessage (state, payload) {
      state.message = payload
    },
    clearMessage (state) {
      state.message = null
    },
    setLoading (state, payload) {
      state.loading = payload
    },
    setError (state, payload) {
      state.error = payload
    },
    clearError (state) {
      state.error = null
    }
  },
  actions: {
    findPosition ({ commit }) {
      axios.post('https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyCTymYxKGB4DeLuPJWDp8BpP8-iO2dhHsk')
      .then(response => {
        commit('setPosition', response.data.location)
      })
        .catch(error => {
          console.log(error)
          commit('setPosition', {lat: 52.237049, lng: 21.017532})
        })
    },
    clearError ({ commit }) {
      commit('clearError')
    },
    clearMessage ({ commit }) {
      commit('clearMessage')
    }
  },
  getters: {
    loading (state) {
      return state.loading
    },
    error (state) {
      return state.error
    },
    position (state) {
      return state.position
    }
  }
}
