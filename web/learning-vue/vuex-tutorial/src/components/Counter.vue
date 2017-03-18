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