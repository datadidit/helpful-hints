<template>
<div id="countapp">
   <h1>Vuex Counter</h1>
  <p>{{ count }}</p>
  <p>
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
  </p>
</div>
</template>

<script>
/*
* MapGetters is a helper function to call the get method on count.
* MapActions is helper function to dispatch(this.$store.dispatch('')) actions to a store. 
*/
import { mapGetters, mapActions } from 'vuex'

export default {
name: 'countapp',
computed: mapGetters([
	'count'
	]), 
methods: mapActions([
	'increment',
	'decrement'
	])
}
</script>