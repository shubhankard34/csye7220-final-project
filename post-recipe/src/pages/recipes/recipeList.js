import React from 'react';
import { Link } from 'react-router-dom';

class RecipeList extends React.Component {

    render() {
        return (
            <div className="list-group">
                {this.renderList()}
            </div>
        );
    }

    renderList() {
        return this.props.recipes.map(
            (item) => {
                let url = "/recipe/" + item.id;
                return (
                    <Link key={item.id} to={url}>
                        <span className="list-group-item list-group-item-action">
                            {item.title}
                        </span>
                    </Link>
                );
            }
        );
    }
}

export default RecipeList;