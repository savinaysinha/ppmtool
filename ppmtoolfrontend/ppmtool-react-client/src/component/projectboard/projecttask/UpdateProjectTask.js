import React, { Component } from "react";
import { connect } from "react-redux";
import { getProjectTask,createProjectTask } from "../../../actions/BacklogActions";
import PropTypes from "prop-types";
import classnames from "classnames";
import { Link } from "react-router-dom";

class UpdateProjectTask extends Component {
  state = {
    id:"",
    summary: "",
    acceptanceCriteria: "",
    dueDate: "",
    priority: "",
    status: ""
  };

  onChange = e => {
  
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  onSubmit = e => {
    e.preventDefault();
    const projectTask = {
      id:this.state.id,
      summary: this.state.summary,
      acceptanceCriteria: this.state.acceptanceCriteria,
      dueDate: this.state.dueDate,
      priority: this.state.priority,
      status: this.state.status,
      projectIdentifier: this.props.match.params.id,
      errors: ""
    };
    this.props.createProjectTask(
      projectTask,
      this.props.history,
      this.props.match.params.id
    );
  };
  
  componentWillReceiveProps(nextProps) {
    const {
      id,
      summary,
      projectSequence,
      projectIdentifier,
      acceptanceCriteria,
      status,
      priority,
      dueDate
    } = nextProps.projectTask;
    this.setState({
      id,
      summary,
      projectSequence,
      projectIdentifier,
      acceptanceCriteria,
      status,
      priority,
      dueDate
    });

    if (nextProps.errors) {
      this.setState({
        errors: nextProps.errors
      });
    }
  }

  componentDidMount() {
    this.props.getProjectTask(
      this.props.match.params.id,
      this.props.match.params.ptSequence,
      this.props.history
    );
  }
  render() {
    const { errors } = this.props;
    return (
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link
                to={`/dashboard/backlog/${this.props.match.params.id}`}
                className="btn btn-light"
              >
                Back to Project Board
              </Link>
              <h4 className="display-4 text-center">Add Project Task</h4>
              <p className="lead text-center">Project Name + Project Code</p>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.summary
                    })}
                    name="summary"
                    placeholder="Project Task summary"
                    value={this.state.summary? this.state.summary :""}
                    onChange={this.onChange}
                  />
                  {errors.summary && (
                    <div className="invalid-feedback">{errors.summary}</div>
                  )}
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Acceptance Criteria"
                    name="acceptanceCriteria"
                    value={this.state.acceptanceCriteria?this.state.acceptanceCriteria:""}
                    onChange={this.onChange}
                  ></textarea>
                </div>
                <h6>Due Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="dueDate"
                    value={this.state.dueDate?this.state.dueDate:""}
                    onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="priority"
                    value={this.state.priority?this.state.priority:""}
                    onChange={this.onChange}
                  >
                    <option value={0}>Select Priority</option>
                    <option value={1}>High</option>
                    <option value={2}>Medium</option>
                    <option value={3}>Low</option>
                  </select>
                </div>

                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="status"
                    value={this.state.status?this.state.status:""}
                    onChange={this.onChange}
                  >
                    <option value="">Select Status</option>
                    <option value="TO_DO">TO DO</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="DONE">DONE</option>
                  </select>
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

UpdateProjectTask.propType = {
  getProjectTask: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

function mapStateToProps(state) {
  return {
    errors: state.errors,
    projectTask: state.projectTask.projectTask
  };
}

export default connect(mapStateToProps, { getProjectTask,createProjectTask})(UpdateProjectTask);
