/* eslint-disable react/jsx-filename-extension */
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Header from './header/Header';
import MainPage from './main/Main';
import Login from './login/Login';
import Register from './register/Register';
import ResetPassword from './ResetPassword/ResetPassword';

function App() {
  return (
    <Router>
      <Header />
      <Switch>
        <Route exact path="/" component={MainPage} />
        <Route path="/login" component={Login} />
        <Route path="/register" component={Register} />
        <Route path="/reset-password" component={ResetPassword} />
      </Switch>
    </Router>
  );
}

export default App;
