import axios from 'axios';

import { SET_CURRENT_RECIPE, SET_CURRENT_RECIPE_INDEX } from './../../../store/constants';

export function setCurrentRecipeSuccess(payload) {
    return { type: SET_CURRENT_RECIPE, payload }
};

export function getRecipe(id) {
    return (dispatch) => {
        axios.get("/v1/recipe/" + id)
            .then(
                (recipes) => {
                    dispatch(setCurrentRecipeSuccess(recipes.data));
                }
            );

    }
}

export function setCurrentRecipeIndex(id) {
    return { type: SET_CURRENT_RECIPE_INDEX, payload: id }
}