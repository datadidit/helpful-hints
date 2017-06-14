<template>
<div>
<img src="./assets/logo.png">
<h1>Material Examples</h1>
<ul>
<li><router-link to="/gettingstarted1">Getting Started 1</router-link></li>
<li><router-link to="/gettingstarted2">Getting Started 2</router-link></li>
<li><router-link to="/layoutexample1">Layout Example 1</router-link></li>
<li><router-link to="/listexample">Layout Example 1</router-link></li>
<li><router-link to="/table">Table Example</router-link></li>
<li><router-link to="/scrollable">Scroll Example</router-link></li>
</ul>
<h1>ChartJS Examples</h1>
<ul>
<li><router-link to="/barchart">ChartJS: Bar Chart Example</router-link></li>
<li><router-link to="/radarchart">ChartJS: Radar Chart Example</router-link></li>
</ul>
<h1>From Template</h1>
<router-link to="hellovue">Hello Vue</router-link>
</div>
</template>

<script type="text/javascript">
export default {
	name: 'hellomaterial'
}
</script>

<style>
</style>