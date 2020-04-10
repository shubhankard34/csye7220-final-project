import React from 'react';
import './App.css';
import { Route, Redirect } from 'react-router';

import { HeaderComponent } from './components/common/header'
import RecipesPage from './pages/recipes';
import RecipeDetailsPage from './pages/recipeDetails';

function App() {
  return (
    <div className="App">
      <HeaderComponent />
      <div className="container">
        <Redirect from='/' to='/recipe' />
        <Route exact path='/recipe' component={RecipesPage}></Route>
        <Route path='/recipe/:id' component={RecipeDetailsPage}></Route>
      </div>
    </div>
  );
}

export default App;
