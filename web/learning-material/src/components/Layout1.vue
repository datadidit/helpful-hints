<template>
<div>
<!-- Simple Example Grid Layout -->
<md-layout>
   <!--Take up 33% of screen -->
  <md-layout md-flex="20" md-align="center" class='left-pane'>
  	<div>
  		Hello<br/>
  		md-flex=20<br/>
  		md-align=center
  	</div>
  </md-layout>
  <md-layout md-align="center" class='right-pane'>
  	World
  </md-layout>
</md-layout>
<!-- Want A Gutter --> 
<md-layout :md-gutter="8">
	<md-layout md-align="center" class="gutter-left-pane">
		<span>8</span>
	</md-layout>

	<md-layout md-align="center" class="gutter-right-pane">
		Bar
	</md-layout>
</md-layout>
</div>
</template>

<script type="text/javascript">
export default{
	name: 'layout1'
}
</script>

<style>
.left-pane {
	background-color: red;
	font-size: 2em;
	min-height: 100px
}
.right-pane {
	background-color: blue;
	font-size: 2em;	
}
.gutter-left-pane {
	background-color: yellow;
}
.gutter-right-pane{
	background-color: green;
}
</style>