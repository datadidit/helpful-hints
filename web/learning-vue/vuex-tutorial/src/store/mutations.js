//Only way to actually change state in a Vuex store is by commiting a mutation.
export const increment = state => {
	state.count++
}

export const decrement = state => {
	state.count--
}

export const updateMoreInfo = (state, update) => {
	state.moreInfoName = update.name
	state.moreInfoAge = update.age
}

export const addItem = (state, item) => {
	state.items.push(item)
}

export const deleteItem = (state, index) => {
	state.items.splice(index, 1)
}
