import React, { Component } from "react";
import ProjectItem from "./projectItems/ProjectItem";
import CreateProjectButton from "./layouts/CreateProjectButton";
import {getAllProject} from '../actions/ProjectActions'
import { connect } from "react-redux";



class Dashboard extends Component {
  componentDidMount(){
    this.props.getAllProject();
  }
  render() {
    return (
      <div>
        
        <div className="projects">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <h1 className="display-4 text-center">Projects</h1>
                <br />
                <CreateProjectButton />
                <br />
                <hr />

                {
                  //<!-- Project Item Component -->
                }
                <ProjectItem />
                {
                  //<!-- End of Project Item Component -->
                }
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
const mapStateToProps=(state)=>({
  projects:state.projects
})
export default connect(mapStateToProps,{getAllProject}) (Dashboard);
