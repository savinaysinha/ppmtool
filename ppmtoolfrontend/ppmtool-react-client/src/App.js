import React from "react";
import "./App.css";
import Dashboard from "./component/Dashboard";
import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Navbar from "./component/layouts/Navbar";
import AddProjectForm from "./component/projectItems/AddProjectForm";
import {Provider} from 'react-redux';
import store from './store'
import UpdateProject from "./component/projectItems/UpdateProject";
function App() {
  return (
    
    <Provider store={store}>
      <Router>
        <Navbar />
        <Route path="/dashboard" component={Dashboard}/>
        <Route exact path="/addProject" component={AddProjectForm}/>
        <Route exact path="/updateProject/:id" component={UpdateProject}/>
      </Router>
      </Provider>
    
  );
}

export default App;
