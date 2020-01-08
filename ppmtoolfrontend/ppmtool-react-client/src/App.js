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
import ProjectBoard from './component/projectboard/ProjectBoard'
import AddProjectTask from './component/projectboard/projecttask/AddProjectTask'
import UpdateProjectTask from './component/projectboard/projecttask/UpdateProjectTask'
function App() {
  return (
    
    <Provider store={store}>
      <Router>
        <Navbar />
        <Route exact path="/dashboard" component={Dashboard}/>
        <Route exact path="/dashboard/addProject" component={AddProjectForm}/>
        <Route exact path="/dashboard/updateProject/:id" component={UpdateProject}/>
        <Route exact path="/dashboard/backlog/:id" component={ProjectBoard}/>
        <Route exact path="/dashboard/backlog/:id/addProjectTask" component={AddProjectTask}/>
        <Route exact path="/dashboard/backlog/:id/:ptSequence/updateTask" component={UpdateProjectTask}/>
      </Router>
      </Provider>
    
  );
}

export default App;
