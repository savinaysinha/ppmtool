import {GET_PROJECTS} from '../actions/type';

const intialState={
    projects:[],
    project:{}
}

export default function(state=intialState,action){
    switch(action.type){
        case GET_PROJECTS:
            return({
                ...state,
                projects:action.payload
            })
        default:
            return state;
    }

}