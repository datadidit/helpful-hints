<temple>
	<div>
		<h1>Name: {{ name }}</h1>
		<h1>Age: {{ age }}</h1>
	</div>
</temple>

<script>
export default {
	name: 'MoreInfo',
	props: ['name', 'age']
}
</script>