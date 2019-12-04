import React, { Component } from 'react'
import Project from './projectItems/Project'
import Navbar from './layouts/Navbar'

 class Dashboard extends Component {
    render() {
        return(
            <div>
                <Navbar />
                <h1 className="alert alert-warning">Welcome To Dashboard</h1>
                <Project />
            </div>
            )
    }
}
export default Dashboard;
