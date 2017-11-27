import Vue from 'vue'
import Vuex from 'vuex'

import user from './user'
import shared from './shared'
import lookingPerson from './lookingPerson'
import communicator from './communicator'

Vue.use(Vuex)

export const store = new Vuex.Store({
  modules: {
    user: user,
    shared: shared,
    lookingPerson: lookingPerson,
    communicator: communicator
  }
})
