import axios from "axios";
import { GET_BACKLOG, GET_ERRORS, GET_PROJECT_TASKS } from "./type";

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
  console.log(res.data);
  
  dispatch({
    type: GET_PROJECT_TASKS,
    payload: res.data
  });
};
