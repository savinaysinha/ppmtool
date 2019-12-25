import axios from 'axios';
import {GET_ERRORS, GET_PROJECTS, GET_PROJECT} from './type'


export const createProject =(project, history)=> async dispatch=>{
    try {
        await axios.post("http://localhost:8080/api/project",project);  
        history.push("/dashboard");
    } catch (error) {
        dispatch({
            type:GET_ERRORS,
            payload:error.response.data
        })
    }
} 
export const getAllProject=()=>async dispatch=>{
    const res=await axios.get("http://localhost:8080/api/project/all");
   
    dispatch({
        type:GET_PROJECTS,
        payload:res.data
    })
}

export const getProjectById=(id,history)=>async dispatch=>{

    const res = await axios.get(`http://localhost:8080/api/project/${id}`);
    console.log(res.data);
    
    dispatch({
        type:GET_PROJECT,
        payload:res.data
    })
}