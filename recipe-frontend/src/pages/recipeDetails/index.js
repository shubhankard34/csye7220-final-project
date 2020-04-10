import React from 'react';
import { connect } from 'react-redux';
import { getRecipe, setCurrentRecipeIndex } from './actions';
import { Link } from 'react-router-dom';

class RecipeDetailsPage extends React.Component {

    constructor(props) {
        super();
    }

    componentWillMount() {
        let id = this.props.match.params.id;
        this.props.fetchData(id);
        this.setCurrentId(id);
    }

    setCurrentId(currentId) {
        const { recipes } = this.props;
        for (let i = 0; i < recipes.length; i++) {
            if (currentId == recipes[i].id) {
                this.props.setCurrentRecipeIndex(i);
                break;
            }
        }

    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.match.params.id != this.props.match.params.id) {
            // Clean component and reload? 
            let newId = nextProps.match.params.id;
            this.props.fetchData(newId);
            this.setCurrentId(newId);
        }
    }

    getNextId(index) {
        let nextRecipe = this.props.recipes[index + 1];
        return nextRecipe ? nextRecipe.id : undefined;
    }

    renderButton(nextUrl) {
        if (nextUrl) {
            return (
                <Link to={nextUrl}>
                    <button type="button" className="btn btn-primary btn-lg">Next Recipe ></button>
                </Link>
            );
        } else {
            return (
                <button type="button" disabled className="btn btn-primary btn-lg">Next Recipe ></button>
            );
        }
    }

    render() {
        let { currentRecipe } = this.props;
        if (currentRecipe) {
            let nextId = this.getNextId(this.props.currentRecipeIndex);
            let nextUrl = nextId ? "/recipe/" + nextId : undefined;
            return (
                <div>
                    <div class="page-header">
                        <h1>{currentRecipe.title}</h1>
                        <div className="row">
                            <div className="col-xs-4"></div>
                            <div className="col-xs-4"></div>
                            <div className="col-xs-4"><small><i>Posted on: {currentRecipe.created_ts}</i></small></div>
                            <div className="col-xs-4"></div>
                            <div className="col-xs-4"></div>
                            <div className="col-xs-4"><small><i>Last Updated on: {currentRecipe.updated_ts}</i></small></div>
                        </div>
                    </div>
                    <div>
                        <div className="next-button">
                            {this.renderButton(nextUrl)}
                        </div>
                        <p><b>Cuisine:</b> {currentRecipe.cuisine}</p>
                        <p><b>Preparation Time:</b> {currentRecipe.prep_time_in_min} minutes</p>
                        <p><b>Cooking Time:</b> {currentRecipe.cook_time_in_min} minutes</p>
                        <p><b>No. of Servings:</b> {currentRecipe.servings}</p>

                        <div class="panel panel-info">
                            <div class="panel-heading">Ingredients</div>
                            <div class="panel-body">
                                <ul>
                                    {
                                        currentRecipe.ingredients.map(
                                            (ingredient) => {
                                                return (
                                                    <li>{ingredient}</li>
                                                );
                                            }
                                        )
                                    }
                                </ul>
                            </div>
                        </div>
                        <div class="panel panel-success">
                            <div class="panel-heading">Steps</div>
                            <div class="panel-body">
                                <ol>
                                    {
                                        currentRecipe.steps.map(
                                            (step) => {
                                                return (
                                                    <li>{step.items}</li>
                                                );
                                            }
                                        )
                                    }
                                </ol>
                            </div>
                        </div>
                        <div class="panel panel-warning">
                            <div class="panel-heading">Nutrition</div>
                            <div class="panel-body">
                                <p><b>Calories:</b> {currentRecipe.nutrition_information.calories}</p>
                                <p><b>Cholesterol:</b> {currentRecipe.nutrition_information.cholesterol_in_mg} mg</p>
                                <p><b>Sodium:</b> {currentRecipe.nutrition_information.sodium_in_mg} mg</p>
                                <p><b>Carbohydrates:</b> {currentRecipe.nutrition_information.carbohydrates_in_grams} g</p>
                                <p><b>Proteins:</b> {currentRecipe.nutrition_information.protein_in_grams} g</p>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
        return null;
    }
}

const mapStateToProps = (state) => {
    return {
        currentRecipe: state.currentRecipe,
        recipes: state.recipes,
        currentRecipeIndex: state.currentRecipeIndex
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        fetchData: (id) => dispatch(getRecipe(id)),
        setCurrentRecipeIndex: (id) => dispatch(setCurrentRecipeIndex(id))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(RecipeDetailsPage);

// export default RecipeDetailsPage;