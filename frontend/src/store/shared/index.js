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
