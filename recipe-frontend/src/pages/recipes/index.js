import React from 'react';
import { connect } from 'react-redux';
import { getRecipes } from './actions';
import RecipeList from './recipeList';

class RecipesPage extends React.Component {

    componentWillMount() {
        this.props.fetchData();
    }

    componentWillReceiveProps(newProps) {
        if (newProps.recipes.length > 0) {
            this.props.history.push('/recipe/' + newProps.recipes[0].id);
        }
    }

    render() {
        return null;
    }
}

const mapStateToProps = (state) => {
    return {
        recipes: state.recipes
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        fetchData: () => dispatch(getRecipes())
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(RecipesPage);