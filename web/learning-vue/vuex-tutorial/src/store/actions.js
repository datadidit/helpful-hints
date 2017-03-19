//Actions commit mutations
export const increment = ({ commit }) => commit('increment')
export const decrement = ({ commit }) => commit('decrement')
export const updateMoreInfo = ({ commit }, name) => commit('updateMoreInfo', name)
export const addItem = ({ commit }, item) => commit('addItem', item)
export const deleteItem = ({ commit }, index) => commit('deleteItem', index)