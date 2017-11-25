export default {
  state: {
    message: null,
    loading: false,
    error: null
  },
  mutations: {
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
    }
  }
}
