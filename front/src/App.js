import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';

import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';

import { AppContext } from './context/ContextProvider';
import history from './history';
import AppRoutes from './routes';

function App() {

  return (
    <AppContext>
      <Router history={history}>
        <div className="App">
          <div className="auth-wrapper">
            <div className="auth-inner">
              <AppRoutes />
            </div>
          </div>
        </div>
      </Router>
    </AppContext>
  );
}

export default App;
