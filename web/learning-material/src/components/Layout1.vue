<template>
<md-layout :md-gutter="40">
<md-layout>
	<md-layout class="left-pane">
		Hello 
	</md-layout>
</md-layout>
<md-layout>
	<md-layout class="right-pane">
		World
	</md-layout>
</md-layout>
<md-layout>
	<md-layout class="gutter-left-pane">
		Foo
	</md-layout>
</md-layout>
</md-layout>
<!--
<md-layout :md-gutter="40">
	<md-layout>
		<md-layout md-align="center" class='left-pane'>
			<div>
				Hello<br/>
				md-flex=20<br/>
				md-align=center
			</div>
		</md-layout>
	</md-layout>
	<md-layout>
		<md-layout md-align="center" class="gutter-left-pane">
			<span>8</span>
		</md-layout>
	</md-layout>

	<md-layout>
		<md-layout class="gutter-right-pane">
			<span>end</span>
		</md-layout>
	</md-layout>
</md-layout>
-->
</template>

<script type="text/javascript">
export default{
	name: 'layout1'
}
</script>

<style>
.fullLayout {
	background: black;
}
.left-pane {
	background: red;
	font-size: 2em;
	min-height: 100px
}
.right-pane {
	background: blue;
	font-size: 2em;	
}
.gutter-left-pane {
	background: yellow;
}
.gutter-right-pane{
	background: green;
}

#box { 
    display: flex;
    margin: 0 0;
}
</style>