import React, { createContext } from 'react';

import useAuth from './hooks/useAuth';

const Context = createContext();

function AppContext({ children }) {
  const { 
    authenticated, loading, handleLogin, voterName, hashedCpf
  } = useAuth();


  return (
    <Context.Provider value={{ loading, authenticated, handleLogin, voterName, hashedCpf }}>
      {children}
    </Context.Provider>
  )
}

export { Context, AppContext };
