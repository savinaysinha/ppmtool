import { createStore, compose, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducer";

const intialState = {};
const middelware = [thunk];

let store;
if (window.navigator.userAgent.includes("Mozilla/5.0")) {
  store = createStore(
    rootReducer,
    intialState,
    compose(
      applyMiddleware(...middelware),
      window.__REDUX_DEVTOOLS_EXTENSION__ &&
        window.__REDUX_DEVTOOLS_EXTENSION__()
    )
  );
}else{
    store = createStore(
        rootReducer,
        intialState,
        compose(
          applyMiddleware(...middelware)
        )
    );
}

export default store;
