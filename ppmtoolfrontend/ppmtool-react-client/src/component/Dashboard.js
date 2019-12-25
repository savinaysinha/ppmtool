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
    const {projects}=this.props.projects;
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
                {projects.map(project=>(
                  <ProjectItem key={project.id} project={project} />
                ))}
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
