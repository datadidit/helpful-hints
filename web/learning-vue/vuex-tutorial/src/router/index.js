import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Counter from '@/components/Counter'
import VuexTutorial from '@/VuexTutorial'
import TableApp from '@/components/TableApp'
import MoreInfo from '@/components/MoreInfo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'FrontPage',
      component: VuexTutorial
    },
    {
      path: '/counter',
      name: 'Counter',
      component: Counter
    },
    {
      path: '/helloVue',
      name: 'VueInfo',
      component: Hello
    },
    {
      path: '/tableExample',
      name: 'TableApp',
      component: TableApp
    },
    {
      path: '/tableExample/moreInfo/:name',
      name: 'Table_MoreInfo',
      component: MoreInfo
    }
  ]
})
