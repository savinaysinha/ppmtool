import React, { Component } from "react";
import { connect } from "react-redux";
import { getProjectById,createProject } from "../../actions/ProjectActions";
import PropTypes from "prop-types";
import classnames from "classnames";

class UpdateProject extends Component {
  state = {
    id: "",
    projectName: "",
    projectIdentifier: "",
    description: "",
    startDate: "",
    endDate: ""
  };
  componentDidMount() {
    this.props.getProjectById(this.props.match.params.id, this.props.history);
  }
  
  componentWillReceiveProps(nextProps) {
    
    const {
      id,
      projectName,
      projectIdentifier,
      description,
      startDate,
      endDate
    } = nextProps.project;

    this.setState({
      id,
      projectName,
      projectIdentifier,
      description,
      startDate,
      endDate
    });
  }

  onChange = e => {
      
    this.setState({
      [e.target.name]: e.target.value
    });
  };
  onSubmit = event => {
    
    event.preventDefault();
    const project = {
      id: this.state.id,
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      startDate: this.state.startDate,
      endDate: this.state.endDate
    };
         this.props.createProject(project, this.props.history);
  };
  render() {
      
    return (
      <div className="project">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">Update Project form</h5>
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
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Unique Project ID"
                    name="projectIdentifier"
                    value={this.state.projectIdentifier}
                    onChange={this.onChange}
                    disabled
                  />
                </div>

                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Project Description"
                    name="description"
                    value={this.state.description}
                    onChange={this.onChange}
                  ></textarea>
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
    );
  }
}
UpdateProject.propTypes = {
  getProjectById: PropTypes.func.isRequired,
  createProject:PropTypes.func.isRequired,
  project:PropTypes.object.isRequired

};

const mapStateToProps = state => ({
  project: state.projects.project
});

export default connect(mapStateToProps, { getProjectById, createProject })(UpdateProject);
