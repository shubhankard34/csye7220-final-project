import { SET_RECIPES, SET_CURRENT_RECIPE, SET_CURRENT_RECIPE_INDEX } from './../constants'

const initialState = {
    recipes: [],
    currentRecipe: undefined,
    currentRecipeIndex: 0
};
function rootReducer(state = initialState, action) {
    switch (action.type) {
        case SET_RECIPES:
            return Object.assign({}, state, {
                recipes: action.payload,
            });
        case SET_CURRENT_RECIPE:
            return Object.assign({}, state, {
                recipes: state.recipes,
                currentRecipe: action.payload
            });
        case SET_CURRENT_RECIPE_INDEX:
            return Object.assign({}, state, {
                recipes: state.recipes,
                currentRecipe: state.currentRecipe,
                currentRecipeIndex: action.payload
            });
        default:
            return state;
    }

};
export default rootReducer;