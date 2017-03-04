/**
 * 
 */
require(`../node_modules/quasar-framework/dist/quasar.mat.css`)

import _ from 'lodash';
import Vue from 'vue';
import Quasar from 'quasar-framework';
import VueRouter from 'vue-router';
import QuasarCSSExample from './App.vue'


//Install Router
Vue.use(VueRouter)
Vue.use(Quasar)

function component () {
  var element = document.createElement('div');

  /* lodash is required for the next line to work */
  element.innerHTML = _.join(['Hello','webpack'], ' ');

  return element;
}

document.body.appendChild(component());

//Vue Stuff
new Vue({
	  el: '#quasar-css-example',
	  render: h => h(QuasarCSSExample)
	})

/*Quasar.start(() => {
	  new Vue({
	    el: '#quasar-css-example',
	    router,
	    render: h => h(QuasarCSSExample)
	  })
	})
*/