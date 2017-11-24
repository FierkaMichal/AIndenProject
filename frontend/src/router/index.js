import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import SignIn from '@/components/User/SignIn'
import SignUp from '@/components/User/SignUp'
import Profile from '@/components/User/Profile'
import Communicator from '@/components/Communicator/Communicator'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/signIn',
      name: 'Sign In',
      component: SignIn
    },
    {
      path: '/signUp',
      name: 'Sign Up',
      component: SignUp
    },
    {
      path: '/profile',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/communicator',
      name: 'Communicator',
      component: Communicator
    }
  ],
  mode: 'history'
})
