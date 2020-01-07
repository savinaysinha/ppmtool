import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import ProjectTask from "./projecttask/projectTask";
import { getProjectTasks } from "../../actions/BacklogActions";

class ProjectBoard extends Component {
  componentDidMount() {
    this.props.getProjectTasks(this.props.match.params.id);
  }
  render() {
    const { projectTasks } = this.props;

    return (
      <div className="container">
        <Link
          to={`/dashboard/backlog/${this.props.match.params.id}/addProjectTask`}
          className="btn btn-primary mb-3"
        >
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        <div className="container">
          <div className="row">
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-secondary text-white">
                  <h3>TO DO</h3>
                </div>
              </div>

              {projectTasks
                .filter(projectTask => projectTask.status === "TO_DO")
                .map(projectTask => (
                  <ProjectTask key={projectTask.id} projectTask={projectTask} />
                ))}
            </div>
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-primary text-white">
                  <h3>In Progress</h3>
                </div>
              </div>
              {projectTasks
                .filter(projectTask => projectTask.status === "IN_PROGRESS")
                .map(projectTask => (
                  <ProjectTask key={projectTask.id} projectTask={projectTask} />
                ))}
            </div>
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-success text-white">
                  <h3>Done</h3>
                </div>
              </div>
              {projectTasks
                .filter(projectTask => projectTask.status === "DONE")
                .map(projectTask => (
                  <ProjectTask key={projectTask.id} projectTask={projectTask} />
                ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    projectTasks: state.projectTasks.projectTasks
  };
};

export default connect(mapStateToProps, { getProjectTasks })(ProjectBoard);
