import { Link } from "react-router-dom";
//import {connect} from 'react-redux'

import React, { Component } from "react";
import { connect } from "react-redux";
import { deleteProjectTask } from "../../../actions/BacklogActions";


class ProjectTask extends Component {
  delete = e => {
    this.props.deleteProjectTask(this.props.projectTask.projectIdentifier,this.props.projectTask.projectSequence);
  };
  render() {
    return (
      <div className="card mb-1 bg-light">
        <div className="card-header text-primary">
          ID: {this.props.projectTask.projectSequence} -- Priority:{" "}
          {this.props.projectTask.priority}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{this.props.projectTask.summary}</h5>
          <p className="card-text text-truncate ">
            {this.props.projectTask.acceptanceCriteria}
          </p>
          <Link to={`/dashboard/backlog/${this.props.projectTask.projectIdentifier}/${this.props.projectTask.projectSequence}/updateTask`} className="btn btn-primary">
            View / Update
          </Link>

          <button className="btn btn-danger ml-4" onClick={this.delete}>
            Delete
          </button>
        </div>
      </div>
    );
  }
}


export default connect(null,{deleteProjectTask}) (ProjectTask)