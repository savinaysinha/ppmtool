import React, { Component } from "react";
import { connect } from "react-redux";
import {createProject} from '../../actions/ProjectActions';
import PropTypes from 'prop-types';

class AddProjectForm extends Component {
  
    state = {
      projectName: "",
      projectIdentifier: "",
      description: "",
      startDate: "",
      endDate: "",
      errors:{}
    };
   
 
  onChange=(e)=>{
      this.setState({
          [e.target.name]:e.target.value
      })
  }
  onSubmit=(e)=>{
      e.preventDefault();
      const project={
        "projectName": this.state.projectName,
        "projectIdentifier": this.state.projectIdentifier,
        "description": this.state.description,
        "startDate": this.state.startDate,
        "endDate": this.state.endDate
      }
     this.props.createProject(project,this.props.history)
  }

  componentWillReceiveProps(nextProps){
    if(nextProps.errors){
      this.setState({
          errors:nextProps.errors
      })
    }
  }
  render() {
    const {errors}=this.state
    return (
      <React.Fragment>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create Project form</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Project Name"
                      name="projectName"
                      value={this.state.projectName}
                      onChange={this.onChange}
                    />
                    <p>{errors.projectName}</p>
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="Unique Project ID"
                      name="projectIdentifier"
                      value={this.state.projectIdentifier}
                      onChange={this.onChange}
                    
                      />
                      <p>{errors.projectIdentifier}</p>
                  </div>
                  {
                    //} <!-- disabled for Edit Only!! remove "disabled" for the Create operation -->
                  }
                  <div className="form-group">
                    <textarea
                      className="form-control form-control-lg"
                      placeholder="Project Description"
                      name="description"
                      value={this.state.description}
                      onChange={this.onChange}
                    ></textarea>
                    <p>{errors.description}</p>
                  </div>
                  <h6>Start Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="startDate"
                      value={this.state.startDate}
                      onChange={this.onChange}
                    />
                  </div>
                  <h6>Estimated End Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="endDate"
                      value={this.state.endDate}
                      onChange={this.onChange}
                    />
                  </div>

                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}
AddProjectForm.protoType={
    createProject:PropTypes.func.isRequired,
    errors:PropTypes.object.isRequired
}

function mapStateToProps(state) {
  return({
    errors:state.errors
  })
}
export default connect(mapStateToProps,{createProject})(AddProjectForm);