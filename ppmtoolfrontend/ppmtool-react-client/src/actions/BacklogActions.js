import axios from "axios";
import {
  GET_BACKLOG,
  GET_ERRORS,
  GET_PROJECT_TASKS,
  DELETE_PROJECT_TASK
} from "./type";

export const createProjectTask = (
  projectTask,
  history,
  projectIdentifier
) => async dispatch => {
  try {
    await axios.post(`/api/backlog/${projectIdentifier}/`, projectTask);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });

    history.push(`/dashboard/backlog/${projectIdentifier}`);
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getProjectTasks = projectIdentifier => async dispatch => {
  const res = await axios.get(`/api/backlog/${projectIdentifier}`);
  dispatch({
    type: GET_PROJECT_TASKS,
    payload: res.data
  });
};

export const deleteProjectTask = (
  projectIdentifier,
  projectSequence
) => async dispatch => {
  if (window.confirm("Are you sure to want to delete it")) {
    await axios.delete(`/api/backlog/${projectIdentifier}/${projectSequence}`);
    dispatch({
      type: DELETE_PROJECT_TASK,
      payload: projectSequence
    });
  }
};

export const getProjectTask = (
  projectIdentifier,
  projectSequence
) => async dispatch => {
  const res = await axios.get(
    `/api/backlog/${projectIdentifier}/${projectSequence}`
  );
  dispatch({
    type: GET_BACKLOG,
    payload: res.data
  });
};
