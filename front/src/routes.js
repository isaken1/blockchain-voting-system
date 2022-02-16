import React, { useContext } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';

import { Context } from './context/ContextProvider';
import Login from './pages/Login';

function PrivateRoute({ ...rest }) {
  const { authenticated } = useContext(Context);
  let navigate = useNavigate();

  if (!authenticated) {
    navigate("/login")
  }

  return <Route {...rest} />;
}

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
    </Routes>
  );
}