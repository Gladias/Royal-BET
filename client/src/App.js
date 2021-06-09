/* eslint-disable react/jsx-filename-extension */
import './App.css';
import React, { useState } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Cookies from 'js-cookie';
import Header from './header/Header';
import MainPage from './main/Main';
import Login from './login/Login';
import Register from './register/Register';
import ResetPassword from './ResetPassword/ResetPassword';
import ProfilePage from './profile/Profile';

function App() {
  const [token, setToken] = useState(Cookies.get('token'));

  return (
    <Router>
      <Header token={token} setToken={setToken} />
      <Switch>
        <Route exact path="/" component={MainPage} />
        <Route path="/profile" component={ProfilePage} />
        <Route path="/login">
          <Login setToken={setToken} />
        </Route>
        <Route path="/register" component={Register} />
        <Route path="/reset-password" component={ResetPassword} />
      </Switch>
    </Router>
  );
}

export default App;
