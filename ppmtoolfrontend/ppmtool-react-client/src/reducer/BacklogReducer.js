import {
  GET_BACKLOG,
  GET_PROJECT_TASKS,
  DELETE_PROJECT_TASK
} from "../actions/type";

const initialState = {
  projectTask: {},
  projectTasks: []
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_BACKLOG:
      return {
        ...state,
        projectTask: action.payload
      };
    case GET_PROJECT_TASKS:
      return {
        ...state,
        projectTasks: action.payload
      };
    case DELETE_PROJECT_TASK:
      return {
        ...state,
        projectTasks: state.projectTasks.filter(
          projectTask => projectTask.projectSequence !== action.payload
        )
      };

    default:
      return state;
  }
}
