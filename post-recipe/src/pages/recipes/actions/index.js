import axios from 'axios';

import { SET_RECIPES } from './../../../store/constants';

export function setRecipesSuccess(payload) {
    return { type: SET_RECIPES, payload }
};

export function getRecipes() {

    return (dispatch) => {
        axios.get("/v1/allRecipes")
            .then(
                (recipes) => {
                    dispatch(setRecipesSuccess(recipes.data));
                }
            );

    }
}