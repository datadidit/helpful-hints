import Vue from 'vue'
import Vuex from 'vuex'
import * as getters from './getters'
import * as actions from './actions'
import * as mutations from './mutations'

Vue.use(Vuex)

//Contains the initial state
const state = {
	count: 0,
  moreInfoName: '',
  moreInfoAge: '',
  items: [
    {'name':'Marcus', 'age': 29},
    {'name':'Joy', 'age':26},
    {'name':'Malcolm', 'age':25},
    {'name':'Hope', 'age':18}
  ]  
}

const store = new Vuex.Store({
	state,
	getters,
	actions,
	mutations
})

if (module.hot) {
  module.hot.accept([
    './getters',
    './actions',
    './mutations'
  ], () => {
    store.hotUpdate({
      getters: require('./getters'),
      actions: require('./actions'),
      mutations: require('./mutations')
    })
  })
}

export default store