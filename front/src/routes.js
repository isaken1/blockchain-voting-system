import React, { useContext } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';

import { Context } from './context/ContextProvider';
import Candidates from './pages/Candidates';
import Elections from './pages/Elections';
import Login from './pages/Login';

function RequireAuth({ children }) {
  const { isAuthenticated } = useContext(Context);
  let navigate = useNavigate();

  if (!isAuthenticated()) {
    navigate("");
  }

  return children;
}

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/elections" element={
        <RequireAuth>
          <Elections />
        </RequireAuth>
        } 
      />
      <Route path="/candidates" element={
        <RequireAuth>
          <Candidates />
        </RequireAuth>
      }
      />
    </Routes>
  );
}