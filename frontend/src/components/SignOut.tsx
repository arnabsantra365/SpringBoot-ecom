import { Button } from '@mui/material'
import React from 'react'
import { useAuth } from '../context/AuthContext'

const SignOut = ()=> {

    const {logout} = useAuth();

    const handleClick = () =>{
        logout();
    }

  return (
    <div>
        <h1>...</h1>
        <Button variant="contained" onClick={handleClick}>SignOut</Button>
    </div>
    
  )
}

export default SignOut