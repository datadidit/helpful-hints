//Only way to actually change state in a Vuex store is by commiting a mutation.
export const increment = state => {
	state.count++
}

export const decrement = state => {
	state.count--
}

export const updateMIName = (state, name) => {
	console.log("Making it here")
	state.moreInfoName = name
}

export const updateMIAge = (state, payload) => {
	state.moreInfoAge = payload
}