//Actions commit mutations
export const increment = ({ commit }) => commit('increment')
export const decrement = ({ commit }) => commit('decrement')
export const updateMIName = ({ commit }, name) => commit('updateMIName', name)
export const updateMIAge = ({ commit }, age) => commit('updateMIAge', age)