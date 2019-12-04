import React from "react";
import "./App.css";
import Dashboard from "./component/Dashboard";
import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Navbar from "./component/layouts/Navbar";
import AddProjectForm from "./component/projectItems/AddProjectForm";
function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Route path="/dashboard" component={Dashboard}/>
        <Route exact path="/addProject" component={AddProjectForm}/>
      </Router>
    </div>
  );
}

export default App;
