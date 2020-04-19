import React from 'react';
import { connect } from 'react-redux';
import axios from 'axios';
// import { getRecipes } from './actions';

class CreateRecipePage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userFirstName: "",
            userLastName: "",
            cook_time_in_min: undefined,
            prep_time_in_min: undefined,
            total_time_in_min: undefined,
            title: "",
            cuisine: "",
            servings: undefined,
            ingredients: "",
            steps: "",
            calories: undefined,
            cholesterol_in_mg: undefined,
            sodium_in_mg: undefined,
            carbohydrates_in_grams: undefined,
            protein_in_grams: undefined
        }
    }

    componentWillMount() {
    }

    componentWillReceiveProps(newProps) {

    }

    render() {
        return (
            <form>
                {this.renderAuthorForm()}
                {this.renderRecipeInfoForm()}
                {this.renderIngStepsForm()}
                {this.renderNutritionInfoForm()}
                <button type="button" class="btn btn-primary btn-lg btn-block" onClick={() => this.createRecipe()}>Create</button>
            </form>
        );
    }
    createRecipe() {
        let recipe = Object.assign(this.state);
        let ingredients = recipe.ingredients.split(/\r\n|\r|\n/g);
        recipe.ingredients = [];
        for (let ing of ingredients) {
            recipe.ingredients.push(ing);
        }
        let steps = recipe.steps.split(/\r\n|\r|\n/g);
        recipe.steps = [];
        let index = 1;
        for (let step of steps) {
            recipe.steps.push({ position: index, items: step });
            index++;
        }
        let ni = {
            calories: recipe.calories,
            sodium_in_mg: recipe.sodium_in_mg,
            cholesterol_in_mg: recipe.cholesterol_in_mg,
            carbohydrates_in_grams: recipe.carbohydrates_in_grams,
            protein_in_grams: recipe.protein_in_grams
        }
        recipe.nutrition_information = Object.assign(ni);
        delete recipe.calories;
        delete recipe.sodium_in_mg;
        delete recipe.cholesterol_in_mg;
        delete recipe.carbohydrates_in_grams;
        delete recipe.protein_in_grams;
        console.log(recipe);
        axios.post("/v1/recipe", recipe)
            .then(
                (res) => {
                    alert("Recipe Added");
                    window.location.reload();
                }
            ).catch(
                (error) => {
                    alert("Some error occured!")
                }
            );

    }

    renderAuthorForm() {
        return (
            <div class="panel panel-default">
                <div class="panel-heading">
                    Author Information
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="form-group col-xs-6 ">
                            <label for="userFirstName">First Name</label>
                            <input type="text" class="form-control" value={this.state.userFirstName} onChange={(e) => this.setState({ userFirstName: e.target.value })} id="userFirstName" placeholder="John" />
                        </div>
                        <div class="form-group col-xs-6">
                            <label for="userLastName">Last Name</label>
                            <input type="text" class="form-control" value={this.state.userLastName} onChange={(e) => this.setState({ userLastName: e.target.value })} id="userLastName" placeholder="Doe" />
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    renderRecipeInfoForm() {
        return (
            <div class="panel panel-default">
                <div class="panel-heading">
                    Recipe Information
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" value={this.state.title} onChange={(e) => this.setState({ title: e.target.value })} id="title" placeholder="Indian Chicken Curry" />
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-8">
                            <label for="cuisine">Cuisine</label>
                            <input type="text" class="form-control" value={this.state.cuisine} onChange={(e) => this.setState({ cuisine: e.target.value })} id="cuisine" placeholder="Indian" />
                        </div>
                        <div class="form-group col-xs-4">
                            <label for="servings">Number of Servings</label>
                            <input type="number" class="form-control" value={this.state.servings} onChange={(e) => this.setState({ servings: e.target.value })} id="servings" placeholder="4" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="prepTime">Prep Time (mins)</label>
                            <input type="number" class="form-control" value={this.state.prep_time_in_min} onChange={(e) => this.setState({ prep_time_in_min: e.target.value })} id="prepTime" placeholder="30" />
                        </div>
                        <div class="form-group col-xs-4">
                            <label for="cookTime">Cook Time (mins)</label>
                            <input type="number" class="form-control" value={this.state.cook_time_in_min} onChange={(e) => this.setState({ cook_time_in_min: e.target.value })} id="cookTime" placeholder="30" />
                        </div>
                        <div class="form-group col-xs-4">
                            <label for="totalTime">Total Time (mins)</label>
                            <input type="number" class="form-control" value={this.state.total_time_in_min} onChange={(e) => this.setState({ total_time_in_min: e.target.value })} id="totalTime" placeholder="60" />
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    renderIngStepsForm() {
        return (
            <div class="panel panel-default">
                <div class="panel-heading">
                    Ingredients and steps
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="ingredients">Ingredients (each on new line)</label>
                        <textarea class="form-control" rows="4" id="ingredients" value={this.state.ingredients} onChange={(e) => this.setState({ ingredients: e.target.value })} placeholder="1lb Boneless Chicken Breas" />
                    </div>
                    <div class="form-group">
                        <label for="steps">Steps (each on new line)</label>
                        <textarea class="form-control" rows="4" id="steps" value={this.state.steps} onChange={(e) => this.setState({ steps: e.target.value })} placeholder="Step One" />
                    </div>
                </div>
            </div>
        );
    }

    renderNutritionInfoForm() {
        return (
            <div class="panel panel-default">
                <div class="panel-heading">
                    Nutritional Information
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="calories">Calories</label>
                            <input type="number" class="form-control" value={this.state.calories} onChange={(e) => this.setState({ calories: e.target.value })} id="calories" placeholder="600" />
                        </div>
                        <div class="form-group col-xs-4">
                            <label for="sodium">Sodium (mg)</label>
                            <input type="number" class="form-control" value={this.state.sodium_in_mg} onChange={(e) => this.setState({ sodium_in_mg: e.target.value })} id="sodium" placeholder="10" />
                        </div>
                        <div class="form-group col-xs-4">
                            <label for="cholesterol">Cholesterol (mg)</label>
                            <input type="number" class="form-control" value={this.state.cholesterol_in_mg} onChange={(e) => this.setState({ cholesterol_in_mg: e.target.value })} id="cholesterol" placeholder="8" />
                        </div>
                        <div class="form-group col-xs-6">
                            <label for="carbs">Carbohydrates (gms)</label>
                            <input type="number" class="form-control" value={this.state.carbohydrates_in_grams} onChange={(e) => this.setState({ carbohydrates_in_grams: e.target.value })} id="carbs" placeholder="60" />
                        </div>
                        <div class="form-group col-xs-6">
                            <label for="proteins">Protein (gms)</label>
                            <input type="number" class="form-control" value={this.state.protein_in_grams} onChange={(e) => this.setState({ protein_in_grams: e.target.value })} id="proteins" placeholder="100" />
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

const mapStateToProps = (state) => {
    return {
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(CreateRecipePage);