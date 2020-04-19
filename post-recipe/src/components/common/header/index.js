import React from 'react';
import { Link } from 'react-router-dom';

export class HeaderComponent extends React.Component {
    render() {
        return (
            <nav className="navbar navbar-inverse">
                <Link to="/recipe">
                    <span className="navbar-brand mb-0 h1">Recipes</span>
                </Link>
            </nav>
        );
    }
}