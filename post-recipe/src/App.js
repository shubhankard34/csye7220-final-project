import React from 'react';
import './App.css';
import { Route, Redirect } from 'react-router';

import { HeaderComponent } from './components/common/header'
import RecipesPage from './pages/recipes';
import RecipeDetailsPage from './pages/recipeDetails';
import CreateRecipePage from './pages/createRecipe';

function App() {
  return (
    <div className="App">
      <HeaderComponent />
      <div className="container">
        <Redirect from='/' to='/create' />
        <Route exact path='/create' component={CreateRecipePage}></Route>
      </div>
    </div>
  );
}

export default App;
