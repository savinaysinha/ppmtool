import React from 'react'
import {Link} from "react-router-dom"
//import {connect} from 'react-redux'

export default function ProjectTask(props) {
    return (
        <div className="card mb-1 bg-light">
                <div className="card-header text-primary">
                  ID: {props.projectTask.projectSequence} -- Priority: {props.projectTask.priority}
                </div>
                <div className="card-body bg-light">
                  <h5 className="card-title">{props.projectTask.summary}</h5>
                  <p className="card-text text-truncate ">
                    {props.projectTask.acceptanceCriteria}
                  </p>
                  <Link to="" className="btn btn-primary">
                    View / Update
                  </Link>

                  <button className="btn btn-danger ml-4">Delete</button>
                </div>
              </div>
    )
}
